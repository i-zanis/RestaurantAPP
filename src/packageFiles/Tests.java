package packageFiles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
    public void createTestUser() {
        StoreDBManager.createUser(
                "FTest",
                "LTest",
                "test@test.com",
                "12345678009",
                "testing123"
        );
    }

    public void deleteTestUser() {
        StoreDBManager.createCustomQuery("DELETE FROM customer WHERE email = 'test@test.com'");
    }

    @Test
    public void testNormaliseEmail() {
        HashMap<String, Boolean> emailCases = new HashMap<>();
        emailCases.put("@gmail.com", false);
        emailCases.put("@g:.com", false);
        emailCases.put("@f@mail.co.uk", false);
        emailCases.put("@mail.co.uk", false);
        emailCases.put("aysdas:@gmal.com", false);
        emailCases.put("asdasd@mail.co.uk", true);
        emailCases.put("hey123@gmail.com", true);
        emailCases.put("hey123@gmail.co.uk", true);

        for (String key : emailCases.keySet()) {
            assertEquals(emailCases.get(key), StoreDBManager.normaliseEmail(key));
        }
    }

    @Test
    public void testGetFirstCustomer() {
        String result = "";
        try {
            ResultSet data = StoreDBManager.all("customer");
            while (data.next()) {
                int id = data.getInt("id");
                String fName = data.getString("first_name");
                String lName = data.getString("last_name");
                String email = data.getString("email");
                String pn = data.getString("phone_number");
                String pwd = data.getString("password");
                String session = data.getString("session_uuid");
                System.out.println("ID = " + id);
                result += "ID = " + id + '\n';
                System.out.println("fName = " + fName);
                result += "fName = " + fName + '\n';
                System.out.println("lName = " + lName);
                result += "lName = " + lName + '\n';
                System.out.println("email = " + email);
                result += "email = " + email + '\n';
                System.out.println("pn = " + pn);
                result += "pn = " + pn + '\n';
                System.out.println("pwd = " + pwd);
                result += "pwd = " + pwd;
                System.out.println(session);

            }
            data.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        String expected = "ID = 1\n" +
                "fName = FTest\n" +
                "lName = LTest\n" +
                "email = test@test.com\n" +
                "pn = 12345678009\n" +
                "pwd = 7f2ababa423061c509f4923dd04b6cf1";
        assertEquals(result, expected);
    }

    @Test
    public void testGetSelected() {
        String result = "";
        try {
            ResultSet data = StoreDBManager.getSelected("customer", "id", "email", "password");
            while (data.next()) {
                int id = data.getInt("id");
                String email = data.getString("email");
                String pwd = data.getString("password");
                System.out.println("id = " + id);
                result += "id = " + id + '\n';
                System.out.println("email = " + email);
                result += "email = " + email + '\n';
                System.out.println("pwd = " + pwd);
                result += "pwd = " + pwd;
            }
            data.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        String expected = "id = 1\n" +
                "email = test@test.com\n" +
                "pwd = 7f2ababa423061c509f4923dd04b6cf1";
        assertEquals(result, expected);
    }

    @Test
    public void testEncryptPassword() {
        String password = "testing123";
        String expected = "7f2ababa423061c509f4923dd04b6cf1";
        assertEquals(StoreDBManager.encryptPassword(password), expected);
    }

    @Test
    public void testCheckPassword() {
        HashMap<String, Boolean> pwdCases = new HashMap<>();
        pwdCases.put("test@test.com,testing123", true);
        pwdCases.put("test@test.com,abcdefgh", false);
        pwdCases.put("test@test.com,testingtesting", false);

        for (String key : pwdCases.keySet()) {
            String[] comp = key.split(",");
            String email = comp[0];
            String pwd = comp[1];
            try {
                boolean status = StoreDBManager.checkPassword(email, pwd);
                assertEquals(pwdCases.get(key), status);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    @Test
    public void testLogin() {
        String email1, pwd1, email2, pwd2;
        email1 = "test@test.com";
        pwd1 = "testing123";
        email2 = "userdoesnotexit@test.com";
        pwd2 = "abcdefgh";

        assertNotEquals(StoreDBManager.login(email1, pwd1), "");
        assertEquals(StoreDBManager.login(email2, pwd2), "");
    }

    @Test
    public void testLogout() {
        String session = StoreDBManager.login("test@test.com", "testing123");
        assertEquals(StoreDBManager.logout(session), "Success");
    }
}

