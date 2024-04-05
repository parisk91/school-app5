package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;

    public Menu() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 400);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblTitle.setBounds(12, 26, 486, 65);
        contentPane.add(lblTitle);

        JLabel lblTitle2 = new JLabel("Ποιότητα στην Εκπαίδευση");
        lblTitle2.setForeground(new Color(0, 128, 0));
        lblTitle2.setFont(new Font("Tahoma", Font.PLAIN, 40));
        lblTitle2.setBounds(14, 28, 486, 65);
        contentPane.add(lblTitle2);

        JButton btnTeachers = new JButton("");
        btnTeachers.setBounds(12, 132, 40, 30);
        btnTeachers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchForm().setVisible(true);
            }
        });
        contentPane.add(btnTeachers);

        JButton btnStudents = new JButton("");
        btnStudents.setBounds(12, 183, 40, 30);
        contentPane.add(btnStudents);

        JSeparator separator = new JSeparator();
        separator.setBounds(22, 102, 450, 1);
        contentPane.add(separator);

        JLabel lblTeachers = new JLabel("Εκπαιδευτές");
        lblTeachers.setForeground(new Color(128, 0, 0));
        lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTeachers.setBounds(67, 127, 123, 40);
        contentPane.add(lblTeachers);

        JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
        lblStudents.setForeground(new Color(128, 0, 0));
        lblStudents.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStudents.setBounds(67, 178, 167, 40);
        contentPane.add(lblStudents);
    }
}