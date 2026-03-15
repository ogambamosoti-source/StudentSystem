// controller/LecturerController.java
import service.LecturerService;
import model.*;
import java.sql.SQLException;
import java.util.*;

public class LecturerController {
    private final LecturerService svc;

    public LecturerController() throws SQLException { svc = new LecturerService(); }

    public List<CourseOffering> getMyCourses(int lecturerId)         { try { return svc.getMyCourses(lecturerId); } catch (SQLException e) { return list(); } }
    public List<Enrollment>     getStudentsIn(int offeringId)        { try { return svc.getStudentsInOffering(offeringId); } catch (SQLException e) { return list(); } }
    public List<Score>          getScores(int offeringId)            { try { return svc.getScoresForOffering(offeringId); } catch (SQLException e) { return list(); } }
    public List<Book>           getAvailableBooks()                  { try { return svc.getAvailableBooks(); } catch (SQLException e) { return list(); } }
    public boolean              borrowBook(int bookId, int lecId)    { try { return svc.borrowBook(bookId, lecId); } catch (SQLException e) { return false; } }

    @SuppressWarnings("unchecked")
    private <T> List<T> list() { return new ArrayList<>(); }
      }
