package flousy.data.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Table teacher
     */
    public static final String TEACHER_TABLE_DROP = "DROP TABLE IF EXISTS " + TeacherDAO.TEACHER_TABLE_NAME + ";";
    public static final String TEACHER_TABLE_CREATE =
            "CREATE TABLE " + TeacherDAO.TEACHER_TABLE_NAME + " ("
                    + TeacherDAO.TEACHER_ID + " TEXT PRIMARY KEY NOT NULL, "
                    + TeacherDAO.TEACHER_FIRSTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_LASTNAME + " TEXT NOT NULL, "
                    + TeacherDAO.TEACHER_EMAIL + " TEXT NOT NULL UNIQUE, "
                    + TeacherDAO.TEACHER_PASSWORD + " TEXT NOT NULL);";

    /**
     * Table year
     */
    public static final String YEAR_TABLE_DROP = "DROP TABLE IF EXISTS " + YearDAO.YEAR_TABLE_NAME + ";";
    public static final String YEAR_TABLE_CREATE =
            "CREATE TABLE " + YearDAO.YEAR_TABLE_NAME + " ("
                    + YearDAO.YEAR_YEAR + " INTEGER PRIMARY KEY NOT NULL);";

    /**
     * Table project
     */
    public static final String PROJECT_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectDAO.PROJECT_TABLE_NAME + ";";
    public static final String PROJECT_TABLE_CREATE =
            "CREATE TABLE " + ProjectDAO.PROJECT_TABLE_NAME + " ("
                    + ProjectDAO.PROJECT_ID + " TEXT PRIMARY KEY NOT NULL, "
                    + ProjectDAO.PROJECT_TITLE + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_GRADE + " TEXT NOT NULL, "
                    + ProjectDAO.PROJECT_DESCRIPTION + " TEXT NOT NULL);";

    /**
     * Table student_has_squad
     */
    public static final String PROJECT_HAS_YEAR_TABLE_DROP = "DROP TABLE IF EXISTS " + ProjectHasYearDAO.PROJECT_HAS_YEAR_TABLE_NAME + ";";
    public static final String PROJECT_HAS_YEAR_TABLE_CREATE =
            "CREATE TABLE " + ProjectHasYearDAO.PROJECT_HAS_YEAR_TABLE_NAME + " ("
                    + ProjectHasYearDAO.PROJECT_HAS_YEAR_PROJECT_ID + " TEXT NOT NULL, "
                    + ProjectHasYearDAO.PROJECT_HAS_YEAR_YEAR_YEAR +" INTEGER NOT NULL, "
                    + "FOREIGN KEY (" + ProjectHasYearDAO.PROJECT_HAS_YEAR_PROJECT_ID + ") REFERENCES " + ProjectDAO.PROJECT_TABLE_NAME + "("+ ProjectDAO.PROJECT_ID + "), "
                    + "FOREIGN KEY (" + ProjectHasYearDAO.PROJECT_HAS_YEAR_YEAR_YEAR + ") REFERENCES " + YearDAO.YEAR_TABLE_NAME + "("+ YearDAO.YEAR_YEAR + "), "
                    + "PRIMARY KEY (" + ProjectHasYearDAO.PROJECT_HAS_YEAR_PROJECT_ID + ", " + ProjectHasYearDAO.PROJECT_HAS_YEAR_YEAR_YEAR + "));";

    /**
     * Table squad
     */
    public static final String SQUAD_TABLE_DROP = "DROP TABLE IF EXISTS " + SquadDAO.SQUAD_TABLE_NAME + ";";
    public static final String SQUAD_TABLE_CREATE =
            "CREATE TABLE " + SquadDAO.SQUAD_TABLE_NAME + " ("
                    + SquadDAO.SQUAD_ID + " TEXT PRIMARY KEY NOT NULL, "
                    + SquadDAO.SQUAD_YEAR +" INTEGER NOT NULL, "
                    + SquadDAO.SQUAD_PROJECT_ID +" TEXT NOT NULL, "
                    + SquadDAO.SQUAD_TEACHER_ID +" TEXT NOT NULL, "
                    + "FOREIGN KEY (" + SquadDAO.SQUAD_YEAR + ") REFERENCES " + YearDAO.YEAR_TABLE_NAME + "("+ YearDAO.YEAR_YEAR + "), "
                    + "FOREIGN KEY (" + SquadDAO.SQUAD_PROJECT_ID + ") REFERENCES " + ProjectDAO.PROJECT_TABLE_NAME + "("+ ProjectDAO.PROJECT_ID + "), "
                    + "FOREIGN KEY (" + SquadDAO.SQUAD_TEACHER_ID + ") REFERENCES " + TeacherDAO.TEACHER_TABLE_NAME + "("+ TeacherDAO.TEACHER_ID + "));";

    /**
     * Table student
     */
    public static final String STUDENT_TABLE_DROP = "DROP TABLE IF EXISTS " + ClientDAO.STUDENT_TABLE_NAME + ";";
    public static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + ClientDAO.STUDENT_TABLE_NAME + " ("
                    + ClientDAO.STUDENT_ID + " TEXT PRIMARY KEY NOT NULL, "
                    + ClientDAO.STUDENT_FIRSTNAME + " TEXT NOT NULL, "
                    + ClientDAO.STUDENT_LASTNAME + " TEXT NOT NULL, "
                    + ClientDAO.STUDENT_EMAIL + " TEXT NOT NULL UNIQUE);";

    /**
     * Table student_has_squad
     */
    public static final String STUDENT_HAS_SQUAD_TABLE_DROP = "DROP TABLE IF EXISTS " + StudentHasSquadDAO.STUDENT_HAS_SQUAD_TABLE_NAME + ";";
    public static final String STUDENT_HAS_SQUAD_TABLE_CREATE =
            "CREATE TABLE " + StudentHasSquadDAO.STUDENT_HAS_SQUAD_TABLE_NAME + " ("
                    + StudentHasSquadDAO.STUDENT_HAS_SQUAD_STUDENT_ID + " TEXT NOT NULL, "
                    + StudentHasSquadDAO.STUDENT_HAS_SQUAD_SQUAD_ID +" TEXT NOT NULL, "
                    + "FOREIGN KEY (" + StudentHasSquadDAO.STUDENT_HAS_SQUAD_STUDENT_ID + ") REFERENCES " + ClientDAO.STUDENT_TABLE_NAME + "("+ ClientDAO.STUDENT_ID + "), "
                    + "FOREIGN KEY (" + StudentHasSquadDAO.STUDENT_HAS_SQUAD_SQUAD_ID + ") REFERENCES " + SquadDAO.SQUAD_TABLE_NAME + "("+ SquadDAO.SQUAD_ID + "), "
                    + "PRIMARY KEY (" + StudentHasSquadDAO.STUDENT_HAS_SQUAD_STUDENT_ID + ", " + StudentHasSquadDAO.STUDENT_HAS_SQUAD_SQUAD_ID + "));";

    /**
     * Table report
     */
    public static final String REPORT_TABLE_DROP = "DROP TABLE IF EXISTS " + ReportDAO.REPORT_TABLE_NAME + ";";
    public static final String REPORT_TABLE_CREATE =
            "CREATE TABLE " + ReportDAO.REPORT_TABLE_NAME + " ("
                    + ReportDAO.REPORT_ID + " TEXT PRIMARY KEY NOT NULL, "
                    + ReportDAO.REPORT_NUMBER_WEEK + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_PLANNING_NOTE + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_PLANNING_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_COMMUNICATION_NOTE + " INTEGER NOT NULL, "
                    + ReportDAO.REPORT_COMMUNICATION_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_COMMENT + " TEXT, "
                    + ReportDAO.REPORT_SQUAD_ID + " TEXT NOT NULL, "
                    + ReportDAO.REPORT_STUDENT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + ReportDAO.REPORT_SQUAD_ID + ") REFERENCES " + SquadDAO.SQUAD_TABLE_NAME + "("+ SquadDAO.SQUAD_ID + "), "
                    + "FOREIGN KEY (" + ReportDAO.REPORT_STUDENT_ID + ") REFERENCES " + ClientDAO.STUDENT_TABLE_NAME + "("+ ClientDAO.STUDENT_ID + "));";

    /**
     * Table note
     */
    public static final String NOTE_TABLE_DROP = "DROP TABLE IF EXISTS " + PhoneDAO.NOTE_TABLE_NAME + ";";
    public static final String NOTE_TABLE_CREATE =
            "CREATE TABLE " + PhoneDAO.NOTE_TABLE_NAME + " ("
                    + PhoneDAO.NOTE_NOTE + " INTEGER NOT NULL, "
                    + PhoneDAO.NOTE_STUDENT_ID + " TEXT NOT NULL, "
                    + PhoneDAO.NOTE_REPORT_ID + " TEXT NOT NULL, "
                    + "FOREIGN KEY (" + PhoneDAO.NOTE_STUDENT_ID + ") REFERENCES " + ClientDAO.STUDENT_TABLE_NAME + "("+ ClientDAO.STUDENT_ID + "), "
                    + "FOREIGN KEY (" + PhoneDAO.NOTE_REPORT_ID + ") REFERENCES " + ReportDAO.REPORT_TABLE_NAME + "("+ ReportDAO.REPORT_ID + "), "
                    + "PRIMARY KEY (" + PhoneDAO.NOTE_STUDENT_ID + ", " + PhoneDAO.NOTE_REPORT_ID + "));";


    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEACHER_TABLE_CREATE);
        db.execSQL(YEAR_TABLE_CREATE);
        db.execSQL(PROJECT_TABLE_CREATE);
        db.execSQL(PROJECT_HAS_YEAR_TABLE_CREATE);
		db.execSQL(SQUAD_TABLE_CREATE);
		db.execSQL(STUDENT_TABLE_CREATE);
		db.execSQL(STUDENT_HAS_SQUAD_TABLE_CREATE);
		db.execSQL(REPORT_TABLE_CREATE);
		db.execSQL(NOTE_TABLE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NOTE_TABLE_DROP);
        db.execSQL(REPORT_TABLE_DROP);
        db.execSQL(STUDENT_HAS_SQUAD_TABLE_DROP);
        db.execSQL(STUDENT_TABLE_DROP);
        db.execSQL(SQUAD_TABLE_DROP);
        db.execSQL(PROJECT_HAS_YEAR_TABLE_DROP);
        db.execSQL(PROJECT_TABLE_DROP);
        db.execSQL(YEAR_TABLE_DROP);
        db.execSQL(TEACHER_TABLE_DROP);

		onCreate(db);
	}
	
	//Methode pour activer la contrainte de cle etrangere a l'ouverture de la base de donnees
	//Par defaut la contrainte n'est pas active dans SQLite
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);

	    if (!db.isReadOnly()) {	        
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}
}
