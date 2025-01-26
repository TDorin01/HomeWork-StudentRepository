package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String idCnp;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String idCnp) {
        validateFirstName(firstName);
        validateLastName(lastName);
        validateDateOfBirth(dateOfBirth);
        validateGender(gender);
        validateIdCnp(idCnp);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.gender = gender.toUpperCase();
        this.idCnp = idCnp;
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
    }

    private void validateDateOfBirth(String dateOfBirth) {
        LocalDate dob = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate minDate = LocalDate.of(1980, 1, 1);
        LocalDate maxDate = LocalDate.now().minusYears(10);

        if (dob.isBefore(minDate) || dob.isAfter(maxDate)) {
            throw new IllegalArgumentException("Date of birth must be between 1980 and the current year - 10.");
        }
    }

    private void validateGender(String gender) {
        if (gender == null || !(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            throw new IllegalArgumentException("Gender must be male, female, M, or F.");
        }
    }

    private void validateIdCnp(String idCnp) {
        if (idCnp == null || idCnp.trim().isEmpty()) {
            throw new IllegalArgumentException("CNP ID can't be empty.");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getidCnp() {
        return idCnp;
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("Student [ID=%s, Name=%s %s, DOB=%s, Gender=%s]", idCnp, firstName, lastName, dateOfBirth, gender);
    }


}
