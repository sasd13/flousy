package flousy.beans;

import java.sql.ResultSet;

import flousy.db.JDBCTool;

public class Utilisateurs {
	int idUtilisateur;
	String numTel;
	String nom;
	String prenom;
	String email;
	float salaire; 
	String login ; 
	String password;
	boolean connected;
	public static String JSON_USER_PARAMETER_NAME="USERJSON";

	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public  static void create(Utilisateurs user){
		
	}
	public  static Utilisateurs connect(String email,String password) throws Throwable{

		String requete = "select * from Utilisateur where email ='"+ email+"' and mdp='"+password+"'";
		ResultSet rs = JDBCTool.execute(requete);
		if (rs.next()) {
			Utilisateurs utilisateur = new Utilisateurs();
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setNumTel(rs.getString("numTel"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setSalaire(rs.getFloat("salaire"));
			utilisateur.setIdUtilisateur(rs.getInt("idUtilisateur"));
			utilisateur.setConnected(true);
			return utilisateur ;

		}else{
			Utilisateurs u = new Utilisateurs();
			u.setConnected(false);
			return u ;
		}
			
		
	}
	public static Boolean  inscriptionUtilisateur(Utilisateurs utilisateur)throws Throwable{
		
		try{
			JDBCTool.executeUpdate("insert into Utilisateur (numTel,nom,prenom,email,salaire,mdp)"
					+ "values ('"+ utilisateur.getNumTel()+"','"+ utilisateur.getNom()+"',"
							+ "'"+utilisateur.getPrenom()+"','"+utilisateur.getEmail()+"',"
									+ ""+utilisateur.getSalaire()+",'"+utilisateur.getPassword()+"')");
			return true ;
		}catch (Throwable e) {
			e.printStackTrace();
			}
		return false; 

	}
	public static boolean existutilisateur(String email)throws Throwable{
		String requete = "select * from Utilisateur where email ='"+ email+"'";
		ResultSet rs = JDBCTool.execute(requete);
		
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
	public boolean isConnected() {
		return connected;
	}
	public Utilisateurs() {

	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public String getNumTel() {
		return numTel;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getEmail() {
		return email;
	}
	public float getSalaire() {
		return salaire;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSalaire(float salaire) {
		this.salaire = salaire;
	}

}
