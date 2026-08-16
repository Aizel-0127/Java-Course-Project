package sort;

import model.Student;
import java.util.List;
import collections.StudentCollection;

public class EvenRecordBookNumber implements SortStrategy {
    @Override
    public void sort(StudentCollection studentsCollection) {
        List<Student> students = studentsCollection.getStudents();
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenRecordBookNumber,
                this::compareByRecordBookNumber
        );
        studentsCollection.setStudents(students);
    }

    private int compareByRecordBookNumber(model.Student firstStudent, model.Student secondStudent) {
        return Integer.compare(firstStudent.getIdGradeBook(), secondStudent.getIdGradeBook());
    }

    private boolean hasEvenRecordBookNumber(Student student) {
        return student.getIdGradeBook() % 2 == 0;
    }
}
