package se.lexicon.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    public void testCreatingStudent_Success(){

        // expected
        int id = 1;
        String name = "Dennis";
        String email = "dennis@test.se";
        String address = "newglrt19";

        // actual
        Student actual = new Student( "Dennis", "dennis@test.se", "newglrt19");

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(id, actual.getId());
        Assertions.assertEquals(name, actual.getName());
        Assertions.assertEquals(email, actual.getEmail());
        Assertions.assertEquals(address, actual.getAddress());
    }
}
