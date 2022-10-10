package TrainingCenter;

public class Course {
    private String name;
    private int durationInHours;

    public Course(String name, int durationInHours) {
        setName(name);
        setDurationInHours(durationInHours);
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

    public void setDurationInHours(int durationInHours) {
        if (durationInHours < 1) {
            throw new IllegalArgumentException();
        } else {
            this.durationInHours = durationInHours;
        }
    }
}
