package se.lexicon.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Course {
    private static int idSequencer = 0;
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private ArrayList<Student> students;

    public Course(String courseName, LocalDate startDate, int weekDuration) {
        id = ++idSequencer;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = new ArrayList<Student>();
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void register(Student student) {
        if(student != null) {
            if(checkForDuplicateID(student.getId()))
                throw new IllegalArgumentException("Error (Course.java): Duplicate id, duplicate students not allowed.");
            students.add(student);
        }
    }
    public void unRegister(Student student) {
        if(student != null)
            students.remove(student);
    }

    private boolean checkForDuplicateID(int id) {
        for (Student student : students) {
            if(student.getId() == id)
                return true;
        }
        return false;
    }
}
