// view/AdminDashboard.java
import controller.AdminController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AdminDashboard extends JFrame {
    private final Admin           admin;
    private final AdminController adminController;

    public AdminDashboard(Admin admin, AdminController adminController) throws SQLException {
        this.admin = admin;
        this.adminController = adminController;
        setTitle("Admin — " + admin.getFirstName());
        setSize(1100, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(topBar(), BorderLayout.NORTH);
        add(tabs(),   BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel topBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(100, 10, 10));
        bar.setPreferredSize(new Dimension(1100, 48));
        JLabel lbl = new JLabel("  🛡 Admin: " + admin.getFirstName() +
            "  |  " + admin.getUserName());
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        JButton out = new JButton("Logout");
        out.addActionListener(e -> { dispose(); new LoginView(); });
        bar.add(lbl, BorderLayout.WEST);
        bar.add(out, BorderLayout.EAST);
        return bar;
    }

    private JTabbedPane tabs() {
        JTabbedPane t = new JTabbedPane();
        t.addTab("👨‍🎓 Students",   studentsTab());
        t.addTab("👨‍🏫 Lecturers",  lecturersTab());
        t.addTab("📘 Courses",     coursesTab());
        t.addTab("🏛 Departments", deptsTab());
        t.addTab("📂 Programs",    programsTab());
        t.addTab("📅 Semesters",   semestersTab());
        t.addTab("🎯 Scores",      scoresTab());
        t.addTab("📋 Offerings",   offeringsTab());
        return t;
    }

    /* ══════════════ STUDENTS ══════════════ */
    private JPanel studentsTab() {
        String[] cols = {"ID","First","Last","Email","Phone","ID No","Username","Level","ProgramID","DeptID"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        Runnable load = () -> {
            m.setRowCount(0);
            for (Student s : controller.getAllStudents())
                m.addRow(new Object[]{s.getId(), s.getFirstName(), s.getLastName(),
                    s.getEmail(), s.getPhoneNumber(), s.getIdNumber(),
                    s.getUserName(), s.getLevelEnum(), s.getProgramId(), s.getDepartmentId()});
        };
        load.run();

        /* registration form */
        JTextField fnF=tf(), lnF=tf(), emF=tf(), phF=tf(),
            idF=tf(), unF=tf(), pwF=tf(), prF=tf(4), dtF=tf(4);
        JComboBox<LevelEnum> lvl = new JComboBox<>(LevelEnum.values());

        JPanel form = new JPanel(new GridLayout(0, 4, 4, 4));
        form.setBorder(BorderFactory.createTitledBorder("Register New Student"));
        form.add(lbl("First Name:")); form.add(fnF);
        form.add(lbl("Last Name:"));  form.add(lnF);
        form.add(lbl("Email:"));      form.add(emF);
        form.add(lbl("Phone:"));      form.add(phF);
        form.add(lbl("ID Number:"));  form.add(idF);
        form.add(lbl("Username:"));   form.add(unF);
        form.add(lbl("Password:"));   form.add(pwF);
        form.add(lbl("Level:"));      form.add(lvl);
        form.add(lbl("Program ID:")); form.add(prF);
        form.add(lbl("Dept ID:"));    form.add(dtF);

        JButton addBtn = btn("✅ Register", new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete",   new Color(180,0,0));

        addBtn.addActionListener(e -> {
            try {
                Student s = new Student(0, fnF.getText(), lnF.getText(),
                    emF.getText(), phF.getText(), idF.getText(), unF.getText(),
                    pwF.getText(), (LevelEnum)lvl.getSelectedItem(),
                    Integer.parseInt(prF.getText()), Integer.parseInt(dtF.getText()));
                msg(ctrl.registerStudent(s) ? "✅ Registered!" : "❌ Failed.");
                load.run();
            } catch (NumberFormatException ex) { msg("Program ID & Dept ID must be numbers."); }
        });
        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) return;
            if (confirm("Delete student?")) { ctrl.deleteStudent((int)m.getValueAt(row,0)); load.run(); }
        });

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btns.add(addBtn); btns.add(delBtn);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(btns, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(top,                    BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ══════════════ LECTURERS ══════════════ */
    private JPanel lecturersTab() {
        String[] cols = {"ID","First","Last","Email","Phone","ID No","Username","DeptID","Specialization"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);

        Runnable load = () -> {
            m.setRowCount(0);
            for (Lecturer l : ctrl.getAllLecturers())
                m.addRow(new Object[]{l.getId(), l.getFirstName(), l.getLastName(),
                    l.getEmail(), l.getPhoneNumber(), l.getIdNumber(),
                    l.getUsername(), l.getDepartmentId(), l.getSpecialization()});
        };
        load.run();

        JTextField fnF=tf(),lnF=tf(),emF=tf(),phF=tf(),
            idF=tf(),unF=tf(),pwF=tf(),dtF=tf(4),spF=tf();
        JPanel form = new JPanel(new GridLayout(0,4,4,4));
        form.setBorder(BorderFactory.createTitledBorder("Register New Lecturer"));
        form.add(lbl("First Name:"));    form.add(fnF);
        form.add(lbl("Last Name:"));     form.add(lnF);
        form.add(lbl("Email:"));         form.add(emF);
        form.add(lbl("Phone:"));         form.add(phF);
        form.add(lbl("ID Number:"));     form.add(idF);
        form.add(lbl("Username:"));      form.add(unF);
        form.add(lbl("Password:"));      form.add(pwF);
        form.add(lbl("Dept ID:"));       form.add(dtF);
        form.add(lbl("Specialization:")); form.add(spF);

        JButton addBtn = btn("✅ Register", new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete",   new Color(180,0,0));
        addBtn.addActionListener(e -> {
            Lecturer l = new Lecturer(0,fnF.getText(),lnF.getText(),
                emF.getText(),phF.getText(),idF.getText(),unF.getText(),
                pwF.getText(),Integer.parseInt(dtF.getText()),spF.getText());
            msg(ctrl.registerLecturer(l)?"✅ Registered!":"❌ Failed.");
            load.run();
        });
        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row<0) return;
            if (confirm("Delete lecturer?")) { ctrl.deleteLecturer((int)m.getValueAt(row,0)); load.run(); }
        });

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btns.add(addBtn); btns.add(delBtn);
        JPanel top = new JPanel(new BorderLayout());
        top.add(form, BorderLayout.CENTER);
        top.add(btns, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ══════════════ COURSES ══════════════ */
    private JPanel coursesTab() {
        String[] cols = {"ID","Name","Code","Credits","Program ID","Level"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Course c : ctrl.getAllCourses())
                m.addRow(new Object[]{c.getId(), c.getName(), c.getCode(),
                    c.getCreditHours(), c.getProgramId(), c.getLevel()});
        };
        load.run();

        JTextField nF=tf(),cF=tf(8),crF=tf(3),prF=tf(4);
        JComboBox<LevelEnum> lvl = new JComboBox<>(LevelEnum.values());
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Add Course to Program"));
        form.add(lbl("Name:")); form.add(nF);
        form.add(lbl("Code:")); form.add(cF);
        form.add(lbl("Credits:")); form.add(crF);
        form.add(lbl("Program ID:")); form.add(prF);
        form.add(lbl("Level:")); form.add(lvl);

        JButton addBtn = btn("✅ Add", new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete", new Color(180,0,0));
        addBtn.addActionListener(e -> {
            Course c = new Course(0,nF.getText(),cF.getText(),
                Integer.parseInt(crF.getText()),Integer.parseInt(prF.getText()),
                (LevelEnum)lvl.getSelectedItem());
            msg(ctrl.addCourse(c)?"✅ Added!":"❌ Failed.");
            load.run();
        });
        delBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.deleteCourse((int)m.getValueAt(row,0)); load.run();
        });
        form.add(addBtn); form.add(delBtn);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ══════════════ DEPARTMENTS ══════════════ */
    private JPanel deptsTab() {
        String[] cols = {"ID","Name","Code"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Department d : ctrl.getAllDepts())
                m.addRow(new Object[]{d.getId(), d.getName(), d.getCode()});
        };
        load.run();

        JTextField nF=tf(), cF=tf(8);
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Manage Departments"));
        form.add(lbl("Name:")); form.add(nF);
        form.add(lbl("Code:")); form.add(cF);
        JButton addBtn = btn("✅ Add", new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete", new Color(180,0,0));
        addBtn.addActionListener(e -> {
            ctrl.addDept(new Department(0, nF.getText(), cF.getText()));
            load.run();
        });
        delBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.deleteDept((int)m.getValueAt(row,0)); load.run();
        });
        form.add(addBtn); form.add(delBtn);

        /* members sub-panel */
        String[] mCols = {"Type","ID","Full Name","Username"};
        DefaultTableModel mM = ro(mCols);
        JButton showBtn = new JButton("👥 Show Members of Selected Dept");
        showBtn.addActionListener(e -> {
            int row = table.getSelectedRow(); if(row<0) return;
            int deptId = (int) m.getValueAt(row, 0);
            mM.setRowCount(0);
            for (Student s : ctrl.getStudentsByDept(deptId))
                mM.addRow(new Object[]{"Student", s.getId(), s.getFullName(), s.getUsername()});
            for (Lecturer l : ctrl.getLecturersByDept(deptId))
                mM.addRow(new Object[]{"Lecturer", l.getId(), l.getFullName(), l.getUsername()});
        });
        form.add(showBtn);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(new JScrollPane(new JTable(mM)), BorderLayout.SOUTH);
        return panel;
    }

    /* ══════════════ PROGRAMS ══════════════ */
    private JPanel programsTab() {
        String[] cols = {"ID","Name","Code","Dept ID","Duration"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Program p : ctrl.getAllPrograms())
                m.addRow(new Object[]{p.getId(), p.getName(), p.getCode(),
                    p.getDepartmentId(), p.getDurationYears()});
        };
        load.run();

        JTextField nF=tf(),cF=tf(8),dF=tf(4),durF=tf(3);
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Manage Programs"));
        form.add(lbl("Name:")); form.add(nF);
        form.add(lbl("Code:")); form.add(cF);
        form.add(lbl("Dept ID:")); form.add(dF);
        form.add(lbl("Duration (yrs):")); form.add(durF);
        JButton addBtn = btn("✅ Add", new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete", new Color(180,0,0));
        addBtn.addActionListener(e -> {
            ctrl.addProgram(new Program(0,nF.getText(),cF.getText(),
                Integer.parseInt(dF.getText()),Integer.parseInt(durF.getText())));
            load.run();
        });
        delBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.deleteProgram((int)m.getValueAt(row,0)); load.run();
        });
        form.add(addBtn); form.add(delBtn);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ══════════════ SEMESTERS ══════════════ */
    private JPanel semestersTab() {
        String[] cols = {"ID","Name","Type","Start","End","Active"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Semester s : ctrl.getAllSemesters())
                m.addRow(new Object[]{s.getId(), s.getName(), s.getType(),
                    s.getStartDate(), s.getEndDate(), s.isActive()?"✓":""});
        };
        load.run();

        JTextField nF=tf(),stF=tf(12),enF=tf(12);
        JComboBox<Semester.SemType> typeBox =
            new JComboBox<>(Semester.SemType.values());
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Manage Semesters"));
        form.add(lbl("Name:")); form.add(nF);
        form.add(lbl("Type:")); form.add(typeBox);
        form.add(lbl("Start (yyyy-mm-dd):")); form.add(stF);
        form.add(lbl("End   (yyyy-mm-dd):")); form.add(enF);
        JButton addBtn    = btn("✅ Add",         new Color(46,139,87));
        JButton activeBtn = btn("⚡ Set Active",  new Color(200,120,0));
        JButton delBtn    = btn("🗑 Delete",       new Color(180,0,0));
        addBtn.addActionListener(e -> {
            ctrl.addSemester(new Semester(0, nF.getText(),
                (Semester.SemType)typeBox.getSelectedItem(),
                stF.getText(), enF.getText(), false));
            load.run();
        });
        activeBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.setActiveSemester((int)m.getValueAt(row,0)); load.run();
        });
        delBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.deleteSemester((int)m.getValueAt(row,0)); load.run();
        });
        form.add(addBtn); form.add(activeBtn); form.add(delBtn);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ══════════════ SCORES (ADMIN ONLY) ══════════════ */
    private JPanel scoresTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

        JLabel banner = new JLabel(
            "  🔐  Only Admin can add or delete scores. " +
            "Lecturers and students have read-only access.");
        banner.setForeground(new Color(140,0,0));
        banner.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JTextField enrF=tf(6), catF=tf(5), exmF=tf(5), totF=tf(5), grF=tf(4);
        JPanel addForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addForm.setBorder(BorderFactory.createTitledBorder("Add Score"));
        addForm.add(lbl("Enrollment ID:")); addForm.add(enrF);
        addForm.add(lbl("CAT:"));          addForm.add(catF);
        addForm.add(lbl("Exam:"));         addForm.add(exmF);
        addForm.add(lbl("Total:"));        addForm.add(totF);
        addForm.add(lbl("Grade:"));        addForm.add(grF);
        JButton addBtn = btn("✅ Add Score", new Color(46,139,87));
        addBtn.addActionListener(e -> {
            Score sc = new Score(0, Integer.parseInt(enrF.getText()),
                Double.parseDouble(catF.getText()),
                Double.parseDouble(exmF.getText()),
                Double.parseDouble(totF.getText()), grF.getText());
            msg(ctrl.addScore(sc)?"✅ Score added!":"❌ Failed.");
        });
        addForm.add(addBtn);

        JTextField delIdF = tf(6);
        JPanel delForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        delForm.setBorder(BorderFactory.createTitledBorder("Delete Score by ID"));
        JButton delBtn = btn("🗑 Delete", new Color(180,0,0));
        delBtn.addActionListener(e -> msg(ctrl.deleteScore(
            Integer.parseInt(delIdF.getText()))?"✅ Deleted!":"❌ Not found."));
        delForm.add(lbl("Score ID:")); delForm.add(delIdF); delForm.add(delBtn);

        JPanel top = new JPanel(new GridLayout(3,1));
        top.add(banner); top.add(addForm); top.add(delForm);
        panel.add(top, BorderLayout.NORTH);
        return panel;
    }

    /* ══════════════ OFFERINGS ══════════════ */
    private JPanel offeringsTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        JTextField cIdF=tf(5),lIdF=tf(5),sIdF=tf(5),roomF=tf(10);
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder(
            "Assign Course to Lecturer for a Semester (Course Offering)"));
        form.add(lbl("Course ID:"));   form.add(cIdF);
        form.add(lbl("Lecturer ID:")); form.add(lIdF);
        form.add(lbl("Semester ID:")); form.add(sIdF);
        form.add(lbl("Room:"));        form.add(roomF);
        JButton addBtn = btn("✅ Create Offering", new Color(25,42,86));
        addBtn.addActionListener(e -> {
            CourseOffering co = new CourseOffering(0,
                Integer.parseInt(cIdF.getText()),
                Integer.parseInt(lIdF.getText()),
                Integer.parseInt(sIdF.getText()), roomF.getText());
            msg(ctrl.addOffering(co)?"✅ Offering created!":"❌ Failed.");
        });
        form.add(addBtn);
        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    /* ── shared helpers ── */
    private DefaultTableModel ro(String[] cols) {
        return new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};
    }
    private JLabel  lbl(String t){ JLabel l=new JLabel(t); l.setFont(new Font("Segoe UI",Font.PLAIN,12)); return l; }
    private JButton btn(String t, Color bg){ JButton b=new JButton(t); b.setBackground(bg); b.setForeground(Color.WHITE); b.setFont(new Font("Segoe UI",Font.BOLD,12)); return b; }
    private JTextField tf()     { return new JTextField(14); }
    private JTextField tf(int w){ return new JTextField(w); }
    private void msg(String t){ JOptionPane.showMessageDialog(this,
    