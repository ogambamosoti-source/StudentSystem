package view;
// view/StudentDashboard.java
import controller.StudentController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StudentDashboard extends JFrame {
    private final Student           student;
    private final StudentController ctrl;

    public StudentDashboard(Student student) throws SQLException {
        this.student = student;
        this.ctrl    = new StudentController();
        setTitle("Student — " + student.getFullName());
        setSize(980, 660);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(topBar(new Color(25, 80, 160),
            "👤 " + student.getFullName() +
            "  |  Username: " + student.getUsername() +
            "  |  Level: " + student.getLevel() +
            "  |  ID No: " + student.getIdNumber()),
            BorderLayout.NORTH);
        add(tabs(), BorderLayout.CENTER);
        setVisible(true);
    }

    private JTabbedPane tabs() {
        JTabbedPane t = new JTabbedPane();
        t.addTab("📋 Available Courses", availableCoursesTab());
        t.addTab("📚 My Enrollments",   enrollmentsTab());
        t.addTab("🎯 My Scores",        scoresTab());
        t.addTab("📖 Books",            booksTab());
        t.addTab("📦 Borrow History",   borrowHistoryTab());
        t.addTab("💸 My Fines",         finesTab());
        t.addTab("📂 Programs",         programsTab());
        return t;
    }

    /* ── 1. Available Courses ── */
    private JPanel availableCoursesTab() {
        String[] cols = {"ID","Course","Code","Lecturer","Semester","Room"};
        DefaultTableModel m = readOnly(cols);
        JTable table = new JTable(m);

        Runnable load = () -> {
            m.setRowCount(0);
            for (CourseOffering co : ctrl.getAvailableOfferings())
                m.addRow(new Object[]{co.getId(), co.getCourseName(),
                    co.getCourseCode(), co.getLecturerName(),
                    co.getSemesterName(), co.getRoom()});
        };
        load.run();

        JButton enroll = btn("Enroll in Selected", new Color(46,139,87));
        enroll.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { msg("Select a course."); return; }
            int offeringId = (int) m.getValueAt(row, 0);
            boolean ok = ctrl.enroll(student.getId(), offeringId);
            msg(ok ? "✅ Enrolled successfully!" : "❌ Already enrolled or error.");
            if (ok) load.run();
        });

        return south(scroll(table), enroll);
    }

    /* ── 2. My Enrollments ── */
    private JPanel enrollmentsTab() {
        String[] cols = {"Enrollment ID","Course","Status","Enroll Date"};
        DefaultTableModel m = readOnly(cols);
        for (Enrollment e : ctrl.getMyEnrollments(student.getId()))
            m.addRow(new Object[]{e.getId(), e.getCourseName(),
                e.getStatus(), e.getEnrollDate()});
        return wrap(scroll(new JTable(m)));
    }

    /* ── 3. Scores ── */
    private JPanel scoresTab() {
        String[] cols = {"Score ID","Course","CAT","Exam","Total","Grade"};
        DefaultTableModel m = readOnly(cols);
        for (Score sc : ctrl.getMyScores(student.getId()))
            m.addRow(new Object[]{sc.getId(), sc.getCourseName(),
                sc.getCatScore(), sc.getExamScore(),
                sc.getTotalScore(), sc.getGrade()});
        return wrap(scroll(new JTable(m)));
    }

    /* ── 4. Books ── */
    private JPanel booksTab() {
        String[] cols = {"ID","Title","Author","ISBN","Category","Available"};
        DefaultTableModel m = readOnly(cols);
        JTable table = new JTable(m);

        Runnable load = () -> {
            m.setRowCount(0);
            for (Book b : ctrl.getAvailableBooks())
                m.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(),
                    b.getIsbn(), b.getCategory(), b.getAvailableCopies()});
        };
        load.run();

        JButton borrow  = btn("📥 Borrow",  new Color(0,100,180));
        JButton reserve = btn("🔖 Reserve", new Color(180,120,0));
        borrow.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            boolean ok = ctrl.borrowBook((int)m.getValueAt(row,0), student.getId());
            msg(ok ? "✅ Borrowed! Due in 14 days." : "❌ Not available.");
            load.run();
        });
        reserve.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            boolean ok = ctrl.reserveBook((int)m.getValueAt(row,0), student.getId());
            msg(ok ? "✅ Reserved for 3 days." : "❌ Failed.");
        });

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btns.add(borrow); btns.add(reserve);
        return south(scroll(table), btns);
    }

    /* ── 5. Borrow History ── */
    private JPanel borrowHistoryTab() {
        String[] cols = {"ID","Book","Borrow Date","Due Date","Return Date","Status","Fine (KES)"};
        DefaultTableModel m = readOnly(cols);
        for (Library r : ctrl.getBorrowHistory(student.getId()))
            m.addRow(new Object[]{r.getId(), r.getBookTitle(),
                r.getBorrowDate(), r.getDueDate(), r.getReturnDate(),
                r.getStatus(), r.getFine()});
        return wrap(scroll(new JTable(m)));
    }

    /* ── 6. Fines ── */
    private JPanel finesTab() {
        String[] cols = {"ID","Book","Due Date","Return Date","Fine (KES)"};
        DefaultTableModel m = readOnly(cols);
        double total = 0;
        for (Library r : ctrl.getMyFines(student.getId())) {
            m.addRow(new Object[]{r.getId(), r.getBookTitle(),
                r.getDueDate(), r.getReturnDate(), r.getFine()});
            total += r.getFine();
        }
        JLabel totalLbl = new JLabel("Total Outstanding: KES " + total,
            SwingConstants.RIGHT);
        totalLbl.setForeground(Color.RED);
        totalLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JPanel p = south(scroll(new JTable(m)), totalLbl);
        return p;
    }

    /* ── 7. Programs ── */
    private JPanel programsTab() {
        String[] cols = {"ID","Program Name","Code","Dept ID","Duration (Yrs)"};
        DefaultTableModel m = readOnly(cols);
        for (Program p : ctrl.getAllPrograms())
            m.addRow(new Object[]{p.getId(), p.getName(), p.getCode(),
                p.getDepartmentId(), p.getDurationYears()});
        return wrap(scroll(new JTable(m)));
    }

    /* ── helpers ── */
    private JPanel topBar(Color bg, String text) {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(bg);
        bar.setPreferredSize(new Dimension(980, 48));
        JLabel lbl = new JLabel("  " + text);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> { dispose(); new LoginView(); });
        bar.add(lbl,    BorderLayout.WEST);
        bar.add(logout, BorderLayout.EAST);
        return bar;
    }
    private JScrollPane scroll(JTable t) { return new JScrollPane(t); }
    private JPanel wrap(JScrollPane sp) {
        JPanel p = new JPanel(new BorderLayout(5,5));
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        p.add(sp); return p;
    }
    private JPanel south(JScrollPane sp, JComponent south) {
        JPanel p = new JPanel(new BorderLayout(5,5));
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        p.add(sp, BorderLayout.CENTER);
        p.add(south, BorderLayout.SOUTH);
        return p;
    }
    private JButton btn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg); b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return b;
    }
    private DefaultTableModel readOnly(String[] cols) {
        return new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
    }
    private void msg(String text) {
        JOptionPane.showMessageDialog(this, text);
    }
          }
