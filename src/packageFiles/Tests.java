package packageFiles;

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
}

