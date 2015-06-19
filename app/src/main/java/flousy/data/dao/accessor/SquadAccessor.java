package flousy.data.dao.accessor;

import proadmin.content.Squad;
import proadmin.content.Year;
import proadmin.content.ListSquads;
import proadmin.content.id.ProjectId;
import proadmin.content.id.SquadId;
import proadmin.content.id.TeacherId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface SquadAccessor {

    void insertSquad(Squad squad);

    void updateSquad(Squad squad);

    void deleteSquad(SquadId squadId);

    Squad selectSquad(SquadId squadId);

    ListSquads selectSquadsOfTeacher(TeacherId teacherId);

    ListSquads selectSquadsOfYear(Year year);

    ListSquads selectSquadsOfProject(ProjectId projectId);

    ListSquads selectSquadsOfTeacherAndYear(TeacherId teacherId, Year year);

    ListSquads selectSquadsOfTeacherAndProject(TeacherId teacherId, ProjectId projectId);

    ListSquads selectSquadsOfYearAndProject(Year year, ProjectId projectId);

    ListSquads selectSquadsOfTeacherAndYearAndProjectAndYear(TeacherId teacherId, Year year, ProjectId projectId);
}
