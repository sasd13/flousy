package flousy.beans;

import java.sql.ResultSet;
import java.util.ArrayList;

import flousy.db.JDBCTool;
public class Categorie {
	int idCategorie;
	String nom;
	String image;
	public static String JSON_CATEGORIE_PARAMETER_NAME="CATEGORIEJSON";

	
	public static Categorie getCategorieByid(int idCategorie) throws Throwable{
		//String idCat =Integer.toString(idCategorie);
		String requete = "select * from Categorie where idCategorie ="+idCategorie+"";
		ResultSet rs = JDBCTool.execute(requete);
		if (rs.next()) {
			Categorie categorie = new Categorie();
			categorie.setNom(rs.getString("nom"));
			categorie.setIdCategorie(rs.getInt("idUtilisateur"));
			categorie.setImage(rs.getString("image"));
			return categorie;

		}else{
			return null;
		}
		
	}
	
	public static boolean existCategorie(String nom)throws Throwable{
		String requete = "select * from Categorie where nom ='"+ nom+"'";
		ResultSet rs = JDBCTool.execute(requete);
		
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		
	}	
	
	public static Categorie getCategorieByName(String nom) throws Throwable{

		String requete = "select * from Categorie where nom ="+nom+"";
		ResultSet rs = JDBCTool.execute(requete);
		if (rs.next()) {
			Categorie categorie = new Categorie();
			categorie.setNom(rs.getString("nom"));
			categorie.setIdCategorie(rs.getInt("idUtilisateur"));
			categorie.setImage(rs.getString("image"));
			return categorie;

		}else{
			return null;
		}
		
		
		
	}
	public static ArrayList<Categorie> getAllCategorie()  throws Throwable{
		String requete = "select * from Categorie";
		ResultSet rs = JDBCTool.execute(requete);
		 ArrayList<Categorie> listCategorie=new ArrayList<>();
		while (rs.next()) {
			Categorie categorie = new Categorie();
			categorie.setNom(rs.getString("nom"));
			categorie.setIdCategorie(rs.getInt("idCategorie"));
			categorie.setImage(rs.getString("image"));
			listCategorie.add(categorie);

		}
		
		return listCategorie ;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public String getNom() {
		return nom;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	

}
