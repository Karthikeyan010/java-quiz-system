package com.quizsystem.models;

import com.quizsystem.interfaces.Question;
import com.quizsystem.models.Verdict;


import java.util.*;


/**
 * Represents a student with personal details and academic performance metrics.
 * The student has a first name, last name, date of birth, and tracks quiz attempts,
 * scores, and revision attempts. It also keeps track of correctly answered questions
 * and allows for caching of student instances.
 */

public class Student {
    private final String firstName; // First name of the student
    private final String lastName; // Last name of the student
    private final Date dateOfBirth; // Date of birth of the student
    private static final double PASSING_SCORE = 0.5; // Fixed passing score (50%)
    public static final int MAX_REVISIONS = 2; // Fixed maximum revisions
    private Verdict finalVerdict; // Final verdict (PASS, FAIL, TBD)
    private int quizAttempts; // Number of quiz attempts
    private int failedAttempts; // Number of failed attempts
    private int revisionAttempts; // Number of revision attempts
    private final List<Double> quizScores; // List to store quiz scores
    private final List<Double> revisionScores; // List to store revision scores
    private List<Question> correctlyAnsweredQuestions = new ArrayList<>(); // List of correctly answered questions
    private static final Map<String, Student> studentCache = new HashMap<>(); // Cache to store students
    private final Set<Question> attemptedQuestions;// Set to track attempted questions





    /**
     * Private constructor for the Student class.
     *
     * @param firstName   the first name of the student
     * @param lastName    the last name of the student
     * @param dateOfBirth the date of birth of the student
     */
    private Student(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Date(dateOfBirth.getTime()); // Ensure immutability
        //Because Date is mutable. If I directly stored or returned the original Date, another class could modify it and change the student’s identity. Defensive copying protects the internal state.

        this.finalVerdict = Verdict.TBD; // Initial verdict
        this.quizAttempts = 0;
        this.failedAttempts = 0;
        this.revisionAttempts = 0;
        this.quizScores = new ArrayList<>();
        this.revisionScores = new ArrayList<>();
        this.attemptedQuestions = new HashSet<>();
    }

    /**
     * Factory method to create or retrieve a cached Student object.
     *
     * @param firstName   the first name of the student
     * @param lastName    the last name of the student
     * @param dateOfBirth the date of birth of the student
     * @return a Student instance, either new or from the cache
     */
    public static Student valueOf(String firstName, String lastName, Date dateOfBirth) {
        if (firstName == null || firstName.isBlank() ||
                lastName == null || lastName.isBlank() ||
                dateOfBirth == null) {
            throw new IllegalArgumentException("Student details cannot be null or blank");
        }
        String key = firstName + lastName + dateOfBirth.getTime(); // Unique key for cache

        return studentCache.computeIfAbsent(key, k -> new Student(firstName, lastName, dateOfBirth));
    }

    /**
     * Adds a correctly answered question to the student's record.
     *
     * @param question the question answered correctly
     */
    public void addCorrectQuestion(Question question) {
        if (!correctlyAnsweredQuestions.contains(question)) {
            correctlyAnsweredQuestions.add(question);
        }
    }

    /**
     * Checks if the student has answered a specific question correctly.
     *
     * @param question the question to check
     * @return true if answered correctly, false otherwise
     */
    public boolean hasAnsweredCorrectly(Question question) {
        return correctlyAnsweredQuestions.contains(question);
    }

    /**
     * Clears the student cache.
     */
    public static void clearCache() {
        studentCache.clear();
    }



    /**
     * Records attempted questions for the student.
     *
     * @param questions a list of questions attempted
     */
    public void recordAttemptedQuestions(List<Question> questions) {
        attemptedQuestions.addAll(questions); // Add questions to the set
    }



    /**
     * Retrieves the set of attempted questions.
     *
     * @return a copy of the set of attempted questions
     */
    public Set<Question> getAttemptedQuestions() {
        return new HashSet<>(attemptedQuestions); // Return a copy for immutability
    }



    /**
     * Retrieves the passing score.
     *
     * @return the passing score
     */
    public static double getPassingScore() {
        return PASSING_SCORE;
    }



    /**
     * Checks if the student has passed.
     *
     * @return true if the student has passed, false otherwise
     */
    public boolean isPassed() {
        return finalVerdict == Verdict.PASS;
    }



    /**
     * Checks if the student has failed.
     *
     * @return true if the student has failed, false otherwise
     */
    public boolean hasFailed() {
        return finalVerdict == Verdict.FAIL;
    }



    /**
     * Records the result of a quiz taken by the student.
     *
     * @param score          the score obtained by the student
     * @param totalQuestions the total number of questions in the quiz
     */
    public void recordQuizResult(double score, int totalQuestions) {
        double percentageScore = score / totalQuestions;

        if (percentageScore >= PASSING_SCORE) {
            this.finalVerdict = Verdict.PASS;
        } else {
            // Do not increment failed attempts yet
            if (revisionAttempts >= MAX_REVISIONS) {
                this.finalVerdict = Verdict.FAIL; // Fail if max revisions are exhausted
                failedAttempts++; // Increment failed attempts only when failing
            } else {
                this.finalVerdict = Verdict.TBD; // Set to TBD if revisions are allowed
            }
        }

        quizAttempts++; // Increment quiz attempts
        quizScores.add(score); // Record the quiz score
    }



    /**
     * Checks if the student has reached the maximum revision attempts.
     *
     * @return true if the maximum revisions have been reached, false otherwise
     */
    public boolean hasReachedMaxRevisions() {
        return revisionAttempts >= MAX_REVISIONS;
    }



    /**
     * Retrieves the final verdict for the student.
     *
     * @return the final verdict
     */
    public Verdict getFinalVerdict() {
        return finalVerdict;
    }



    /**
     * Retrieves the number of quiz attempts made by the student.
     *
     * @return the number of quiz attempts
     */
    public int getQuizAttempts() {
        return quizAttempts;
    }



    /**
     * Retrieves the number of revision attempts made by the student.
     *
     * @return the number of revision attempts
     */
    public int getRevisionAttempts() {
        return revisionAttempts;
    }



    /**
     * Retrieves the list of quiz scores for the student.
     *
     * @return a copy of the list of quiz scores
     */
    public List<Double> getQuizScores() {
        return new ArrayList<>(quizScores); // Return a copy for immutability
    }



    /**
     * Retrieves the list of revision scores for the student.
     *
     * @return a copy of the list of revision scores
     */
    public List<Double> getRevisionScores() {
        return new ArrayList<>(revisionScores); // Return a copy for immutability
    }



    /**
     * Retrieves the full name of the student.
     *
     * @return the full name as a string
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }



    /**
     * Retrieves the date of birth of the student.
     *
     * @return a copy of the date of birth
     */
    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime()); // Return a copy for immutability
    }



    /**
     * Increments the number of revision attempts made by the student.
     *
     * @throws IllegalStateException if the maximum number of revisions has been reached
     */
    public void incrementRevisionAttempts() {
        if (revisionAttempts >= MAX_REVISIONS) {
            throw new IllegalStateException("Cannot increment beyond the maximum number of revisions.");
        }
        revisionAttempts++; // Increment the revision attempts count
    }



    /**
     * Clears all quiz scores for the student.
     */
    public void clearQuizScores() {
        quizScores.clear(); // Clears the quiz score list
    }



    /**
     * Resets the student's revision attempts back to zero.
     */
    public void resetRevisionAttempts() {
        this.revisionAttempts = 0; // Resets revision attempts back to 0
    }



    /**
     * Retrieves the number of Failed attempts made by the student.
     *
     * @return the number of Failed attempts
     */
    public int getFailedAttempts() {
        return this.failedAttempts;
    }



    /**
     * Sets the final verdict for the student.
     *
     * @param verdict the final verdict to set
     */
    public void setFinalVerdict(Verdict verdict) {
        this.finalVerdict = verdict;
    }



    /**
     * Retrieves the first name of the student.
     *
     * @return the first name of the student
     */
    public String getFirstName() {
        return this.firstName;
    }



    /**
     * Retrieves the last name of the student.
     *
     * @return the last name of the student
     */
    public String getLastName() {
        return this.lastName;
    }



    /**
     * Compares this student to the specified object for equality.
     *
     * @param o the object to compare this student against
     * @return true if the specified object is equal to this student; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if both references point to the same object
        if (!(o instanceof Student)) return false; // Check if the object is an instance of Student
        Student student = (Student) o; // Cast the object to Student
        return firstName.equals(student.firstName) && // Compare first names
                lastName.equals(student.lastName) &&   // Compare last names
                dateOfBirth.equals(student.dateOfBirth); // Compare dates of birth
    }



    /**
     * Returns a hash code value for the student based on first name, last name, and date of birth.
     *
     * @return a hash code value for this student
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }




    /**
     * Returns a string representation of the student, including their details.
     *
     * @return a string representing the student
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", finalVerdict=" + finalVerdict +
                ", quizAttempts=" + quizAttempts +
                ", failedAttempts=" + failedAttempts +
                ", revisionAttempts=" + revisionAttempts +
                '}'; // Include all relevant fields in the string representation
    }

}




