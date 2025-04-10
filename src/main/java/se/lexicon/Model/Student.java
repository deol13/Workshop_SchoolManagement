package se.lexicon.Model;

public class Student {
    private static int idSequencer = 0;
    private int id;
    private String name;
    private String email;
    private String address;

    public Student(String name, String email, String address) {
        id = ++idSequencer;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
