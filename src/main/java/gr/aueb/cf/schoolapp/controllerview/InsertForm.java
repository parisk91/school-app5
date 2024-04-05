package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.validator.TeacherValidator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class InsertForm extends JFrame {
    private static final long serialVersionUID = 1L;

    // Wiring
    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private final JTextField frmSname;
    private final JTextField frmFname;

    public InsertForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setBackground(SystemColor.activeCaption);
        setTitle("Εισαγωγή Εκπαιδευτή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 511, 270);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblSname = new JLabel("Επίθετο");
        lblSname.setForeground(new Color(153, 0, 0));
        lblSname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSname.setBounds(38, 52, 56, 16);
        contentPane.add(lblSname);

        JLabel lblFname = new JLabel("Όνομα");
        lblFname.setForeground(new Color(153, 0, 0));
        lblFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFname.setBounds(38, 87, 56, 16);
        contentPane.add(lblFname);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Map<String, String> errors = new HashMap<>();

                // Data Binding
                String inputLastname = frmSname.getText().trim();
                String inputFirstname = frmFname.getText().trim();

                // Validation
//                if (inputLastname.equals("") || inputFirstname.equals("")) {
//                    JOptionPane.showMessageDialog(null, "Not valid input", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }

                try {
                    // Data Binding - Create DTO
                    TeacherInsertDTO insertDTO = new TeacherInsertDTO();
                    insertDTO.setFirstname(inputFirstname);
                    insertDTO.setLastname(inputLastname);

                    errors = TeacherValidator.validate(insertDTO);

                    if (!errors.isEmpty()) {
                        String firstnameMessage = (errors.get("firstname") != null) ? "Firstname: " + errors.get("firstname") : "";
                        String lastnameMessage = (errors.get("lastname") != null) ? "Lastname: " + errors.get("lastname") : "";
                        JOptionPane.showMessageDialog(null, firstnameMessage + " " + lastnameMessage , "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Teacher teacher = teacherService.insertTeacher(insertDTO);

                    if (teacher == null) {
                        JOptionPane.showMessageDialog(null, "Teacher not inserted", "INSERT", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    JOptionPane.showMessageDialog(null, "Teacher" + teacher.getLastname()
                            + " was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
                } catch (TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnInsert.setForeground(new Color(0, 0, 153));
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 15));

        frmSname = new JTextField();
        frmSname.setColumns(45);
        frmSname.setBounds(97, 51, 277, 22);
        contentPane.add(frmSname);

        frmFname = new JTextField();
        frmFname.setBounds(97, 87, 277, 22);
        contentPane.add(frmFname);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                frmSname.setText("");
                frmFname.setText("");
            }
        });

        JSeparator separator = new JSeparator();
        separator.setBounds(28, 168, 450, 1);
        contentPane.add(separator);
        btnInsert.setBounds(300, 183, 82, 25);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(true);
                Main.getInsertForm().setVisible(false);

            }
        });
        btnClose.setForeground(new Color(0, 0, 153));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnClose.setBounds(392, 183, 95, 25);
        contentPane.add(btnClose);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(18, 13, 469, 144);
        contentPane.add(panel);
    }
}
