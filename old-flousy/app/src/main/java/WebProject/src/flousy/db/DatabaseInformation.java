package flousy.db;

public class DatabaseInformation {
	String nom;
	String login;
	String motDePasse;
	String driver;
	String bridge;
	String host;
	
	
	public DatabaseInformation() {
		// TODO Auto-generated constructor stub
	}
	
	public static DatabaseInformation getDefaultDatabaseInformations(){
		DatabaseInformation db = new DatabaseInformation();
		db.setNom("Floussy");
		db.setLogin("root");
		db.setMotDePasse("simo");
		db.setHost("localhost:3306");
		db.setDriver("com.mysql.jdbc.Driver");
		db.setBridge("jdbc:mysql:");
		return db;
	}


	public String getNom() {
		return nom;
	}


	public String getLogin() {
		return login;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public String getDriver() {
		return driver;
	}


	public String getBridge() {
		return bridge;
	}


	public String getHost() {
		return host;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public void setBridge(String bridge) {
		this.bridge = bridge;
	}


	public void setHost(String host) {
		this.host = host;
	}
	
	
	
}
