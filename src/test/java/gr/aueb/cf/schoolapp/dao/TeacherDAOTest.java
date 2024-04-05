package gr.aueb.cf.schoolapp.dao;


import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {
    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws TeacherDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    //Έλεγχος για insert
    @Test
    void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Kur.");

        teacherDAO.insert(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname("Kur.");
        assertEquals(1, teachers.size());
    }

    @Test
    void update() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setFirstname("AnnaUpdated");
        teacher.setLastname("GiannoutsouUpdated");

        teacherDAO.update(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname(teacher.getLastname());
        // assertEquals(teachers.get(0).getFirstname(), "AnnaUpdated");
        assertEquals(1, teachers.size());
    }

    @Test
    void delete()throws TeacherDAOException {
        int id = 1;
        teacherDAO.delete(id);

        Teacher teacher = teacherDAO.getById(id);
        assertNull(teacher);
    }

    @Test
    void getByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getByLastname("Androu");
        assertEquals(1, teachers.size());
    }

    @Test
    void getById() throws TeacherDAOException {
        int id = 4;
        Teacher teacher = teacherDAO.getById(id);
        // assertEquals("Lennon", teacher.getLastname());
        assertNotNull(teacher);
    }

    public static void createDummyData() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Thanassis");
        teacher.setLastname("Androutsos");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Anna");
        teacher.setLastname("Giannoutsou");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Alice");
        teacher.setLastname("Wonderland");
        teacherDAO.insert(teacher);

        teacher.setFirstname("John");
        teacher.setLastname("Lennon");
        teacherDAO.insert(teacher);
    }



}