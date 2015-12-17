package flousy.beans;

import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import flousy.db.DatabaseInformation;
import flousy.db.JDBCTool;

@SuppressWarnings("unused")
public class ProduitUtilisateur {
	int idProduit;
	int idCategorie;
	int idUtilisateur;
	
	public static String JSON_PRODUIT_USER_NAME="PRODUIT_USER_JSON";

	
	public int getIdProduit() {
		return idProduit;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public static Boolean ajoutProduitUtilisateur(ProduitUtilisateur produitutilisateur)throws Throwable{
		
		String requete ="insert into Produit_has_Utilisateur (Produit_idProduit,Produit_Categorie_idCategorie,Utilisateur_idUtilisateur)"
				+ "values ("+ produitutilisateur.getIdProduit()+","+produitutilisateur.getIdCategorie()+","
						+ ""+produitutilisateur.getIdUtilisateur()+")";
		
		
		try{
			JDBCTool.executeUpdate(requete);
			return true ;
		}catch (Throwable e) {
			e.printStackTrace();
			}
		return false; 
		
	}
	public static int chercherUtilisateurId(String email) throws Throwable{
		String requete = "select idUtilisateur from Utilisateur where email ='"+email+"'";
		ResultSet rs = JDBCTool.execute(requete);
		if(rs.next()){
			int id =rs.getInt("idUtilisateur");		
					return id;
			 
		}else{
			return -1;
		}
		
	}
	

}
