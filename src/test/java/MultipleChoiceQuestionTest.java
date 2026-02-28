import com.quizsystem.models.MultipleChoiceQuestion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link MultipleChoiceQuestion} class.
 * This class contains unit tests to verify the functionality of MultipleChoiceQuestion methods.
 */
public class MultipleChoiceQuestionTest {

    private MultipleChoiceQuestion question;

    /**
     * Sets up the test environment before each test.
     * Initializes a MultipleChoiceQuestion instance for testing.
     */
    @BeforeEach
    public void setUp() {
        // Create a set of options
        Set<String> options = new HashSet<>();
        options.add("Option A");
        options.add("Option B");
        options.add("Option C");
        options.add("Option D");

        // Create a set of correct answers
        Set<String> correctAnswers = new HashSet<>();
        correctAnswers.add("Option A");
        correctAnswers.add("Option B");

        // Create a MultipleChoiceQuestion instance for testing
        question = MultipleChoiceQuestion.valueOf("Which options are correct?", options, correctAnswers);
    }

    /**
     * Cleans up the test environment after each test.
     * No specific cleanup required for this test class.
     */
    @AfterEach
    public void tearDown() {
        // No specific cleanup required
    }

    /**
     * Tests the creation of a new MultipleChoiceQuestion.
     * Asserts that the question is created with the expected text, options, and correct answers.
     */
    @Test
    public void testValueOf_CreatesNewQuestion() {
        // Arrange
        Set<String> options = new HashSet<>();
        options.add("Option A");
        options.add("Option B");
        options.add("Option C");
        options.add("Option D");
        Set<String> correctAnswers = new HashSet<>();
        correctAnswers.add("Option A");
        correctAnswers.add("Option B");

        // Act
        MultipleChoiceQuestion newQuestion = MultipleChoiceQuestion.valueOf("Which options are correct?", options, correctAnswers);

        // Assert
        assertNotNull(newQuestion, "The new question should not be null.");
        assertEquals("Which options are correct?", newQuestion.getQuestion(), "The question text should match.");
        assertEquals(options, newQuestion.getOptions(), "The options should match the provided options.");
        assertEquals(correctAnswers, newQuestion.getCorrectAnswers(), "The correct answers should match the provided correct answers.");
    }

    /**
     * Tests the isCorrectAnswer method with a single correct answer.
     * Asserts that the correct answer returns true.
     */
    @Test
    public void testIsCorrectAnswer_SingleCorrectAnswer() {
        // Act
        boolean result = question.isCorrectAnswer("Option A");

        // Assert
        assertTrue(result, "The answer 'Option A' should be recognized as correct.");
    }

    /**
     * Tests the isCorrectAnswer method with a single incorrect answer.
     * Asserts that an incorrect answer returns false.
     */
    @Test
    public void testIsCorrectAnswer_SingleIncorrectAnswer() {
        // Act
        boolean result = question.isCorrectAnswer("Option C");

        // Assert
        assertFalse(result, "The answer 'Option C' should not be recognized as correct.");
    }

    /**
     * Tests the isCorrectAnswer method with multiple correct answers.
     * Asserts that matching answers return true.
     */
    @Test
    public void testIsCorrectAnswer_MultipleCorrectAnswers() {
        // Arrange
        Set<String> answers = new HashSet<>();
        answers.add("Option A");
        answers.add("Option B");

        // Act
        boolean result = question.isCorrectAnswer(answers);

        // Assert
        assertTrue(result, "The answers should match the correct answers exactly.");
    }

    /**
     * Tests the isCorrectAnswer method with a set of answers that do not match.
     * Asserts that mismatched answers return false.
     */
    @Test
    public void testIsCorrectAnswer_IncorrectMultipleAnswers() {
        // Arrange
        Set<String> answers = new HashSet<>();
        answers.add("Option A");
        answers.add("Option C");

        // Act
        boolean result = question.isCorrectAnswer(answers);

        // Assert
        assertFalse(result, "The answers should not match the correct answers exactly.");
    }

    /**
     * Tests the creation of a MultipleChoiceQuestion with null inputs.
     * Asserts that IllegalArgumentException is thrown for null question text.
     */
    @Test
    public void testValueOf_NullQuestionText() {
        // Arrange
        Set<String> options = new HashSet<>();
        options.add("Option A");
        Set<String> correctAnswers = new HashSet<>();
        correctAnswers.add("Option A");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MultipleChoiceQuestion.valueOf(null, options, correctAnswers);
        });
        assertEquals("Question text, options, and correct answers cannot be null", exception.getMessage());
    }

    /**
     * Tests the creation of a MultipleChoiceQuestion with empty options.
     * Asserts that IllegalArgumentException is thrown for empty options.
     */
    @Test
    public void testValueOf_EmptyOptions() {
        // Arrange
        Set<String> correctAnswers = new HashSet<>();
        correctAnswers.add("Option A");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MultipleChoiceQuestion.valueOf("Sample question", new HashSet<>(), correctAnswers);
        });
        assertEquals("Options and correct answers cannot be empty", exception.getMessage());
    }

    /**
     * Tests the creation of a MultipleChoiceQuestion with correct answers not in options.
     * Asserts that IllegalArgumentException is thrown when correct answers are not a subset of options.
     */
    @Test
    public void testValueOf_CorrectAnswersNotInOptions() {
        // Arrange
        Set<String> options = new HashSet<>();
        options.add("Option A");
        options.add("Option B");

        Set<String> correctAnswers = new HashSet<>();
        correctAnswers.add("Option C"); // Not in options

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            MultipleChoiceQuestion.valueOf("Sample question", options, correctAnswers);
        });
        assertEquals("Correct answers must be a subset of options", exception.getMessage());
    }
}
