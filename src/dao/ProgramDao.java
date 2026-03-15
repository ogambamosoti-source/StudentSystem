package dao;
import model.Program;
import model.LevelEnum; // assuming you have an enum for DEGREE, DIPLOMA, etc.
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramDao {
    private Connection connection;

    public ProgramDao(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void addProgram(Program program) {
        String sql = "INSERT INTO program(programName, programID, level, durationYears, departmentId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, program.getProgramName());
            statement.setInt(2, program.getProgramID());
            statement.setString(3, program.getLevel().name()); // store enum as string
            statement.setString(4, program.getDurationYears());
            statement.setInt(5, program.getDepartment());
            statement.executeUpdate();
            System.out.println("Program added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (all programs)
    public List<Program> getAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String sql = "SELECT * FROM program";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Program program = new Program(
                    rs.getString("programName"),
                    rs.getInt("programID"),
                    LevelEnum.valueOf(rs.getString("level")),
                    rs.getString("durationYears"),
                    rs.getInt("departmentId")
                );
                programs.add(program);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

    // READ (single program by ID)
    public Program getProgramById(int programID) {
        String sql = "SELECT * FROM program WHERE programID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, programID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Program(
                    rs.getString("programName"),
                    rs.getInt("programID"),
                    LevelEnum.valueOf(rs.getString("level")),
                    rs.getString("durationYears"),
                    rs.getInt("departmentId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public void updateProgram(Program program) {
        String sql = "UPDATE program SET programName = ?, level = ?, durationYears = ?, departmentId = ? WHERE programID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, program.getProgramName());
            statement.setString(2, program.getLevel().name());
            statement.setString(3, program.getDurationYears());
            statement.setInt(4, program.getDepartment());
            statement.setInt(5, program.getProgramID());
            statement.executeUpdate();
            System.out.println("Program updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteProgram(int programID) {
        String sql = "DELETE FROM program WHERE programID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, programID);
            statement.executeUpdate();
            System.out.println("Program deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
