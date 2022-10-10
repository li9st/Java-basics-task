import TrainingCenter.Course;
import TrainingCenter.Program;
import TrainingCenter.Student;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static Constants.Constants.*;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        Program javaDeveloper = new Program("Java Developer");
        Program aqe = new Program("J2EE Developer");

        javaDeveloper.addCourse(new Course("Java", 16));
        javaDeveloper.addCourse(new Course("JDBC", 24));
        javaDeveloper.addCourse(new Course("Spring", 16));

        aqe.addCourse(new Course("Test Design", 10));
        aqe.addCourse(new Course("Page Object", 16));
        aqe.addCourse(new Course("Selenium", 16));

        students.add(new Student("Ivan", "Ivanov", javaDeveloper, LocalDateTime.of(2020, Month.JUNE, 1, 10, 0)));
        students.add(new Student("Ivan", "Sidrov", aqe, LocalDateTime.of(2020, Month.JUNE, 1, 10, 0)));

        System.out.println("For short report enter 0 or just press enter, for long report input anything else \nInput:");
        String input = s.nextLine();

        for (Student student : students)
            if (input.equals("0") || input.isEmpty()) {
                System.out.println(student + student.hasFinished(LocalDateTime.of(2020, Month.JUNE, 8, 15, 00)) + "\n");
            } else {
                System.out.println("Full name " + student.getFullName());
                System.out.println("Working time " + START_OF_DAY_HOUR + " to " + END_OF_DAY_HOUR);
                System.out.println("Program name " + student.getProgram().getName());
                System.out.println("Program duration (hours) " + student.getProgram().getDurationInHours());
                System.out.println("Start date " + student.getStartDate().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy H:mm")));
                System.out.println("End date " + student.calculateFinishDate().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy H:mm")));
                System.out.println(student.hasFinished(LocalDateTime.of(2020, Month.JUNE, 8, 15, 00)) + "\n");
            }
    }
}
