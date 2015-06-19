package flousy.data.dao.accessor;

import proadmin.content.Teacher;
import proadmin.content.id.TeacherId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface TeacherAccessor {

    void insertTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    void deleteTeacher(TeacherId teacherId);

    Teacher selectTeacher(TeacherId teacherId);

    Teacher selectTeacher(String email);
}
