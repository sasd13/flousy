package flousy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by simo on 23/03/2015.
 */
public class JDBCTool extends DbConnect {
    private static JDBCTool ourInstance = new JDBCTool();

    public static JDBCTool getInstance() {
        return ourInstance;
    }

    public  java.sql.Connection openConnection(

            DatabaseInformation databaseInformation) throws Throwable {
        java.sql.Connection conn = null;
        try {
            String pilote = databaseInformation.getDriver();
            Class.forName(pilote);
            String adresse = databaseInformation.getHost();
            String user = databaseInformation.getLogin();
            String pass = databaseInformation.getMotDePasse();
            String databasename = databaseInformation.getNom();
            String bridge = databaseInformation.getBridge();
            String url;

            url = new String(bridge + "//" + adresse + "/" + databasename);

            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
        } catch (Exception ex) {
            throw ex;
        }
        return conn;
    }

    public  ResultSet execute(String req) throws Throwable {
        Connection connection = null;
        ResultSet rs = null;
        Statement stmt = null;

        try {

            connection = openConnection(DatabaseInformation
                    .getDefaultDatabaseInformations());
            stmt = connection.createStatement();
            rs = stmt.executeQuery(req);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return rs;
    }

    public  int executeUpdate(String req) throws Throwable {
        Connection connection = null;
        Statement stmt = null;
        int sc = -1;
        try {

            connection = openConnection(DatabaseInformation
                    .getDefaultDatabaseInformations());
            stmt = connection.createStatement();
            sc = stmt.executeUpdate(req);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return sc;
    }






    private JDBCTool() {
    }
}
