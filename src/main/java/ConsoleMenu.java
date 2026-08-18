import java.util.*;

import collections.StudentCollection;
import counter.StudentOccurrenceCounter;
import sort.*;
import model.Student;

public class ConsoleMenu {
    private boolean running = true;
    private final Scanner scanner = new Scanner(System.in);
    private final StudentCollection students = new StudentCollection();

    public void run() {
        while (running) {
            
            showMenu();
            int choice = readChoice();
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("1. Создать Student");
        System.out.println("2. Загрузить данные из файла");
        System.out.println("3. Заполнить случайно");
        System.out.println("4. Отсортировать");
        System.out.println("5. Показать данные");
        System.out.println("6. Сохранить данные в файл");
        System.out.println("7. Подсчитать количество вхождений");
        System.out.println("0. Выйти");
        System.out.print("Выберите действие: ");
    }

    private int readChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число.");
            scanner.next();
        }

        return scanner.nextInt();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 0:
                // Выход из программы
                System.out.println("Программа завершена.");
                running = false;
                break;

            case 1:
                // Создать Student
                createStudent();
                break;

            case 2:
                // Загрузить данные из файла
                loadFromFile();
                break;

            case 3:
                // Заполнить массив случайными данными
                fillRandom();
                break;

            case 4:
                // Отсортировать объекты
                sortStudents();
                break;

            case 5:
                // Показать данные
                showData();
                break;
            case 6:
                // Сохранение данных в файл
                saveToFile();
                break;
            case 7:
                // Подсчет количества вхождений
                countOccurrences();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");
        }
    }

    private void createStudent() {
        try {
            System.out.print("Введите количество студентов для создания: ");
            int count = scanner.nextInt();

            if (count <= 0) {
                System.out.println("Количество студентов должно быть больше 0.");
                return;
            }

            for (int i = 0; i < count; i++) {
                System.out.println("Студент #" + (i + 1));
                Student student = readStudent();
                students.add(student);
                System.out.println("Студент создан:");
                System.out.println(student);
            }

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private Student readStudent() {
        System.out.print("Введите номер группы: ");
        int groupNumber = scanner.nextInt();

        System.out.print("Введите средний балл: ");
        double averageScore = Double.parseDouble(
                scanner.next().replace(',', '.')
        );

        System.out.print("Введите номер зачетной книжки: ");
        int idGradeBook = scanner.nextInt();

        return new Student.Builder()
                .groupNumber(groupNumber)
                .averageScore(averageScore)
                .idGradeBook(idGradeBook)
                .build();
    }

    private void loadFromFile() {
        // Подключить загрузку данных из файла
        scanner.nextLine();
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        //Подключение FileReader
        students.setStudents(FileReader.readStudentsFromFile(filePath));
    }

    private void fillRandom() {
        students.clear();
        
        int count=0;
        do {
            System.out.println("Введите количество студентов для заполнения: ");
            count = scanner.nextInt();
        } while (count <= 0);
        students.fillRandom(count);

        System.out.println("Студенты заполнены случайными данными.");
    }

private void sortStudents() {
    System.out.println("1. По возрастанию");
    System.out.println("2. По убыванию");
    System.out.println("3. Чётные по среднему баллу");
    System.out.println("4. Чётные по номеру группы");
    System.out.println("5. Чётные по номеру зачётной книжки");
    System.out.print("Выберите сортировку: ");

    int choice = readChoice();

    switch (choice) {
        case 1: {
            AllAscending sorter = new AllAscending();
            sorter.sort(students);
            break;
        }
        case 2: {
            AllDescending sorter = new AllDescending();
            sorter.sort(students);
            break;
        }
        case 3: {
            EvenAverageScore sorter = new EvenAverageScore();
            sorter.sort(students);
            break;
        }
        case 4: {
            EvenGroupNumber sorter = new EvenGroupNumber();
            sorter.sort(students);
            break;
        }
        case 5: {
            EvenRecordBookNumber sorter = new EvenRecordBookNumber();
            sorter.sort(students);
            break;
        }
        default:
            System.out.println("Неверный выбор.");
    }
}

    private void showData() {
        // Показать данные
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        for (Student student : students.getStudents()) {
            System.out.println(student);
        }
    }

    private void saveToFile() {
        scanner.nextLine();

        if (students.isEmpty()) {
            System.out.println("Нет данных для сохранения.");
            return;
        }

        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        FileReader.writeStudentsToFile(
                filePath,
                students.getStudents()
        );
    }

    private void countOccurrences() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        try {
            System.out.print("Введите число для поиска: ");

            double number = Double.parseDouble(
                    scanner.next().replace(',', '.')
            );

            StudentOccurrenceCounter counter =
                    new StudentOccurrenceCounter();

            int count = counter.count(students, number);

            System.out.println(
                    "Количество вхождений: " + count
            );

        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            scanner.nextLine();
        }
    }
}
