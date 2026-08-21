package com.quizsystem.interfaces;

import java.util.Set;

/**
 * The interfaces package contains the contracts of the system.
 * For example, Question defines what every question type should support,
 * QuizGenerator defines the quiz generation and evaluation behaviour, and
 * StatisticsProvider defines the statistics-related operations.
 * This helps me program against interfaces instead of concrete classes.
 *
 * The {@code Question} interface defines the contract for different types of
 * questions in a quiz system. It provides methods to retrieve the question text
 * and validate answers against the correct answer(s).-->
 * I used an interface to keep the system flexible and loosely coupled. For example,
 * today I have MultipleChoiceQuestion and FreeResponseQuestion,
 * but in the future I can add TrueFalseQuestion or FillInTheBlankQuestion without
 * changing the quiz generation logic. As long as the new class implements the Question interface,
 * the rest of the system can work with it.
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



