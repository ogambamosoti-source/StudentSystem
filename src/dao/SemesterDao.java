package dao;
import java.sql.SQLException;
import java.sql.*;
import model.Semester;
import java.util.ArrayList;
import java.util.List;



public class SemesterDao {
private Connection connection;
public SemesterDao(Connection connection){
    this.connection = connection;
}
    // CREATE
    public void addSemester(Semester semester) {
        String sql = "INSERT INTO semester(academicYear, semester, startDate, endDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, semester.getAcademicYear());
            statement.setString(2, semester.getSemester());
            statement.setDate(3, Date.valueOf(semester.getStartDate())); // assuming LocalDate in model
            statement.setDate(4, Date.valueOf(semester.getEndDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (all semesters)
    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        String sql = "SELECT * FROM semester";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Semester semester = new Semester(
                    rs.getInt("academicYear"),
                    rs.getString("semester"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate()
                );
                semesters.add(semester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semesters;
    }

    // READ (single semester by academicYear + semester)
    public Semester getSemester(int academicYear, String semesterName) {
        String sql = "SELECT * FROM semester WHERE academicYear = ? AND semester = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, academicYear);
            statement.setString(2, semesterName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Semester(
                    rs.getInt("academicYear"),
                    rs.getString("semester"),
                    rs.getDate("startDate").toLocalDate(),
                    rs.getDate("endDate").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateSemester(Semester semester) {
        String sql = "UPDATE semester SET startDate = ?, endDate = ? WHERE academicYear = ? AND semester = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(semester.getStartDate()));
            statement.setDate(2, Date.valueOf(semester.getEndDate()));
            statement.setInt(3, semester.getAcademicYear());
            statement.setString(4, semester.getSemester());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteSemester(int academicYear, String semesterName) {
        String sql = "DELETE FROM semester WHERE academicYear = ? AND semester = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, academicYear);
            statement.setString(2, semesterName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
