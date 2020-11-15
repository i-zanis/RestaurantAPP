import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StoreDBManager {
    private static Connection connect() throws Exception {
        String url = "jdbc:postgresql://kandula.db.elephantsql.com:5432/ikzmpslm";
        String user = "ikzmpslm";
        String pwd = "73LI1sNi24J6WulfwD8LtACSgmyHTHOp";
        Connection con = null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(url, user, pwd);
        con.setAutoCommit(false);
        System.out.println("Opened database successfully");
        return con;
    }

    public static ResultSet getAllObjects(String tableName) throws Exception {
        Connection con = connect();
        ResultSet resultSet = null;
        Statement statement = con.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        resultSet.close();

        return resultSet;
    }


    public static ResultSet createCustomQuery(String query) throws Exception {

        Connection con = connect();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        statement.close();
        con.commit();
        con.close();
        return resultSet;
    }

    public void createDatabase() {
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
                    "   password       TEXT            NOT NULL);" +
                    "CREATE TABLE basket (" +
                    "   id BIGSERIAL PRIMARY KEY NOT NULL, " +
                    "   customer_id BIGINT REFERENCES store_customers (id) ON DELETE CASCADE  NOT NULL, " +
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
            System.exit(0);
        }
    }
}
