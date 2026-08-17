package collections;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Student;

public class StudentCollection {
    private List<Student> students;

    // Конструктор пустой коллекции
    public StudentCollection() {
        this.students = new ArrayList<>();
    }

    public void add(Student student){
        students.add(student);
    }

    public void clear(){
        students.clear();
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
            return new Student.Builder()
            .groupNumber(groupNumber)   
            .averageScore(averageScore)
            .idGradeBook(idGradeBook)
            .build();
        }).limit(count).collect(Collectors.toList());
    }

    // ----- Доступ к данным -----
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public int size() {
        return students.size();
    }

    public boolean isEmpty()
    {
        return students.isEmpty();
    }
    // Можно добавить метод для замены всей коллекции (используется сортировщиком)
    public void setStudents(List<Student> newList) {
        this.students = new ArrayList<>(newList);
    }
}