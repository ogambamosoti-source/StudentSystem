// view/LecturerDashboard.java
import controller.LecturerController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class LecturerDashboard extends JFrame {
    private final Lecturer           lecturer;
    private final LecturerController ctrl;

    public LecturerDashboard(Lecturer lecturer) throws SQLException {
        this.lecturer = lecturer;
        this.ctrl     = new LecturerController();
        setTitle("Lecturer — " + lecturer.getFullName());
        setSize(980, 660);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(topBar(), BorderLayout.NORTH);
        add(tabs(),   BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel topBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(20, 100, 40));
        bar.setPreferredSize(new Dimension(980, 48));
        JLabel lbl = new JLabel("  👨‍🏫 " + lecturer.getFullName() +
            "  |  " + lecturer.getUsername() +
            "  |  " + lecturer.getSpecialization());
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JButton out = new JButton("Logout");
        out.addActionListener(e -> { dispose(); new LoginView(); });
        bar.add(lbl, BorderLayout.WEST);
        bar.add(out, BorderLayout.EAST);
        return bar;
    }

    private JTabbedPane tabs() {
        JTabbedPane t = new JTabbedPane();
        t.addTab("📋 My Courses",    coursesTab());
        t.addTab("👥 Students",      studentsTab());
        t.addTab("🎯 Scores",        scoresTab());
        t.addTab("📖 Library",       libraryTab());
        return t;
    }

    /* ── 1. My Courses ── */
    private JPanel coursesTab() {
        String[] cols = {"Offering ID","Course","Code","Semester","Room"};
        DefaultTableModel m = ro(cols);
        for (CourseOffering co : ctrl.getMyCourses(lecturer.getId()))
            m.addRow(new Object[]{co.getId(), co.getCourseName(),
                co.getCourseCode(), co.getSemesterName(), co.getRoom()});
        return wrap(new JScrollPane(new JTable(m)));
    }

    /* ── 2. Students ── */
    private JPanel studentsTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        List<CourseOffering> offerings = ctrl.getMyCourses(lecturer.getId());
        JComboBox<CourseOffering> picker = new JComboBox<>();
        offerings.forEach(picker::addItem);

        String[] cols = {"Enroll ID","Student Name","Status","Enroll Date"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);

        picker.addActionListener(e -> {
            m.setRowCount(0);
            CourseOffering co = (CourseOffering) picker.getSelectedItem();
            if (co == null) return;
            for (Enrollment en : ctrl.getStudentsIn(co.getId()))
                m.addRow(new Object[]{en.getId(), en.getStudentName(),
                    en.getStatus(), en.getEnrollDate()});
        });
        if (picker.getItemCount() > 0) picker.setSelectedIndex(0);

        panel.add(picker,                    BorderLayout.NORTH);
        panel.add(new JScrollPane(table),    BorderLayout.CENTER);
        return panel;
    }

    /* ── 3. Scores (read-only) ── */
    private JPanel scoresTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        JLabel notice = new JLabel(
            "  ⚠  Scores are managed by Admin only — this view is read-only.");
        notice.setForeground(new Color(160,50,0));
        notice.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        List<CourseOffering> offerings = ctrl.getMyCourses(lecturer.getId());
        JComboBox<CourseOffering> picker = new JComboBox<>();
        offerings.forEach(picker::addItem);

        String[] cols = {"Score ID","Student","Course","CAT","Exam","Total","Grade"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);

        picker.addActionListener(e -> {
            m.setRowCount(0);
            CourseOffering co = (CourseOffering) picker.getSelectedItem();
            if (co == null) return;
            for (Score sc : ctrl.getScores(co.getId()))
                m.addRow(new Object[]{sc.getId(), sc.getStudentName(),
                    sc.getCourseName(), sc.getCatScore(),
                    sc.getExamScore(), sc.getTotalScore(), sc.getGrade()});
        });
        if (picker.getItemCount() > 0) picker.setSelectedIndex(0);

        JPanel top = new JPanel(new GridLayout(2,1));
        top.add(notice); top.add(picker);

        panel.add(top,                    BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ── 4. Library ── */
    private JPanel libraryTab() {
        String[] cols = {"ID","Title","Author","ISBN","Available"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Book b : ctrl.getAvailableBooks())
                m.addRow(new Object[]{b.getId(), b.getTitle(),
                    b.getAuthor(), b.getIsbn(), b.getAvailableCopies()});
        };
        load.run();
        JButton borrow = new JButton("📥 Borrow Selected");
        borrow.setBackground(new Color(0,100,180));
        borrow.setForeground(Color.WHITE);
        borrow.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            boolean ok = ctrl.borrowBook((int)m.getValueAt(row,0), lecturer.getId());
            JOptionPane.showMessageDialog(this,
                ok ? "✅ Borrowed! Due in 21 days." : "❌ Not available.");
            load.run();
        });
        JPanel p = new JPanel(new BorderLayout(5,5));
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        p.add(new JScrollPane(table), BorderLayout.CENTER);
        p.add(borrow, BorderLayout.SOUTH);
        return p;
    }

    private DefaultTableModel ro(String[] cols) {
        return new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
    }
    private JPanel wrap(JScrollPane sp) {
        JPanel p = new JPanel(new BorderLayout(5,5));
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        p.add(sp); return p;
    }
  }
