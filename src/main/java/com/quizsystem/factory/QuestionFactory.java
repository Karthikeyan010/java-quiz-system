package com.quizsystem.factory;

import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;

import java.util.Set;

/**
 * The {@code QuestionFactory} class provides factory methods for creating
 * instances of {@link FreeResponseQuestion} and {@link MultipleChoiceQuestion}.
 *
 * <p>This class encapsulates the logic for creating question instances,
 * ensuring that all necessary parameters are provided and correctly handled.</p>
 */
public class QuestionFactory {

    // Since this class has only static members I add a private constructor to prevent creating unnessary QuestionFactory object
    private QuestionFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Factory method to create a {@link FreeResponseQuestion}.
     *
     * @param question      The text of the question.
     * @param correctAnswer The correct answer for the question.
     * @return A new instance of {@link FreeResponseQuestion} with the specified
     *         question text and correct answer.
     * @throws IllegalArgumentException if either {@code question} or
     *         {@code correctAnswer} is {@code null} or empty.
     */
    public static FreeResponseQuestion createFreeResponseQuestion(String question, String correctAnswer) {
        return FreeResponseQuestion.valueOf(question, correctAnswer);
    }

    /**
     * Factory method to create a {@link MultipleChoiceQuestion}.
     *
     * @param questionText   The text of the question.
     * @param options        A set of possible answer options.
     * @param correctAnswers A set of correct answers for the question.
     * @return A new instance of {@link MultipleChoiceQuestion} with the
     *         specified question text, options, and correct answers.
     * @throws IllegalArgumentException if {@code questionText} is {@code null}
     *         or empty, or if {@code options} or {@code correctAnswers} is
     *         {@code null} or empty.
     */
    public static MultipleChoiceQuestion createMultipleChoiceQuestion(String questionText, Set<String> options, Set<String> correctAnswers) {
        return MultipleChoiceQuestion.valueOf(questionText, options, correctAnswers);
    }
}

