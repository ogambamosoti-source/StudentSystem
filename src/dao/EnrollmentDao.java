package dao;
import java.util.ArrayList;
import java.util.List;
import model.Enrollment;
import java.sql.*;

public class EnrollmentDao {
private Connection connection;
public EnrollmentDao(Connection connection){
    this.connection = connection;
}
    // CREATE
    public void addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment(studentId, programId, enrollmentDate, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, enrollment.getStudentId());
            statement.setInt(2, enrollment.getProgramId());
            statement.setDate(3, Date.valueOf(enrollment.getEnrollmentDate())); // assuming LocalDate
            statement.setString(4, enrollment.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (all enrollments)
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollment";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                    rs.getInt("studentId"),
                    rs.getInt("programId"),
                    rs.getDate("enrollmentDate").toLocalDate(),
                    rs.getString("status")
                );
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // READ (single enrollment by student + program)
    public Enrollment getEnrollment(int studentId, int programId) {
        String sql = "SELECT * FROM enrollment WHERE studentId = ? AND programId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, programId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                    rs.getInt("studentId"),
                    rs.getInt("programId"),
                    rs.getDate("enrollmentDate").toLocalDate(),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE enrollment SET enrollmentDate = ?, status = ? WHERE studentId = ? AND programId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(enrollment.getEnrollmentDate()));
            statement.setString(2, enrollment.getStatus());
            statement.setInt(3, enrollment.getStudentId());
            statement.setInt(4, enrollment.getProgramId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteEnrollment(int studentId, int programId) {
        String sql = "DELETE FROM enrollment WHERE studentId = ? AND programId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, programId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

