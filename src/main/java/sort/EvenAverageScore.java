package sort;

import model.Student;
import java.util.List;
import collections.StudentCollection;

public class EvenAverageScore implements SortStrategy {
    @Override
    public void sort(StudentCollection studentsCollection) {
        List<Student> students = studentsCollection.getStudents();
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenAverageScore,
                this::compareByAverageScore
        );
        studentsCollection.setStudents(students);
    }

    private int compareByAverageScore(model.Student firstStudent, model.Student secondStudent) {
        return Double.compare(firstStudent.getAverageScore(), secondStudent.getAverageScore());
    }

    private boolean hasEvenAverageScore(model.Student student) {
        return (int) Math.floor(student.getAverageScore()) % 2 == 0;
    }
    
}
