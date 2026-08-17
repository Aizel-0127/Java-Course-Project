package counter;

import collections.StudentCollection;
import model.Student;

import java.util.List;

public class StudentOccurrenceCounter {

    public int countAndPrint(StudentCollection studentCollection, double number) {
        int count = count(studentCollection, number);
        System.out.println("Количество вхождений: " + count);
        return count;
    }

    public int count(StudentCollection studentCollection, double number) {
        List<Student> students = studentCollection.getStudents();

        if (students.isEmpty()) {
            return 0;
        }

        // Коллекция делится между несколькими потоками для параллельного подсчёта.
        int threadCount = getThreadCount(students.size());
        int[] results = new int[threadCount];
        Thread[] threads = new Thread[threadCount];

        int basePartSize = students.size() / threadCount;
        int additionalElements = students.size() % threadCount;
        int startIndex = 0;

        for (int i = 0; i < threadCount; i++) {
            int currentPartSize = basePartSize;

            if (i < additionalElements) {
                currentPartSize++;
            }

            int fromIndex = startIndex;
            int toIndex = startIndex + currentPartSize;
            int resultIndex = i;

            threads[i] = new Thread(() ->
                    results[resultIndex] = countInPart(
                            students, number, fromIndex, toIndex
                    )
            );

            threads[i].start();
            startIndex = toIndex;
        }

        // Дожидаемся завершения всех потоков.
        waitThreads(threads);

        int totalCount = 0;

        for (int result : results) {
            totalCount += result;
        }

        return totalCount;
    }

    private int getThreadCount(int collectionSize) {
        if (collectionSize == 1) {
            return 1;
        }

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int minimumThreadCount = 2;

        return Math.min(
                collectionSize,
                Math.max(minimumThreadCount, availableProcessors)
        );
    }

    private int countInPart(
            List<Student> students,
            double number,
            int fromIndex,
            int toIndex
    ) {
        int count = 0;

        for (int i = fromIndex; i < toIndex; i++) {
            if (containsNumber(students.get(i), number)) {
                count++;
            }
        }

        return count;
    }

    private boolean containsNumber(Student student, double number) {
        return student.getGroupNumber() == number
                || Double.compare(student.getAverageScore(), number) == 0
                || student.getIdGradeBook() == number;
    }

    private void waitThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Подсчет был прерван", e);
            }
        }
    }
}