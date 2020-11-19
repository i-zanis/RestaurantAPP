package packageFiles;

import javax.lang.model.type.NullType;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.security.MessageDigest;
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
        // switched because of priority in the input fields
        if (!normaliseEmail(email))
            return "Invalid email address.";
        if (phoneNumber.isEmpty())
            return "Phone number is required.";

        // switched because of priority in the input fields
        String rePhonePattern = "^\\d{10,11}$";
        Pattern pattern = Pattern.compile(rePhonePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phoneNumber.strip());
        if (!matcher.find())
            return "Invalid UK phone number, must be 10 or 11 digits.";

        // double check email and password in the frontend
        if (password.isEmpty())
            return "Password is required.";
        if (password.length() < 8)
            return "Weak password less than 8 characters.";




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
            System.exit(0);
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
            System.exit(0);
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
            System.exit(0);
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
            System.exit(0);
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
            System.exit(0);
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
                    System.exit(0);
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
            return "Lol are you kidding me!!!";
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
        if (sessionUUID == null) throw new NullPointerException("Lol are you kidding me!!!");
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
            System.exit(0);
        }
        return resultSet;
    }


    private static class Session {
        private static String getUUID() {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }
}
