package school;

import java.util.List;

public class Class {

    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "MyClass::" + students.toString();
    }
}
