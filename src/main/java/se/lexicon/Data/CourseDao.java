package se.lexicon.Data;

import se.lexicon.Model.Course;

import java.time.LocalDate;
import java.util.ArrayList;

public class CourseDao implements ICourseDao{
    private ArrayList<Course> courses;

    public CourseDao() {
        courses = new ArrayList<>();
    }

    @Override
    public Course save(Course course) {
        if(course != null) {
            if(checkForDuplicateID(course.getId()))
                throw new IllegalArgumentException("Error (CourseDao.java): Duplicate id, duplicate courses not allowed.");
            courses.add(course);
        }
        return course;
    }

    private boolean checkForDuplicateID(int id) {
        for (Course course : courses) {
            if(course.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public Course findById(int id) {
        for (Course course : courses) {
            if (course.getId() == id)
                return course;
        }
        return null;
    }

    @Override
    public ArrayList<Course> findByName(String name) {
        ArrayList<Course> sameNameSet = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseName().equals(name))
                sameNameSet.add(course);
        }
        return sameNameSet;
    }

    @Override
    public ArrayList<Course> findByDate(LocalDate date) {
        ArrayList<Course> sameNameList = new ArrayList<>();
        for (Course course : courses) {
            if (course.getStartDate().equals(date))
                sameNameList.add(course);
        }
        return sameNameList;
    }

    @Override
    public ArrayList<Course> findAll() {
        return courses;
    }

    @Override
    public boolean delete(Course course) {
        return courses.remove(course);
    }
}
