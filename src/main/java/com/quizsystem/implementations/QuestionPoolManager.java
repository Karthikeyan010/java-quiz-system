package com.quizsystem.implementations;

import com.quizsystem.factory.QuestionFactory;
import com.quizsystem.interfaces.Question;
import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The implementations package contains the actual business logic.
 * QuestionPoolManager manages the pool of available questions,
 * QuizImpl handles quiz generation, answer evaluation, revision handling, and statistics flow,
 * and StatisticsProviderImpl provides the implementation for generating student statistics.
 *
 * The {@code QuestionPoolManager} class manages a pool of questions used in quizzes.
 * It allows adding different types of questions, retrieving the question pool,
 * removing specific questions, and clearing the entire pool. This class follows
 * the singleton design pattern to ensure that there is only one instance managing
 * the question pool throughout the application.
 */
public class QuestionPoolManager {
    private static QuestionPoolManager instance; // Singleton instance
    private final List<Question> questionPool; // List to store questions

    /**
     * Private constructor for the QuestionPoolManager class.
     * Initializes the question pool.
     */
    private QuestionPoolManager() {
        this.questionPool = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of QuestionPoolManager.
     *
     * @return the singleton instance of QuestionPoolManager
     */
    public static synchronized QuestionPoolManager getInstance() {
        if (instance == null) {
            instance = new QuestionPoolManager();
            // Initialize with default questions
            instance.initializeDefaultQuestions();
        }
        return instance;
    }

    /**
     * Initializes the question pool with default questions.
     */
    private void initializeDefaultQuestions() {
        // Add default MultipleChoiceQuestion
        questionPool.add(MultipleChoiceQuestion.valueOf("What is 2 + 2?", Set.of("4", "3", "5"), Collections.singleton("4")));
        // Add default FreeResponseQuestion
        questionPool.add(FreeResponseQuestion.valueOf("What is the capital of France?", "Paris"));
    }

    /**
     * Adds a FreeResponseQuestion to the question pool.
     *
     * @param questionText the text of the question
     * @param correctAnswer the correct answer to the question
     * @throws IllegalArgumentException if any argument is null
     */
    public void addFreeResponseQuestion(String questionText, String correctAnswer) {
        if (questionText == null || correctAnswer == null) {
            throw new IllegalArgumentException("Question text and correct answer cannot be null.");
        }
        FreeResponseQuestion freeResponseQuestion = QuestionFactory.createFreeResponseQuestion(questionText, correctAnswer);
        questionPool.add(freeResponseQuestion);
    }

    /**
     * Adds a MultipleChoiceQuestion to the question pool.
     *
     * @param questionText the text of the question
     * @param correctAnswers the set of correct answers
     * @param options the set of possible options for the question
     * @throws IllegalArgumentException if any argument is null
     */
    public void addMultipleChoiceQuestion(String questionText, Set<String> correctAnswers, Set<String> options) {
        if (questionText == null || correctAnswers == null || options == null) {
            throw new IllegalArgumentException("Question text, options, and correct answers cannot be null.");
        }
        MultipleChoiceQuestion multipleChoiceQuestion = QuestionFactory.createMultipleChoiceQuestion(questionText, correctAnswers, options);
        questionPool.add(multipleChoiceQuestion);
    }

    /**
     * Retrieves the entire question pool.
     *
     * @return a list of questions in the pool
     */
    public List<Question> getQuestionPool() {
        return new ArrayList<>(questionPool); // Return a copy for immutability
    }

    /**
     * Removes a specific question from the pool.
     *
     * @param question the question to remove
     * @throws IllegalArgumentException if the question is null
     */
    public void removeQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null.");
        }
        questionPool.remove(question);
    }

    /**
     * Clears the entire question pool.
     */
    public void clearPool() {
        questionPool.clear();
    }
}
