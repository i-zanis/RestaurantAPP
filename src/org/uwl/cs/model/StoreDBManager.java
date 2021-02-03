package org.uwl.cs.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoreDBManager {
    public static Connection connect() throws Exception {

        String url = "jdbc:postgresql://kandula.db.elephantsql.com:5432/ikzmpslm";
        String user = "ikzmpslm";
        String pwd = "73LI1sNi24J6WulfwD8LtACSgmyHTHOp";
        Class.forName("org.postgresql.Driver");
        Connection con = null;
        con = DriverManager.getConnection(url, user, pwd);
        con.setAutoCommit(false);
        return con;
    }

    public static void createDatabase() {
        Statement statement = null;
        String sqlQuery;

        try {
            Connection con = connect();
            statement = con.createStatement();

            sqlQuery = "CREATE TABLE customer " +
                    "(  id BIGSERIAL   PRIMARY KEY     NOT NULL," +
                    "   first_name     VARCHAR(30)     NOT NULL, " +
                    "   last_name      VARCHAR(30)     NOT NULL, " +
                    "   email          VARCHAR(200)    NOT NULL UNIQUE, " +
                    "   phone_number   VARCHAR(11)     NOT NULL, " +
                    "   password       TEXT            NOT NULL, " +
                    "   session_uuid   TEXT" +
                    ");" +
                    "CREATE TABLE basket (" +
                    "   id BIGSERIAL PRIMARY KEY NOT NULL, " +
                    "   customer_id BIGINT REFERENCES customer (id) ON DELETE CASCADE  NOT NULL, " +
                    "   UNIQUE(customer_id)" +
                    ");" +
                    "CREATE TABLE product (" +
                    "   id BIGSERIAL PRIMARY KEY NOT NULL, " +
                    "   title VARCHAR(150) NOT NULL UNIQUE, " +
                    "   description TEXT, " +
                    "   price  FLOAT DEFAULT 0.00 NOT NULL" +
                    ");" +
                    "CREATE TABLE basket_product (" +
                    "   id BIGSERIAL PRIMARY KEY NOT NULL, " +
                    "   quantity int NOT NULL, " +
                    "   basket_id BIGINT REFERENCES basket (id) ON DELETE CASCADE NOT NULL, " +
                    "   product_id BIGINT REFERENCES product (id) ON DELETE CASCADE NOT NULL" +
                    ");";


            statement.executeUpdate(sqlQuery);
            statement.close();
            con.commit();
            con.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //System.exit(0);
        }
    }

    public static String createUser(String firstName, String lastName, String email, String phoneNumber, String password) {
        if (firstName.isEmpty())
            return "First name is required.";
        if (lastName.isEmpty())
            return "Last name is required.";
        if (email.isEmpty())
            return "Email is required.";
        if (phoneNumber.isEmpty())
            return "Phone number is required.";
        // double check email and password in the frontend
        if (password.isEmpty())
            return "Password is required.";
        if (password.length() < 8)
            return "Weak password less than 8 characters.";

        if (!normaliseEmail(email))
            return "Invalid email address.";

        String rePhonePattern = "^\\d{10,11}$";
        Pattern pattern = Pattern.compile(rePhonePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phoneNumber.strip());
        if (!matcher.find())
            return "Invalid UK phone number, must be 10 or 11 digits.";

        password = encryptPassword(password);

        String sqlQuery;
        sqlQuery = String.format("INSERT INTO customer " +
                "(first_name, last_name, email, phone_number, password)" +
                "VALUES ('%s', '%s', '%s', '%s', '%s');", firstName, lastName, email, phoneNumber, password);
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            statement.executeUpdate(sqlQuery);
            con.commit();
            sqlQuery = String.format("SELECT id FROM customer WHERE email='%s';", email);
            ResultSet userIDResult = statement.executeQuery(sqlQuery);
            userIDResult.next();
            int userID = userIDResult.getInt("id");

            sqlQuery = String.format("INSERT INTO basket (customer_id) VALUES (%d);", userID);
            statement.executeUpdate(sqlQuery);
            statement.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        return "User has been successfully created.";
    }

    public static boolean normaliseEmail(String email) {
        String rePattern = "^\\w+@(\\w+.[a-zA-Z]+|\\w+.[a-zA-Z]+.[a-zA-Z]+)$";
        Pattern pattern = Pattern.compile(rePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email.strip());
        return matcher.find();
    }

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String pwd = no.toString(16);
            while (pwd.length() < 32) {
                pwd = "0" + pwd;
            }
            return pwd;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean checkPassword(String email, String password) throws SQLException {
        String pwd = encryptPassword(password);
        ResultSet result = createCustomQuery(
                "SELECT password FROM customer WHERE email=" + "'" + email.strip() + "' FETCH FIRST ROW ONLY;");
        try {
            result.next();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        String current_pwd = result.getString("password");
        result.close();
        return current_pwd.equals(pwd);
    }

    public static ResultSet all(String tableName) {
        /*
         * Returns all columns from a selected database table
         */

        ResultSet resultSet = null;

        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        // Close the ResultSet after you finish resultSet.close()
        return resultSet;
    }

    public static ResultSet getSelected(String tableName, String... fields) {
        /*
         * Returns selected fields in all columns from a selected database table
         */
        String columns = String.join(", ", fields);

        ResultSet resultSet = null;

        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT " + columns + " FROM " + tableName + ";");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //  System.exit(0);
        }
        // Close the ResultSet after you finish resultSet.close()
        return resultSet;
    }


    public static ResultSet createCustomQuery(String query) {
        ResultSet resultSet = null;

        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            con.commit();
            statement.close();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //   System.exit(0);
        }
        return resultSet;

    }

    public static boolean createCustomCommand(String command) {
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            statement.executeUpdate(command);
            con.commit();
            statement.close();
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public static String login(String email, String password) {
        try {
            if (checkPassword(email, password)) {
                // session here
                // return type must be UUID String.
                String session = Session.getUUID();
                String sqlQuery = "UPDATE customer SET session_uuid = " +
                        "'" + session + "' " +
                        "WHERE email = '" + email + "';";

                try {
                    Connection con = connect();
                    Statement statement = con.createStatement();
                    statement.executeUpdate(sqlQuery);
                    statement.close();
                    con.commit();
                    con.close();
                } catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    // System.exit(0);
                }

                return session;
            }
            /*
             *  Implement this else statement in the gui.
             *
             * check if session is empty first with login.isEmpty()
             *
             *      else {
             *          return "Invalid email and password combination.";
             *      }
             */

        } catch (SQLException e) {
            return "";
        }
        return "";
    }

    public static String logout(String session) {
        if (session == null)
            return "Not this time..";
        String sqlQuery = "UPDATE customer SET session_uuid = null " +
                "WHERE session_uuid = '" + session + "';";

        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            statement.executeUpdate(sqlQuery);
            statement.close();
            con.commit();
            con.close();
            return "Success";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return "Something went wrong";
        }
    }

    public static ResultSet getCustomer(String sessionUUID) {
        if (sessionUUID == null) throw new NullPointerException("Stop hacking the app.");
        ResultSet resultSet = null;
        String query = "SELECT * FROM customer WHERE " +
                "session_uuid='" + sessionUUID + "';";
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            statement.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        return resultSet;
    }

    public static ArrayList<Object> getObjectOr404(String table, int pk) {
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + table + " WHERE " +
                "id=" + pk + ";";
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            statement.close();
            con.commit();
            con.close();
            ArrayList<Object> arrayList = new ArrayList<Object>();
            arrayList.add(200);
            arrayList.add(resultSet);
            return arrayList;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            ArrayList<Object> arrayList = new ArrayList<Object>();
            arrayList.add(404);
            return arrayList;
            // System.exit(0);
        }
    }

    // ADD PRODUCT TO BASKET
    public static String addProductToBasket(String session, int pk, int quantity) {
        ArrayList<Object> obj = getObjectOr404("product", pk);

        if ((int) obj.get(0) == 200) {
            try {
                Connection con = connect();
                Statement statement = con.createStatement();
                String query;
                query = String.format("SELECT id FROM customer WHERE session_uuid='%s';", session);
                ResultSet userIDResult = statement.executeQuery(query);
                userIDResult.next();
                int currUserID = userIDResult.getInt("id");
                userIDResult.close();
                query = String.format("SELECT id FROM basket WHERE customer_id=%d LIMIT 1;", currUserID);
                ResultSet currUserBasketID = statement.executeQuery(query);
                currUserBasketID.next();
                int currBasketID = currUserBasketID.getInt("id");
                System.out.println("curr basket id = " + currBasketID);
                currUserBasketID.close();

                ResultSet exists = createCustomQuery(String.format("SELECT * FROM basket_product " +
                        "WHERE basket_id=%d AND product_id=%d", currBasketID, pk));
                if (exists.next()) {
                    int newQuantity = exists.getInt("quantity") + quantity;
                    String updateQuery = String.format("UPDATE basket_product SET quantity=%d " +
                            "WHERE basket_id=%d AND product_id=%d", newQuantity, currBasketID, pk);
                    statement.executeUpdate(updateQuery);
                } else {
                    query = String.format("INSERT INTO basket_product (basket_id, product_id, quantity) " +
                            "VALUES (%d, %d, %d);", currBasketID, pk, quantity);
                    statement.executeUpdate(query);
                }
                statement.close();
                con.commit();
                con.close();
                return "Product has been added to the basket";

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());

                return "Something went wrong";
            }
            //query = "INSERT INTO product () ";
        } else if ((int) obj.get(0) == 404) {
            return "Product does not exist";
        }
        return "Error found.";
    }

    public static LinkedList<Object[]> getBasketContent(String session) {
        LinkedList<Object[]> resultList = new LinkedList<>();
        try {
            Connection con = connect();
            Statement statement = con.createStatement();
            String query;
            query = String.format("SELECT id FROM customer WHERE session_uuid='%s';", session);
            ResultSet userIDResult = statement.executeQuery(query);
            userIDResult.next();
            int currUserID = userIDResult.getInt("id");
            userIDResult.close();

            query = String.format("SELECT id FROM basket WHERE customer_id=%d;", currUserID);
            ResultSet basketID = statement.executeQuery(query);
            basketID.next();
            int basketUserID = basketID.getInt("id");
            basketID.close();
            query = String.format("SELECT * FROM basket_product bp INNER JOIN product " +
                    "p on p.id = bp.product_id WHERE bp.basket_id=%s;", basketUserID);
            ResultSet tableJoin = statement.executeQuery(query);
            while (tableJoin.next()) {
                resultList.add(new Object[]{
                        tableJoin.getInt("id"),
                        tableJoin.getInt("basket_id"),
                        tableJoin.getInt("product_id"),
                        tableJoin.getString("title"),
                        tableJoin.getString("description"),
                        tableJoin.getFloat("price"),
                        tableJoin.getInt("quantity"),
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    private static class Session {
        private static String getUUID() {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    public static void main(String[] args) {
        //System.out.println(createUser("Jei", "Sama", "jei@gmail.com", "1234567899",
        //      "testing123"));
        String session = login("jei@gmail.com", "testing123");
        addProductToBasket(session, 1, 5);
        addProductToBasket(session, 3, 5);
        //createCustomCommand("DELETE FROM basket_product;");
        LinkedList<Object[]> map = getBasketContent(session);
        for (Object[] obj : map) {
            System.out.println("###################");

            for (Object o : obj) {
                System.out.println(o);
            }
        }
        logout(session);
    }
}
