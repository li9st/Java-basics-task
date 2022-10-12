import TrainingCenter.Course;
import TrainingCenter.Program;
import TrainingCenter.Student;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static Constants.Constants.*;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        LocalDateTime startDate = getStartDate();
        LocalDateTime checkingDate = getCheckingDate();

        Program javaDeveloper = new Program("Java Developer");
        Program aqe = new Program("J2EE Developer");

        javaDeveloper.addCourse(new Course("Java", 7));
        javaDeveloper.addCourse(new Course("JDBC", 24));
        javaDeveloper.addCourse(new Course("Spring", 16));

        aqe.addCourse(new Course("Test Design", 8));
        aqe.addCourse(new Course("Page Object", 16));
        aqe.addCourse(new Course("Selenium", 16));

        students.add(new Student("Ivan", "Ivanov", javaDeveloper, startDate));
        students.add(new Student("Ivan", "Sidrov", aqe, startDate));

        System.out.println("For short report enter 0 or just press enter, for long report input anything else \nInput:");
        String input = s.nextLine();

        for (Student student : students) {
            if (input.equals("0") || input.isEmpty()) {
                System.out.println(student + student.hasFinished(checkingDate) + "\n");
            } else {
                System.out.println("Full name " + student.getFullName());
                System.out.println("Working time " + START_OF_DAY_HOUR + " to " + END_OF_DAY_HOUR);
                System.out.println("Program name " + student.getProgram().getName());
                System.out.println("Program duration (hours) " + student.getProgram().getDurationInHours());
                System.out.println("Start date " + student.getStartDate().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy H:mm")));
                System.out.println("End date " + student.calculateFinishDate().format(DateTimeFormatter.ofPattern("dd/MMM/yyyy H:mm")));
                System.out.println(student.hasFinished(checkingDate) + "\n");
            }
        }
    }

    private static LocalDateTime getStartDate() {
        Scanner s = new Scanner(System.in);
        LocalDateTime date;
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
            System.out.println("Please enter start date in format day/month/year hour:minute. Working hours are from 10 to 18.(Example: 5/10/2020 18:50)");
            String userInput = s.nextLine();
            date = LocalDateTime.parse(userInput, dateFormat);
            if(date.getHour() >= 18 || date.getHour() < 10){
                throw new IllegalArgumentException();
            }
        } catch (DateTimeParseException e) {
            System.out.println("The Format you entered is incorrect.");
            return getStartDate();
        }
        catch (IllegalArgumentException e) {
            System.out.println("The School opens at 10:00 and closes at 18:00 please input some time in between then.");
            return getStartDate();
        }
        return date;
    }


    private static LocalDateTime getCheckingDate() {
        Scanner s = new Scanner(System.in);
        LocalDateTime date;
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:m");
            System.out.println("Please enter checking date in format day/month/year hour:minute. (Example: 5/10/2020 18:50)");
            String userInput = s.nextLine();
            date = LocalDateTime.parse(userInput, dateFormat);
        } catch (DateTimeParseException e) {
            System.out.println("The Format you entered is incorrect.");
            return getCheckingDate();
        }
        return date;
    }
}
