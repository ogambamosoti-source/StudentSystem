package Controller;
// controller/AdminController.java
import service.AdminService;
import model.*;
import java.sql.SQLException;
import java.util.*;

public class AdminController {
    private final AdminService svc;

    public AdminController() throws SQLException { svc = new AdminService(); }

    public boolean         registerStudent(Student s)        { try { return svc.registerStudent(s); } catch (SQLException e) { return false; } }
    public List<Student>   getAllStudents()                   { try { return svc.getAllStudents(); } catch (SQLException e) { return list(); } }
    public boolean         deleteStudent(int id)             { try { return svc.deleteStudent(id); } catch (SQLException e) { return false; } }
    public List<Student>   getStudentsByDept(int d)          { try { return svc.getStudentsByDept(d); } catch (SQLException e) { return list(); } }

    public boolean         registerLecturer(Lecturer l)      { try { return svc.registerLecturer(l); } catch (SQLException e) { return false; } }
    public List<Lecturer>  getAllLecturers()                  { try { return svc.getAllLecturers(); } catch (SQLException e) { return list(); } }
    public boolean         deleteLecturer(int id)            { try { return svc.deleteLecturer(id); } catch (SQLException e) { return false; } }
    public List<Lecturer>  getLecturersByDept(int d)         { try { return svc.getLecturersByDept(d); } catch (SQLException e) { return list(); } }

    public boolean         addCourse(Course c)               { try { return svc.addCourse(c); } catch (SQLException e) { return false; } }
    public List<Course>    getAllCourses()                    { try { return svc.getAllCourses(); } catch (SQLException e) { return list(); } }
    public boolean         deleteCourse(int id)              { try { return svc.deleteCourse(id); } catch (SQLException e) { return false; } }
    public boolean         addOffering(CourseOffering co)    { try { return svc.addOffering(co); } catch (SQLException e) { return false; } }

    public boolean         addScore(Score sc)                { try { return svc.addScore(sc); } catch (SQLException e) { return false; } }
    public boolean         deleteScore(int id)               { try { return svc.deleteScore(id); } catch (SQLException e) { return false; } }

    public boolean         addDept(Department d)             { try { return svc.addDept(d); } catch (SQLException e) { return false; } }
    public List<Department>getAllDepts()                      { try { return svc.getAllDepts(); } catch (SQLException e) { return list(); } }
    public boolean         deleteDept(int id)                { try { return svc.deleteDept(id); } catch (SQLException e) { return false; } }

    public boolean         addProgram(Program p)             { try { return svc.addProgram(p); } catch (SQLException e) { return false; } }
    public List<Program>   getAllPrograms()                   { try { return svc.getAllPrograms(); } catch (SQLException e) { return list(); } }
    public boolean         deleteProgram(int id)             { try { return svc.deleteProgram(id); } catch (SQLException e) { return false; } }

    public boolean         addSemester(Semester s)           { try { return svc.addSemester(s); } catch (SQLException e) { return false; } }
    public List<Semester>  getAllSemesters()                  { try { return svc.getAllSemesters(); } catch (SQLException e) { return list(); } }
    public boolean         setActiveSemester(int id)         { try { return svc.setActiveSemester(id); } catch (SQLException e) { return false; } }
    public boolean         deleteSemester(int id)            { try { return svc.deleteSemester(id); } catch (SQLException e) { return false; } }

    @SuppressWarnings("unchecked")
    private <T> List<T> list() { return new ArrayList<>(); }
  }
