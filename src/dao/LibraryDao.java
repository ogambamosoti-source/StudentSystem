package dao;

import model.Library;
import model.Person;
import model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDao {
    private Connection connection;

    public LibraryDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void addLibrary(Library library) {
        String sql = "INSERT INTO library(borrowerId) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, library.getBorrower().getId()); // assuming Person has getId()
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                int libraryId = keys.getInt(1);
                System.out.println("Library record created with ID: " + libraryId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (all libraries)
    public List<Library> getAllLibraries() {
        List<Library> libraries = new ArrayList<>();
        String sql = "SELECT * FROM library";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // You’d need to fetch Person and Book objects separately
                Person borrower = new Person(rs.getInt("borrowerId"), rs.getString("name")); 
                Library library = new Library(borrower);
                libraries.add(library);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libraries;
    }

    // READ (single library by ID)
    public Library getLibraryById(int libraryId) {
        String sql = "SELECT * FROM library WHERE libraryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libraryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Person borrower = new Person(rs.getInt("borrowerId"), rs.getString("name"));
                return new Library(borrower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateLibraryBorrower(int libraryId, Person borrower) {
        String sql = "UPDATE library SET borrowerId = ? WHERE libraryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrower.getId());
            statement.setInt(2, libraryId);
            statement.executeUpdate();
            System.out.println("Library borrower updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteLibrary(int libraryId) {
        String sql = "DELETE FROM library WHERE libraryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, libraryId);
            statement.executeUpdate();
            System.out.println("Library deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
} 
