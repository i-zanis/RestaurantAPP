package org.uwl.cs.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
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
            statement.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        System.out.println("User has been successfully created.");
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
            statement.close();
            con.commit();
            con.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            //   System.exit(0);
        }
        return resultSet;

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

                query = String.format("INSERT INTO basket_product (basket_id, product_id, quantity) " +
                        "VALUES (%d, %d, %d);", currBasketID, pk, quantity);
                statement.executeUpdate(query);
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


    private static class Session {
        private static String getUUID() {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }

    public static void main(String[] args) {
        System.out.println("##### customer #####");
        ResultSet resultSet = all("customer");
        try {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("first_name"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("last_name"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("email"));
                System.out.println();
            }
        } catch (SQLException ignored) {
        }
        System.out.println("##### basket #####");
        resultSet = all("basket");
        try {
            while (resultSet.next()) {
                System.out.print(resultSet.getString("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getInt("customer_id"));
                System.out.println();
            }
        } catch (SQLException ignored) {
        }
        System.out.println("##### products #####");
        resultSet = all("product");
        try {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("title"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("description"));
                System.out.print(" | ");
                System.out.print(resultSet.getFloat("price"));
                System.out.println();
            }
        } catch (SQLException ignored) {
        }

        //String session = login("t@t.com", "testing123");



        System.out.println("##### basket products #####");
        resultSet = all("basket_product");
        try {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("basket_id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("product_id"));
                System.out.println();
            }
            resultSet.close();

        } catch (SQLException ignored) {
        }
        System.out.println("##### join #####");
        ResultSet tableJoin = createCustomQuery("SELECT * FROM basket_product bp INNER JOIN product p on p.id = bp.product_id WHERE bp.basket_id = 2;");
        try {
            while (tableJoin.next()) {
                System.out.print(tableJoin.getInt("id"));
                System.out.print(" | ");
                System.out.print(tableJoin.getString("basket_id"));
                System.out.print(" | ");
                System.out.print(tableJoin.getString("product_id"));
                System.out.print(" | ");
                System.out.print(tableJoin.getString("title"));
                System.out.print(" | ");
                System.out.print(tableJoin.getString("description"));
                System.out.print(" | ");
                System.out.print(tableJoin.getFloat("price"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
