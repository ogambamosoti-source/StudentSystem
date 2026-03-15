// view/LoginView.java
import controller.AuthController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private AuthController authController;

    public LoginView() throws SQLException {
        authController = new AuthController();
        setTitle("School Management System - Login");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx=0; g.gridy=0; add(new JLabel("Email:"), g);
        g.gridx=1; emailField = new JTextField(20); add(emailField, g);

        g.gridx=0; g.gridy=1; add(new JLabel("Password:"), g);
        g.gridx=1; passwordField = new JPasswordField(20); add(passwordField, g);

        g.gridx=0; g.gridy=2; add(new JLabel("Role:"), g);
        g.gridx=1;
        roleCombo = new JComboBox<>(new String[]{"STUDENT","LECTURER","ADMIN","LIBRARIAN"});
        add(roleCombo, g);

        g.gridx=0; g.gridy=3; g.gridwidth=2;
        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> handleLogin());
        add(loginBtn, g);

        getContentPane().setBackground(new Color(240, 248, 255));
        setVisible(true);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();

        Person person = authController.login(email, password, role);
        if (person == null) {
            JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        dispose();
        try {
            switch (role) {
                case "STUDENT":
                    new StudentDashboard((model.Student) person).setVisible(true);
                    break;
                case "LECTURER":
                    new LecturerDashboard((model.Lecturer) person).setVisible(true);
                    break;
                case "ADMIN":
                    new AdminDashboard((model.Admin) person).setVisible(true);
                    break;
                case "LIBRARIAN":
                    new LibrarianDashboard((model.Librarian) person).setVisible(true);
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading dashboard: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try { new LoginView(); }
            catch (SQLException e) { e.printStackTrace(); }
        });
    }
}
