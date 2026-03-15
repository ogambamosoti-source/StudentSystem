package service;
import model.*;
import dao.*;
import java.sql.SQLException;
public class AuthService {
private final StudentDao studentDao;
private final LecturerDao lecturerDao;
private final AdminDao adminDao;
private final LibrarianDao librarianDao;

public AuthService(StudentDao studentDao,LecturerDao lecturerDao,AdminDao adminDao,LibrarianDao librarianDao){
    this.studentDao = new StudentDao();
}
}

