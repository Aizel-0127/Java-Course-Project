import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentCollection {
    private List<Student> students;

    // Конструктор пустой коллекции
    public StudentCollection() {
        this.students = new ArrayList<>();
    }

    // Конструктор с начальным списком
    public StudentCollection(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    // 2. Случайное заполнение заданной длины (используем стримы)
    public void fillRandom(int count) {
        Random rand = new Random();
        this.students = Stream.generate(() -> {
            int groupNumber = rand.nextInt(100) + 1;
            double averageScore = 1.0 + rand.nextDouble() * 4.0; // от 1 до 5
            int idGradeBook = rand.nextInt(10000) + 1;
            return Builder.groupNumber(groupNumber).averageScore(averageScore).idGradeBook(idGradeBook).build();
        }).limit(count).collect(Collectors.toList());
    }

    // ----- Доступ к данным -----
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public int size() {
        return students.size();
    }

    // Можно добавить метод для замены всей коллекции (используется сортировщиком)
    public void setStudents(List<Student> newList) {
        this.students = new ArrayList<>(newList);
    }
}