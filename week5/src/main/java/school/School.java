package school;

import java.util.List;

public class School {

    private List<Class> myClasses;

    public List<Class> getMyClasses() {
        return myClasses;
    }

    public void setMyClasses(List<Class> myClasses) {
        this.myClasses = myClasses;
    }

    @Override
    public String toString() {
        return "MyClass::" + myClasses.toString();
    }
}
