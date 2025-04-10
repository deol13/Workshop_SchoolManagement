package se.lexicon.Data;

import se.lexicon.Model.Student;

import java.util.ArrayList;

public interface IStudentDao {
    Student save(Student student);
    Student findByEmail(String email);
    Student findByName(String name);
    Student findById(int id);
    ArrayList<Student> findAll();
    boolean delete(Student student);
}
