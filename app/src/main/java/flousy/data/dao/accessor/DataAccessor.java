package flousy.data.dao.accessor;

/**
 * Created by Samir on 11/06/2015.
 */
public interface DataAccessor extends TeacherAccessor, YearAccessor, ProjectAccessor, SquadAccessor, StudentAccessor, ReportAccessor, NoteAccessor {

    void open();
    void close();
    DataAccessorType getType();
}
