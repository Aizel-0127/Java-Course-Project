package sort;

import model.Student;
import java.util.List;
import collections.StudentCollection;

public class EvenGroupNumber implements SortStrategy {
    @Override
    public void sort(StudentCollection studentsCollection) {
        List<Student> students = studentsCollection.getStudents();
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenGroupNumber,
                this::compareByGroupNumber
        );
        studentsCollection.setStudents(students);
    }

    private int compareByGroupNumber(Student firstStudent, Student secondStudent) {
        return Integer.compare(firstStudent.getGroupNumber(), secondStudent.getGroupNumber());
    }

    private boolean hasEvenGroupNumber(Student student) {
        return student.getGroupNumber() % 2 == 0;
    }
}
