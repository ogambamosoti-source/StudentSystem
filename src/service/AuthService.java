// service/AuthService.java
import dao.*;
import model.*;
import java.sql.SQLException;

public class AuthService {
    private final StudentDAO   studentDAO;
    private final LecturerDAO  lecturerDAO;
    private final AdminDAO     adminDAO;
    private final LibrarianDAO librarianDAO;

    public AuthService() throws SQLException {
        studentDAO   = new StudentDAO();
        lecturerDAO  = new LecturerDAO();
        adminDAO     = new AdminDAO();
        librarianDAO = new LibrarianDAO();
    }

    public Person login(String username, String password, String role)
            throws SQLException {
        switch (role) {
            case "STUDENT": {
                Student s = studentDAO.findByUsername(username);
                return (s != null && s.getPassword().equals(password)) ? s : null;
            }
            case "LECTURER": {
                Lecturer l = lecturerDAO.findByUsername(username);
                return (l != null && l.getPassword().equals(password)) ? l : null;
            }
            case "ADMIN": {
                Admin a = adminDAO.findByUsername(username);
                return (a != null && a.getPassword().equals(password)) ? a : null;
            }
            case "LIBRARIAN": {
                Librarian lb = librarianDAO.findByUsername(username);
                return (lb != null && lb.getPassword().equals(password)) ? lb : null;
            }
            default: return null;
        }
    }
                    }
                        

