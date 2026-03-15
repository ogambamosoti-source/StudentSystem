package controller;
// controller/LibrarianController.java
import service.LibraryService;
import model.*;
import java.sql.SQLException;
import java.util.*;

public class LibrarianController {
    private final LibraryService svc;

    public LibrarianController() throws SQLException { svc = new LibraryService(); }

    public boolean       addBook(Book b)                            { try { return svc.addBook(b); } catch (SQLException e) { return false; } }
    public boolean       deleteBook(int id)                         { try { return svc.deleteBook(id); } catch (SQLException e) { return false; } }
    public List<Book>    getAllBooks()                               { try { return svc.getAllBooks(); } catch (SQLException e) { return list(); } }
    public boolean       issueBook(int bkId, int brId, String type) { try { return svc.issueBook(bkId, brId, type); } catch (SQLException e) { return false; } }
    public boolean       processReturn(int rId, int bkId, String due){ try { return svc.processReturn(rId, bkId, due); } catch (SQLException e) { return false; } }
    public boolean       reserveBook(int bkId,int brId,String type) { try { return svc.reserveBook(bkId, brId, type); } catch (SQLException e) { return false; } }
    public List<Library> getAllBorrowed()                            { try { return svc.getAllBorrowed(); } catch (SQLException e) { return list(); } }
    public List<Library> getOverdue()                               { try { return svc.getOverdue(); } catch (SQLException e) { return list(); } }
    public List<Library> getReserved()                              { try { return svc.getReserved(); } catch (SQLException e) { return list(); } }

    @SuppressWarnings("unchecked")
    private <T> List<T> list() { return new ArrayList<>(); }
      }
