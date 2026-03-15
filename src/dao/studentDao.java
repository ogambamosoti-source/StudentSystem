package dao;

import model.Program;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
private Connection connection;

public StudentDao(Connection connection){
    this.connection = connection;
}
public void addStudent(Student student){
    String sql = "INSERT INTO students(idNumber,firstName,lastName,email,phoneNumber,regNumber,password,role,programID)VALUES (?,?,?,?,?,?,?,?,?)";
try(PreparedStatement statement = connection.prepareStatement(sql)){
    statement.setString(1,student.getIdNumber());
    statement.setString(2,student.getFirstName());
    statement.setString(3,student.getLastName());
    statement.setString(4,student.getEmail());
    statement.setInt(5,student.getPhoneNumber());
    statement.setString(6,student.getRegNumber());
    statement.setString(7,student.getPassword());
    statement.setString(8,student.getRole());
    statement.setString(9,student.getProgramID());
    statement.executeUpdate();
    System.out.println("Student added successfully");
}catch(SQLException e){
    e.printStackTrace();
}
}
public Student getStudentByIdNumber(String idNumber){
    String sql = "SELECT* FROM students WHERE idNumber = ?";
    try(PreparedStatement statement = connection.prepareStatement( sql)){
        statement.setString(1, idNumber);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            Program program = new Program(resultSet.getString("programID"));
            return new Student(
                resultSet.getInt("idNumber"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getInt("phoneNumber"),
            resultSet.getString("regNumber"),
        resultSet.getString("password"),
    resultSet.getString("role"),
program);
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
}
public List<Student> getAllStudents(){
    List<Student> students = new ArrayList<>();
    String sql = "SELECT * FROM students";
    try(Statement statement = connection.createStatement()){
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            Program program = new Program(resultSet.getString("programID"));
            students.add(new Student(
                  resultSet.getInt("idNumber"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getInt("phoneNumber"),
                resultSet.getString("regnumber"),
                resultSet.getString("password"),
                resultSet.getString("role"),
                program)
         );
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return students;
}
public void updateStudent(Student student){
    String sql = "UPDATE students SET firstname=?,lastName=?,email=?,phoneNumber=?,regNumber=?,password=?,role=?,program=?WHERE IdNumber=?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,student.getFirstName());
        statement.setString(2,student.getLastName());
        statement.setString(3,student.getEmail());
        statement.setInt(4,student.getPhoneNumber());
        statement.setString(5,student.getRegNumber());
        statement.setString(6,student.getPassword());
        statement.setString(7,student.getRole());
        statement.setString(8,student.getProgramID());
        statement.setString(9,student.getIdNumber());
        statement.executeUpdate();
        System.out.println("Student updated succesfully");
    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void deleteStudent(int idNumber){
    String sql = "DELETE FROM students WHERE idNumber =?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,idNumber);
        statement.executeUpdate();
        System.out.println("Student deleted successfully");
    }catch(SQLException e){
        e.printStackTrace();
    }

}
}
