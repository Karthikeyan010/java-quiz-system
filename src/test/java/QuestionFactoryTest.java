import com.quizsystem.factory.QuestionFactory;
import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link QuestionFactory} class.
 *
 * This class contains test cases for creating instances of different question types,
 * specifically {@link FreeResponseQuestion} and {@link MultipleChoiceQuestion}.
 * It verifies the correctness of the created questions and ensures that appropriate
 * exceptions are thrown for invalid inputs.
 */
public class QuestionFactoryTest {

    /**
     * Sets up the test environment before each test case.
     * Currently, no specific setup is required.
     */
    @BeforeEach
    public void setUp() {
        // Any setup if needed (currently not required)
    }

    /**
     * Tests the creation of a {@link FreeResponseQuestion}.
     *
     * This test verifies that a free response question is created correctly with the
     * provided text and answer. It checks that the question is not null and that the
     * question text matches the expected value.
     */
    @Test
    public void testCreateFreeResponseQuestion_ReturnsCorrectFreeResponseQuestion() {
        // Arrange
        String questionText = "What is the capital of Italy?";
        String correctAnswer = "Rome";

        // Act
        FreeResponseQuestion freeResponseQuestion = QuestionFactory.createFreeResponseQuestion(questionText, correctAnswer);

        // Assert
        assertNotNull(freeResponseQuestion);  // Ensure the question is not null
        assertEquals(questionText, freeResponseQuestion.getQuestion());  // Check if question text is correct
        // We're not verifying the correct answer explicitly as per your requirements
    }

    /**
     * Tests the creation of a {@link MultipleChoiceQuestion}.
     *
     * This test verifies that a multiple choice question is created correctly with the
     * provided text, options, and correct answers. It checks that the question is not
     * null, that the question text matches the expected value, and that the options are
     * set correctly.
     */
    @Test
    public void testCreateMultipleChoiceQuestion_ReturnsCorrectMultipleChoiceQuestion() {
        // Arrange
        String questionText = "What is 5 + 5?";
        Set<String> options = Set.of("9", "10", "11");
        Set<String> correctAnswers = Set.of("10");

        // Act
        MultipleChoiceQuestion multipleChoiceQuestion = QuestionFactory.createMultipleChoiceQuestion(questionText, options, correctAnswers);

        // Assert
        assertNotNull(multipleChoiceQuestion);  // Ensure the question is not null
        assertEquals(questionText, multipleChoiceQuestion.getQuestion());  // Check if question text is correct
        assertEquals(3, multipleChoiceQuestion.getOptions().size());  // Check if the options are set correctly
        assertTrue(multipleChoiceQuestion.getOptions().containsAll(options));  // Check if all options are present
        // We're not verifying the correct answers explicitly
    }

    /**
     * Tests the creation of a {@link MultipleChoiceQuestion} with empty options.
     *
     * This test verifies that an {@link IllegalArgumentException} is thrown when trying
     * to create a multiple choice question with an empty set of options.
     */
    @Test
    public void testCreateMultipleChoiceQuestion_EmptyOptions_ThrowsException() {
        // Arrange
        String questionText = "What is 2 + 2?";
        Set<String> options = Set.of();  // Empty options
        Set<String> correctAnswers = Set.of("4");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.createMultipleChoiceQuestion(questionText, options, correctAnswers);
        });
    }

    /**
     * Tests the creation of a {@link MultipleChoiceQuestion} with empty correct answers.
     *
     * This test verifies that an {@link IllegalArgumentException} is thrown when trying
     * to create a multiple choice question with an empty set of correct answers.
     */
    @Test
    public void testCreateMultipleChoiceQuestion_EmptyCorrectAnswers_ThrowsException() {
        // Arrange
        String questionText = "What is 2 + 2?";
        Set<String> options = Set.of("1", "2", "3", "4");
        Set<String> correctAnswers = Set.of();  // Empty correct answers

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.createMultipleChoiceQuestion(questionText, options, correctAnswers);
        });
    }

    /**
     * Tests the creation of a {@link FreeResponseQuestion} with a null question.
     *
     * This test verifies that an {@link IllegalArgumentException} is thrown when trying
     * to create a free response question with a null question text.
     */
    @Test
    public void testCreateFreeResponseQuestion_NullQuestion_ThrowsException() {
        // Arrange
        String correctAnswer = "Answer";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.createFreeResponseQuestion(null, correctAnswer);
        });
    }

    /**
     * Tests the creation of a {@link FreeResponseQuestion} with a null answer.
     *
     * This test verifies that an {@link IllegalArgumentException} is thrown when trying
     * to create a free response question with a null answer.
     */
    @Test
    public void testCreateFreeResponseQuestion_NullAnswer_ThrowsException() {
        // Arrange
        String questionText = "What is the question?";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            QuestionFactory.createFreeResponseQuestion(questionText, null);
        });
    }
}
