package sort;

import model.Student;
import java.util.List;

public class EvenGroupNumber implements SortStrategy<Student> {
    @Override
    public void sort(List<Student> students) {
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenGroupNumber,
                this::compareByGroupNumber
        );
    }

    private int compareByGroupNumber(Student firstStudent, Student secondStudent) {
        return Integer.compare(firstStudent.getGroupNumber(), secondStudent.getGroupNumber());
    }

    private boolean hasEvenGroupNumber(Student student) {
        return student.getGroupNumber() % 2 == 0;
    }
}
