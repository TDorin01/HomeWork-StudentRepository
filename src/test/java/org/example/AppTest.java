package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest extends TestCase {
    @Test
    void testAddStudent() {
        StudentRepository repository = new StudentRepository();
        Student student = new Student("Bianca", "Anton", "1995-08-20", "Female", "678901");
        repository.addStudent(student);

        // Assert that the student is added successfully
        assertEquals(1, repository.listStudents("lastname").size());
    }

    @Test
    void testAddDuplicateStudent() {
        StudentRepository repository = new StudentRepository();
        Student student = new Student("Bianca", "Anton", "1995-08-20", "Female", "678901");
        repository.addStudent(student);

        // Assert that adding a duplicate student throws an exception
        assertThrows(IllegalArgumentException.class, () -> repository.addStudent(student),
                "Expected an IllegalArgumentException for duplicate student ID.");
    }

    @Test
    void testDeleteStudent() {
        StudentRepository repository = new StudentRepository();
        Student student = new Student("Cristian", "Marcovici", "1990-06-28", "Male", "423654");
        repository.addStudent(student);

        // Delete the student
        repository.deleteStudent("423654");

        // Assert that the student is deleted successfully
        assertTrue(repository.listStudents("lastname").isEmpty());
    }

    @Test
    void testDeleteNonExistentStudent() {
        StudentRepository repository = new StudentRepository();

        // Assert that deleting a non-existent student throws an exception
        assertThrows(IllegalArgumentException.class, () -> repository.deleteStudent("423651"),
                "Expected an IllegalArgumentException for non-existent student ID.");
    }

    @Test
    void testRetrieveStudentsByAge() {
        StudentRepository repository = new StudentRepository();
        Student student = new Student("Ionut", "Marin", "1985-05-15", "Female", "123456");
        repository.addStudent(student);

        // Retrieve students by age
        List<Student> students = repository.retrieveStudentsByAge(39);

        // Assert that the correct student is retrieved
        assertEquals("123456", students.get(0).getidCnp());
    }

    @Test
    void testRetrieveStudentsByInvalidAge() {
        StudentRepository repository = new StudentRepository();

        // Assert that retrieving students by negative age throws an exception
        assertThrows(IllegalArgumentException.class, () -> repository.retrieveStudentsByAge(-1),
                "Expected an IllegalArgumentException for negative age.");
    }

    @Test
    void testListStudentsByLastName() {
        StudentRepository repository = new StudentRepository();
        Student student1 = new Student("Bianca", "Anton", "1995-08-20", "Female", "678901");
        Student student2 = new Student("Ionut", "Marin", "1985-05-15", "Male", "123456");
        repository.addStudent(student1);
        repository.addStudent(student2);

        // List students by last name
        List<Student> students = repository.listStudents("lastname");

        // Assert the correct order
        assertEquals("Anton", students.get(0).getLastName());
        assertEquals("Marin", students.get(1).getLastName());
    }

    @Test
    void testListStudentsByBirthDate() {
        StudentRepository repository = new StudentRepository();
        Student student1 = new Student("Bianca", "Anton", "1995-08-20", "Female", "678901");
        Student student2 = new Student("Ionut", "Marin", "1985-05-15", "Male", "123456");
        repository.addStudent(student1);
        repository.addStudent(student2);

        // List students by birth date
        List<Student> students = repository.listStudents("birthdate");

        // Assert the correct order
        assertEquals("Marin", students.get(0).getLastName());
        assertEquals("Anton", students.get(1).getLastName());
    }

}