package com.quizsystem.factory;

import com.quizsystem.models.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code StudentFactory} class provides a factory method for creating or
 * retrieving {@link Student} instances.
 *
 * <p>This class implements a caching mechanism to store already created
 * {@code Student} objects to avoid duplicates.</p>
 */
public class StudentFactory {

    // Cache to store already created students
    private static final Map<String, Student> studentCache = new HashMap<>();

    /**
     * Factory method to create or retrieve a {@link Student}.
     *
     * @param firstName   the first name of the student
     * @param lastName    the last name of the student
     * @param dateOfBirth the date of birth of the student
     * @return an existing or new {@link Student} object
     * @throws NullPointerException if {@code firstName}, {@code lastName},
     *         or {@code dateOfBirth} is {@code null}.
     */
    public static Student getStudent(String firstName, String lastName, Date dateOfBirth) {
        // Validate inputs to prevent null values
        if (firstName == null) {
            throw new NullPointerException("First name cannot be null.");
        }
        if (lastName == null) {
            throw new NullPointerException("Last name cannot be null.");
        }
        if (dateOfBirth == null) {
            throw new NullPointerException("Date of birth cannot be null.");
        }

        // Create a new student and add to the cache
        Student newStudent = Student.valueOf(firstName, lastName, dateOfBirth);
        return newStudent;
    }
}





