package model;
public class Student {
    private int groupNumber; // номер группы
    private double averageScore; // средний балл
    private int idGradeBook; // номер зачетной книжки

    public Student(Builder builder) {
        this.groupNumber = builder.groupNumber;
        this.averageScore = builder.averageScore;
        this.idGradeBook = builder.idGradeBook;
    }

    public int getGroupNumber() {
        return groupNumber;
    }
    public double getAverageScore() {
        return averageScore;
    }
    public int getIdGradeBook() {
        return idGradeBook;
    }

    //Сеттеры с валидацией
    public void setGroupNumber(int groupNumber) {
        validateGroupNumber(groupNumber);
        this.groupNumber = groupNumber;
    }

    public void setAverageScore(double averageScore) {
        validateAverageScore(averageScore);
        this.averageScore = averageScore;
    }

    public void setIdGradeBook(int idGradeBook) {
        validateIdGradeBook(idGradeBook);
        this.idGradeBook = idGradeBook;
    }

    //Методы валидации
    private static void validateGroupNumber(int groupNumber) {
        if (groupNumber <=0)
            throw new IllegalArgumentException("Номер группы должен быть больше 0");
    }

    private static void validateAverageScore(double averageScore) {
        if (averageScore < 0.0) throw new IllegalArgumentException("Средний балл должен быть больше 0");
        else if(averageScore > 5.0) throw new IllegalArgumentException("Средний балл должен быть меньше 5");
    }

    private static void validateIdGradeBook(int idGradeBook) {
        if (idGradeBook <= 0)
            throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
    }

    @Override
    public String toString() {
        return "Студент числится в группе " + groupNumber + ", номер зачетной книжки: " + idGradeBook + ", средний былл: " + averageScore;
    }

    public static class Builder {
        private int groupNumber;
        private double averageScore;
        private int idGradeBook;

        public Builder groupNumber(int groupNumber) {
            validateGroupNumber(groupNumber);
            this.groupNumber = groupNumber;
            return this;
        }

        public Builder averageScore(double averageScore) {
            validateAverageScore(averageScore);
            this.averageScore = averageScore;
            return this;
        }

        public Builder idGradeBook(int idGradeBook) {
            validateIdGradeBook(idGradeBook);
            this.idGradeBook = idGradeBook;
            return this;
        }

        public Student build() {
            if (groupNumber == 0 || idGradeBook == 0)
                throw new IllegalStateException("Обязательные поля номер группы и номер зачетной книжки не заданы.");
            return new Student(this); // вызов приватного конструктора
        }
    }
}
