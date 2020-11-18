import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testNormaliseEmail() {
        HashMap<String, Boolean> emailCases = new HashMap<String, Boolean>();
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
        String result = "Connected to database successfully\n";
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
                System.out.println("fName = " + fName);
                result += "fName = " + fName + '\n';
                System.out.println("lName = " + lName);
                result += "lName = " + lName + '\n';
                System.out.println("email = " + email);
                result += "email = " + email + '\n';
                System.out.println("pn = " + pn);
                result += "pn = " + pn + '\n';
                System.out.println("pwd = " + pwd);
                result += "pwd = " + pwd + '\n';
                System.out.println("session = " + session);
                result += "session = " + session;
                System.out.println();
            }
            data.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        String expected = "Connected to database successfully\n" +
                "fName = FTest\n" +
                "lName = LTest\n" +
                "email = test@test.com\n" +
                "pn = 12345678009\n" +
                "pwd = 7f2ababa423061c509f4923dd04b6cf1\n" +
                "session = null";
        assertEquals(result, expected);
    }

}

