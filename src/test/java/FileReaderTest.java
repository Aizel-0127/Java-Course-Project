import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import model.Student;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testReadValidFile() throws Exception {
        Path file = tempDir.resolve("students.txt");
        String content = """
                101,4.5,12345
                102,3.8,12346
                """;
        Files.writeString(file, content);

        List<Student> students = FileReader.readStudentsFromFile(file.toString());

        assertEquals(2, students.size());
        assertEquals(101, students.get(0).getGroupNumber());
        assertEquals(4.5, students.get(0).getAverageScore());
    }

    @Test
    void testInvalidLinesSkipped() throws Exception {
        Path file = tempDir.resolve("invalid.txt");
        String content = """
                101,4.5,12345
                102,abc,12346    # поломанный балл (буквы)
                103,5.0          # мало колонок
                104,4.0,12348
                """;
        Files.writeString(file, content);

        List<Student> students = FileReader.readStudentsFromFile(file.toString());

        assertEquals(2, students.size()); // прочитаются только 101 и 104
        assertEquals(101, students.get(0).getGroupNumber());
        assertEquals(104, students.get(1).getGroupNumber());
    }

    @Test
    void testWriteStudentsToFile() throws Exception {
        Path file = tempDir.resolve("output.txt");

        // создаем тестовый список студентов через билдер
        List<Student> studentsToSend = new ArrayList<>();
        studentsToSend.add(new Student.Builder().groupNumber(201).averageScore(4.8).idGradeBook(55555).build());
        studentsToSend.add(new Student.Builder().groupNumber(202).averageScore(3.9).idGradeBook(55556).build());

        // запускаем метод записи
        FileReader.writeStudentsToFile(file.toString(), studentsToSend);

        // проверяем, что файл создался
        assertTrue(Files.exists(file));

        // считываем строки напрямую и сверяем формат записи
        List<String> lines = Files.readAllLines(file);
        assertEquals(2, lines.size());
        assertEquals("201,4.8,55555", lines.get(0));
        assertEquals("202,3.9,55556", lines.get(1));
    }
}