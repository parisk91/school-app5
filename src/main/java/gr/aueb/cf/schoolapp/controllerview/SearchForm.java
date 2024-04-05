package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private String inputLastname;
    private final JTextField txt_lastname;

    public SearchForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 489, 381);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbl_lastname = new JLabel("Επώνυμο");
        lbl_lastname.setForeground(new Color(178, 34, 34));
        lbl_lastname.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_lastname.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lbl_lastname.setBounds(166, 30, 118, 45);
        contentPane.add(lbl_lastname);

        txt_lastname = new JTextField();
        txt_lastname.setBounds(125, 77, 200, 22);
        contentPane.add(txt_lastname);
        txt_lastname.setColumns(10);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.setForeground(new Color(0, 0, 255));

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputLastname = txt_lastname.getText();
                Main.getSearchForm().setEnabled(false);
                Main.getUpdateDeleteForm().setVisible(true);
            }
        });

        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSearch.setBounds(163, 112, 133, 45);
        contentPane.add(btnSearch);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(68, 13, 324, 151);
        contentPane.add(panel_1);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(false);
                Main.getInsertForm().setVisible(true);
            }
        });

        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnInsert.setBounds(166, 200, 133, 45);
        contentPane.add(btnInsert);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_2.setBounds(68, 177, 324, 93);
        contentPane.add(panel_2);

        JButton btnClose = new JButton("Close");
        btnClose.setForeground(new Color(0, 0, 205));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(true);
                Main.getSearchForm().setVisible(false);
            }
        });

        btnClose.setBounds(295, 296, 97, 25);
        contentPane.add(btnClose);
    }

    public String getInputLastname() {
        return inputLastname;
    }
}