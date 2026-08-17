package sort;

import collections.StudentCollection;
import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SortStrategyTest {

    @Test
    void shouldSortEmptyCollection() {
        StudentCollection students = new StudentCollection();

        new AllAscending().sort(students);

        assertTrue(students.isEmpty());
    }

    @Test
    void shouldSortCollectionWithOneStudent() {
        Student student = createStudent(5, 4.0, 1001);

        StudentCollection students =
                new StudentCollection(List.of(student));

        new AllDescending().sort(students);

        assertEquals(1, students.size());
        assertEquals(student, students.getStudents().get(0));
    }

    @Test
    void shouldSortAllFieldsAscending() {
        StudentCollection students = new StudentCollection(List.of(
                createStudent(3, 4.0, 1003),
                createStudent(1, 5.0, 1001),
                createStudent(2, 3.0, 1002),
                createStudent(1, 4.0, 1004)
        ));

        new AllAscending().sort(students);

        assertEquals(1, students.getStudents().get(0).getGroupNumber());
        assertEquals(4.0, students.getStudents().get(0).getAverageScore());

        assertEquals(1, students.getStudents().get(1).getGroupNumber());
        assertEquals(5.0, students.getStudents().get(1).getAverageScore());

        assertEquals(2, students.getStudents().get(2).getGroupNumber());
        assertEquals(3.0, students.getStudents().get(2).getAverageScore());

        assertEquals(3, students.getStudents().get(3).getGroupNumber());
    }

    @Test
    void shouldSortAllFieldsDescending() {
        StudentCollection students = new StudentCollection(List.of(
                createStudent(1, 4.0, 1004),
                createStudent(3, 4.0, 1003),
                createStudent(2, 3.0, 1002),
                createStudent(1, 5.0, 1001)
        ));

        new AllDescending().sort(students);

        assertEquals(3, students.getStudents().get(0).getGroupNumber());
        assertEquals(2, students.getStudents().get(1).getGroupNumber());
        assertEquals(1, students.getStudents().get(2).getGroupNumber());
        assertEquals(5.0, students.getStudents().get(2).getAverageScore());
        assertEquals(1, students.getStudents().get(3).getGroupNumber());
        assertEquals(4.0, students.getStudents().get(3).getAverageScore());
    }

    @Test
    void shouldSortStudentsWithEvenAverageScore() {
        StudentCollection students = new StudentCollection(List.of(
                createStudent(1, 3.5, 1001),
                createStudent(2, 4.5, 1002),
                createStudent(3, 2.5, 1003),
                createStudent(4, 5.0, 1004),
                createStudent(5, 2.0, 1005)
        ));

        new EvenAverageScore().sort(students);

        List<Student> result = students.getStudents();

        assertEquals(3.5, result.get(0).getAverageScore());
        assertEquals(2.0, result.get(1).getAverageScore());
        assertEquals(2.5, result.get(2).getAverageScore());
        assertEquals(5.0, result.get(3).getAverageScore());
        assertEquals(4.5, result.get(4).getAverageScore());
    }

    @Test
    void shouldSortStudentsWithEvenGroupNumber() {
        StudentCollection students = new StudentCollection(List.of(
                createStudent(8, 4.0, 1001),
                createStudent(3, 4.0, 1002),
                createStudent(2, 4.0, 1003),
                createStudent(7, 4.0, 1004),
                createStudent(4, 4.0, 1005)
        ));

        new EvenGroupNumber().sort(students);

        List<Student> result = students.getStudents();

        assertEquals(2, result.get(0).getGroupNumber());
        assertEquals(3, result.get(1).getGroupNumber());
        assertEquals(4, result.get(2).getGroupNumber());
        assertEquals(7, result.get(3).getGroupNumber());
        assertEquals(8, result.get(4).getGroupNumber());
    }

    @Test
    void shouldSortStudentsWithEvenRecordBookNumber() {
        StudentCollection students = new StudentCollection(List.of(
                createStudent(1, 4.0, 1008),
                createStudent(2, 4.0, 1003),
                createStudent(3, 4.0, 1002),
                createStudent(4, 4.0, 1007),
                createStudent(5, 4.0, 1004)
        ));

        new EvenRecordBookNumber().sort(students);

        List<Student> result = students.getStudents();

        assertEquals(1002, result.get(0).getIdGradeBook());
        assertEquals(1003, result.get(1).getIdGradeBook());
        assertEquals(1004, result.get(2).getIdGradeBook());
        assertEquals(1007, result.get(3).getIdGradeBook());
        assertEquals(1008, result.get(4).getIdGradeBook());
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