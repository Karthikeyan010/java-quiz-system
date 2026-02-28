import com.quizsystem.models.FreeResponseQuestion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class for the {@link FreeResponseQuestion} class.
 * This class contains unit tests to verify the functionality of FreeResponseQuestion methods.
 */
public class FreeResponseQuestionTest {

    private FreeResponseQuestion question;

    /**
     * Sets up the test environment before each test.
     * Initializes a FreeResponseQuestion instance for testing.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the question and correct answer
        question = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");
    }

    /**
     * Cleans up the test environment after each test.
     * Clears the question cache if necessary.
     */
    @AfterEach
    public void tearDown() {
        // Clear the question cache to avoid side effects
        FreeResponseQuestion.clearCache();
    }

    /**
     * Tests the creation of a new FreeResponseQuestion.
     * Asserts that a question is created with the expected text and correct answer.
     */
    @Test
    public void testValueOf_CreatesNewQuestion() {
        // Act
        FreeResponseQuestion newQuestion = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");

        // Assert
        assertNotNull(newQuestion);
        assertEquals("What is the capital of France?", newQuestion.getQuestion());
        assertTrue(newQuestion.isCorrectAnswer("Paris"), "The answer 'Paris' should be recognized as correct.");
    }

    /**
     * Tests that the valueOf method returns the same instance for the same question and answer.
     * Asserts that the questions are cached properly.
     */
    @Test
    public void testValueOf_UsesCacheForSameQuestion() {
        // Act
        FreeResponseQuestion firstCall = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");
        FreeResponseQuestion secondCall = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");

        // Assert
        assertSame(firstCall, secondCall, "Both calls should return the same instance due to caching.");
    }

    /**
     * Tests the isCorrectAnswer method with a correct answer.
     * Asserts that the correct answer returns true.
     */
    @Test
    public void testIsCorrectAnswer_CorrectAnswer() {
        // Act
        boolean result = question.isCorrectAnswer("Paris");

        // Assert
        assertTrue(result, "The answer 'Paris' should be recognized as correct.");
    }

    /**
     * Tests the isCorrectAnswer method with an incorrect answer.
     * Asserts that an incorrect answer returns false.
     */
    @Test
    public void testIsCorrectAnswer_IncorrectAnswer() {
        // Act
        boolean result = question.isCorrectAnswer("London");

        // Assert
        assertFalse(result, "The answer 'London' should not be recognized as correct.");
    }



    /**
     * Tests the isCorrectAnswer method with a set of answers.
     * Asserts that calling this method throws an UnsupportedOperationException.
     */
    @Test
    public void testIsCorrectAnswer_SetOfAnswers() {
        // Arrange
        Set<String> answers = new HashSet<>();
        answers.add("Paris");

        // Act & Assert
        assertThrows(UnsupportedOperationException.class, () -> question.isCorrectAnswer(answers),
                "FreeResponseQuestion does not support multiple answers.");
    }

    /**
     * Tests the equals method for equality between two FreeResponseQuestion objects.
     * Asserts that two questions with the same text and answer are equal.
     */
    @Test
    public void testEquals_SameQuestionAndAnswer() {
        // Arrange
        FreeResponseQuestion anotherQuestion = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");

        // Act & Assert
        assertEquals(question, anotherQuestion, "Questions with the same text and answer should be equal.");
    }

    /**
     * Tests the equals method for inequality between two FreeResponseQuestion objects with different answers.
     * Asserts that two questions with different answers are not equal.
     */
    @Test
    public void testEquals_DifferentAnswers() {
        // Arrange
        FreeResponseQuestion anotherQuestion = FreeResponseQuestion.valueOf("What is the capital of France?", "London");

        // Act & Assert
        assertNotEquals(question, anotherQuestion, "Questions with different answers should not be equal.");
    }

    /**
     * Tests the hashCode method for consistent hashing.
     * Asserts that two questions with the same text and answer produce the same hash code.
     */
    @Test
    public void testHashCode_SameQuestionAndAnswer() {
        // Arrange
        FreeResponseQuestion anotherQuestion = FreeResponseQuestion.valueOf("What is the capital of France?", "Paris");

        // Act & Assert
        assertEquals(question.hashCode(), anotherQuestion.hashCode(), "Hash codes should be equal for equal questions.");
    }

    /**
     * Tests the toString method for proper string representation.
     * Asserts that the string representation contains the question text and correct answer.
     */
    @Test
    public void testToString() {
        // Act
        String result = question.toString();

        // Assert
        assertTrue(result.contains("What is the capital of France?"), "String representation should contain the question text.");
        assertTrue(result.contains("Paris"), "String representation should contain the correct answer.");
    }
}
