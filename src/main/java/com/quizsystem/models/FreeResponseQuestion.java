package com.quizsystem.models;

import com.quizsystem.interfaces.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;




/**
 * The models package contains the core domain objects of the system.
 * These are the main entities, such as Student, FreeResponseQuestion,
 * MultipleChoiceQuestion, and Verdict. These classes represent the actual
 * data and behaviour used in the quiz system.
 *
 * Represents a free response question with a question text and a correct answer.
 * This class implements the {@link Question} interface and provides methods
 * to create, retrieve, and validate free response questions.
 */
public class FreeResponseQuestion implements Question {
    private final String question; // The question text
    private final String correctAnswer; // The correct answer for the question
    private static final Map<String, FreeResponseQuestion> questionCache = new HashMap<>();// used static Map as a cahe , so if the same free response question is created again, the system can return the existing object instead of creating a duplicate
// if the system became multi-threaded, I would replace HashMap with ConcurrentHashMap for the cache forr thread safety
    /**
     * Constructor for FreeResponseQuestion.
     *
     * @param question      the question text
     * @param correctAnswer the correct answer to the question
     * @throws IllegalArgumentException if the question or answer is null
     */
    private FreeResponseQuestion(String question, String correctAnswer) {
        if (question == null || correctAnswer == null) {
            throw new IllegalArgumentException("Question and answer cannot be null");
        }
        this.question = question.trim();
        this.correctAnswer = correctAnswer.trim();
    }

    /**
     * Factory method to create or retrieve a FreeResponseQuestion.
     *
     * @param question      the question text
     * @param correctAnswer the correct answer to the question
     * @return an existing or new FreeResponseQuestion instance
     * @throws IllegalArgumentException if the question or answer is null
     */
    public static FreeResponseQuestion valueOf(String question, String correctAnswer) {
        if (question == null || correctAnswer == null) {
            throw new IllegalArgumentException("Question and answer cannot be null");
        }
        // Create a unique key based on question and correct answer
        String key = question.trim() + "|" + correctAnswer.trim();

        // Check if the question already exists in the cache
        if (questionCache.containsKey(key)) {
            return questionCache.get(key); // Return the existing question
        } else {
            FreeResponseQuestion newQuestion = new FreeResponseQuestion(question, correctAnswer);
            questionCache.put(key, newQuestion);
            return newQuestion;
        }  // Create a new question and add to the cache

    }

    /**
     * Clears the question cache.
     */
    public static void clearCache() {
        questionCache.clear();
    }

    @Override
/**
 * Retrieves the text of the question.
 *
 * @return the question text
 */
    public String getQuestion() {
        return question; // Return the question text
    }

    @Override
/**
 * Checks if the provided answer is correct.
 *
 * @param answer the answer to be checked
 * @return true if the answer is correct, false otherwise
 */
    public boolean isCorrectAnswer(String answer) {
        if (answer == null) {
            return false;
        }
        // Case-insensitive comparison
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }

    @Override
/**
 * Checks if the provided set of answers is correct.
 *
 * @param answers the set of answers to be checked
 * @throws UnsupportedOperationException if called, as free response questions do not support multiple answers
 */
    public boolean isCorrectAnswer(Set<String> answers) {
        // Free response questions do not support multiple correct answers; return false.
        throw new UnsupportedOperationException("FreeResponseQuestion does not support multiple answers.");
    }


    @Override
/**
 * Checks if this FreeResponseQuestion is equal to another object.
 *
 * @param o the object to compare with this FreeResponseQuestion
 * @return true if the object is a FreeResponseQuestion with the same question and correct answer; false otherwise
 */
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (!(o instanceof FreeResponseQuestion)) return false; // Check if the object is an instance of FreeResponseQuestion
        FreeResponseQuestion that = (FreeResponseQuestion) o; // Cast to FreeResponseQuestion
        return question.equals(that.question) && correctAnswer.equals(that.correctAnswer); // Compare question and correct answer
    }


    @Override
/**
 * Returns a hash code value for this FreeResponseQuestion.
 *
 * The hash code is generated based on the question text and the correct answer.
 *
 * @return a hash code value for this FreeResponseQuestion
 */
    public int hashCode() {
        return Objects.hash(question, correctAnswer); // Generate hash code based on question and correct answer
    }


    @Override
/**
 * Returns a string representation of this FreeResponseQuestion.
 *
 * The string representation includes the question text and the correct answer.
 *
 * @return a string representation of the FreeResponseQuestion
 */
    public String toString() {
        return "FreeResponseQuestion{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}'; // Format the string representation of the question and answer
    }

}
