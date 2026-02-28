package com.quizsystem.interfaces;

import java.util.Set;

/**
 * The {@code Question} interface defines the contract for different types of
 * questions in a quiz system. It provides methods to retrieve the question text
 * and validate answers against the correct answer(s).
 */
public interface Question {

    /**
     * Gets the question text.
     *
     * @return the question as a {@code String}
     */
    String getQuestion();

    /**
     * Checks if a single answer is correct.
     *
     * @param answer the answer to check
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    boolean isCorrectAnswer(String answer);

    /**
     * Checks if multiple answers are correct.
     *
     * @param answers the set of answers to check
     * @return {@code true} if all answers are correct, {@code false} otherwise
     */
    boolean isCorrectAnswer(Set<String> answers);
}



