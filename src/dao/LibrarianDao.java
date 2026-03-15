package dao;
import model.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LibrarianDao {
    private Connection connection;

    public LibrarianDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void addLibrarian(Librarian librarian) {
        String sql = "INSERT INTO librarian(firstName, lastName, email, phoneNumber, idNumber, role, staffId, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, librarian.getFirstName());
            statement.setString(2, librarian.getLastName());
            statement.setString(3, librarian.getEmail());
            statement.setString(4, librarian.getPhoneNumber());
            statement.setString(5, librarian.getIdNumber());
            statement.setString(6, librarian.getRole());
            statement.setString(7, librarian.getStaffId());
            statement.setString(8, librarian.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (all librarians)
    public List<Librarian> getAllLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        String sql = "SELECT * FROM librarian";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Librarian librarian = new Librarian(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("idNumber"),
                    rs.getString("role"),
                    rs.getString("staffId"),
                    rs.getString("password")
                );
                librarians.add(librarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarians;
    }

    // READ (single librarian by staffId)
    public Librarian getLibrarianByStaffId(String staffId) {
        String sql = "SELECT * FROM librarian WHERE staffId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, staffId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Librarian(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("idNumber"),
                    rs.getString("role"),
                    rs.getString("staffId"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateLibrarian(Librarian librarian) {
        String sql = "UPDATE librarian SET firstName = ?, lastName = ?, email = ?, phoneNumber = ?, idNumber = ?, role = ?, password = ? WHERE staffId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, librarian.getFirstName());
            statement.setString(2, librarian.getLastName());
            statement.setString(3, librarian.getEmail());
            statement.setString(4, librarian.getPhoneNumber());
            statement.setString(5, librarian.getIdNumber());
            statement.setString(6, librarian.getRole());
            statement.setString(7, librarian.getPassword());
            statement.setString(8, librarian.getStaffId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteLibrarian(String staffId) {
        String sql = "DELETE FROM librarian WHERE staffId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
