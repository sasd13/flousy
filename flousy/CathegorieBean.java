package flousy;

public class CathegorieBean {

	String nom;
	String photo;
	
	public CathegorieBean(String nom,String photo) {
		this.nom=nom;
		this.photo=photo;
	}

	public String getNom() {
		return nom;
	}

	public String getPhoto() {
		return photo;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
