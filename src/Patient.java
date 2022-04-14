import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class Patient {
    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getAge() {
        return age;
    }

    public String getStatus() {
        return status;
    }

    private final String first;
    private final String last;
    private int age;
    private String status;

    public Patient(String first, String last, String age, String status) {
        this.first = first;
        this.last = last;
        this.age = ageCalculator(age);
        this.status = status;
    }

    public static int ageCalculator(String birthDate) {
        /*
        Takes a String formatted mm/dd/yyyy and calculates the current age in years.
         */
        int month = Integer.getInteger((birthDate.substring(0, 2)));
        int day = Integer.getInteger((birthDate.substring(3, 5)));
        int year = Integer.getInteger((birthDate.substring(6)));

        LocalDate currentDate = LocalDate.now(); //Present Date
        LocalDate inputDate = LocalDate.of(year, month, day); //Date of individual string

        Period difference = Period.between(currentDate, inputDate); //Difference between birthDate and today

        return difference.getYears(); //Returns yearly difference
    }

}