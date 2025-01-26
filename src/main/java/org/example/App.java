package org.example;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();

        try {
            Student student1 = new Student("Ionut", "Marin", "1985-05-15", "Male", "123456");
            Student student2 = new Student("Bianca", "Anton", "1995-08-20", "Female", "678901");
            Student student3 = new Student("Cristian", "Marcovici", "1990-06-28", "Male", "423654");

            repository.addStudent(student1);
            repository.addStudent(student2);
            repository.addStudent(student3);

            System.out.println("Students by age 39: " + repository.retrieveStudentsByAge(39));
            System.out.println("Students ordered by last name: " + repository.listStudents("lastname"));
            System.out.println("Students ordered by birth date: " + repository.listStudents("birthdate"));

            repository.deleteStudent("123456");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
