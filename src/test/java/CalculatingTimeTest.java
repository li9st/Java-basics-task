import TrainingCenter.Course;
import TrainingCenter.Program;
import TrainingCenter.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class CalculatingTimeTest {
    private Student student;
    private LocalDateTime startDate = LocalDateTime.of(2020, Month.JUNE, 8, 10, 0);

    @Before
    public void initaliseStudent() {
        Program javaDeveloper = new Program("Java Developer");

        javaDeveloper.addCourse(new Course("Java", 10));
        javaDeveloper.addCourse(new Course("JDBC", 20));
        javaDeveloper.addCourse(new Course("Spring", 10));
        javaDeveloper.addCourse(new Course("Java", 98));

        student = new Student("Ivan", "Ivanov", javaDeveloper, startDate);
    }

    @Test
    public void calculatingFinishDateDifferentDay() {
        assertEquals("Finish date is not correct", LocalDateTime.of(2020, Month.JULY, 1, 12, 00), student.calculateFinishDate());
    }

    @Test
    public void calculatingFinishDateSameDay() {
        Program javaDeveloper = new Program("Java Developer");

        javaDeveloper.addCourse(new Course("Java", 1));
        javaDeveloper.addCourse(new Course("JDBC", 2));

        student.setProgram(javaDeveloper);
        assertEquals("Finish date is not correct", LocalDateTime.of(2020, Month.JUNE, 8, 13, 00), student.calculateFinishDate());
    }

    @Test
    public void checkingUnfinishedDateMessage() {

        String expectedMessage = "Training is not finished. 11 d 21 hours are left until the end.";
        String actualMessage = student.hasFinished(LocalDateTime.of(2020, Month.JUNE, 19, 15, 00));

        assertEquals("The correct message is not displayed when checking unfinished date", expectedMessage, actualMessage);
    }

    @Test
    public void checkingFinishedDateMessage() {

        String expectedMessage = "Training completed. 3 hours have passed since the end.";
        String actualMessage = student.hasFinished(LocalDateTime.of(2020, Month.JULY, 1, 15, 00));

        assertEquals("The correct message is not displayed when checking unfinished date", expectedMessage, actualMessage);
    }

    @Test
    public void checkingJustFinishedDateMessage() {

        String expectedMessage = "Training completed. 0 hours have passed since the end.";
        String actualMessage = student.hasFinished(LocalDateTime.of(2020, Month.JULY, 1, 12, 00));

        assertEquals("The correct message is not displayed when checking unfinished date", expectedMessage, actualMessage);
    }

    @Test
    public void checkingFinishDateIfProgramEmpty() {
        student.setProgram(new Program("Java Developer"));
        assertEquals("Finish date is not correct", startDate, student.calculateFinishDate());
    }

    @Test
    public void startDateCannotBeSaturday(){
        student.setStartDate(LocalDateTime.of(2020, Month.JUNE, 6, 10, 0));

        assertTrue("Start date didn't automatically move from Saturday to Monday",student.getStartDate().getDayOfWeek().equals(DayOfWeek.MONDAY));
        assertEquals("Start date is not first Monday to come",LocalDateTime.of(2020,Month.JUNE,8,10,00), student.getStartDate());
    }

    @Test
    public void startDateCannotBeSunday(){
        student.setStartDate(LocalDateTime.of(2020, Month.JUNE, 7, 10, 0));

        assertTrue("Start date didn't automatically move from Sunday to Monday",student.getStartDate().getDayOfWeek().equals(DayOfWeek.MONDAY));
        assertEquals("Start date is not first Monday to come",LocalDateTime.of(2020,Month.JUNE,8,10,00), student.getStartDate());
    }

    @Test
    public void finishDateCannotBeSaturday(){
        Program javaDeveloper = new Program("Java Developer");
        javaDeveloper.addCourse(new Course("Java", 41));
        student.setProgram(javaDeveloper);

        assertEquals("Finish date is Saturday",LocalDateTime.of(2020,Month.JUNE,15,11,00), student.calculateFinishDate());
    }

    @Test
    public void finishDateCannotBeSunday(){
        Program javaDeveloper = new Program("Java Developer");
        javaDeveloper.addCourse(new Course("Java", 49));
        student.setProgram(javaDeveloper);

        assertEquals("Finish date is Sunday",LocalDateTime.of(2020,Month.JUNE,16,11,00), student.calculateFinishDate());
    }
}
