package flousy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class JDBCTool {

	public static java.sql.Connection openConnection(
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

	public static ResultSet execute(String req) throws Throwable {
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

	public static int executeUpdate(String req) throws Throwable {
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

	

}
