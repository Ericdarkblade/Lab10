import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("1. Add a patient to the file (We don’t consider patients have duplicate names)");
        System.out.println("2. Delete a patient record");
        System.out.println("3. Count the total number of sick and recovered patients");
        System.out.println("4. Find average age for all patients");
        System.out.println("5. Sort patients by age");
        System.out.println("6. Sort patients by first name or last name (We don’t consider patients have middle name)");
        System.out.println("7. Shuffle patients");
        System.out.println("8. Exit");
    }

    public static void main(String[] args) {
        //Laziest Menu Ever
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter patient filePath");
        String filePath = in.toString();
        while (true) {
            menu();
            int selection = in.nextInt();
            if (selection == 1) {
                System.out.println("\nPlease Input Name in the format 'First Last'.");
                String name = in.toString();
                System.out.println("Please Input BirthDate in the format 'mm/dd/yyyy'.");
                String birth = in.toString();
                try {
                    FileIO.addPatient(name, birth, filePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (selection == 2) {
                System.out.println("\nPlease Input Name in the format 'First Last'.");
                String name = in.toString();
                try {
                    FileIO.deletePatient(name, filePath);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (selection == 3) {
                System.out.println("Please enter the status of patients you'd like to count");
                System.out.println("Options: \nsick\nrecover\n'', will return total count");
                String status = in.toString();
                try {
                    FileIO.countPatients(status, filePath);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (selection == 4) {
                int Average = 0;
                try {
                    Average = FileIO.averageAge(filePath);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Average Age is: " + Average);
            } else if (selection == 5) {
                try {
                    FileIO.sortPatientsByAge(filePath);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Sorted Patients by Age");
            } else if (selection == 6) {
                System.out.println("\nA: Sort by First Name");
                System.out.println("B: Sort by Last Name");
                String option =
                if ()

            }
        }
    }
}

