package flousy.ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import flousy.beans.Produit;
import flousy.beans.ProduitUtilisateur;
import flousy.beans.Utilisateurs;

/**
 * Servlet implementation class AjoutProduitUtilisateur
 */
@WebServlet("/AjoutProduitUtilisateur")
public class AjoutProduitUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutProduitUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String PutilisateurGson= request.getParameter(ProduitUtilisateur.JSON_PRODUIT_USER_NAME);
		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ProduitUtilisateur produitutilisateur = gson.fromJson(PutilisateurGson, ProduitUtilisateur.class);

		try{
			Boolean insertProduit=ProduitUtilisateur.ajoutProduitUtilisateur(produitutilisateur);
					String jsonR= gson.toJson(insertProduit);
					writer.write(jsonR);

		}catch (Throwable e) {
			e.printStackTrace();
			}
		
	}
	
}
