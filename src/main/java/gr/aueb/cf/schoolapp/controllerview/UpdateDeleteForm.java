package gr.aueb.cf.schoolapp.controllerview;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

public class UpdateDeleteForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField udfrm_sname;
    private final JTextField udfrm_fname;
    private final JTextField udfrm_id;
    private ResultSet rs;
    private String lastname;

    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    private int listPosition;
    private int listSize;
    private List<Teacher> teachers;

    public UpdateDeleteForm() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    // refresh query
                    //System.out.println(Main.getSearchForm().getInputLastname());
                    teachers = teacherService.getTeachersByLastname(Main.getSearchForm().getInputLastname());

                    listPosition = 0;
                    listSize = teachers.size();

                    if (listSize == 0) {
                        udfrm_id.setText("");
                        udfrm_fname.setText("");
                        udfrm_sname.setText("");
                        return;
                    }

                    udfrm_id.setText(Integer.toString(teachers.get(listPosition).getId()));
                    udfrm_fname.setText(teachers.get(listPosition).getFirstname());
                    udfrm_sname.setText(teachers.get(listPosition).getLastname());
                } catch (TeacherDAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error in getting teachers", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setBackground(SystemColor.activeCaption);
        setTitle("Εκπαιδευτές");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 340, 252);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        JLabel lbl_ID = new JLabel("ID");
        lbl_ID.setForeground(new Color(153, 0, 0));
        lbl_ID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_ID.setBounds(9, 13, 103, 16);
        contentPane.add(lbl_ID);

        JLabel lbl_sname = new JLabel("Επώνυμο");
        lbl_sname.setForeground(new Color(153, 0, 0));
        lbl_sname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_sname.setBounds(9, 48, 103, 16);
        contentPane.add(lbl_sname);

        JLabel lbl_fname = new JLabel("Όνομα");
        lbl_fname.setForeground(new Color(153, 0, 0));
        lbl_fname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_fname.setBounds(9, 83, 103, 16);
        contentPane.add(lbl_fname);

        udfrm_id = new JTextField();
        udfrm_id.setEditable(false);
        udfrm_id.setBounds(120, 13, 56, 22);
        contentPane.add(udfrm_id);
        udfrm_id.setColumns(10);

        udfrm_sname = new JTextField();
        udfrm_sname.setBounds(120, 48, 189, 22);
        contentPane.add(udfrm_sname);
        udfrm_sname.setColumns(10);

        udfrm_fname = new JTextField();
        udfrm_fname.setBounds(120, 81, 189, 22);
        contentPane.add(udfrm_fname);
        udfrm_fname.setColumns(10);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Data Binding
                String id = udfrm_id.getText().trim();
                String inputLastname = udfrm_sname.getText().trim();
                String inputFirstname = udfrm_fname.getText().trim();

                // Validation
                if (inputLastname.equals("") || inputFirstname.equals("") || id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Not valid input", "UPDATE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    TeacherUpdateDTO updateDTO = new TeacherUpdateDTO();
                    updateDTO.setId(Integer.parseInt(id));
                    updateDTO.setFirstname(inputFirstname);
                    updateDTO.setLastname(inputLastname);

                    Teacher teacher = teacherService.updateTeacher(updateDTO);

                    // Success
                    JOptionPane.showMessageDialog(null, "Teacher "
                            + " was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
                } catch (TeacherDAOException  | TeacherNotFoundException e1) {
                    // Failure
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    int id;

                    // Data Binding
                    String idStr = udfrm_id.getText();
                    id = Integer.parseInt(udfrm_id.getText());

                    // Validation
                    if (idStr.equals("")) return;

                    // Call Service after user-confirmation
                    response = JOptionPane.showConfirmDialog (null, "Είστε σίγουρος;",
                            "Warning", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION){
                        teacherService.deleteTeacher(id);
                        JOptionPane.showMessageDialog (null, "Teacher was deleted successfully",
                                "DELETE", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (TeacherDAOException | TeacherNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog (null, message, "DELETE", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete.setBounds(9, 165, 89, 25);
        contentPane.add(btnDelete);
        btnUpdate.setBounds(91, 165, 92, 25);
        contentPane.add(btnUpdate);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchForm().setEnabled(true);
                Main.getUpdateDeleteForm().setVisible(false);
            }
        });
        btnClose.setBounds(180, 165, 95, 25);
        contentPane.add(btnClose);

        JButton btnFirst = new JButton("");
        btnFirst.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("First record.png"))));
        btnFirst.setBounds(9, 132, 39, 25);
        btnFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    udfrm_id.setText(String.format("%s", teachers.get(listPosition).getId()));
                    udfrm_sname.setText(teachers.get(listPosition).getLastname());
                    udfrm_fname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });

        contentPane.add(btnFirst);
        JButton btnPrev = new JButton("");
        btnPrev.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png"))));
        btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition > 0) {
                    listPosition--;
                    udfrm_id.setText(String.format("%s", teachers.get(listPosition).getId()));
                    udfrm_sname.setText(teachers.get(listPosition).getLastname());
                    udfrm_fname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });

        btnPrev.setBounds(44, 132, 39, 25);
        contentPane.add(btnPrev);

        JButton btnNext = new JButton("");
        btnNext.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Next_track.png"))));

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    udfrm_id.setText(String.format("%s", teachers.get(listPosition).getId()));
                    udfrm_sname.setText(teachers.get(listPosition).getLastname());
                    udfrm_fname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });

        JButton btnLast = new JButton("");
        btnLast.setIcon(new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png"))));
        btnLast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    udfrm_id.setText(String.format("%s", teachers.get(listPosition).getId()));
                    udfrm_sname.setText(teachers.get(listPosition).getLastname());
                    udfrm_fname.setText(teachers.get(listPosition).getFirstname());
                }
            }
        });

        btnNext.setBounds(78, 132, 46, 25);
        contentPane.add(btnNext);
        btnLast.setBounds(120, 132, 39, 25);
        contentPane.add(btnLast);

        JSeparator separator = new JSeparator();
        separator.setBounds(9, 113, 305, 4);
        contentPane.add(separator);


    }
}