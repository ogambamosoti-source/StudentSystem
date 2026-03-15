// view/LibrarianDashboard.java
import controller.LibrarianController;
import model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class LibrarianDashboard extends JFrame {
    private final Librarian           librarian;
    private final LibrarianController ctrl;

    public LibrarianDashboard(Librarian librarian) throws SQLException {
        this.librarian = librarian;
        this.ctrl      = new LibrarianController();
        setTitle("Librarian — " + librarian.getFullName());
        setSize(1000, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(topBar(), BorderLayout.NORTH);
        add(tabs(),   BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel topBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(80, 45, 10));
        bar.setPreferredSize(new Dimension(1000, 48));
        JLabel lbl = new JLabel("  📚 Librarian: " + librarian.getFullName() +
            "  |  " + librarian.getUsername());
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
        t.addTab("📚 Manage Books",    booksTab());
        t.addTab("📤 Issue Book",      issueTab());
        t.addTab("📥 Return Book",     returnTab());
        t.addTab("🔖 Reservations",    reservationsTab());
        t.addTab("⚠ Overdue & Fines", overdueTab());
        return t;
    }

    /* ── 1. Books ── */
    private JPanel booksTab() {
        String[] cols = {"ID","Title","Author","ISBN","Category","Status","Total","Available"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);
        Runnable load = () -> {
            m.setRowCount(0);
            for (Book b : ctrl.getAllBooks())
                m.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(),
                    b.getIsbn(), b.getCategory(), b.getStatus(),
                    b.getTotalCopies(), b.getAvailableCopies()});
        };
        load.run();

        JTextField tF=tf(14),aF=tf(12),iF=tf(12),cF=tf(10),cpF=tf(3);
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Add New Book"));
        form.add(lbl("Title:"));    form.add(tF);
        form.add(lbl("Author:"));   form.add(aF);
        form.add(lbl("ISBN:"));     form.add(iF);
        form.add(lbl("Category:")); form.add(cF);
        form.add(lbl("Copies:"));   form.add(cpF);

        JButton addBtn = btn("✅ Add",    new Color(46,139,87));
        JButton delBtn = btn("🗑 Delete", new Color(180,0,0));
        addBtn.addActionListener(e -> {
            int copies = Integer.parseInt(cpF.getText());
            ctrl.addBook(new Book(0, tF.getText(), aF.getText(), iF.getText(),
                cF.getText(), Book.BookStatus.AVAILABLE, copies, copies));
            load.run();
        });
        delBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            ctrl.deleteBook((int)m.getValueAt(row,0)); load.run();
        });
        form.add(addBtn); form.add(delBtn);

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    /* ── 2. Issue ── */
    private JPanel issueTab() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30,80,30,80));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8,8,8,8);
        g.fill   = GridBagConstraints.HORIZONTAL;

        JTextField bookIdF=tf(8), borrowerIdF=tf(8);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"STUDENT","LECTURER"});

        g.gridx=0;g.gridy=0; panel.add(lbl("Book ID:"),       g);
        g.gridx=1;            panel.add(bookIdF,               g);
        g.gridx=0;g.gridy=1; panel.add(lbl("Borrower ID:"),   g);
        g.gridx=1;            panel.add(borrowerIdF,           g);
        g.gridx=0;g.gridy=2; panel.add(lbl("Borrower Type:"), g);
        g.gridx=1;            panel.add(typeBox,               g);

        JButton issueBtn = btn("📤 Issue Book", new Color(0,100,180));
        issueBtn.addActionListener(e -> {
            boolean ok = ctrl.issueBook(
                Integer.parseInt(bookIdF.getText()),
                Integer.parseInt(borrowerIdF.getText()),
                (String)typeBox.getSelectedItem());
            msg(ok?"✅ Book issued! Due in 14/21 days.":"❌ Failed — check availability.");
        });
        g.gridx=0;g.gridy=3;g.gridwidth=2; panel.add(issueBtn,g);
        return panel;
    }

    /* ── 3. Return ── */
    private JPanel returnTab() {
        String[] cols = {"Record ID","Book","Borrower ID","Type","Borrow Date","Due Date","Status"};
        DefaultTableModel m = ro(cols);
        JTable table = new JTable(m);

        Runnable load = () -> {
            m.setRowCount(0);
            for (Library r : ctrl.getAllBorrowed())
                m.addRow(new Object[]{r.getId(), r.getBookTitle(),
                    r.getBorrowerId(), r.getBorrowerType(),
                    r.getBorrowDate(), r.getDueDate(), r.getStatus()});
        };
        load.run();

        JButton returnBtn = btn("📥 Process Return for Selected", new Color(46,139,87));
        returnBtn.addActionListener(e -> {
            int row=table.getSelectedRow(); if(row<0) return;
            int    recId  = (int)    m.getValueAt(row,0);
            // Need bookId — stored in Library object; re-fetch from all borrowed
            List<Library> all = ctrl.getAllBorrowed();
            Library rec = all.stream()
                .filter(r -> r.getId()==recId).findFirst().orElse(null);
            if (rec==null) return;
            String dueDate = (String) m.getValueAt(row,5);
            boolean ok = ctrl.processReturn(recId, rec.getBookId(), dueDate);
            msg(ok?"✅ Returned. Fine applied if overdue.":"❌ Return failed.");
            load.run();
        });

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(returnBtn,              BorderLayout.SOUTH);
        return panel;
    }

    /* ── 4. Reservations ── */
    private JPanel reservationsTab() {
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));

        JTextField bkF=tf(6),brF=tf(6);
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"STUDENT","LECTURER"});
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        form.setBorder(BorderFactory.createTitledBorder("Reserve a Book"));
        form.add(lbl("Book ID:"));     form.add(bkF);
        form.add(lbl("Borrower ID:")); form.add(brF);
        form.add(lbl("Type:"));        form.add(typeBox);
        JButton resBtn = btn("🔖 Reserve", new Color(180,120,0));
        resBtn.addActionListener(e -> msg(ctrl.reserveBook(
            Integer.parseInt(bkF.getText()),
            Integer.parseInt(brF.getText()),
            (String)typeBox.getSelectedItem())?"✅ Reserved!":"❌ Failed."));
        form.add(resBtn);

        String[] cols = {"Record ID","Book","Borrower ID","Type","Reserve Date","Due Date"};
        DefaultTableModel m = ro(cols);
        for (Library r : ctrl.getReserved())
            m.addRow(new Object[]{r.getId(), r.getBookTitle(),
                r.getBorrowerId(), r.getBorrowerType(),
                r.getBorrowDate(), r.getDueDate()});

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(new JTable(m)), BorderLayout.CENTER);
        return panel;
    }

    /* ── 5. Overdue & Fines ── */
    private JPanel overdueTab() {
        String[] cols = {"Record ID","Book","Borrower ID","Type","Due Date","Fine (KES)"};
        DefaultTableModel m = ro(cols);
        double total = 0;
        for (Library r : ctrl.getOverdue()) {
            m.addRow(new Object[]{r.getId(), r.getBookTitle(),
                r.getBorrowerId(), r.getBorrowerType(),
                r.getDueDate(), r.getFine()});
            total += r.getFine();
        }
        JLabel totalLbl = new JLabel("  Total Fines: KES " + total, SwingConstants.RIGHT);
        totalLbl.setForeground(Color.RED);
        totalLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));

        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        panel.add(new JScrollPane(new JTable(m)), BorderLayout.CENTER);
        panel.add(totalLbl, BorderLayout.SOUTH);
        return panel;
    }

    /* ── helpers ── */
    private DefaultTableModel ro(String[] cols) {
        return new DefaultTableModel(cols,0){public boolean isCellEditable(int r,int c){return false;}};
    }
    private JLabel  lbl(String t){ JLabel l=new JLabel(t); l.setFont(new Font("Segoe UI",Font.PLAIN,12)); return l; }
    private JButton btn(String t,Color bg){ JButton b=new JButton(t); b.setBackground(bg); b.setForeground(Color.WHITE); b.setFont(new Font("Segoe UI",Font.BOLD,12)); return b; }
    private JTextField tf(int w){ return new JTextField(w); }
    private void msg(String t){ JOptionPane.showMessageDialog(this,t); }
  }
