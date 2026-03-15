// controller/StudentController.java
import service.StudentService;
import model.*;
import java.sql.SQLException;
import java.util.*;

public class StudentController {
    private final StudentService svc;

    public StudentController() throws SQLException { svc = new StudentService(); }

    public List<CourseOffering> getAvailableOfferings()              { try { return svc.getAvailableOfferings(); } catch (SQLException e) { return list(); } }
    public boolean enroll(int studentId, int offeringId)             { try { return svc.enroll(studentId, offeringId); } catch (SQLException e) { return false; } }
    public List<Enrollment> getMyEnrollments(int studentId)          { try { return svc.getMyEnrollments(studentId); } catch (SQLException e) { return list(); } }
    public List<Score>      getMyScores(int studentId)               { try { return svc.getMyScores(studentId); } catch (SQLException e) { return list(); } }
    public List<Book>       getAvailableBooks()                      { try { return svc.getAvailableBooks(); } catch (SQLException e) { return list(); } }
    public boolean          borrowBook(int bookId, int studentId)    { try { return svc.borrowBook(bookId, studentId); } catch (SQLException e) { return false; } }
    public boolean          reserveBook(int bookId, int studentId)   { try { return svc.reserveBook(bookId, studentId); } catch (SQLException e) { return false; } }
    public List<Library>    getBorrowHistory(int studentId)          { try { return svc.getBorrowHistory(studentId); } catch (SQLException e) { return list(); } }
    public List<Library>    getMyFines(int studentId)                { try { return svc.getMyFines(studentId); } catch (SQLException e) { return list(); } }
    public List<Program>    getAllPrograms()                          { try { return svc.getAllPrograms(); } catch (SQLException e) { return list(); } }

    @SuppressWarnings("unchecked")
    private <T> List<T> list() { return new ArrayList<>(); }
                                                                                                                              }
