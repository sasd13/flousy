package flousy.data.dao.accessor;

import proadmin.content.ListStudents;
import proadmin.content.Student;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface StudentAccessor {

    void insertStudent(Student student, SquadId squadId);

    void updateStudent(Student student);

    void deleteStudentFromSquad(StudentId studentId, SquadId squadId);

    Student selectStudent(StudentId studentId);

    Student selectStudent(String email);

    ListStudents selectStudentsOfSquad(SquadId squadId);
}
