import collections.StudentCollection;
import counter.StudentOccurrenceCounter;
import model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentOccurrenceCounterTest {

    @Test
    void shouldReturnZeroForEmptyCollection() {
        StudentOccurrenceCounter counter = new StudentOccurrenceCounter();

        int result = counter.count(new StudentCollection(), 5);

        assertEquals(0, result);
    }

    @Test
    void shouldFindNumberInDifferentColumns() {
        StudentOccurrenceCounter counter = new StudentOccurrenceCounter();

        StudentCollection students = new StudentCollection(List.of(
                createStudent(101, 5.0, 1001),
                createStudent(5, 3.5, 1003),
                createStudent(90, 2.0, 5),
                createStudent(101, 4.5, 1004),
                createStudent(102, 3.5, 1005)
        ));

        int result = counter.count(students, 5);

        assertEquals(3, result);
    }

    @Test
    void shouldReturnZeroWhenNumberIsNotFound() {
        StudentOccurrenceCounter counter = new StudentOccurrenceCounter();

        StudentCollection students = new StudentCollection(List.of(
                createStudent(101, 5.0, 1001),
                createStudent(5, 3.5, 1003),
                createStudent(90, 2.0, 5),
                createStudent(101, 4.5, 1004),
                createStudent(102, 3.5, 1005)
        ));

        int result = counter.count(students, 999);

        assertEquals(0, result);
    }

    @Test
    void shouldFindMultipleOccurrencesInSameColumn() {
        StudentOccurrenceCounter counter = new StudentOccurrenceCounter();

        StudentCollection students = new StudentCollection(List.of(
                createStudent(101, 5.0, 1001),
                createStudent(5, 3.5, 1003),
                createStudent(90, 2.0, 5),
                createStudent(101, 4.5, 1004),
                createStudent(102, 3.5, 1005)
        ));

        int result = counter.count(students, 3.5);

        assertEquals(2, result);
    }

    private Student createStudent(
            int groupNumber,
            double averageScore,
            int idGradeBook
    ) {
        return new Student.Builder()
                .groupNumber(groupNumber)
                .averageScore(averageScore)
                .idGradeBook(idGradeBook)
                .build();
    }
}