package com.quizsystem.implementations;

import com.quizsystem.interfaces.Question;
import com.quizsystem.interfaces.QuizGenerator;
import com.quizsystem.models.FreeResponseQuestion;
import com.quizsystem.models.MultipleChoiceQuestion;
import com.quizsystem.models.Student;

import java.util.*;

/**
 * The {@code QuizImpl} class implements the {@code QuizGenerator} interface
 * and provides functionality to generate quizzes, evaluate answers, and
 * manage quiz statistics for students.
 */
public class QuizImpl implements QuizGenerator {
    // Pool of available questions
    public List<Question> questionPool = QuestionPoolManager.getInstance().getQuestionPool();

    /**
     * Constructs a {@code QuizImpl} instance with the specified question pool.
     *
     * @param questionPool a list of questions to be used for generating quizzes
     * @throws IllegalArgumentException if the question pool is null or empty
     */
    public QuizImpl(List<Question> questionPool) {
        if (questionPool == null || questionPool.isEmpty()) {
            throw new IllegalArgumentException("Question pool cannot be null or empty.");
        }
        this.questionPool = new ArrayList<>(questionPool); // Make a copy for safety
    }

    /**
     * Generates a quiz with a specified number of questions for a student.
     *
     * @param numberOfQuestions the number of questions to include in the quiz
     * @param student the student for whom the quiz is generated
     * @return a list of selected questions for the quiz
     * @throws IllegalArgumentException if the number of questions is less than or equal to zero
     *                                  or exceeds the number of questions in the pool
     * @throws IllegalStateException if the quiz cannot contain both free response and multiple choice questions
     */
    @Override
    public List<Question> generateQuiz(int numberOfQuestions, Student student) {
        if (numberOfQuestions <= 0) {
            throw new IllegalArgumentException("Number of questions must be positive.");
        }
        if (numberOfQuestions > questionPool.size()) {
            throw new IllegalArgumentException("Not enough questions in the pool to generate the quiz.");
        }

        // Shuffle the question pool to ensure randomness
        Collections.shuffle(questionPool);

        Set<Question> selectedQuestions = new LinkedHashSet<>();
        for (Question question : questionPool) {
            // Ensure the student hasn't answered this question correctly before
            if (selectedQuestions.size() < numberOfQuestions &&
                    !student.hasAnsweredCorrectly(question)) {
                selectedQuestions.add(question);
            }
        }

        // Check the current composition of the selected questions
        boolean hasFreeResponse = selectedQuestions.stream()
                .anyMatch(q -> q instanceof FreeResponseQuestion);
        boolean hasMultipleChoice = selectedQuestions.stream()
                .anyMatch(q -> q instanceof MultipleChoiceQuestion);

        // If not both types are present, replace a majority type question
        if (!hasFreeResponse || !hasMultipleChoice) {
            // Determine the majority type
            Question typeToRemove = null;
            for (Question question : selectedQuestions) {
                if (hasFreeResponse && question instanceof FreeResponseQuestion) {
                    typeToRemove = question; // This is a FreeResponse question
                } else if (hasMultipleChoice && question instanceof MultipleChoiceQuestion) {
                    typeToRemove = question; // This is a MultipleChoice question
                }
            }

            // Remove the selected majority type question
            if (typeToRemove != null) {
                selectedQuestions.remove(typeToRemove);
            }

            // Add a new question of the opposite type
            for (Question question : questionPool) {
                if (selectedQuestions.size() < numberOfQuestions && !selectedQuestions.contains(question) &&
                        !student.hasAnsweredCorrectly(question)) {
                    if (typeToRemove instanceof FreeResponseQuestion && question instanceof MultipleChoiceQuestion) {
                        selectedQuestions.add(question);
                    } else if (typeToRemove instanceof MultipleChoiceQuestion && question instanceof FreeResponseQuestion) {
                        selectedQuestions.add(question);
                    }
                }
            }

            // Check again to ensure both types are now present
            hasFreeResponse = selectedQuestions.stream().anyMatch(q -> q instanceof FreeResponseQuestion);
            hasMultipleChoice = selectedQuestions.stream().anyMatch(q -> q instanceof MultipleChoiceQuestion);

            if (!hasFreeResponse || !hasMultipleChoice) {
                throw new IllegalStateException("Quiz must contain both free response and multiple choice questions.");
            }
        }

        return new ArrayList<>(selectedQuestions); // Return as a List
    }

    /**
     * Evaluates the quiz taken by a student and records the result.
     *
     * @param student the student taking the quiz
     * @param questions the list of questions in the quiz
     * @param answers the list of answers provided by the student
     * @return the score achieved by the student as a percentage
     * @throws IllegalStateException if the student has already passed the quiz
     */
    @Override
    public double takeQuiz(Student student, List<Question> questions, List<List<String>> answers) {
        if (student.isPassed()) {
            throw new IllegalStateException("Student has already passed the quiz. No further attempts are allowed.");
        }
        validateQuizEligibility(student, questions, answers);

        int correctCount = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            List<String> givenAnswers = answers.get(i);

            if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
                if (mcQuestion.isCorrectAnswer(new HashSet<>(givenAnswers))) {
                    correctCount++;
                    student.addCorrectQuestion(question); // Add the question to correctly answered
                }
            } else if (question instanceof FreeResponseQuestion) {
                if (question.isCorrectAnswer(givenAnswers.get(0))) {
                    correctCount++;
                    student.addCorrectQuestion(question); // Add the question to correctly answered
                }
            }
        }

        double score = (double) correctCount / questions.size() * 100; // Percentage score
        student.recordQuizResult(score, questions.size()); // Record result in student

        // Update the verdict
        if (score >= 50) {
            student.recordQuizResult(score, questions.size());
        } else {
            student.recordQuizResult(score, questions.size());
        }

        return score;
    }

    /**
     * Evaluates a revision quiz taken by a student and records the result.
     *
     * @param student the student taking the revision quiz
     * @param questions the list of questions in the revision quiz
     * @param answers the list of answers provided by the student
     * @return the score achieved by the student as a percentage
     * @throws IllegalStateException if the student has reached the maximum number of revisions
     */
    @Override
    public double takeRevisionQuiz(Student student, List<Question> questions, List<List<String>> answers) {
        if (student.hasReachedMaxRevisions()) {
            throw new IllegalStateException("Student has reached the maximum number of revisions.");
        }

        double score = takeQuiz(student, questions, answers); // Reuse takeQuiz logic
        student.incrementRevisionAttempts(); // Ensure revision attempts are tracked separately
        return score;
    }

    /**
     * Generates a revised quiz for a student with a specified number of questions.
     *
     * @param student the student for whom the revision quiz is generated
     * @param numberOfQuestions the number of questions to include in the revision quiz
     * @return a list of selected questions for the revision quiz
     * @throws IllegalStateException if the student has reached the maximum number of revisions
     */
    @Override
    public List<Question> revise(Student student, int numberOfQuestions) {
        if (student.hasReachedMaxRevisions()) {
            throw new IllegalStateException("Student has reached the maximum number of revisions.");
        }

        return generateQuiz(numberOfQuestions, student); // Generate a revised quiz
    }

    /**
     * Generates statistics for a student based on their quiz performance.
     *
     * @param student the student for whom the statistics are generated
     * @return a map containing various statistics, such as final verdict, attempts, and scores
     */
    @Override
    public Map<String, Object> generateStatistics(Student student) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("finalVerdict", student.getFinalVerdict());
        stats.put("attempts", student.getQuizAttempts());
        stats.put("revisions", student.getRevisionAttempts());
        stats.put("quizScores", student.getQuizScores());
        stats.put("revisionScores", student.getRevisionScores()); // Added revision scores
        return stats;
    }

    /**
     * Validates the eligibility of a student to take a quiz based on provided questions and answers.
     *
     * @param student the student taking the quiz
     * @param questions the list of questions in the quiz
     * @param answers the list of answers provided by the student
     * @throws IllegalArgumentException if questions or answers are null or if their sizes do not match
     */
    private void validateQuizEligibility(Student student, List<Question> questions, List<List<String>> answers) throws IllegalArgumentException {
        if (questions == null || answers == null) {
            throw new IllegalArgumentException("Questions and answers cannot be null.");
        }

        if (questions.size() != answers.size()) {
            throw new IllegalArgumentException("The number of answers must match the number of questions.");
        }
    }
}

