package dao;
import model.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LecturerDao {
private Connection connection;

public LecturerDao(Connection connection){
    this.connection = connection;
}
//ad lecturer
public void addLecturer(Lecturer lecturer){
    String sql = "INSERT INTO Lecturers(idNNumber,firstName,lastName,email,phoneNumber,department)";
    try(PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setInt(1,lecturer.getIdNumber());
        statement.setString(2,lecturer.getFirstName());
        statement.setString(3,lecturer.getLastName() );
        statement.setString(4,lecturer.getEmail());
        statement.setString(5,lecturer.getUserName());
        statement.setInt(6,lecturer.getPhoneNumber());
        statement.setString(7,lecturer.getDepartment());
        statement.executeUpdate();
        System.out.println("Lecture added successfully");
        
    }catch(SQLException e){
        e.printStackTrace();
    }
}
public Lecturer getLecturerById(int idNumber){
    String sql = "SELECT * FROM lecturers WHERE idNumber =?";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1,idNumber);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new Lecturer(
                resultSet.getInt("idNumber"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("email"),
                resultSet.getInt("phoneNumber"),
                resultSet.getString("username"),
                resultSet.getString("department")); 
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
    return null;
}
public List<Lecturer>getAllLecturers(){
    List<lecturer> lecturers = new ArrayList<>();
    String sql = "SELECT * FROM lecturers";
    try(Statement statement = connection.createStatement()){
        lecturers.add(new lecturer(
resultSet.getInt("idNumber"),
resultSet.getString("firstName"),
resultSet.getString("lastName"),
resultSet.getString("email"),
resultSet.getInt("phoneNumber"),
resultSet.getString("userName"),
resultSet.getString("department")
        ));
    }catch(SQLException e){
        e.printStackTrace();
    }
    return lecturers;
}
public void updatelecturer(String lecturer){
    String sql = "UPDATE lecturer SET firstName=?,lastName=?,email=?,phoneNumber=?,staffId=?,idNumber=?,userName=?,department=?";
    try(PreparedStatement prepareStatement = connection.prepareStatement(sql)){
        statement.SetString(1,lecturer.getFirstName());
        statement.setString(2,lecturer.getLastName());
        statement.setString(3,lecturer.getEmail());
        statement.setInt(4,lecturer.getPhoneNumber());
        statement.setInt(5,lecturer.getIdNumber());
        statement.setString(6,lecturer.getUserName());
        statement.setString(7,leturer.getDepartment());
        statement.executeUpdate();
        System.out.println("student updated successfully");
    }catch(SQLException e){
e.printStackTrace();
    }
    public void deleteLecturer(int idNumber){
        String sql = "DELETE FROM lecturers WHERE idNumber =?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,idNumber);
            statement.executeUpdate();
            System.out.println("lecturer deleted successfully");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
