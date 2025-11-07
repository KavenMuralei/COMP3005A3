import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/a3";
        String user = "postgres";
        String password = "admin";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void getAllStudents(Statement state){
        try{
            state.executeQuery("SELECT * FROM students");
            ResultSet resultSet = state.getResultSet();
            while(resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
                System.out.println(resultSet.getInt("student_id"));
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
