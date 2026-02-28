import com.quizsystem.factory.QuestionFactory;
import com.quizsystem.implementations.QuestionPoolManager;
import com.quizsystem.interfaces.Question;
import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link QuestionPoolManager} class.
 *
 * This class contains test cases for managing a pool of questions, including adding,
 * retrieving, removing, and clearing questions. It verifies that the question pool
 * behaves as expected and that predefined questions are present.
 */
public class QuestionPoolManagerTest {

    private QuestionPoolManager questionPoolManager;
    List<Question> questionPool;

    /**
     * Sets up the test environment before each test case.
     * Initializes the {@link QuestionPoolManager} instance and retrieves the
     * current question pool.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the QuestionPoolManager before each test
        questionPoolManager = QuestionPoolManager.getInstance();
        questionPool = questionPoolManager.getQuestionPool();
    }

    /**
     * Tests the addition of a {@link FreeResponseQuestion} to the question pool.
     *
     * This test verifies that a free response question is added correctly to the
     * question pool. It checks that the question pool size increases and that the
     * added question is of the correct type and contains the expected question text.
     */
    @Test
    public void testAddFreeResponseQuestion() {
        // Retrieve the singleton instance of QuestionPoolManager
        QuestionPoolManager questionPoolManager = QuestionPoolManager.getInstance();

        // Clear any existing questions before running the test to ensure isolation
        questionPoolManager.clearPool();

        // Add a FreeResponseQuestion
        String questionText = "What is the capital of Germany?";
        String correctAnswer = "Berlin";

        questionPoolManager.addFreeResponseQuestion(questionText, correctAnswer);

        // Retrieve the updated question pool
        List<Question> questionPool = questionPoolManager.getQuestionPool();

        // Assert that the question was added correctly
        assertEquals(1, questionPool.size(), "The question pool should have 1 question after adding a new one.");
        assertTrue(questionPool.get(0) instanceof FreeResponseQuestion);

        FreeResponseQuestion freeResponseQuestion = (FreeResponseQuestion) questionPool.get(0);
        assertEquals(questionText, freeResponseQuestion.getQuestion());
        assertNotNull(freeResponseQuestion);  // Not checking the correct answer explicitly
    }


    /**
     * Tests the addition of a {@link MultipleChoiceQuestion} to the question pool.
     *
     * This test verifies that a multiple choice question is added correctly to the
     * question pool. It checks that the question pool size increases and that the
     * added question is of the correct type and contains the expected question text.
     */
    @Test
    public void testAddMultipleChoiceQuestion() {
        // Retrieve the singleton instance of QuestionPoolManager
        QuestionPoolManager questionPoolManager = QuestionPoolManager.getInstance();

        // Clear any existing questions before running the test to ensure isolation
        questionPoolManager.clearPool();

        // Add a MultipleChoiceQuestion
        String questionText = "What is 2 + 2?";
        Set<String> correctAnswers = new HashSet<>(Collections.singletonList("4"));
        Set<String> options = new HashSet<>(Arrays.asList("3", "4", "5"));

        questionPoolManager.addMultipleChoiceQuestion(questionText, options, correctAnswers);

        // Retrieve the updated question pool
        List<Question> questionPool = questionPoolManager.getQuestionPool();

        // Assert that the question was added correctly
        assertEquals(1, questionPool.size(), "The question pool should have 1 question after adding a new one.");
        assertTrue(questionPool.get(0) instanceof MultipleChoiceQuestion);

        MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) questionPool.get(0);
        assertEquals(questionText, multipleChoiceQuestion.getQuestion());
        assertNotNull(multipleChoiceQuestion);
    }



    /**
     * Tests the retrieval of predefined questions from the question pool.
     *
     * This test verifies that the question pool contains the expected number of
     * predefined questions and checks the correctness of their contents.
     */
    @Test
    public void testGetQuestionPool_ReturnsPredefinedQuestions() {
        // Get the predefined questions from the pool
        List<Question> questionPool = this.questionPool;

        // Assert that the predefined questions are correct
        assertEquals(2, questionPool.size());

        // Check the first question (MultipleChoiceQuestion)
        assertTrue(questionPool.get(0) instanceof MultipleChoiceQuestion);
        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) questionPool.get(0);
        assertEquals("What is 2 + 2?", mcq.getQuestion());
        assertTrue(mcq.getOptions().containsAll(Arrays.asList("4", "3", "5")));

        // Check the second question (FreeResponseQuestion)
        assertTrue(questionPool.get(1) instanceof FreeResponseQuestion);
        FreeResponseQuestion frq = (FreeResponseQuestion) questionPool.get(1);
        assertEquals("What is the capital of France?", frq.getQuestion());
    }

    /**
     * Tests the removal of a question from the question pool.
     *
     * This test verifies that a question can be added and subsequently removed
     * from the question pool, ensuring that the question pool reflects the
     * removal correctly.
     */
    @Test
    public void testRemoveQuestion() {
        // Add a question and then remove it
        String questionText = "What is the capital of Japan?";
        String correctAnswer = "Tokyo";
        FreeResponseQuestion question = QuestionFactory.createFreeResponseQuestion(questionText, correctAnswer);
        questionPoolManager.addFreeResponseQuestion(questionText, correctAnswer);

        questionPoolManager.removeQuestion(question);

        // Assert that the question was removed
        List<Question> questionPool = this.questionPool;
        assertFalse(questionPool.contains(question));
    }

    /**
     * Tests clearing the question pool.
     *
     * This test verifies that the question pool can be cleared, ensuring that
     * all questions are removed and the pool is empty afterward.
     */
    @Test
    public void testClearPool() {
        // Retrieve the singleton instance of QuestionPoolManager
        QuestionPoolManager questionPoolManager = QuestionPoolManager.getInstance();

        // Add a question to ensure there's something in the pool
        questionPoolManager.addFreeResponseQuestion("What is the capital of Italy?", "Rome");

        // Clear the pool
        questionPoolManager.clearPool();

        // Retrieve the updated question pool from the singleton
        List<Question> questionPool = questionPoolManager.getQuestionPool();

        // Assert that the pool is empty
        assertTrue(questionPool.isEmpty(), "The question pool should be empty after clearing it.");
    }

}

