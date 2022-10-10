import TrainingCenter.Course;
import TrainingCenter.Program;
import TrainingCenter.Student;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class ValidInputTest {

    @Test(expected = IllegalArgumentException.class)
    public void canStudentFirstNameBeEmpty() {
        Program javaDeveloper = new Program("Java Developer");

        javaDeveloper.addCourse(new Course("Java", 10));
        javaDeveloper.addCourse(new Course("JDBC", 20));
        javaDeveloper.addCourse(new Course("Spring", 10));
        javaDeveloper.addCourse(new Course("Java", 98));

        Student student = new Student("", "Ivanov", javaDeveloper, LocalDateTime.of(2020, Month.JUNE, 7, 10, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void canStudentLastNameBeEmpty() {
        Program javaDeveloper = new Program("Java Developer");

        javaDeveloper.addCourse(new Course("Java", 10));
        javaDeveloper.addCourse(new Course("JDBC", 20));
        javaDeveloper.addCourse(new Course("Spring", 10));
        javaDeveloper.addCourse(new Course("Java", 98));

        Student student = new Student("Ivan", "", javaDeveloper, LocalDateTime.of(2020, Month.JUNE, 7, 10, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCourseNameBeEmpty() {
        Course course = new Course("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCourseDurationBeZero() {
        Course course = new Course("Java", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canCourseDurationBeNegative() {
        Course course = new Course("Java", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canProgramNameBeEmpty() {
        Program program = new Program("");
    }
}
