package se.lexicon.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {

    @Test
    public void testCreatingACourse_Success(){
        // expected
        String courseName = "Java Programming";
        LocalDate startDate = LocalDate.now().plusWeeks(5);
        int weekDuration = 5;

        // actual
        Course actual = new Course("Java Programming", LocalDate.now().plusWeeks(5), 5);

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getStudents());
        Assertions.assertEquals(0, actual.getStudents().size());
        Assertions.assertEquals(courseName, actual.getCourseName());
        Assertions.assertEquals(startDate, actual.getStartDate());
        Assertions.assertEquals(weekDuration, actual.getWeekDuration());
    }

    @Test
    public void testRegisterStudent_Success() {
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(5), 5);
        Student student = new Student("Dennis", "dennis@test.se", "newglrt19");

        // expected
        Student expected = new Student("Dennis", "dennis@test.se", "newglrt19");

        // actual
        course.register(student);
        try{
            Student actual = course.getStudents().get(0);

            // verify
            Assertions.assertEquals(expected.getName(), actual.getName());
            Assertions.assertEquals(expected.getEmail(), actual.getEmail());
            Assertions.assertEquals(expected.getAddress(), actual.getAddress());
        }
        catch (Exception e) {
            Assertions.assertNull(e);
        }
    }

    @Test
    public void testRegisterStudent_Duplicate_AddFails(){
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(5), 5);
        Student student = new Student("Dennis", "dennis@test.se", "newglrt19");
        course.register(student);

        // Expected:
        String expected = "Error (Course.java): Duplicate id, duplicate students not allowed.";

        // Actual:
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> course.register(student));
        String actual = exception.getMessage();

        // Verify the result:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUnRegisterStudent_Success() {
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(5), 5);
        Student student = new Student("Dennis", "dennis@test.se", "newglrt19");
        course.register(student);

        // expected
        int sizeBefore = course.getStudents().size();

        // actual
        course.unRegister(student);
        int sizeAfter = course.getStudents().size();

        // verify
        Assertions.assertEquals(1, sizeBefore);
        Assertions.assertEquals(0, sizeAfter);
    }
}
