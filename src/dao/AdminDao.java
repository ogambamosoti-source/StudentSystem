

import model.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private Connection connection;

    public AdminDao(Connection connection) {
        this.connection = connection;
    }

    // Create new admin
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admins (firstName,lastName,email,phoneNumber,staffId,role,idNumber,username, password) VALUES (?, ?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            statement.setString(1,admin.getFirstName());
            statement.setLastName(2,admin.getLastName());
            statement.setString(3,admin.getEmail());
            statement.setInt(4,admin.getPhoneNumber());
        statement.setString(5,admin.getStaffId());
            statement.setString(6, admin.getUsername());
            statement.setString(7, admin.getPassword());
            statement.setInt(8,admin.getIdNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read admin by ID
    public Admin getAdminById(int id) {
        String sql = "SELECT * FROM admins WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (resultSet.next()) {
                return new Admin(
                    resultSet.getString("firstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("email"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("StaffId"),
                    resultSet.getInt("id"),
                     resultSet.getString("username"),
                      resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all admins
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (rs.next()) {
                admins.add(new Admin(
                    resultSet.getString("firstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("email"),
                    resultSet.getInt("PhoneNumber"),
                    resultSet.getString("StaffId"),
                    resultSet.getInt("id"),
                     resultSet.getString("username"), 
                     resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Update admin
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE admins SET firstName =?,lastName =?,email=?,phoneNumber=?,staffId=?username = ?, password = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            statement.setString(1,admin.getFirstName());
            statement.setLastName(2,admin.getLastName());
            statement.setString(3,admin.getEmail());
            statement.setInt(4,admin.getPhoneNumber());
        statement.setString(5,admin.getStaffId());
            statement.setString(6, admin.getUsername());
            statement.setString(7, admin.getPassword());
            statement.setInt(8,admin.getIdNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete admin
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM admins WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

