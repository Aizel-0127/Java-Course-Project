package sort;
import model.Student;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

final class SortHelper {
    private SortHelper() {
    }

    static int compareByAllFields(Student firstStudent, Student secondStudent) {
        int groupComparison = Integer.compare(firstStudent.getGroupNumber(), secondStudent.getGroupNumber());
        if (groupComparison != 0) {
            return groupComparison;
        }

        int averageScoreComparison = Double.compare(firstStudent.getAverageScore(), secondStudent.getAverageScore());
        if (averageScoreComparison != 0) {
            return averageScoreComparison;
        }

        return Integer.compare(firstStudent.getIdGradeBook(), secondStudent.getIdGradeBook());
    }

    static void sortOnlyMatchingValuesByInsertion(
            List<Student> students,
            Predicate<Student> studentFilter,
            Comparator<Student> comparator
    ) {
        for (int i = 1; i < students.size(); i++) {
            if (!studentFilter.test(students.get(i))) {
                continue;
            }

            model.Student currentStudent = students.get(i);
            int previousMatchingIndex = findPreviousMatchingIndex(students, i - 1, studentFilter);

            while (previousMatchingIndex >= 0 && comparator.compare(students.get(previousMatchingIndex), currentStudent) > 0) {
                int nextMatchingIndex = findNextMatchingIndex(students, previousMatchingIndex + 1, studentFilter);
                students.set(nextMatchingIndex, students.get(previousMatchingIndex));
                previousMatchingIndex = findPreviousMatchingIndex(students, previousMatchingIndex - 1, studentFilter);
            }

            int insertIndex = findNextMatchingIndex(students, previousMatchingIndex + 1, studentFilter);
            students.set(insertIndex, currentStudent);
        }
    }

    private static int findPreviousMatchingIndex(
            List<Student> students,
            int startIndex,
            Predicate<Student> studentFilter
    ) {
        for (int i = startIndex; i >= 0; i--) {
            if (studentFilter.test(students.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private static int findNextMatchingIndex(
            List<Student> students,
            int startIndex,
            Predicate<Student> studentFilter
    ) {
        for (int i = startIndex; i < students.size(); i++) {
            if (studentFilter.test(students.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
