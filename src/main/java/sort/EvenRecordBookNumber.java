package sort;

import model.Student;
import java.util.List;

public class EvenRecordBookNumber implements SortStrategy<model.Student> {
    @Override
    public void sort(List<model.Student> students) {
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenRecordBookNumber,
                this::compareByRecordBookNumber
        );
    }

    private int compareByRecordBookNumber(model.Student firstStudent, model.Student secondStudent) {
        return Integer.compare(firstStudent.getIdGradeBook(), secondStudent.getIdGradeBook());
    }

    private boolean hasEvenRecordBookNumber(Student student) {
        return student.getIdGradeBook() % 2 == 0;
    }
}
