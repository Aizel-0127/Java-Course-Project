package sort;
import model.Student;
import java.util.List;

import collections.StudentCollection;

public class AllAscending implements SortStrategy {
    @Override
    public void sort(StudentCollection studentsCollection) {
        List<Student> students = studentsCollection.getStudents();
        for (int i = 1; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            int previousIndex = i - 1;

            while (previousIndex >= 0
                    && SortHelper.compareByAllFields(students.get(previousIndex), currentStudent) > 0) {
                students.set(previousIndex + 1, students.get(previousIndex));
                previousIndex--;
            }

            students.set(previousIndex + 1, currentStudent);
        }
        studentsCollection.setStudents(students);
    }
}
