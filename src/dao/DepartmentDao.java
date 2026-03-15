package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;


public class DepartmentDao {
private Connection connection;

public DepartmentDao(Connection connection){
    this.connection = connection;
}
public void addDepartment(Department department){
    String sql = "INSERT INTO department(name,description)VALUES(?,?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,department.getName());
        statement.setString(2,department.getDescription());
        statement.executeUpdate(sql);
        System.out.println("department added successfully");
    } catch (SQlException e) {
        e.printStackTrace();
    }
}
//read all departments
public List<Department> getAllDepartments(){
    List<Department> departments = new ArrayList();
    String sql = "SELECT *FROM departments";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            department.add(new department(
                resultSet.getString("name"),
                resultSet.getString("descreption")
            ));
            
        }

    }catch(SQLException e){
        e.printStackTrace();
    }
} 
//single department by name 
public Department getDepartmentName(String name){
    String sql = "SELECT FROM department WHERE name =?";
    try (PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,name);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return new department(
                resultSet.getString("name"),
                resultSet.getString("descreption")
            );
        }    
    } catch (Exception e) {
    e.printStackTrace();
    }
    return null;
}
    // UPDATE
    public void updateDepartment(Department department) {
        String sql = "UPDATE department SET description = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, department.getDescription());
            statement.setString(2, department.getName());
            statement.executeUpdate();
            System.out.println("Department updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteDepartment(String name) {
        String sql = "DELETE FROM department WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Department deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
