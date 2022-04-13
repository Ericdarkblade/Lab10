// VSCODE

public class IOReader {
    public static String isBirthVaild (int day, int month, int year){
//check if the given birth is valid.
    return null;
    }
    public static void addPatient (String name, String Birth, String fileName) throws IOException {
// Add a new patient record to the file.
// if given birth is not valid, then patient will not be added into the file.
// Birth must save in a format of Month/Day/Year, in total length of 10, such
//        as “02/03/2022”, “11/12/2001”, “01/24/1998”, “12/01/1980” and so on.
    }
    public static void deletePatient (String name, String fileName) throws IOException {
// Delete an existing patient record from the file
// can’t delete if the patient still sick.
// if there are two patients occur with the same name, and they are both not
// sick, only delete the first one. Otherwise, delete the first patient who is not sick.
    }
    public static int countPatients(String status, String fileName) throws FileNotFoundException {
// return numbers of sick patients or recovery patients in the file.
// if client given “” (empty string), then return the total number of patients.
    }
    public static void averageAge(String fileName) throws FileNotFoundException {
// find the average age for all patients in the file.
    }
    public static void sortPatientsByAge(String fileName)throws FileNotFoundException {
// modify file by sorting patients by age for all patients from young to old
    }
    public static void sortPatientsByName(String firstOrLast, String fileName)throws FileNotFoundException {
// modify file by sorting patients by first name or last name for all patients from a~z
    }
    public static void shufflePatients(String fileName)throws FileNotFoundException
    {
// modify file by shuffle all patients, so they are not in any order
// Using random in this method is required.
    }
}
