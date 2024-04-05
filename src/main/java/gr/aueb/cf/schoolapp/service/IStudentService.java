package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.dto.StudentUpdateDTO;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;

import java.util.List;

public interface IStudentService {
    Student insertStudent(StudentInsertDTO dto) throws StudentDAOException, StudentNotFoundException;
    Student updateStudent(StudentUpdateDTO dto) throws StudentNotFoundException, StudentDAOException;
    void deleteStudent(Integer id) throws StudentNotFoundException, StudentDAOException;
    Student getStudentById(Integer id) throws StudentDAOException, StudentNotFoundException;
    List<Student> getStudentByLastname(String lastname) throws StudentDAOException;
}
