package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CourseOffering;

public class CourseOfferingDao {
    private Connection connection;

    public CourseOfferingDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void addCourseOffering(CourseOffering courseOffering) {
        String sql = "INSERT INTO courseoffering (id, courseCode, courseName, semester, capacity) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseOffering.getId());
            statement.setString(2, courseOffering.getCourseCode());
            statement.setString(3, courseOffering.getCourseName());
            statement.setString(4, courseOffering.getSemester());
            statement.setInt(5, courseOffering.getCapacity());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (single record)
    public CourseOffering getCourseOfferingById(int id) {
        String sql = "SELECT * FROM courseoffering WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new CourseOffering(
                    rs.getInt("id"),
                    rs.getString("courseCode"),
                    rs.getString("courseName"),
                    rs.getString("semester"),
                    rs.getInt("capacity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ (all records)
    public List<CourseOffering> getAllCourseOfferings() {
        List<CourseOffering> offerings = new ArrayList<>();
        String sql = "SELECT * FROM courseoffering";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                offerings.add(new CourseOffering(
                    rs.getInt("id"),
                    rs.getString("courseCode"),
                    rs.getString("courseName"),
                    rs.getString("semester"),
                    rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerings;
    }

    // UPDATE
    public void updateCourseOffering(CourseOffering courseOffering) {
        String sql = "UPDATE courseoffering SET courseCode = ?, courseName = ?, semester = ?, capacity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, courseOffering.getCourseCode());
            statement.setString(2, courseOffering.getCourseName());
            statement.setString(3, courseOffering.getSemester());
            statement.setInt(4, courseOffering.getCapacity());
            statement.setInt(5, courseOffering.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteCourseOffering(int id) {
        String sql = "DELETE FROM courseoffering WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

