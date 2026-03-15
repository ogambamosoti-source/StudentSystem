package dao;
import model.*;
import java.sql.*;
import java.util.SQLExeption;
import java.util.ArrayList;
import java.util.List;



public class ScoreDao {
private Connection connection;

public ScoreDao(Connection connection){
    this.connection = connection;
}
public void addScore(Score score){
    String sql = "INSERT INTO score(studentId,courseId,catMarks,examMarks,total,grade,semester)VALUEs(?,?,?,?,?,?,?)";
    try(PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setString(1,score.getStudentId());
          statement.setString(2,score.getCourseId());
            statement.setString(3,score.getCatMarks());
              statement.setString(4,score.getExamMarks());
                statement.setString(5,score.getTotal());
                  statement.setString(6,score.getGrade());
                    statement.setString(7,score.getSemester());
                    statement.executeUpdate();
    }catch(SQLException e){
        e.printStackTrace();
    }
}
    // READ (all scores)
    public List<Score> scores() {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM score";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Score score = new Score(
                    rs.getInt("studentId"),
                    rs.getInt("courseId"),
                    rs.getDouble("catMarks"),
                    rs.getDouble("examMarks"),
                    rs.getDouble("total"),
                    rs.getString("grade"),
                    rs.getString("semester")
                );
                scores.add(score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scores;
    }

    // READ (single score by student + course)
    public Score getScore(int studentId, int courseId) {
        String sql = "SELECT * FROM score WHERE studentId = ? AND courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Score(
                    rs.getInt("studentId"),
                    rs.getInt("courseId"),
                    rs.getDouble("catMarks"),
                    rs.getDouble("examMarks"),
                    rs.getDouble("total"),
                    rs.getString("grade"),
                    rs.getString("semester")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateScore(Score score) {
        String sql = "UPDATE score SET catMarks = ?, examMarks = ?, total = ?, grade = ?, semester = ? WHERE studentId = ? AND courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, score.getCatMarks());
            statement.setDouble(2, score.getExamMarks());
            statement.setDouble(3, score.getTotal());
            statement.setString(4, score.getGrade());
            statement.setString(5, score.getSemester());
            statement.setInt(6, score.getStudentId());
            statement.setInt(7, score.getCourseId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteScore(int studentId, int courseId) {
        String sql = "DELETE FROM score WHERE studentId = ? AND courseId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
