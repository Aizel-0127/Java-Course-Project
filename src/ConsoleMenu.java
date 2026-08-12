import java.util.Scanner;

public class ConsoleMenu {
    private boolean running = true;
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (running) {
            showMenu();
            int choice = readChoice();
            handleChoice(choice);
        }
    }

    private void showMenu() {
        System.out.println("1. Создать Student");
        System.out.println("2. Загрузить данные из файла");
        System.out.println("3. Заполнить случайно");
        System.out.println("4. Отсортировать");
        System.out.println("5. Показать данные");
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
                // Выйти из программы
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

            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");
        }
    }

    private void createStudent() {
        // Подключить создание студента
    }

    private void loadFromFile() {
        // Подключить загрузку данных из файла
    }

    private void fillRandom() {
        // Подключить заполнение массива случайными данными
    }

    private void sortStudents() {
        // Подключить сортировку студентов
    }

    private void showData() {
        // Показать данные
    }
}