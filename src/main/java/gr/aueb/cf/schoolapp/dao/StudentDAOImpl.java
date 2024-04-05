package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO{
    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            String firstname = student.getFirstname();
            String lastname = student.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();

            if (n != 1) {
                return null;
            }

            JOptionPane.showMessageDialog(null, n + " rows affected", "Insert", JOptionPane.INFORMATION_MESSAGE);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Insert Error in teacher: " + student);
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = student.getId();
            String firstname = student.getFirstname();
            String lastname = student.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);
            int n = ps.executeUpdate();
            if (n != 1) {
                return null;
            }
            JOptionPane.showMessageDialog(null, n + " rows affected", "Update", JOptionPane.INFORMATION_MESSAGE);
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Update Error in teacher: " + student);
        }
    }

    @Override
    public void delete(Integer id) throws StudentDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Delete Error in teacher with id: " + id);
        }
    }

    @Override
    public Student getById(Integer id) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE id = ?";
        Student student = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Get By Id Error in teacher with id: " + id);
        }
        return student;
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT * FROM STUDENTS WHERE LASTNAME LIKE ?";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs;
            ps.setString(1, lastname + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StudentDAOException("SQL Get By lastname Error in teacher with lastname: " + lastname);
        }
        return students;
    }

}
