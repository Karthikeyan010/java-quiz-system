


import com.quizsystem.implementations.StatisticsProviderImpl;
import com.quizsystem.interfaces.StatisticsProvider;
import com.quizsystem.models.Student;
import com.quizsystem.models.Verdict;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Test class for {@link StatisticsProviderImpl}.
 * This class contains unit tests to verify the functionality of the statistics provider
 * that calculates and provides statistics for a list of students based on their quiz results.
 */
public class StatisticsProviderImplTest {

    private List<Student> students; // List of sample students for testing
    private StatisticsProvider statisticsProvider; // Instance of the statistics provider being tested

    /**
     * Sets up the test environment by initializing a list of students with sample quiz results
     * and creating an instance of {@link StatisticsProviderImpl}.
     */
    @Before
    public void setUp() {
        students = new ArrayList<>();

        // Create sample students
        Student student1 = Student.valueOf("John", "Doe", new Date(102, 5, 30));  // Date format: year-1900, month-1, day
        Student student2 = Student.valueOf("Jane", "Smith", new Date(103, 3, 15));
        Student student3 = Student.valueOf("Mark", "Johnson", new Date(101, 10, 10));

        // Simulate quiz results
        student1.recordQuizResult(8, 10);  // 80% score (PASS)
        student2.recordQuizResult(4, 10);  // 40% score (FAIL)

        student3.recordQuizResult(6, 10);  // 60% score (PASS)

        // Simulate some failed attempts
        student2.setFinalVerdict(Verdict.FAIL);

        // Add students to the list
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // Initialize the StatisticsProviderImpl with the list of students
        statisticsProvider = new StatisticsProviderImpl(students);
    }

    /**
     * Tests the {@link StatisticsProviderImpl#getAverageScore()} method to ensure
     * it calculates the correct average score for all students.
     */
    @Test
    public void testGetAverageScore() {
        // Calculate expected average
        double expectedAverage = (8 + 4 + 6) / 3.0;
        double actualAverage = statisticsProvider.getAverageScore();

        assertEquals("Average score should match the expected value.", expectedAverage, actualAverage, 0.01);
    }

    /**
     * Tests the {@link StatisticsProviderImpl#getNumberOfStudentsPassed()} method to verify
     * it returns the correct number of students who passed.
     */
    @Test
    public void testGetNumberOfStudentsPassed() {
        long passedStudents = statisticsProvider.getNumberOfStudentsPassed();
        assertEquals("There should be 2 students who passed.", 2, passedStudents);
    }

    /**
     * Tests the {@link StatisticsProviderImpl#getNumberOfStudentsFailed()} method to verify
     * it returns the correct number of students who failed.
     */
    @Test
    public void testGetNumberOfStudentsFailed() {
        long failedStudents = statisticsProvider.getNumberOfStudentsFailed();
        assertEquals("There should be 1 student who failed.", 1, failedStudents);
    }

    /**
     * Tests the {@link StatisticsProviderImpl#getFailedStudents()} method to ensure it returns
     * the correct list of failed students, verifying their names and counts.
     */
    @Test
    public void testGetFailedStudents() {
        List<Student> failedStudents = statisticsProvider.getFailedStudents();
        assertEquals("There should be 1 failed student.", 1, failedStudents.size());
        assertEquals("The failed student should be Jane Smith.", "Jane Smith", failedStudents.get(0).getFullName());
    }

    /**
     * Tests the {@link StatisticsProviderImpl#generateStatistics(Student)} method to verify
     * it generates the correct statistics for a specific student.
     */
    @Test
    public void testGenerateStatisticsForStudent() {
        Student student = students.get(0); // Get the first student (John Doe)
        Map<String, Object> stats = statisticsProvider.generateStatistics(student);

        // Verify the statistics map
        assertNotNull("Statistics map should not be null.", stats);
        assertEquals("Quiz attempts should match.", student.getQuizAttempts(), stats.get("attempts"));
        assertEquals("Revision attempts should match.", student.getRevisionAttempts(), stats.get("revisions"));
        assertEquals("Failed attempts should match.", student.getFailedAttempts(), stats.get("failedAttempts"));
        assertEquals("Verdict should match.", student.getFinalVerdict(), stats.get("verdict"));
    }
}

