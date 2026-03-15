// controller/LecturerController.java
import service.LecturerService;
import model.*;
import java.sql.SQLException;
import java.util.*;

public class LecturerController {
    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) { 
      this.lecturerService = lecturerService;
     }

    public List<Course> getAllCourses(String lecturerIdNumber)         {
       try {
         return lecturerService.getAllCourses(lecturerIdNumber);
         } catch (SQLException e) { 
        e.printStackTrace();
         }
         }
    public List<Enrollment>  getStudentsIn(int offeringId)        {
       try {
         return lecturerService.getStudentsInOffering(offeringId);
         } catch (SQLException e) {
           e.printStackTrace();
          }
         }
    public List<Score> getScores(int offeringId)            {
       try {
         return lecturerService.getScoresForOffering(offeringId);
         } catch (SQLException e) { return list();
          }
         }
    public List<Book>  getAvailableBooks()                  {
       try {
         return lecturerService.getAvailableBooks();
         } catch (SQLException e) {
         e.printStackTrace();
         }
         }
    public boolean  borrowBook(int bookId, String lecturerIdNumber)    {
       try { 
        return lecturerService.borrowBook(bookId, lecturerIdNumber);
       } catch (SQLException e) {
         e.printStackTrace();
         } 
        }
        public boolean returnBook(int BookId, String lecturerIdNumber){
          try{
            return lecturerService.returnBook(lecturerIdNumber, BookId);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
            public List<Book> getBorrowedBooks(int lecturerIdNumber) {
        try {
            return lecturerService.getBorrowedBooks(lecturerIdNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

