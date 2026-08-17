

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Student;

public class StudentTest {
    @Test
    void shouldCreateStudentUsingBuilder() {
        Student student = new Student.Builder()
                .groupNumber(101)
                .averageScore(4.5)
                .idGradeBook(12345)
                .build();

        assertEquals(101, student.getGroupNumber());
        assertEquals(4.5, student.getAverageScore());
        assertEquals(12345, student.getIdGradeBook());
    }

    @Test
    void shouldRejectInvalidGroupNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Student.Builder()
                        .groupNumber(0)
                        .averageScore(4.5)
                        .idGradeBook(12345)
                        .build()
        );
    }

    @Test
    void shouldRejectInvalidAverageScore() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Student.Builder()
                        .groupNumber(101)
                        .averageScore(6.0)
                        .idGradeBook(12345)
                        .build()
        );
    }
    
    @Test
    void shouldRejectInvalidIdGradeBook() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Student.Builder()
                        .groupNumber(101)
                        .averageScore(4.5)
                        .idGradeBook(0)
                        .build()
        );
    }
    
    @Test
    void shouldRejectNegativeAverageScore() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Student.Builder()
                        .groupNumber(101)
                        .averageScore(-1.0)
                        .idGradeBook(12345)
                        .build()
        );
    }
}
