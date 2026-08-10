package sort;

import model.Student;
import java.util.List;

public class EvenAverageScore implements SortStrategy<model.Student> {
    @Override
    public void sort(List<Student> students) {
        SortHelper.sortOnlyMatchingValuesByInsertion(
                students,
                this::hasEvenAverageScore,
                this::compareByAverageScore
        );
    }

    private int compareByAverageScore(model.Student firstStudent, model.Student secondStudent) {
        return Double.compare(firstStudent.getAverageScore(), secondStudent.getAverageScore());
    }

    private boolean hasEvenAverageScore(model.Student student) {
        return (int) Math.floor(student.getAverageScore()) % 2 == 0;
    }
}
