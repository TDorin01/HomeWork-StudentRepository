package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class StudentRepository {
    private static final Logger logger = Logger.getLogger(StudentRepository.class.getName());
    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        if (students.containsKey(student.getidCnp())) {
            logger.warning("Student with CNP ID " + student.getidCnp() + " already exists.");
            throw new IllegalArgumentException("Student with this CNP ID already exists.");
        }
        students.put(student.getidCnp(), student);
        logger.info("Student added: " + student);
    }

    public void deleteStudent(String idCnp) {
        if (idCnp == null || idCnp.trim().isEmpty()) {
            logger.warning("CNP ID is empty.");
            throw new IllegalArgumentException("CNP ID cannot be empty.");
        }
        if (!students.containsKey(idCnp)) {
            logger.warning("Student with CNP ID " + idCnp + " does not exist.");
            throw new IllegalArgumentException("Student with this CNP ID does not exist.");
        }
        students.remove(idCnp);
        logger.info("Student with CNP ID " + idCnp + " deleted.");
    }

    public List<Student> retrieveStudentsByAge(int age) {
        if (age < 0) {
            logger.warning("Age is negative.");
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        List<Student> result = students.values().stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
        logger.info("Retrieved students with age " + age + ": " + result);
        return result;
    }

    public List<Student> listStudents(String orderBy) {
        if (orderBy == null || orderBy.trim().isEmpty()) {
            logger.warning("Order by field is empty.");
            throw new IllegalArgumentException("Order by field cannot be empty.");
        }

        List<Student> result;
        switch (orderBy.toLowerCase()) {
            case "lastname":
                result = students.values().stream()
                        .sorted(Comparator.comparing(Student::getLastName))
                        .collect(Collectors.toList());
                break;
            case "birthdate":
                result = students.values().stream()
                        .sorted(Comparator.comparing(Student::getDateOfBirth))
                        .collect(Collectors.toList());
                break;
            default:
                logger.warning("Invalid order by field: " + orderBy);
                throw new IllegalArgumentException("Invalid order by field. Use 'LastName' or 'BirthDate'.");
        }
        logger.info("Students listed by " + orderBy + ": " + result);
        return result;
    }
}