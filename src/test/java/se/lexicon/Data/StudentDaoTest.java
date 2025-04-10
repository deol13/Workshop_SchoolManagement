package se.lexicon.Data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.Model.Student;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentDaoTest {
    @Test
    public void testSave_Student_Success(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "Dennis", "dennis@test.se", "newglrt19");

        // Expected
        String expectedName = "Dennis";
        String expectedEmail = "dennis@test.se";
        String expectedAddress = "newglrt19";

        // Actual
        Student actual = studentDao.save(student);

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedEmail, actual.getEmail());
        Assertions.assertEquals(expectedAddress, actual.getAddress());
    }

    @Test
    public void testSave_Student_Duplicate_ExceptionThrown(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);

        // Expected
        String expected = "Error (StudentDao.java): Duplicate id, duplicate students not allowed.";

        // Actual
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> studentDao.save(student));
        String actual = exception.getMessage();

        // Verify the result:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindByEmail_SuccessfullyFind(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        String expectedName = "Dennis";
        String expectedEmail = "dennis@test.se";
        String expectedAddress = "newglrt19";

        // Actual
        Student actual = studentDao.findByEmail(student2.getEmail());

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedEmail, actual.getEmail());
        Assertions.assertEquals(expectedAddress, actual.getAddress());
    }

    @Test
    public void testFindByEmail_FailInFinding(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected

        // Actual
        Student actual = studentDao.findByEmail("gunilla@test.se");

        // verify
        Assertions.assertNull(actual);
    }

    @Test
    public void testFindByName_SuccessfullyFind(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        String expectedName = "Dennis";
        String expectedEmail = "dennis@test.se";
        String expectedAddress = "newglrt19";

        // Actual
        Student actual = studentDao.findByName(student2.getName());

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedEmail, actual.getEmail());
        Assertions.assertEquals(expectedAddress, actual.getAddress());
    }

    @Test
    public void testFindByName_FailInFinding(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected

        // Actual
        Student actual = studentDao.findByName("Gunilla");

        // verify
        Assertions.assertNull(actual);
    }

    @Test
    public void testFindById_SuccessfullyFind(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        String expectedName = "Dennis";
        String expectedEmail = "dennis@test.se";
        String expectedAddress = "newglrt19";

        // Actual
        int id = student2.getId();
        Student actual = studentDao.findById(id);

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedName, actual.getName());
        Assertions.assertEquals(expectedEmail, actual.getEmail());
        Assertions.assertEquals(expectedAddress, actual.getAddress());
    }

    @Test
    public void testFindById_FailInFinding(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected

        // Actual
        Student actual = studentDao.findById(-10);

        // verify
        Assertions.assertNull(actual);
    }

    @Test
    public void testFindAll(){
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        int expectedSize = 2;

        // Actual
        ArrayList<Student> studentsList = studentDao.findAll();

        // verify
        Assertions.assertEquals(expectedSize, studentsList.size());
        Assertions.assertEquals(student, studentsList.get(0));
        Assertions.assertEquals(student2, studentsList.get(1));
    }

    @Test
    public void testRemove_Success() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        int expectedSize = 1;

        // Actual
        boolean actual = studentDao.delete(student2);
        ArrayList<Student> studentsList = studentDao.findAll();

        // verify
        Assertions.assertTrue(actual);
        Assertions.assertEquals(expectedSize, studentsList.size());
        Assertions.assertEquals(student, studentsList.get(0));
    }
    @Test
    @DisplayName("Trying to remove an object that doesn't exist")
    public void testRemove_Failure() {
        StudentDao studentDao = new StudentDao();
        Student student = new Student( "John", "john@test.se", "ludma10");
        Student student2 = new Student( "Dennis", "dennis@test.se", "newglrt19");
        Student student3 = new Student( "Gunilla", "gunilla@test.se", "newglrt10");
        studentDao.save(student);
        studentDao.save(student2);

        // Expected
        int expectedSize = 2;

        // Actual
        boolean actual = studentDao.delete(student3);
        ArrayList<Student> studentsList = studentDao.findAll();

        // verify
        Assertions.assertFalse(actual);
        Assertions.assertEquals(expectedSize, studentsList.size());
        Assertions.assertEquals(student, studentsList.get(0));
        Assertions.assertEquals(student2, studentsList.get(1));
    }

}
