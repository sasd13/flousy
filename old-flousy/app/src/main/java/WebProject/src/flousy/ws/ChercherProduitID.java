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
import flousy.beans.Utilisateurs;

/**
 * Servlet implementation class ChercherProduitID
 */
@WebServlet("/ChercherProduitID")
public class ChercherProduitID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChercherProduitID() {
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
		String ProduitidG= request.getParameter(Produit.JSON_PRODUIT_PARAMETER_NAME);
		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Produit produit = gson.fromJson(ProduitidG,Produit.class);
		int idProduit = produit.getIdProduit();
		
		try{
		produit= Produit.chercherProduitId(idProduit);	
		String jsonR= gson.toJson(produit);
		writer.write(jsonR);
		
		}catch (Throwable e) {
			e.printStackTrace();
			}
	
	}

}
