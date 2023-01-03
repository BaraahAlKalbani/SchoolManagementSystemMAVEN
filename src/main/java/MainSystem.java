import java.io.*;
import java.util.*;

/**
 * 
 * @author Bara'ah Nasser Al-Kalbani
 * 
 *         a School Management System to manage Students and Teachers Records in
 *         an external .csv File
 *
 */
public class MainSystem {
    /**
     * This Program will Write to a .csv file get records from it and delete and add
     * 
     * @param args: the Main Method argument
     */
    public static void main(String[] args) {

        // TODO Auto-generated method stub
        // this will check if the needed files is already created or not
        String StudentFileName = "dataFile/Students.csv";
        String TeachersFileName = "dataFile/Teachers.csv";
        try {
            File StudentFile = new File(StudentFileName);
            File teacherFile = new File(TeachersFileName);
            // Open the mainMenuInput and output files
            if (StudentFile.createNewFile()) {
                System.out.println("StudentFile created successfully");
            }

            if (teacherFile.createNewFile()) {
                System.out.println("teacherFile created successfully");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        // these are the Linked Hash Maps Needed for Saving re Records of the students
        // and the Teachers
        LinkedHashMap<String, String[]> studentsList = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> teachersList = new LinkedHashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("\t\t--School Managment System--");
        System.out.println("_________________________________________________");

        boolean mainMenu = false;
        while (mainMenu != true) {
            System.out.println("Select Option:");
            System.out.println("\t1> Admin Pannel");
            System.out.println("\t2> Career Opertonities");
            System.out.println("\t3> ADMISION ");
            System.out.println("\t4> Display Lists ");
            System.out.println("\t5> Exit Program ");
            System.out.println("\tEnter Your mainMenuInput:");
            int mainMenuInput = sc.nextInt();
            sc.nextLine();
            System.out.println("**************************************************\n");
            if (mainMenuInput == 1) {
                boolean adminMenu = false;
                while (adminMenu == false) {

                    System.out.println("\t\t**Admin**");
                    System.out.println("Select Option:");
                    System.out.println("\t1> Manage Student");
                    System.out.println("\t2> Manage Teacher ");
                    System.out.println("\t3> Main menu");
                    int adminMenuInput = sc.nextInt();
                    sc.nextLine();

                    if (adminMenuInput == 1) {
                        boolean adminStudentManagmentMenu = false;
                        while (!adminStudentManagmentMenu) {
                            System.out.println("*******************ADMIN*********************\n");
                            System.out.println("\t\t**Manage Student**");
                            System.out.println("_________________________________________________");
                            System.out.println("\t1> Edit student");
                            System.out.println("\t2> delete student ");
                            System.out.println("\t3> go back");
                            int adminStudentMenuInput = sc.nextInt();
                            sc.nextLine();
                            switch (adminStudentMenuInput) {
                            case 1:
                                // first get the Id from the user than Check if it exist or not
                                // update the list from the file
                                updateList(StudentFileName, studentsList);
                                System.out.println("Enter student Id to edit:");
                                String InputID = sc.nextLine();
                                if (studentsList.containsKey(InputID)) {
                                    // if the ID is there in the file
                                    System.out.println("Enter student name edit:");
                                    String studentName = sc.nextLine();
                                    System.out.println("Enter student email edit:");
                                    String studentEmail = sc.nextLine();
                                    System.out.println("Enter student enroll status  true/false to edit:");
                                    String studentStatus = sc.nextLine();

                                    String[] newStudentRecord = { InputID, studentName, studentEmail, studentStatus };

                                    studentsList.put(InputID, newStudentRecord);
                                    // put the updated HashMap into the file
                                    UpdateFile(StudentFileName, studentsList);
                                } else {
                                    System.out.println("Invalid Id value");
                                }

                                break;
                            case 2:
                                // update the list from the file
                                updateList(StudentFileName, studentsList);
                                System.out.println("Enter student Id to delete:");
                                String InputID1 = sc.nextLine();
                                // if the ID is there in the file

                                if (studentsList.containsKey(InputID1)) {
                                    // remove from the hash map
                                    studentsList.remove(InputID1);
                                    // add the HashMap to the File
                                    UpdateFile(StudentFileName, studentsList);
                                    System.out.println("student Deleted");

                                } else {
                                    System.out.println("Invalid ID");
                                }
                                break;
                            case 3:
                                // go back to the previous menu
                                adminStudentManagmentMenu = true;
                                break;

                            default:
                                System.out.println("Invalid mainMenuInput");
                                break;
                            }
                        }

                    } else if (adminMenuInput == 2) {
                        boolean adminTeacherMenu = false;
                        while (!adminTeacherMenu) {
                            System.out.println("*******************ADMIN*********************\n");
                            System.out.println("\t\t**Manage Teacher List**");
                            System.out.println("_________________________________________________");
                            System.out.println("\t1> Edit Teacher");
                            System.out.println("\t2> delete Teacher ");
                            System.out.println("\t3> go back");
                            int adminTeacherMenuInput = sc.nextInt();
                            sc.nextLine();
                            switch (adminTeacherMenuInput) {
                            case 1:
                                // update the list from the file
                                updateList(TeachersFileName, teachersList);
                                System.out.println("Enter Teacher Id to edit:");
                                String InputID = sc.nextLine();
                                // if the ID is there in the file
                                if (studentsList.containsKey(InputID)) {
                                    System.out.println("Enter Teacher name edit:");
                                    String teacherName = sc.nextLine();
                                    System.out.println("Enter Teacher email edit:");
                                    String teacherEmail = sc.nextLine();
                                    System.out.println("Enter Teacher subject edit:");
                                    String teacherSubject = sc.nextLine();
                                    System.out.println("Enter Teacher salary edit:");
                                    String teacherSalary = sc.nextLine();
                                    String[] teaNewData = { InputID, teacherName, teacherEmail, teacherSubject,
                                            teacherSalary };
                                    studentsList.put(InputID, teaNewData);
                                    // add the HashMap to the File
                                    UpdateFile(TeachersFileName, teachersList);
                                } else {
                                    System.out.println("Invalid Id value");
                                }

                                break;
                            case 2:
                                // update the list from the file
                                updateList(TeachersFileName, teachersList);
                                System.out.println("Enter Teacher Id to delete:");
                                String InputID1 = sc.nextLine();
                                // if the ID is there in the file
                                if (teachersList.containsKey(InputID1)) {
                                    // remove from the hash list
                                    teachersList.remove(InputID1);
                                    // add the HashMap to the File
                                    UpdateFile(TeachersFileName, teachersList);
                                    System.out.println("Teacher Deleted");
                                } else {
                                    System.out.println("Invalid ID");
                                }
                                break;
                            case 3:
                                // go back to the previous menu
                                adminTeacherMenu = true;
                                break;

                            default:
                                System.out.println("Invalid mainMenuInput");
                                break;
                            }
                        }

                    } else if (adminMenuInput == 3) {
                        // go back to the previous menu
                        adminMenu = true;
                    }
                }
            } else if (mainMenuInput == 2) {
                boolean careerOpMenu = false;
                while (careerOpMenu == false) {
                    System.out.println("\t\t**Career Opertonities**");
                    System.out.println("Select Option:");
                    System.out.println("\t1> add Teacher ");
                    System.out.println("\t2> Main menu");
                    int careerOpInput = sc.nextInt();
                    sc.nextLine();
                    if (careerOpInput == 1) {
                        System.out.println("\t\t**Hire new Teacher**");
                        System.out.println("_________________________________________________");
                        System.out.println("Enter new Teacher name:");
                        String teacherName = sc.nextLine();
                        System.out.println("Enter new Teacher email :");
                        String teacherEmail = sc.nextLine();
                        System.out.println("Enter new Teacher subject :");
                        String teacherSubject = sc.nextLine();
                        System.out.println("Enter new Teacher salary :");
                        String teacherSalary = sc.nextLine();
                        // update the HashMap
                        updateList(TeachersFileName, teachersList);
                        // get the new Teacher ID
                        int lastTeacherId = 0;
                        for (String key : teachersList.keySet()) {
                            lastTeacherId = Integer.parseInt(key);

                        }
                        int newTeacherID = lastTeacherId + 1;
                        String[] newTeacher = { String.valueOf(newTeacherID), teacherName, teacherEmail, teacherSubject,
                                teacherSalary };
                        // Add the New Teacher Record Into the File
                        addRecordToFile(TeachersFileName, newTeacher);

                    } else if (careerOpInput == 2) {
                        // Go to the Previous Menu
                        careerOpMenu = true;
                    } else {
                        System.out.println("\t--Invalid Input--");
                        System.out.println("_________________________________________________");
                    }

                }

            } else if (mainMenuInput == 3) {
                boolean admisionMenu = false;
                while (admisionMenu != true) {
                    System.out.println("\t\t**Admision**");
                    System.out.println("Select Option:");
                    System.out.println("\t1> add Student ");
                    System.out.println("\t2> Main menu");
                    int admisionMenuInput = sc.nextInt();
                    sc.nextLine();
                    if (admisionMenuInput == 1) {
                        System.out.println("\t\t**Regester new Student**");
                        System.out.println("_________________________________________________");
                        System.out.println("Enter new student name :");
                        String studentName = sc.nextLine();
                        System.out.println("Enter new student email :");
                        String studentEmail = sc.nextLine();
                        System.out.println("Enter student enroll status  true/false:");
                        String studentStatus = sc.nextLine();
                        // Update The File
                        updateList(StudentFileName, studentsList);
                        // get the New Student ID
                        int lastStudentId = 0;
                        for (String key : studentsList.keySet()) {
                            lastStudentId = Integer.parseInt(key);

                        }
                        int newStudentId = lastStudentId + 1;
                        String[] newStudentRecord = { String.valueOf(newStudentId), studentName, studentEmail,
                                studentStatus };
                        // Insert the new student records Into the File
                        addRecordToFile(StudentFileName, newStudentRecord);

                    } else if (admisionMenuInput == 2) {
                        admisionMenu = true;
                    } else {
                        System.out.println("\t--Invalid Input--");
                        System.out.println("_________________________________________________");
                    }
                }

            } else if (mainMenuInput == 4) {
                boolean displayMenu = false;
                while (displayMenu == false) {
                    System.out.println("Select Option:");
                    System.out.println("\t1> Display Students List ");
                    System.out.println("\t2> Display Teachers");
                    System.out.println("\t3> Main menu");
                    int DisplayMenuInput = sc.nextInt();
                    sc.nextLine();
                    if (DisplayMenuInput == 1) {

                        // update the List
                        updateList(StudentFileName, studentsList);
                        System.out.println("\t1> Display All Students ");
                        System.out.println("\t2> Display a Specific Student");//
                        System.out.println("Select Option:");
                        int displayStudentMenuInput = sc.nextInt();
                        sc.nextLine();
                        if (displayStudentMenuInput == 2) {
                            System.out.println("Enter Student ID:");
                            String studentID = sc.nextLine();
                            if (studentsList.containsKey(studentID)) {
                                System.out.println(Arrays.toString(studentsList.get(studentID)));
                            } else {
                                System.out.println("Invalid ID!!");

                            }
                        } else if (displayStudentMenuInput == 1) {
                            displayAllList(studentsList);
                        }

                    } else if (DisplayMenuInput == 2) {
                        // update the List
                        updateList(TeachersFileName, teachersList);
                        System.out.println("\t1> Display All Teachers ");
                        System.out.println("\t2> Display a Specific Teacher");//
                        System.out.println("Select Option:");
                        int displayTeacherMenuInput = sc.nextInt();
                        sc.nextLine();
                        if (displayTeacherMenuInput == 2) {
                            System.out.println("Enter Teacher ID:");
                            String teacherID = sc.nextLine();
                            if (teachersList.containsKey(teacherID)) {
                                System.out.println(Arrays.toString(teachersList.get(teacherID)));
                            } else {
                                System.out.println("Invalid ID!!");

                            }
                        } else if (displayTeacherMenuInput == 1) {

                            displayAllList(teachersList);
                        }
                    } else if (DisplayMenuInput == 3) {
                        // go to the previous Menu
                        displayMenu = true;
                    } else {
                        System.out.println("\t--Invalid mainMenuInput--");
                        System.out.println("_________________________________________________");
                    }

                }

            } else if (mainMenuInput == 5) {
                mainMenu = true;
                System.out.println("\t--Shuting Off Program--");
            } else {
                System.out.println("\t--Invalid mainMenuInput--");
                System.out.println("_________________________________________________");
            }
        }
    }

    /**
     * This method will update the LinkedHashMap from the File
     * 
     * @param fileLocation: the Location of the File
     * @param list:         the LinkedHashMap to store in it the data
     */
    public static void updateList(String fileLocation, LinkedHashMap<String, String[]> list) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            String line;
            // while the line is not empty:
            while ((line = reader.readLine()) != null) {
                // Insert the line content into an newRecordArray and separate the columns by ,
                String[] box = line.split(",");
                // put the Id as the Key and the whole newRecordArray as the value
                list.put(box[0], box);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The StudentFile not found" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Method will display the content of the Linked HashMap
     * 
     * @param list: The Linked Hash MAp that Will Be Displayed
     */
    public static void displayAllList(LinkedHashMap<String, String[]> list) {
        for (String key : list.keySet()) {
            // Display the List
            System.out.println(Arrays.toString(list.get(key)));
        }
    }

    /**
     * This Method will update the File from Scratch using the LinkedHashMap
     * 
     * @param fileLocation: the Location of the File
     * @param list:         the LinkedHashMap to store in it the data
     */
    public static void UpdateFile(String fileLocation, LinkedHashMap<String, String[]> list) {
        FileWriter writer;
        try {
            writer = new FileWriter(fileLocation);
            for (String key : list.keySet()) {
                String[] lineValue = list.get(key);
                String lineString = String.join(",", lineValue);
                writer.write(lineString + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * This Method Will add the new data to the file needed.
     * 
     * @param fileLocation:  the Location of the File
     * @param newRecordArray : the String[] Array containing the data needed to be
     *                       saved in the File
     */
    public static void addRecordToFile(String fileLocation, String[] newRecordArray) {
        try {
            File StudentFile = new File(fileLocation);
            // Open the mainMenuInput and output files
            if (StudentFile.createNewFile()) {
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
            writer.write(String.join(",", newRecordArray) + "\n");
            System.out.println("Added");
            writer.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            System.out.println("The StudentFile not found" + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
