package com.quizsystem.models;

import com.quizsystem.interfaces.Question;

import java.util.Set;

/**
 * Represents a multiple-choice question with a question text, possible options, and correct answers.
 * This class implements the {@link Question} interface and provides methods to create, retrieve,
 * and validate multiple-choice questions.
 */
public class MultipleChoiceQuestion implements Question {
    private String questionText; // The text of the question
    private Set<String> options; // Possible options
    private Set<String> correctAnswers; // The correct answer(s)

    /**
     * Constructor for MultipleChoiceQuestion.
     *
     * @param questionText the text of the question
     * @param options      the possible options for the question
     * @param correctAnswers the correct answer(s) for the question
     */
    private MultipleChoiceQuestion(String questionText, Set<String> options, Set<String> correctAnswers) {
        this.questionText = questionText;
        this.options = options;// Set.copyOf(options);
        this.correctAnswers = correctAnswers;//Set.copyOf(correctAnswers);
    }

    /**
     * Factory method to create a MultipleChoiceQuestion.
     *
     * @param questionText  the text of the question
     * @param options       the possible options for the question
     * @param correctAnswers the correct answer(s) for the question
     * @return a new instance of MultipleChoiceQuestion
     * @throws IllegalArgumentException if inputs are invalid
     */
    public static MultipleChoiceQuestion valueOf(String questionText, Set<String> options, Set<String> correctAnswers) {
        // Validate inputs
        if (questionText == null || options == null || correctAnswers == null) {
            throw new IllegalArgumentException("Question text, options, and correct answers cannot be null");
        }
        if (options.isEmpty() || correctAnswers.isEmpty()) {
            throw new IllegalArgumentException("Options and correct answers cannot be empty");
        }
        if (!options.containsAll(correctAnswers)) {
            throw new IllegalArgumentException("Correct answers must be a subset of options");
        }

        // Create and return a new instance
        return new MultipleChoiceQuestion(questionText.trim(), options, correctAnswers);
    }

    @Override
    /**
     * Retrieves the text of the question.
     *
     * @return the question text
     */
    public String getQuestion() {
        return questionText;
    }

    /**
     * Checks if a single answer is correct.
     *
     * @param answer the answer to check
     * @return true if the answer is correct, false otherwise
     */
    @Override
    public boolean isCorrectAnswer(String answer) {
        return correctAnswers.contains(answer);
    }

    /**
     * Checks if multiple answers are correct.
     *
     * @param answers the set of answers to check
     * @return true if the answers match the correct answers exactly, false otherwise
     */
    @Override
    public boolean isCorrectAnswer(Set<String> answers) {
        return correctAnswers.equals(answers); // All selected answers must match exactly with the correct answers
    }

    /**
     * Retrieves the possible options for the question.
     *
     * @return the set of options
     */
    public Set<String> getOptions() {
        return options;
    }

    /**
     * Retrieves the correct answers for the question.
     *
     * @return the set of correct answers
     */
    public Set<String> getCorrectAnswers() {
        return correctAnswers;//Set.copOf(correctAnswers)
    }
}

