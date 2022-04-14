import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class FileIO {

    public static String isBirthValid(int day, int month, int year) {
//check if the given birth is valid.
        try {
            LocalDate betterThanWhatICouldDo = LocalDate.of(year, month, day);
            return String.format("%1$02d/%2$02d/%3$04d", month, day, year); //Formatted to always be 10 String
        } catch (Exception e) {
            System.out.println("IllegalDateProvided");
            throw new RuntimeException(e);
        }
    }

    public static void addPatient(String name, String Birth, String fileName) throws IOException {
// Add a new patient record to the file.
// if given birth is not valid, then patient will not be added into the file.
// Birth must save in a format of Month/Day/Year, in total length of 10, such
//        as “02/03/2022”, “11/12/2001”, “01/24/1998”, “12/01/1980” and so on.
        PatientList notWastingWork = new PatientList(fileName);
        String first = name.substring(0, name.indexOf(' '));
        String last = name.substring(name.indexOf(' ') + 1);
        notWastingWork.addPatient(new Patient(first, last, Birth, "sick")); //Told to manually insert status
    }

    public static void deletePatient(String name, String fileName) throws Exception {
// Delete an existing patient record from the file
// can’t delete if the patient still sick.
// if there are two patients occur with the same name, and they are both not
// sick, only delete the first one. Otherwise, delete the first patient who is not sick.
        PatientList notWastingWork = new PatientList(fileName);
        String first = name.substring(0, name.indexOf(' '));
        String last = name.substring(name.indexOf(' ') + 1);
        notWastingWork.removePatient(notWastingWork.getPatient(name)); //Told to manually insert status
    }

    public static int countPatients(String status, String fileName) throws Exception {
// return numbers of sick patients or recovery patients in the file.
// if client given “” (empty string), then return the total number of patients.
        PatientList notWastingWork;
        try {
            notWastingWork = new PatientList(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (status.toLowerCase().equals("sick")) {
            notWastingWork.getSickPatientCount();
        } else if (status.toLowerCase().equals("recover")) {
            notWastingWork.getRecoverPatientCount();
        } else {
            throw new Exception("Status argument is Illegal. Please enter either 'sick' or 'recover'");
        }
        return -1;
    }

    public static int averageAge(String fileName) throws FileNotFoundException {
// find the average age for all patients in the file.
        PatientList notWastingWork;
        try {
            notWastingWork = new PatientList(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return notWastingWork.getAverageAge();
    }

    public static void sortPatientsByAge(String fileName) throws FileNotFoundException {
// modify file by sorting patients by age for all patients from young to old
        PatientList notWastingWork;
        try {
            notWastingWork = new PatientList(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notWastingWork.writeByAge();
    }

    public static void sortPatientsByName(String firstOrLast, String fileName) throws FileNotFoundException {
// modify file by sorting patients by first name or last name for all patients from a~z
        PatientList notWastingWork;
        try {
            notWastingWork = new PatientList(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (firstOrLast.equals("first")) {
            notWastingWork.writeByFirst();//Binary Trees are not Collection.Sort
        } else if (firstOrLast.equals("last")) {
            notWastingWork.writeByLast();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void shufflePatients(String fileName) throws FileNotFoundException {
// modify file by shuffle all patients, so they are not in any order
// Using random in this method is required.
        PatientList notWastingWork;
        try {
            notWastingWork = new PatientList(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        notWastingWork.writeByRandom();
    }
}
