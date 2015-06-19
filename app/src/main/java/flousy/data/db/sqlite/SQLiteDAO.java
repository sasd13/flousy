package flousy.data.db.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import proadmin.content.id.ListIds;
import proadmin.content.MapNotes;
import proadmin.content.ListProjects;
import proadmin.content.ListReports;
import proadmin.content.ListStudents;
import proadmin.content.ListYears;
import proadmin.content.Squad;
import proadmin.content.Project;
import proadmin.content.Report;
import proadmin.content.Student;
import proadmin.content.Teacher;
import proadmin.content.Year;
import proadmin.content.ListSquads;
import proadmin.content.id.ProjectId;
import proadmin.content.id.ReportId;
import proadmin.content.id.SquadId;
import proadmin.content.id.StudentId;
import proadmin.content.id.TeacherId;
import proadmin.data.dao.accessor.DataAccessor;
import proadmin.data.dao.accessor.DataAccessorType;

public class SQLiteDAO implements DataAccessor {

    private static SQLiteDAO instance = null;
	
	private static final int VERSION = 1;
	private static final String NOM = "database.db";
	private SQLiteDatabase db = null;
	private DatabaseHandler dbHandler = null;

	private TeacherDAO teacherDAO;
    private YearDAO yearDAO;
	private ProjectDAO projectDAO;
	private ProjectHasYearDAO projectHasYearDAO;
	private SquadDAO squadDAO;
	private ClientDAO clientDAO;
	private StudentHasSquadDAO studentHasSquadDAO;
	private ReportDAO reportDAO;
	private PhoneDAO phoneDAO;

	private SQLiteDAO() {
        teacherDAO = new TeacherDAO();
        yearDAO = new YearDAO();
        projectDAO = new ProjectDAO();
        projectHasYearDAO = new ProjectHasYearDAO();
        squadDAO = new SquadDAO();
        clientDAO = new ClientDAO();
        studentHasSquadDAO = new StudentHasSquadDAO();
        reportDAO = new ReportDAO();
        phoneDAO = new PhoneDAO();
    }

    public static synchronized SQLiteDAO getInstance() {
        if (instance == null) {
            instance = new SQLiteDAO();
        }

        return instance;
    }

    public void create(Context context) {
        dbHandler = new DatabaseHandler(context, NOM, null, VERSION);
    }

	@Override
    public void open() {
        db = dbHandler.getWritableDatabase();

        teacherDAO.setDb(db);
        yearDAO.setDb(db);
        projectDAO.setDb(db);
        projectHasYearDAO.setDb(db);
        squadDAO.setDb(db);
        clientDAO.setDb(db);
        studentHasSquadDAO.setDb(db);
        reportDAO.setDb(db);
        phoneDAO.setDb(db);
	}

    @Override
	public void close() {
        db.close();
	}

    @Override
    public DataAccessorType getType() {
        return DataAccessorType.SQLITE;
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        if (!teacherDAO.contains(teacher.getId()) && !teacherDAO.contains(teacher.getEmail())) {
            teacherDAO.insert(teacher);
        }
    }

    @Override
	public void updateTeacher(Teacher teacher) {
        Teacher teacher2 = selectTeacher(teacher.getEmail());
        if (teacher2.getId().equals(teacher.getId())) {
            teacherDAO.update(teacher);
        }
	}

    @Override
	public void deleteTeacher(TeacherId teacherId) {
        ListSquads listSquads = selectSquadsOfTeacher(teacherId);
        for (Object squad : listSquads) {
            deleteSquad(((Squad) squad).getId());
        }

		teacherDAO.delete(teacherId);
	}

    @Override
	public Teacher selectTeacher(TeacherId teacherId) {
		return teacherDAO.select(teacherId);
	}

    @Override
	public Teacher selectTeacher(String email) {
		return teacherDAO.select(email);
	}

    @Override
    public void deleteYear(Year year) {
        ListSquads listSquads = selectSquadsOfYear(year);
        for (Object squad : listSquads) {
            deleteSquad(((Squad) squad).getId());
        }

        ListProjects listProjects = selectProjectsOfYear(year);
        for (Object project : listProjects) {
            deleteProjectFromYear(((Project) project).getId(), year);
        }

        yearDAO.delete(year);
    }

    @Override
    public ListYears selectYears() {
        return yearDAO.selectAll();
    }

    @Override
    public ListYears selectYearsOfProjectByDesc(ProjectId projectId) {
        return projectHasYearDAO.selectAllOfProjectByDesc(projectId);
    }

    @Override
    public Year selectYearCreationOfProject(ProjectId projectId) {
        ListYears listYears = projectHasYearDAO.selectAllOfProjectByDesc(projectId);

        return listYears.get(listYears.size() - 1);
    }

    @Override
	public void insertProject(Project project, Year year) {
        if (!yearDAO.contains(year)) {
            yearDAO.insert(year);
        }

        if (!projectDAO.contains(project.getId())) {
            projectDAO.insert(project);
        }

        if (!projectHasYearDAO.contains(project.getId(), year)) {
            projectHasYearDAO.insert(project.getId(), year);
        }
	}

    @Override
	public void updateProject(Project project) {
		projectDAO.update(project);
	}

    @Override
	public void deleteProject(ProjectId projectId) {
		ListSquads listSquads = selectSquadsOfProject(projectId);
        for (Object squad : listSquads) {
            deleteSquad(((Squad) squad).getId());
        }

		projectHasYearDAO.deleteAllOfProject(projectId);
		projectDAO.delete(projectId);
	}

    @Override
	public void deleteProjectFromYear(ProjectId projectId, Year year) {
        ListSquads listSquads = selectSquadsOfYearAndProject(year, projectId);
        for (Object squad : listSquads) {
            deleteSquad(((Squad) squad).getId());
        }

		projectHasYearDAO.delete(projectId, year);
	}

    @Override
	public Project selectProject(ProjectId projectId) {
		return projectDAO.select(projectId);
	}

    @Override
    public ListProjects selectProjectsOfYear(Year year) {
        ListProjects listProjects = new ListProjects();

        ListIds listProjectsIds = projectHasYearDAO.selectAllOfYear(year);
        for (Object projectId : listProjectsIds) {
            listProjects.add(projectDAO.select((ProjectId) projectId));
        }

        return listProjects;
    }

    @Override
	public void insertSquad(Squad squad) {
        if (!squadDAO.contains(squad.getId())) {
            long rowId = squadDAO.insert(squad);

            if (rowId > 0) {
                for (Object student : squad.getListStudents()) {
                    insertStudent((Student) student, squad.getId());
                }

                for (Object report : squad.getListReports()) {
                    insertReport((Report) report);
                }
            }
        }
	}

    @Override
	public void updateSquad(Squad squad) {
		squadDAO.update(squad);

        for (Object student : squad.getListStudents()) {
            updateStudent((Student) student);
        }

        for (Object report : squad.getListReports()) {
            updateReport((Report) report);
        }
	}

    @Override
	public void deleteSquad(SquadId squadId) {
        ListReports listReports = selectReportsOfSquad(squadId);
        for (Object report : listReports) {
            deleteReport(((Report) report).getId());
        }

        ListStudents listStudents = selectStudentsOfSquad(squadId);
        for (Object student : listStudents) {
            deleteStudentFromSquad(((Student) student).getId(), squadId);
        }

		squadDAO.delete(squadId);
	}

    @Override
	public Squad selectSquad(SquadId squadId) {
		return squadDAO.select(squadId);
	}

    @Override
    public ListSquads selectSquadsOfTeacher(TeacherId teacherId) {
        return squadDAO.selectAllOfTeacher(teacherId);
    }

    @Override
    public ListSquads selectSquadsOfYear(Year year) {
        return squadDAO.selectAllOfYear(year);
    }

    @Override
    public ListSquads selectSquadsOfProject(ProjectId projectId) {
        return squadDAO.selectAllOfProject(projectId);
    }

    @Override
    public ListSquads selectSquadsOfTeacherAndYear(TeacherId teacherId, Year year) {
        return squadDAO.selectAllOfTeacherAndYear(teacherId, year);
    }

    @Override
    public ListSquads selectSquadsOfTeacherAndProject(TeacherId teacherId, ProjectId projectId) {
        return squadDAO.selectAllOfTeacherAndProject(teacherId, projectId);
    }

	@Override
	public ListSquads selectSquadsOfYearAndProject(Year year, ProjectId projectId) {
		return squadDAO.selectAllOfYearAndProject(year, projectId);
	}

    @Override
    public ListSquads selectSquadsOfTeacherAndYearAndProjectAndYear(TeacherId teacherId, Year year, ProjectId projectId) {
        return squadDAO.selectAllOfTeacherAndYearAndProject(teacherId, year, projectId);
    }

    @Override
	public void insertStudent(Student student, SquadId squadId) {
        if (!clientDAO.contains(student.getId()) && !clientDAO.contains(student.getEmail())) {
            clientDAO.insert(student);
        }

        if (!studentHasSquadDAO.contains(student.getId(), squadId)) {
            studentHasSquadDAO.insert(student.getId(), squadId);
        }
	}

    @Override
	public void updateStudent(Student student) {
        Student student2 = selectStudent(student.getEmail());
        if (student2.getId().equals(student.getId())) {
            clientDAO.update(student);
        }
	}

    @Override
	public void deleteStudentFromSquad(StudentId studentId, SquadId squadId) {
        ListReports listReports = reportDAO.selectAllOfSquadAndStudent(squadId, studentId);

        //TODO

		studentHasSquadDAO.delete(studentId, squadId);
	}

    @Override
	public Student selectStudent(StudentId studentId) {
		return clientDAO.select(studentId);
	}

    @Override
    public Student selectStudent(String email) {
        return clientDAO.select(email);
    }

    @Override
	public ListStudents selectStudentsOfSquad(SquadId squadId) {
        ListStudents listStudents = new ListStudents();

        ListIds listIds = studentHasSquadDAO.selectAllOfSquad(squadId);
        for (Object id : listIds) {
            listStudents.add(clientDAO.select((StudentId) id));
        }

        return listStudents;
	}

    @Override
	public void insertReport(Report report) {
        if (!reportDAO.contains(report.getId())) {
            reportDAO.insert(report);
        }
	}

    @Override
	public void updateReport(Report report) {
		reportDAO.update(report);
	}

    @Override
	public void deleteReport(ReportId reportId) {
        phoneDAO.deleteAllOfReport(reportId);
		reportDAO.delete(reportId);
	}

    @Override
	public Report selectReport(ReportId reportId) {
		return reportDAO.select(reportId);
	}

	@Override
	public ListReports selectReportsOfSquad(SquadId squadId) {
        return reportDAO.selectAllOfSquad(squadId);
	}

    @Override
    public void insertNotes(MapNotes mapNotes, ReportId reportId) {
        StudentId[] tabStudentsIds = mapNotes.getKeys();
        for (StudentId studentId : tabStudentsIds) {
            if (!phoneDAO.contains(reportId, studentId)) {
                phoneDAO.insert(mapNotes.get(studentId), reportId, studentId);
            }
        }
    }

    @Override
    public void updateNotes(MapNotes mapNotes, ReportId reportId) {
        StudentId[] tabStudentsIds = mapNotes.getKeys();
        for (StudentId studentId : tabStudentsIds) {
            phoneDAO.update(mapNotes.get(studentId), reportId, studentId);
        }
    }

    @Override
	public MapNotes selectNotesOfReport(ReportId reportId) {
        return phoneDAO.selectAllOfReport(reportId);
	}
}
