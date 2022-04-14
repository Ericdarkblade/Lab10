
import java.io.*;
import java.util.*;

public class PatientList {

    //Class Information
    private String filePath = ""; //String representing the filepath to the records of patients needed to access


    private int patientCount = 0;
    private int sickPatientCount = 0;
    private int recoverPatientCount = 0;
    private int averageAge = 0;

    public int getPatientCount() {
        return patientCount;
    }

    public int getSickPatientCount() {
        return sickPatientCount;
    }

    public int getRecoverPatientCount() {
        return recoverPatientCount;
    }

    public int getAverageAge() {
        return averageAge;
    }

    //DataBase Structure
    private ArrayList<Patient> patientsByFile = new ArrayList<>(); //ArrayList of Arrays representing People and their information

    private Set<Patient> patientsByAge = new TreeSet<Patient>((Patient a, Patient b) -> {//Patients Sorted by Age
        return Integer.compare(a.getAge(), b.getAge());
    });

    private Set<Patient> patientsByFirst = new TreeSet<Patient>(Comparator.comparing(Patient::getFirst));//Patients Sorted by First Name

    private Set<Patient> patientsByLast = new TreeSet<Patient>(Comparator.comparing(Patient::getLast));//Patients Sorted by Last Name

    public Patient getPatient(String name) {
        return patientHashMap.get(name);
    }

    private HashMap<String, Patient> patientHashMap = new HashMap<>();//Hashmap of FirstLast (nospaces) to Patient

    public PatientList(String filePath) throws IOException { //Constructor receiving the filepath to the patient records.
        File f = new File(filePath);
        if (!(f.exists() && !f.isDirectory())) {
            throw new FileNotFoundException();
        } else {
            this.filePath = filePath;
        }
        fileReader();
    }

    public void fileReader() throws IOException {
        BufferedReader reader = null; //Buffered Reader for input File
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String currentLine;

        //Loop Counters
        int counter = 0; //Counter for patient count
        int sickCounter = 0; //Counter for sick patients
        int ageSum = 0; //Sums up every patient's age to find the average after looping

        while (true) {
            try {
                if ((currentLine = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (currentLine.equals("")) {
                break;
            }

            //Data Digestion
            String first = currentLine.substring(0, currentLine.indexOf(' ')); //First Name in the current Line
            currentLine = currentLine.substring(first.length() + 1); //Cuts the First name and whitespace from the currentLine

            String last = currentLine.substring(0, currentLine.indexOf(' ')); //Last Name in the current Line
            currentLine = currentLine.substring(last.length() + 1); //Cuts the Last name and whitespace from the currentLine

            String birth = currentLine.substring(0, currentLine.indexOf(' '));
            currentLine = currentLine.substring(birth.length() + 1); //Cuts the Date and whitespace from the currentLine

            String status = currentLine.substring(0, currentLine.indexOf(' '));

            //Data Digestion
            Patient currentPatient = new Patient(first, last, birth, status);//Current Patient in loop
            addPatient(currentPatient); //Adds patient to array, hashmap, and treesets

            //Counters
            ageSum += currentPatient.getAge(); //Adds the collective age of the patients in order to calculate the average later.
            if (status.equals("sick")) {
                sickCounter++;//Counts the number of sick individuals
            }
            //recovery counter will instead be conducted by taking the total patients - sick patients to safe a few cpu cycles
            counter++; //Counter for total patients
        }
        reader.close();
        patientCount = counter; //Stores patientCount
        sickPatientCount = sickCounter; //Stores sickPatientCount
        recoverPatientCount = patientCount - sickPatientCount; //Calculates the # of Recovered Patients
        averageAge = ageSum / patientCount; //Calculates the average Age of all initial patients


    }

    private void fileWriter(ArrayList<Patient> sortedArrayList) throws IOException { //Must pass in an arraylist (Use getMethods for the sets
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Replace with Set
        for (Patient patient : sortedArrayList) {
            writer.write(patient.getFirst() + patient.getLast() + Integer.toString(patient.getAge()) + patient.getStatus());
        }
        writer.close();//Closed writer

    }

    public void writeByAge() {
        try {
            fileWriter(new ArrayList<>(patientsByAge));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeByFirst() {
        try {
            fileWriter(new ArrayList<>(patientsByFirst));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeByLast() {
        try {
            fileWriter(new ArrayList<>(patientsByLast));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeByFile() {
        try {
            fileWriter(patientsByFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeByRandom() {
        Random randomGenerator = new Random();
        ArrayList<Patient> patientArrayCopy = new ArrayList<>(patientsByFile);//I hope this copies the values
        ArrayList<Patient> randomPatients = new ArrayList<>();
        for(int i = 0; i < patientsByFile.size();i++){
            int randomInt = randomGenerator.nextInt(0,patientArrayCopy.size());
            randomPatients.add(patientArrayCopy.get(randomInt));
        }
        try {
            fileWriter(randomPatients);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPatient(Patient patient) {
        patientsByFirst.add(patient);
        patientsByLast.add(patient);
        patientsByAge.add(patient);
        patientsByFile.add(patient);
        patientHashMap.putIfAbsent(patient.getFirst() + " " + patient.getLast(), patient); //HashMap of Patient Names First+Last -> patient Object
        writeByFile();
    }

    public void removePatient(Patient patient) throws Exception {
        if (patient.getStatus().equals("sick")) {
            throw new Exception("Can not remove sick patient.");
        } else {
            fileReader();
            patientsByFirst.remove(patient);
            patientsByLast.remove(patient);
            patientsByAge.remove(patient);
            patientsByFile.remove(patient);
            patientHashMap.remove(patient.getFirst() + patient.getLast()); //HashMap of Patient Names First+Last -> patient Object
            writeByFile();
        }
    }
}
