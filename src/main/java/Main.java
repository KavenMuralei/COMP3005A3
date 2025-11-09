//Kaven Muraleitharan
//SN: 101305963

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
//    url = "jdbc:postgresql://localhost:5432/a3";
//    user = "postgres";
//    password = "admin";
    private static String url; //link to the database with the student table
    private static String user; // username of the database
    private static String password;//password for the database


    public static void getAllStudents(){
        /*
        displays all student records in the students table

        throws: exception if there is a database connection or query error
         */
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students ORDER BY student_id ASC");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                System.out.print(resultSet.getInt("student_id") + ", ");
                System.out.print(resultSet.getString("first_name") + ", ");
                System.out.print(resultSet.getString("last_name") + ", ");
                System.out.print(resultSet.getString("email") + ", ");
                System.out.println(resultSet.getDate("enrollment_date"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println("Error when collecting the students: "+e);
        }
    }
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        /*
        adds a new student record into the students table

        param: String first_name - the students first name
        param: String last_name - the students last name
        param: String email - the students email
        param: String enrollment_date - the students enrollment date (yy-mm-dd)

        throws: exception if the database connection fails or the SQL statement has an error.
        (typically if the SQL statement has an error, then the user inputted the incorrect format for the database)
         */
        try{
            //reject empty or null fields
            if (first_name == null || first_name.trim().isEmpty() || last_name == null || last_name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
                System.out.println("Error: first name, last name, and email cannot be empty.");
                return; // Stop execution
            }
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (first_name, last_name, email, enrollment_date) " + "VALUES ('" + first_name + "', '" + last_name + "', '" + email + "', '" + enrollment_date + "')");
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println("Error when adding the student: "+ e);
        }
    }

    public static void updateStudentEmail(int student_id, String email){
        /*
        updates the email of an existing student record given their student id

        param: int student_id - the student id of the student we are trying to update
        param: String email - the new email

        throws: exception if the database connection fails or the SQL statement has an error.
        (typically if the SQL statement has an error, then the user inputted the incorrect format for the database)
         */
        try{
            if(email == null){
                System.out.println("Error: email cannot be null");
            }
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET email = '"+email+"' WHERE student_id="+student_id);
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println("Error when updating the students email: "+e);
        }
    }

    public static void deleteStudent(int student_id){
        /*
        delete a student record from the table give their student id

        param: int student_id - the student id of te student we are trying to delete

        throws: exception if the database connection fails or the SQL statement has an error.
        (typically if the SQL statement has an error, then the user inputted the incorrect format for the database)
         */
        try{
            String url = "jdbc:postgresql://localhost:5432/a3";
            String user = "postgres";
            String password = "admin";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM students WHERE student_id="+student_id);
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println("Error when deleting a student "+e);
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (true) { //Loop to ensure the user inputs url, user, and pass.

            try {
                System.out.println("Enter database url:");
                url = scanner.nextLine();
                System.out.println("Enter database username:");
                user = scanner.nextLine();
                System.out.println("Enter database password:");
                password = scanner.nextLine();
                Class.forName("org.postgresql.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                if (connection != null) {
                    System.out.println("Connected to database");
                    break;
                } else {
                    System.out.println("Failed to connect to database.");
                }
            } catch (Exception e) {
                System.out.println("Connection Failed: " + e);
            }
        }
        int options =-1;
        // Loop which executes the implemented functions
        do {
            try{
                System.out.println("Select an option to edit the students table");
                System.out.println("0 - Exit");
                System.out.println("1 - Get all students");
                System.out.println("2 - Add a student");
                System.out.println("3 - Update a student email");
                System.out.println("4 - Delete a student");
                options = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("Error in inputted text: "+e);
            }
            if(options == 1){
                getAllStudents();
            }
            else if(options == 2){
                try{ //try catch to prevent input errors
                    System.out.println("Enter a first name");
                    String firstName= scanner.nextLine();
                    System.out.println("Enter a last name");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter an email");
                    String email = scanner.nextLine();
                    System.out.println("Enter an enrollment date (YYYY-MM-DD)");
                    String date = scanner.nextLine();
                    addStudent(firstName,lastName,email,date);
                }catch (Exception e){
                   System.out.println("Error occurred in the syntax of entered text: "+e);
                }
            }
            else if (options ==3 ) {
                try{//try catch to prevent input errors
                    System.out.println("Enter the student ID you want to delete");
                    int studentID = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the new email");
                    String email = scanner.nextLine();
                    updateStudentEmail(studentID,email);
                }catch (Exception e){
                    System.out.println("Error occurred in the syntax of entered text: "+e);
                }

            }
            else if (options ==4){
                try{//try catch to prevent input errors
                    System.out.println("Enter the student ID you want to delete");
                    int studentID = Integer.parseInt(scanner.nextLine());
                    deleteStudent(studentID);
                }catch(Exception e){
                    System.out.println("Error occurred in the syntax of entered text: "+e);
                }
            }
        } while (options != 0);
    }

}
