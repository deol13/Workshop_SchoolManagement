package se.lexicon.Data;

import se.lexicon.Model.Student;

import java.util.ArrayList;

public class StudentDao implements IStudentDao {
   private ArrayList<Student> students;

    public StudentDao() {
        students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {
        if(student != null) {
            if(checkForDuplicateID(student.getId()))
                throw new IllegalArgumentException("Error (StudentDao.java): Duplicate id, duplicate students not allowed.");
            students.add(student);
        }
        return student;
    }

    private boolean checkForDuplicateID(int id) {
        for (Student student : students) {
            if(student.getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public Student findByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email))
                return student;
        }
        return null;
    }

    @Override
    public Student findByName(String name) {
        for (Student student : students) {
            if (student.getName().equals(name))
                return student;
        }
        return null;
    }

    @Override
    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    @Override
    public ArrayList<Student> findAll() {
        return students;
    }

    @Override
    public boolean delete(Student student) {
        return students.remove(student);
    }
}
