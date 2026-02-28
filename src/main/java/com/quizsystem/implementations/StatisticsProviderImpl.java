package com.quizsystem.implementations;



import com.quizsystem.interfaces.StatisticsProvider;
import com.quizsystem.models.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code StatisticsProviderImpl} class implements the {@link StatisticsProvider}
 * interface and provides statistical analysis for a list of {@link Student} objects.
 *
 * <p>This class computes various statistics such as average scores,
 * number of students who passed or failed, and generates statistics for
 * individual students.</p>
 */
public class StatisticsProviderImpl implements StatisticsProvider {

    private final List<Student> students;

    /**
     * Constructor for {@code StatisticsProviderImpl}.
     *
     * @param students the list of students whose statistics are to be computed
     */
    public StatisticsProviderImpl(List<Student> students) {
        this.students = students;
    }

    /**
     * Calculates the average score of all quizzes taken by all students.
     *
     * @return the average quiz score
     */
    @Override
    public double getAverageScore() {
        return students.stream()
                .flatMap(student -> student.getQuizScores().stream())
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0); // Return 0 if no scores are available
    }

    /**
     * Gets the number of students who have passed.
     *
     * @return the number of students with a passing verdict
     */
    @Override
    public long getNumberOfStudentsPassed() {
        return students.stream()
                .filter(Student::isPassed) // Using isPassed method from Student class
                .count();
    }

    /**
     * Gets the number of students who have failed.
     *
     * @return the number of students with a failing verdict
     */
    @Override
    public long getNumberOfStudentsFailed() {
        return students.stream()
                .filter(Student::hasFailed) // Using hasFailed method from Student class
                .count();
    }

    /**
     * Gets a list of students who have failed.
     *
     * @return a list of students with a failing verdict
     */
    @Override
    public List<Student> getFailedStudents() {
        return students.stream()
                .filter(Student::hasFailed)
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of students who have reached the maximum number of revisions allowed.
     *
     * @return a list of students who have maxed out their revision attempts
     */
    @Override
    public List<Student> getStudentsWithMaxRevisions() {
        return students.stream()
                .filter(Student::hasReachedMaxRevisions)
                .collect(Collectors.toList());
    }

    /**
     * Generates and returns a map of statistics related to a specific student.
     *
     * @param student the student whose statistics are being generated
     * @return a map containing statistics like quiz scores and attempts
     */
    @Override
    public Map<String, Object> generateStatistics(Student student) {
        return Map.of(
                "quizScores", student.getQuizScores(),
                "attempts", student.getQuizAttempts(),
                "revisions", student.getRevisionAttempts(),
                "failedAttempts", student.getFailedAttempts(),
                "verdict", student.getFinalVerdict()
        );
    }
}


