package com.quizsystem.interfaces;

import com.quizsystem.models.Student;

import java.util.List;
import java.util.Map;

/**
 * The {@code QuizGenerator} interface defines the methods for generating and
 * managing quizzes for students. It provides functionalities to create quizzes,
 * evaluate student performance, and generate statistics based on quiz attempts.
 */
public interface QuizGenerator {

    /**
     * Generates a quiz with a specified number of questions for a given student.
     *
     * @param numberOfQuestions the number of questions to include in the quiz
     * @param student           the student taking the quiz
     * @return a list of {@link Question} for the quiz
     * @throws IllegalArgumentException if the number of questions is non-positive
     *                                  or exceeds the available questions.
     */
    List<Question> generateQuiz(int numberOfQuestions, Student student);

    /**
     * Allows a student to take a quiz and returns their score.
     *
     * @param student   the student taking the quiz
     * @param questions the list of questions for the quiz
     * @param answers   the list of answers provided by the student
     * @return the score achieved by the student as a {@code double}
     * @throws IllegalStateException if the student has already passed the quiz
     * @throws IllegalArgumentException if the questions or answers are null or
     *                                  if their sizes do not match.
     */
    double takeQuiz(Student student, List<Question> questions, List<List<String>> answers);

    /**
     * Allows a student to take a revision quiz.
     *
     * @param student   the student taking the revision quiz
     * @param questions the list of questions for the revision quiz
     * @param answers   the list of answers provided by the student
     * @return the score achieved by the student as a {@code double}
     * @throws IllegalStateException if the student has reached the maximum number of revisions.
     */
    double takeRevisionQuiz(Student student, List<Question> questions, List<List<String>> answers);

    /**
     * Provides new quiz questions for a student to revise.
     *
     * @param student           the student revising
     * @param numberOfQuestions the number of questions to include in the revision
     * @return a list of {@link Question} for the revision
     * @throws IllegalStateException if the student has reached the maximum number of revisions.
     */
    List<Question> revise(Student student, int numberOfQuestions);

    /**
     * Generates statistics for a student based on their quiz performance.
     *
     * @param student the student for whom statistics are generated
     * @return a map containing statistics related to the student's quiz performance
     * @throws IllegalArgumentException if the student is null.
     */
    Map<String, Object> generateStatistics(Student student);
}


