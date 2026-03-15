package controller;
// controller/AuthController.java
import service.AuthService;
import model.Person;
import java.sql.SQLException;

public class AuthController {
    private final AuthService svc;

    public AuthController() throws SQLException { svc = new AuthService(); }

    public Person login(String username, String password, String role) {
        try { return svc.login(username, password, role); }
        catch (SQLException e) { e.printStackTrace(); return null; }
    }
  }
