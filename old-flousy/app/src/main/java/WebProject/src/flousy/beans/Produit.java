package flousy.beans;

import java.sql.ResultSet;
import java.util.ArrayList;

import flousy.db.JDBCTool;

public class Produit {

	int idProduit;
	String nom;
	float prix;
	String photo;
	int idCategorie;
	public static String JSON_PRODUIT_PARAMETER_NAME="PRODUITJSON";

public static int insererProduit(Produit produit)throws Throwable{
		
	String requete	="insert into Produit (nom,prix,photo,Categorie_idCategorie) "
			+ "values ('"+ produit.getNom()+"',"+ produit.getPrix()+",'"+produit.getPhoto()+"',"+produit.getIdCategorie()+")";	
	//int id = JDBCTool.executeUpdate(requete);
	//produit.setIdProduit(id);
	//return produit;
	try{
		return JDBCTool.executeUpdate(requete);
	}catch (Throwable e) {
		e.printStackTrace();
		}
		return -1;
	}

public static Boolean supprimerProduit(int  idproduit)throws Throwable{
	
	String requete	="delete from Produit where idProduit="+idproduit+"";	
	try{
		JDBCTool.executeUpdate(requete);
		return true;
	}catch (Throwable e) {
		e.printStackTrace();
		}
		return false;
	}
public static Boolean supprimerProduitUtilisateur(int idproduit)throws Throwable{
	
	String requete	="delete from Produit_has_Utilisateur where Produit_idProduit="+idproduit+"";	
	try{
		JDBCTool.executeUpdate(requete);
		return true;
	}catch (Throwable e) {
		e.printStackTrace();
		}
		return false;
	}

public static int chercherProduit(String nom) throws Throwable{
	String requete = "select idCategorie from Categorie where nom ='"+nom+"'";
	ResultSet rs = JDBCTool.execute(requete);
	if(rs.next()){
		int id =rs.getInt("idcategorie");		
				return id;
		 
	}else{
		return -1;
	}
	
}

public static Produit chercherProduitId(int idProduit) throws Throwable{
	String requete = "select * from Produit where idProduit ="+idProduit;
	ResultSet rs = JDBCTool.execute(requete);
	if (rs.next()) {
		Produit produit = new Produit();
		produit.setIdProduit(rs.getInt("idProduit"));
		produit.setNom(rs.getString("nom"));
		produit.setPrix(rs.getFloat("prix"));
		produit.setPhoto(rs.getString("photo"));
		produit.setIdCategorie(rs.getInt("Categorie_idCategorie"));
		return produit;

	}else{
		return null;
	}
}
public static Boolean updateProduit(Produit produit)throws Throwable{
	String requete ="update Produit set nom='"+produit.getNom()+"',prix="+produit.getPrix()+""
			+",photo="+produit.getPhoto()+",Categorie_idCategorie="+produit.getIdCategorie()+" where idProduit="+produit.getIdProduit()+"";
	try{
		JDBCTool.executeUpdate(requete);
		return true ;
	}catch (Throwable e) {
		e.printStackTrace();
		}
	return false; 
	
}

public static ArrayList<Produit> ProduitCategorie(int idCategorie,int idUser)throws Throwable{
	
	String requete ="select * from Produit,Utilisateur,Produit_has_Utilisateur"
			+ " where Produit.idproduit=Produit_has_Utilisateur.Produit_idProduit "
			+ "and Produit.Categorie_idCategorie ="+idCategorie+" and Utilisateur.idUtilisateur ="+idUser;
	ResultSet rs = JDBCTool.execute(requete);
	 ArrayList<Produit> listProduit=new ArrayList<>();
	 while(rs.next()){
		 Produit produit = new Produit();
		 produit.setIdProduit(rs.getInt("idProduit"));
		 produit.setIdCategorie(rs.getInt("Categorie_idCategorie"));
		 produit.setNom(rs.getString("nom"));
		 produit.setPrix(rs.getFloat("prix"));
		 produit.setPhoto(rs.getString("photo"));
		 listProduit.add(produit);
		 
	 }
	
	return listProduit;
}
public static ArrayList<Produit> afficherProduiUtilisateur(int idUtilisateur)throws Throwable{
	
		String requete ="select * from Produit,Produit_has_Utilisateur "
						+ "where Produit.idProduit=Produit_has_Utilisateur.Produit_idProduit "
						+ "and Produit_has_Utilisateur.Utilisateur_idUtilisateur="+idUtilisateur+"";
		ResultSet rs = JDBCTool.execute(requete);
		 ArrayList<Produit> listProduit=new ArrayList<>();
		 while(rs.next()){
			 Produit produit = new Produit();
			 produit.setIdProduit(rs.getInt("idProduit"));
			 produit.setIdCategorie(rs.getInt("Categorie_idCategorie"));
			 produit.setNom(rs.getString("nom"));
			 produit.setPrix(rs.getFloat("prix"));
			 produit.setPhoto(rs.getString("photo"));
			 listProduit.add(produit);
			 
		 }
		
		return listProduit;
	}


	public int getIdProduit() {
		return idProduit;
	}
	public String getNom() {
		return nom;
	}
	public float getPrix() {
		return prix;
	}
	public String getPhoto() {
		return photo;
	}
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}


	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
