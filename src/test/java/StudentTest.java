



import com.quizsystem.factory.QuestionFactory;
import com.quizsystem.interfaces.Question;
import com.quizsystem.models.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.quizsystem.models.*;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Test class for {@link Student}.
 * This class contains unit tests to verify the functionality of the {@link Student} class,
 * including its methods for recording quiz results, managing questions, and handling student state.
 */
public class StudentTest {

    private Student student; // The Student instance used for testing
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Date format for parsing birth dates

    /**
     * Sets up the test environment by creating a new Student instance before each test.
     *
     * @throws ParseException if the birth date cannot be parsed
     */
    @BeforeEach
    public void setUp() throws ParseException {
        // Create a new Student instance for each test
        Date birthDate = dateFormat.parse("30/06/2002");
        student = Student.valueOf("John", "Doe", birthDate);
    }

    /**
     * Cleans up the test environment by clearing the student cache after each test.
     */
    @AfterEach
    public void tearDown() {
        // Clear the student cache after each test
        Student.clearCache();
    }

    /**
     * Tests the creation of a Student instance to ensure all attributes are set correctly.
     */
    @Test
    public void testStudentCreation() {
        assertNotNull(student);
        assertEquals("John Doe", student.getFullName());
        assertEquals(dateFormat.format(student.getDateOfBirth()), "30/06/2002");
        assertEquals(Student.getPassingScore(), 0.5);
    }

    /**
     * Tests adding a correct question to the student and verifies the student's response.
     */
    @Test
    public void testAddCorrectQuestion() {
        Question question = QuestionFactory.createFreeResponseQuestion("What is the capital of France?", "Paris");
        student.addCorrectQuestion(question);
        assertTrue(student.hasAnsweredCorrectly(question));
    }

    /**
     * Tests the functionality of checking if the student has answered a question correctly.
     */
    @Test
    public void testHasAnsweredCorrectly() {
        Question question = QuestionFactory.createFreeResponseQuestion("What is the capital of France?", "Paris");
        assertFalse(student.hasAnsweredCorrectly(question));
        student.addCorrectQuestion(question);
        assertTrue(student.hasAnsweredCorrectly(question));
    }

    /**
     * Tests recording a quiz result for a passing score and verifies the final verdict and attempt counts.
     */
    @Test
    public void testRecordQuizResultPass() {
        student.recordQuizResult(8, 10); // 80%
        assertEquals(Verdict.PASS, student.getFinalVerdict());
        assertEquals(1, student.getQuizAttempts());
        assertEquals(0, student.getFailedAttempts());
    }

    /**
     * Tests recording a quiz result for a failing score after reaching the maximum revision attempts.
     */
    @Test
    public void testRecordQuizResultFail() {
        // Simulate reaching the maximum revision attempts
        for (int i = 0; i < Student.MAX_REVISIONS; i++) {
            student.incrementRevisionAttempts();
        }

        // Record a failing quiz result
        student.recordQuizResult(3, 10); // 30%

        // Assertions
        assertEquals(Verdict.FAIL, student.getFinalVerdict(), "The final verdict should be FAIL after maximum revisions are exhausted.");
        assertEquals(1, student.getQuizAttempts(), "Quiz attempts should be incremented.");
        assertEquals(1, student.getFailedAttempts(), "Failed attempts should be incremented.");
    }

    /**
     * Tests recording a quiz result for an undecided score (TBD) and verifies the final verdict.
     */
    @Test
    public void testRecordQuizResultTBD() {
        student.recordQuizResult(4, 10); // 40%
        assertEquals(Verdict.TBD, student.getFinalVerdict());
        assertEquals(1, student.getQuizAttempts());
        assertEquals(0, student.getFailedAttempts());
    }

    /**
     * Tests the functionality of incrementing revision attempts for the student.
     */
    @Test
    public void testIncrementRevisionAttempts() {
        student.incrementRevisionAttempts();
        assertEquals(1, student.getRevisionAttempts());
    }

    /**
     * Tests checking if the student has reached the maximum revision attempts and verifies exception handling.
     */
    @Test
    public void testHasReachedMaxRevisions() {
        for (int i = 0; i < 2; i++) {
            student.incrementRevisionAttempts();
        }
        assertTrue(student.hasReachedMaxRevisions());
        assertThrows(IllegalStateException.class, student::incrementRevisionAttempts);
    }

    /**
     * Tests clearing the quiz scores of the student and verifies that the scores list is empty.
     */
    @Test
    public void testClearQuizScores() {
        student.recordQuizResult(8, 10); // 80%
        student.clearQuizScores();
        assertTrue(student.getQuizScores().isEmpty());
    }

    /**
     * Tests resetting the revision attempts of the student and verifies the count is reset to zero.
     */
    @Test
    public void testResetRevisionAttempts() {
        student.incrementRevisionAttempts();
        assertEquals(1, student.getRevisionAttempts());
        student.resetRevisionAttempts();
        assertEquals(0, student.getRevisionAttempts());
    }

    /**
     * Tests the caching functionality of the Student class to ensure that identical students are the same instance.
     */
    @Test
    public void testCacheFunctionality() {
        // Create the original student with a specific date of birth
        Date birthDate = new Date(102, 5, 30); // This corresponds to June 30, 2002
        Student originalStudent = Student.valueOf("John", "Doe", birthDate);

        // Now create another student with the same details
        Student anotherStudent = Student.valueOf("John", "Doe", birthDate);

        assertSame(originalStudent, anotherStudent, "Students should be the same instance due to caching");
    }
}

