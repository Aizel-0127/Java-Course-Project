package sort;

import model.Student;
import java.util.List;

public class AllDescending implements SortStrategy<Student> {
    @Override
    public void sort(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            boolean hasSwap = false;

            for (int j = 0; j < students.size() - i - 1; j++) {
                if (SortHelper.compareByAllFields(students.get(j), students.get(j + 1)) < 0) {
                    Student temporaryStudent = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temporaryStudent);
                    hasSwap = true;
                }
            }

            if (!hasSwap) {
                return;
            }
        }
    }
}
