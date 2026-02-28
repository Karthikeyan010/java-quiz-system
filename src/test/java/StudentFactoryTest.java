import com.quizsystem.factory.StudentFactory;
import com.quizsystem.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link StudentFactory}.
 * This class contains unit tests to verify the functionality of the StudentFactory
 * which is responsible for creating and caching instances of the {@link Student} class.
 */
public class StudentFactoryTest {

    private String firstName; // The first name of the student used for testing
    private String lastName; // The last name of the student used for testing
    private Date dateOfBirth; // The date of birth of the student used for testing

    /**
     * Sets up the test environment by initializing the first name, last name, and
     * date of birth for the student before each test.
     */
    @BeforeEach
    public void setUp() {
        firstName = "John";
        lastName = "Doe";
        dateOfBirth = new Date(102, 5, 30); // Example: June 30, 2002
    }

    /**
     * Tests the {@link StudentFactory#getStudent(String, String, Date)} method to ensure
     * it returns a new student instance with the correct attributes.
     */
    @Test
    public void testGetStudent_ReturnsNewStudent() {
        // Act
        Student student = StudentFactory.getStudent(firstName, lastName, dateOfBirth);

        // Assert
        assertNotNull(student);  // Ensure the student is not null
        assertEquals(firstName, student.getFirstName());  // Check if the first name is correct
        assertEquals(lastName, student.getLastName());  // Check if the last name is correct
        assertEquals(dateOfBirth, student.getDateOfBirth());  // Check if the date of birth is correct
    }

    /**
     * Tests that the {@link StudentFactory#getStudent(String, String, Date)} method
     * uses caching to return the same student instance for identical parameters.
     */
    @Test
    public void testGetStudent_UsesCacheWhenStudentExists() {
        // Act
        Student firstCall = StudentFactory.getStudent(firstName, lastName, dateOfBirth);
        Student secondCall = StudentFactory.getStudent(firstName, lastName, dateOfBirth);

        // Assert
        assertSame(firstCall, secondCall);  // Both should be the same instance if caching works
    }

    /**
     * Tests the {@link StudentFactory#getStudent(String, String, Date)} method to ensure
     * it creates different student instances for different first names.
     */
    @Test
    public void testGetStudent_CreatesDifferentStudentsForDifferentNames() {
        // Arrange
        String differentFirstName = "Jane";

        // Act
        Student firstStudent = StudentFactory.getStudent(firstName, lastName, dateOfBirth);
        Student secondStudent = StudentFactory.getStudent(differentFirstName, lastName, dateOfBirth);

        // Assert
        assertNotSame(firstStudent, secondStudent);  // They should be different students
        assertEquals(differentFirstName, secondStudent.getFirstName());  // The second student should have the different first name
    }

    /**
     * Tests the {@link StudentFactory#getStudent(String, String, Date)} method to ensure
     * it creates different student instances for different dates of birth.
     */
    @Test
    public void testGetStudent_CreatesDifferentStudentsForDifferentDates() {
        // Arrange
        Date differentDateOfBirth = new Date(103, 7, 15); // Example: August 15, 2003

        // Act
        Student firstStudent = StudentFactory.getStudent(firstName, lastName, dateOfBirth);
        Student secondStudent = StudentFactory.getStudent(firstName, lastName, differentDateOfBirth);

        // Assert
        assertNotSame(firstStudent, secondStudent);  // They should be different students
        assertEquals(differentDateOfBirth, secondStudent.getDateOfBirth());  // The second student should have the different date of birth
    }

    /**
     * Tests the {@link StudentFactory#getStudent(String, String, Date)} method to verify
     * it throws a {@link NullPointerException} when the first or last name is null.
     */
    @Test
    public void testGetStudent_NullName_ThrowsException() {
        // Assert
        assertThrows(NullPointerException.class, () -> {
            StudentFactory.getStudent(null, lastName, dateOfBirth);  // Test null first name
        });

        assertThrows(NullPointerException.class, () -> {
            StudentFactory.getStudent(firstName, null, dateOfBirth);  // Test null last name
        });
    }

    /**
     * Tests the {@link StudentFactory#getStudent(String, String, Date)} method to verify
     * it throws a {@link NullPointerException} when the date of birth is null.
     */
    @Test
    public void testGetStudent_NullDateOfBirth_ThrowsException() {
        // Assert
        assertThrows(NullPointerException.class, () -> {
            StudentFactory.getStudent(firstName, lastName, null);  // Test null date of birth
        });
    }
}

