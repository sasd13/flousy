package flousy.data.dao.accessor;

import proadmin.content.ListProjects;
import proadmin.content.Project;
import proadmin.content.Year;
import proadmin.content.id.ProjectId;

/**
 * Created by Samir on 11/06/2015.
 */
public interface ProjectAccessor {

    void insertProject(Project project, Year year);

    void updateProject(Project project);

    void deleteProject(ProjectId projectId);

    void deleteProjectFromYear(ProjectId projectId, Year year);

    Project selectProject(ProjectId projectId);

    ListProjects selectProjectsOfYear(Year year);
}
