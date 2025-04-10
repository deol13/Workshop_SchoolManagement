package se.lexicon.Data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.Model.Course;
import se.lexicon.Model.Student;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseDaoTest {
    @Test
    public void testSave_Course_Success(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);

        // Expected
        String expectedCourseName = "Java Programming";
        LocalDate expectedDate = LocalDate.now().plusWeeks(6);
        int expectedWeekDuration = 6;

        // Actual
        Course actual = courseDao.save(course);

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedCourseName, actual.getCourseName());
        Assertions.assertEquals(expectedDate, actual.getStartDate());
        Assertions.assertEquals(expectedWeekDuration, actual.getWeekDuration());
    }

    @Test
    public void testSave_Course_Duplicate_ExceptionThrown(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        courseDao.save(course);
        // Student student = new Student( "Dennis", "dennis@test.se", "newglrt19");

        // Expected
        String expected = "Error (CourseDao.java): Duplicate id, duplicate courses not allowed.";

        // Actual
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> courseDao.save(course));
        String actual = exception.getMessage();

        // Verify the result:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindById_SuccessfullyFind(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        String expectedCourseName = "C# Programming";
        LocalDate expectedDate = LocalDate.now().plusWeeks(8);
        int expectedWeekDuration = 8;

        // Actual
        int id = course2.getId();
        Course actual = courseDao.findById(id);

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedCourseName, actual.getCourseName());
        Assertions.assertEquals(expectedDate, actual.getStartDate());
        Assertions.assertEquals(expectedWeekDuration, actual.getWeekDuration());
    }

    @Test
    public void testFindById_FailInFinding(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected

        // Actual
        Course actual = courseDao.findById(-10);

        // verify
        Assertions.assertNull(actual);
    }

    @Test
    public void testFindByName_SuccessfullyFind(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        String expectedCourseName2 = "C# Programming";
        LocalDate expectedDate2 = LocalDate.now().plusWeeks(8);
        int expectedWeekDuration2 = 8;

        int expectedSize = 1;

        // Actual
        ArrayList<Course> actual = courseDao.findByName(course2.getCourseName());

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedSize, actual.size());

        Assertions.assertEquals(expectedCourseName2, actual.get(0).getCourseName());
        Assertions.assertEquals(expectedDate2, actual.get(0).getStartDate());
        Assertions.assertEquals(expectedWeekDuration2, actual.get(0).getWeekDuration());
    }

    @Test
    public void testFindByName_FailInFinding(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        int expectedSize = 0;

        // Actual
        ArrayList<Course> actual = courseDao.findByName("Python Programming");

        // verify
        Assertions.assertEquals(expectedSize, actual.size());
    }

    @Test
    public void testFindByDate_SuccessfullyFind(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        String expectedCourseName2 = "C# Programming";
        LocalDate expectedDate2 = LocalDate.now().plusWeeks(8);
        int expectedWeekDuration2 = 8;

        int expectedSize = 1;

        // Actual
        ArrayList<Course> actual = courseDao.findByDate(course2.getStartDate());

        // verify
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expectedSize, actual.size());

        Assertions.assertEquals(expectedCourseName2, actual.get(0).getCourseName());
        Assertions.assertEquals(expectedDate2, actual.get(0).getStartDate());
        Assertions.assertEquals(expectedWeekDuration2, actual.get(0).getWeekDuration());
    }

    @Test
    public void testFindByDate_FailInFinding(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        int expectedSize = 0;

        // Actual
        ArrayList<Course> actual = courseDao.findByDate(LocalDate.now().plusWeeks(3));

        // verify
        Assertions.assertEquals(expectedSize, actual.size());
    }

    @Test
    public void testFindAll(){
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        int expectedSize = 2;

        // Actual
        ArrayList<Course> courseList = courseDao.findAll();

        // verify
        Assertions.assertEquals(expectedSize, courseList.size());
        Assertions.assertEquals(course, courseList.get(0));
        Assertions.assertEquals(course2, courseList.get(1));
    }

    @Test
    public void testRemove_Success() {
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        int expectedSize = 1;

        // Actual
        boolean actual = courseDao.delete(course2);
        ArrayList<Course> courseList = courseDao.findAll();

        // verify
        Assertions.assertTrue(actual);
        Assertions.assertEquals(expectedSize, courseList.size());
        Assertions.assertEquals(course, courseList.get(0));
    }

    @Test
    @DisplayName("Trying to remove an object that doesn't exist")
    public void testRemove_Failure() {
        CourseDao courseDao = new CourseDao();
        Course course = new Course("Java Programming", LocalDate.now().plusWeeks(6), 6);
        Course course2 = new Course("C# Programming", LocalDate.now().plusWeeks(8), 8);
        Course course3 = new Course("Python Programming", LocalDate.now().plusWeeks(5), 8);
        courseDao.save(course);
        courseDao.save(course2);

        // Expected
        int expectedSize = 2;

        // Actual
        boolean actual = courseDao.delete(course3);
        ArrayList<Course> courseList = courseDao.findAll();

        // verify
        Assertions.assertFalse(actual);
        Assertions.assertEquals(expectedSize, courseList.size());
        Assertions.assertEquals(course, courseList.get(0));
        Assertions.assertEquals(course2, courseList.get(1));
    }
}
