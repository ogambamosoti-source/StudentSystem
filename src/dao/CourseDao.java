package dao;
import model.*;//course objects
import java.sql.Connection;//store a connection
import java.sql.PreparedStatement;//
import java.sql.SQLException;
import java.util.ArrayList;//xcreate an arrayList in listCourse
import java.util.List;//return list<course>



public class CourseDao {
private Connection connection;

public CourseDao(Connection connection){
    this.connection = connection;
}
    // Add a new course
    public void addCourse(Course course) {
        String sql = "INSERT INTO courses(courseName, courseCode, creditHours, departmentId) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getCreditHours());
            stmt.setInt(4, course.getDepartmentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // TODO: handle exception properly
        }
    }

    // Get course by name
    public Course getCourseByName(String name) {
        String sql = "SELECT * FROM courses WHERE courseName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("courseName"),
                        rs.getString("courseCode"),
                        rs.getInt("creditHours"),
                        rs.getInt("departmentId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // not found
    }

    // Update course
    public void updateCourse(Course course) {
        String sql = "UPDATE courses SET courseName=?, courseCode=?, creditHours=?, departmentId=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setString(2, course.getCourseCode());
            stmt.setInt(3, course.getCreditHours());
            stmt.setInt(4, course.getDepartmentId());
            stmt.setInt(5, course.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete course
    public void deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List all courses
    public List<Course> listCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("courseName"),
                        rs.getString("courseCode"),
                        rs.getInt("creditHours"),
                        rs.getInt("departmentId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}


