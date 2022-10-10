package TrainingCenter;

import java.util.ArrayList;

public class Program {
    private String name;
    private int durationInHours;
    private ArrayList<Course> listOfCourses;

    public Program(String name) {
        setName(name);
        durationInHours = 0;
        listOfCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void addCourse(Course course) {
        listOfCourses.add(course);
        durationInHours += course.getDurationInHours();
    }
}
