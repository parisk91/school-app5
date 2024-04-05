package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {

    /**
     * No instances of this class should be available
     */
    private TeacherValidator() {

    }

    public static Map<String, String> validate(TeacherInsertDTO teacherInsertDTO) {
        Map<String, String> errors = new HashMap<>();

        if (teacherInsertDTO.getFirstname().length() < 2 || teacherInsertDTO.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (teacherInsertDTO.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (teacherInsertDTO.getLastname().length() < 2 || teacherInsertDTO.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (teacherInsertDTO.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }
        return errors;
    }
}