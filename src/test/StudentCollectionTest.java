package collections;

import model.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentCollectionTest {

    @Test
    void shouldCreateEmptyCollection() {
        StudentCollection collection = new StudentCollection();

        assertEquals(0, collection.size());
        assertTrue(collection.isEmpty());
    }

    @Test
    void shouldAddStudent() {
        StudentCollection collection = new StudentCollection();

        Student student = createStudent(101, 4.5, 12345);

        collection.add(student);

        assertEquals(1, collection.size());
        assertFalse(collection.isEmpty());
        assertEquals(student, collection.getStudents().get(0));
    }

    @Test
    void shouldClearCollection() {
        StudentCollection collection = new StudentCollection();

        collection.add(createStudent(101, 4.5, 12345));
        collection.add(createStudent(102, 3.8, 12346));

        collection.clear();

        assertEquals(0, collection.size());
        assertTrue(collection.isEmpty());
    }

    @Test
    void shouldCreateCollectionFromList() {
        Student student1 = createStudent(101, 4.5, 12345);
        Student student2 = createStudent(102, 3.8, 12346);

        StudentCollection collection =
                new StudentCollection(List.of(student1, student2));

        assertEquals(2, collection.size());
        assertEquals(List.of(student1, student2), collection.getStudents());
    }

    @Test
    void shouldReturnCopyOfStudents() {
        Student student = createStudent(101, 4.5, 12345);

        StudentCollection collection =
                new StudentCollection(List.of(student));

        List<Student> students = collection.getStudents();

        students.clear();

        // Внутренняя коллекция не должна измениться
        assertEquals(1, collection.size());
    }

    @Test
    void shouldSetStudents() {
        StudentCollection collection = new StudentCollection();

        Student student1 = createStudent(101, 4.5, 12345);
        Student student2 = createStudent(102, 3.8, 12346);

        collection.setStudents(List.of(student1, student2));

        assertEquals(2, collection.size());
        assertEquals(
                List.of(student1, student2),
                collection.getStudents()
        );
    }

    @Test
    void shouldFillRandomCollection() {
        StudentCollection collection = new StudentCollection();

        collection.fillRandom(10);

        assertEquals(10, collection.size());
        assertFalse(collection.isEmpty());

        for (Student student : collection.getStudents()) {
            assertNotNull(student);
            assertTrue(student.getGroupNumber() >= 1);
            assertTrue(student.getGroupNumber() <= 100);

            assertTrue(student.getAverageScore() >= 1.0);
            assertTrue(student.getAverageScore() <= 5.0);

            assertTrue(student.getIdGradeBook() >= 1);
            assertTrue(student.getIdGradeBook() <= 10000);
        }
    }

    @Test
    void shouldFillZeroStudents() {
        StudentCollection collection = new StudentCollection();

        collection.fillRandom(0);

        assertEquals(0, collection.size());
        assertTrue(collection.isEmpty());
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