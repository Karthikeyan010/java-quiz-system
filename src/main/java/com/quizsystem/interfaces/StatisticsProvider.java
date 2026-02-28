package com.quizsystem.interfaces;



import com.quizsystem.models.Student;

import java.util.List;
import java.util.Map;

/**
 * The {@code StatisticsProvider} interface defines methods for providing
 * statistics related to students' quiz performance. It allows for the
 * calculation of average scores, tracking pass and fail counts, and
 * retrieving statistics for individual students.
 */
public interface StatisticsProvider {

    /**
     * Calculates the average score of all quizzes taken by all students.
     *
     * @return the average quiz score as a {@code double}. Returns 0.0 if
     *         no quizzes have been taken.
     */
    double getAverageScore();

    /**
     * Gets the number of students who have passed.
     *
     * @return the number of students with a passing verdict as a {@code long}.
     */
    long getNumberOfStudentsPassed();

    /**
     * Gets the number of students who have failed.
     *
     * @return the number of students with a failing verdict as a {@code long}.
     */
    long getNumberOfStudentsFailed();

    /**
     * Gets a list of students who have failed.
     *
     * @return a list of {@link Student} objects with a failing verdict.
     */
    List<Student> getFailedStudents();

    /**
     * Gets a list of students who have reached the maximum number of
     * revisions allowed.
     *
     * @return a list of {@link Student} objects who have maxed out
     *         their revision attempts.
     */
    List<Student> getStudentsWithMaxRevisions();

    /**
     * Generates and returns a map of statistics related to a specific
     * student.
     *
     * @param student the student whose statistics are being generated
     * @return a map containing statistics like quiz scores, attempts,
     *         and revisions for the specified student
     * @throws NullPointerException if the provided student is null.
     */
    Map<String, Object> generateStatistics(Student student);
}

