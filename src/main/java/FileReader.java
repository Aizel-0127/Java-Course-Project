import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Student;

public class FileReader {

    public static List<Student> readStudentsFromFile(String filePath) {
        List<Student> studentsList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int group = Integer.parseInt(parts[0].trim());
                        double score = Double.parseDouble(parts[1].trim());
                        int id = Integer.parseInt(parts[2].trim());

                        Student student = new Student.Builder()
                                .groupNumber(group)
                                .averageScore(score)
                                .idGradeBook(id)
                                .build();

                        studentsList.add(student);
                    } catch (Exception e) {
                        System.out.println("Ошибка в строке: " + line + " — пропускаем");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return studentsList;
    }

    public static void writeStudentsToFile(String filePath, List<Student> students) {
        try {
            List<String> lines = new ArrayList<>();
            for (Student s : students) {
                String line = s.getGroupNumber() + "," + s.getAverageScore() + "," + s.getIdGradeBook();
                lines.add(line);
            }
            Files.write(Paths.get(filePath), lines);
            System.out.println("Файл сохранён: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
        }
    }
}

