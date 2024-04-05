package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        if (dto == null) return null;
        Teacher teacher = null;

        try {
            teacher = map(dto);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherNotFoundException, TeacherDAOException {
        if (dto == null) return null;
        Teacher teacher = null;

        try {
            teacher = map(dto);
//
//            if (teacherDAO.getById(teacher.getId()) == null) {
//                throw new TeacherNotFoundException(teacher);
//            }

            Teacher existingTeacher = Optional.ofNullable(teacherDAO.getById(teacher.getId()))
                    .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
//            return teacherDAO.update(teacher);
            return Optional.of(teacherDAO.update(teacher))
                    .orElseThrow(() -> new RuntimeException("runtime exception"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherNotFoundException, TeacherDAOException {
        if (id == null) return;

        try {
//            if (teacherDAO.getById(id) == null) {
//                throw new TeacherNotFoundException("Teacher not found");
//            }
//            teacherDAO.delete(id);
            Teacher existingTeacher = Optional.ofNullable(teacherDAO.getById(id))
                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " +  id + " not found"));
            teacherDAO.delete(existingTeacher.getId());
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        try {
//            teacher = teacherDAO.getById(id);
//            if (teacher == null) {
//                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
//            }
            return Optional.ofNullable(teacherDAO.getById(id))
                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " +  id + " not found"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
        // return teacher;
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException {
        //List<Teacher> teachers;

        try {
//            teachers = teacherDAO.getByLastname(lastname);
//            return teachers;
            return Optional.ofNullable(teacherDAO.getByLastname(lastname))
                    .orElseThrow(() -> new RuntimeException("Teachers not found"));
        } catch (TeacherDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Teacher map(TeacherInsertDTO dto) {
        return new Teacher(null, dto.getFirstname(), dto.getLastname());
    }

    private Teacher map(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}