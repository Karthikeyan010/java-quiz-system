import com.quizsystem.factory.StudentFactory;
import com.quizsystem.implementations.QuizImpl;
import com.quizsystem.interfaces.Question;
import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;
import com.quizsystem.models.Student;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link QuizImpl} class.
 *
 * This class contains test cases for the functionalities of the Quiz implementation,
 * including quiz generation, taking quizzes, revision, and statistics generation.
 * It verifies that the quiz behaves as expected under various conditions, including
 * error handling and correct outcomes for valid inputs.
 */
public class QuizImplTest {

    private QuizImpl quiz;
    private List<Question> questionPool;
    private Student student;

    /**
     * Sets up the test environment before each test case.
     * Initializes a sample question pool with predefined questions and creates
     * a {@link Student} instance for testing.
     */
    @BeforeEach
    public void setUp() {
        // Prepare a sample question pool
        questionPool = new ArrayList<>();
        questionPool.add(MultipleChoiceQuestion.valueOf("What is 2+2?",
                new HashSet<>(Arrays.asList("3", "4", "5")),
                new HashSet<>(Collections.singletonList("4"))));
        // Adding another set of questions to ensure we have enough for testing
        questionPool.add(MultipleChoiceQuestion.valueOf("What is 3+3?",
                new HashSet<>(Arrays.asList("5", "6", "7")),
                new HashSet<>(Collections.singletonList("6")))); // Another Multiple Choice Question
        questionPool.add(FreeResponseQuestion.valueOf("What is the capital of France?", "Paris"));
        student = StudentFactory.getStudent("Appu", "Karthik", new Date());
        quiz = new QuizImpl(questionPool);
    }

    /**
     * Resets the test environment after each test case.
     * Nullifies instances to ensure no state is shared between tests.
     */
    @AfterEach
    public void tearDown() {
        // Reset or nullify instances to ensure no state is shared between tests
        quiz = null;
        student = null;
        questionPool = null;
    }

    /**
     * Tests the constructor of {@link QuizImpl} to ensure it throws an
     * {@link IllegalArgumentException} when a null question pool is provided.
     */
    @Test
    public void testConstructor_ThrowsExceptionForNullPool() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuizImpl(null);
        });
    }

    /**
     * Tests the constructor of {@link QuizImpl} to ensure it throws an
     * {@link IllegalArgumentException} when an empty question pool is provided.
     */
    @Test
    public void testConstructor_ThrowsExceptionForEmptyPool() {
        assertThrows(IllegalArgumentException.class, () -> {
            new QuizImpl(Collections.emptyList());
        });
    }

    /**
     * Tests the {@link QuizImpl#generateQuiz} method to ensure it throws an
     * {@link IllegalArgumentException} when a negative question count is specified.
     */
    @Test
    public void testGenerateQuiz_ThrowsExceptionForNegativeQuestionCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            quiz.generateQuiz(-1, student);
        });
    }

    /**
     * Tests the {@link QuizImpl#generateQuiz} method to ensure it throws an
     * {@link IllegalArgumentException} when the requested question count exceeds
     * the available questions in the pool.
     */
    @Test
    public void testGenerateQuiz_ThrowsExceptionForExceedingQuestionCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            quiz.generateQuiz(5, student);
        });
    }

    /**
     * Tests the {@link QuizImpl#generateQuiz} method to verify that it returns
     * the correct number of questions as requested.
     */
    @Test
    public void testGenerateQuiz_ReturnsCorrectNumberOfQuestions() {
        List<Question> generatedQuiz = quiz.generateQuiz(2, student);
        assertEquals(2, generatedQuiz.size());
    }

    /**
     * Tests the {@link QuizImpl#generateQuiz} method to ensure that the generated
     * quiz includes both multiple choice and free response questions.
     */
    @Test
    public void testGenerateQuiz_IncludesBothQuestionTypes() {
        // Ensure we have at least one of each type in the pool
        questionPool.add(MultipleChoiceQuestion.valueOf("What is 3+3?",
                new HashSet<>(Arrays.asList("5", "6", "7")),
                new HashSet<>(Collections.singletonList("6"))));

        List<Question> generatedQuiz = quiz.generateQuiz(2, student);
        boolean hasMultipleChoice = generatedQuiz.stream().anyMatch(q -> q instanceof MultipleChoiceQuestion);
        boolean hasFreeResponse = generatedQuiz.stream().anyMatch(q -> q instanceof FreeResponseQuestion);

        assertTrue(hasMultipleChoice);
        assertTrue(hasFreeResponse);
    }

    /**
     * Tests the {@link QuizImpl#takeQuiz} method to ensure it throws an
     * {@link IllegalStateException} if the student has already passed the quiz.
     */
    @Test
    public void testTakeQuiz_ThrowsExceptionIfStudentPassed() {
        student.recordQuizResult(60, 2); // Simulate passing
        assertThrows(IllegalStateException.class, () -> {
            quiz.takeQuiz(student, Collections.singletonList(questionPool.get(0)), Collections.singletonList(Arrays.asList("4")));
        });
    }

    /**
     * Tests the {@link QuizImpl#takeRevisionQuiz} method to ensure it throws an
     * {@link IllegalStateException} if the student has reached the maximum number
     * of revision attempts.
     */
    @Test
    public void testTakeRevisionQuiz_ThrowsExceptionIfMaxRevisionsReached() {
        student.incrementRevisionAttempts();
        student.incrementRevisionAttempts(); // Assume max is reached
        assertThrows(IllegalStateException.class, () -> {
            quiz.takeRevisionQuiz(student, Collections.singletonList(questionPool.get(0)), Collections.singletonList(Arrays.asList("4")));
        });
    }

    /**
     * Tests the {@link QuizImpl#revise} method to ensure it throws an
     * {@link IllegalStateException} if the student has reached the maximum number
     * of revision attempts.
     */
    @Test
    public void testRevise_ThrowsExceptionIfMaxRevisionsReached() {
        student.incrementRevisionAttempts();
        student.incrementRevisionAttempts(); // Assume max is reached
        assertThrows(IllegalStateException.class, () -> {
            quiz.revise(student, 1);
        });
    }

    /**
     * Tests the {@link QuizImpl#revise} method to ensure it returns a quiz
     * with the correct number of questions for revision.
     */
    @Test
    public void testRevise_ReturnsQuiz() {
        List<Question> revisedQuiz = quiz.revise(student, 2);
        assertNotNull(revisedQuiz);
        assertEquals(2, revisedQuiz.size());
    }

    /**
     * Tests the {@link QuizImpl#generateStatistics} method to verify that it
     * returns the correct statistics for a student's quiz attempts.
     */
    @Test
    public void testGenerateStatistics_ReturnsCorrectStatistics() {
        student.recordQuizResult(80, 1);
        Map<String, Object> stats = quiz.generateStatistics(student);

        // Since quizScores is a list, we need to check the size and the content of the list
        List<Double> scores = (List<Double>) stats.get("quizScores");
        assertEquals(1, scores.size()); // Check that there's one score recorded
        assertEquals(80.0, scores.get(0)); // Check the first (and only) score
        assertEquals(1, stats.get("attempts")); // Check the attempts
    }
}

