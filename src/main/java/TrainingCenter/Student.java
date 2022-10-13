package TrainingCenter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static Constants.Constants.*;

public class Student {
    private String firstName;
    private String lastName;
    private Program program;
    private LocalDateTime startDate;

    public Student(String firstName, String lastName, Program program, LocalDateTime startDate) {
        setFirstName(firstName);
        setLastName(lastName);
        this.program = program;
        setStartDate(startDate);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.lastName = lastName;
        }
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            startDate = startDate.plusDays(2);
        }
        if (startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            startDate = startDate.plusDays(1);
        }
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + program.getName() + ") ";
    }

    public String hasFinished(LocalDateTime timeOfChecking) {
        LocalDateTime finishDate = calculateFinishDate();
        long days;
        long hours;
        String hasFinished;
        if (timeOfChecking.isBefore(finishDate)) {
            hasFinished = "Training is not finished. ";
            hours = timeOfChecking.until(finishDate, ChronoUnit.HOURS);
            days = hours / 24;
            hours = hours % 24;
            hasFinished += days > 0 ? days + " d " : "";
            hasFinished += hours + " hours are left until the end.";

        } else {
            hasFinished = "Training completed. ";
            hours = finishDate.until(timeOfChecking, ChronoUnit.HOURS);
            days = hours / 24;
            hours = hours % 24;
            hasFinished += days > 0 ? days + " d " : "";
            hasFinished += hours + " hours have passed since the end.";
        }
        return hasFinished;
    }

    public LocalDateTime calculateFinishDate() {

        if (program.getDurationInHours() == 0) {
            return startDate;
        }

        LocalDateTime programFinishDate = startDate;

        int remainingDays = program.getDurationInHours() / WORKING_HOURS;
        int remainingHours = program.getDurationInHours() % WORKING_HOURS;

        if (remainingHours == 0 && startDate.getHour() == 10 && startDate.getMinute() == 0) {
            remainingDays = remainingDays == 0 ? remainingDays : remainingDays - 1;
            remainingHours += WORKING_HOURS;
        }

        while (remainingDays-- > 0) {
            programFinishDate = programFinishDate.plusDays(1);

            if (programFinishDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                programFinishDate = programFinishDate.plusDays(2);
            }
            if (programFinishDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                programFinishDate = programFinishDate.plusDays(1);
            }
        }

        programFinishDate = programFinishDate.plusHours(remainingHours);

        if (programFinishDate.getHour() >= 18 && !(programFinishDate.getHour() == 18 && programFinishDate.getMinute() == 0)) {
            programFinishDate = programFinishDate.plusDays(1);
            programFinishDate = programFinishDate.minusHours(8);
        }

        if(programFinishDate.getHour() == 0)
        {
            programFinishDate = programFinishDate.plusHours(16);
        }

        if (programFinishDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            programFinishDate = programFinishDate.plusDays(2);
        }
        if (programFinishDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            programFinishDate = programFinishDate.plusDays(1);
        }
        return programFinishDate;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
