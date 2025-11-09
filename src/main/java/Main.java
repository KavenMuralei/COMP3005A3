import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int options = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Select an option to edit the students table");
            System.out.println("0 - Exit");
            System.out.println("1 - Get all students");
            System.out.println("2 - Add a student");
            System.out.println("3 - Update a student email");
            System.out.println("4 - Delete a student");
            options = Integer.parseInt(scanner.nextLine());

            if(options == 1){
                getAllStudents();
            }
            else if(options == 2){
                try{
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
                   System.out.println("Error occurred when adding student: "+e);
                }
            }
            else if (options ==3 ) {
                System.out.println("Enter the student ID you want to delete");
                int studentID = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter the new email");
                String email = scanner.nextLine();
                updateStudentEmail(studentID,email);
            }
            else if (options ==4){
                System.out.println("Enter the student ID you want to delete");
                int studentID = Integer.parseInt(scanner.nextLine());
                deleteStudent(studentID);
            }

        } while (options != 0);
    }
    public static void getAllStudents(){
        try{
            String url = "jdbc:postgresql://localhost:5432/a3";
            String user = "postgres";
            String password = "admin";
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
            System.out.println(e);
        }
    }
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        try{
            String url = "jdbc:postgresql://localhost:5432/a3";
            String user = "postgres";
            String password = "admin";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO students (first_name, last_name, email, enrollment_date) " + "VALUES ('" + first_name + "', '" + last_name + "', '" + email + "', '" + enrollment_date + "')");
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateStudentEmail(int student_id, String email){
        try{
            String url = "jdbc:postgresql://localhost:5432/a3";
            String user = "postgres";
            String password = "admin";
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET email = '"+email+"' WHERE student_id="+student_id);
            statement.close();
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void deleteStudent(int student_id){
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
            System.out.println(e);
        }
    }
}
