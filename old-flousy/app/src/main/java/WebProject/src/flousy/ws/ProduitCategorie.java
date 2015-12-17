package flousy.ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import flousy.beans.Produit;

/**
 * Servlet implementation class ProduitCategorie
 */
@WebServlet("/ProduitCategorie")
public class ProduitCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduitCategorie() {
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
		String idUtilisateur = request.getParameter("idUtilisateur");
		String idCategorie = request.getParameter("idCategorie");
		int idCate =Integer.parseInt(idCategorie);

		int idUser =Integer.parseInt(idUtilisateur);
		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Produit> listproduit = new ArrayList<>();
		try{
			listproduit = Produit.ProduitCategorie(idCate, idUser);
			String jsonR= gson.toJson(listproduit);
			writer.write(jsonR);
		}catch (Throwable e) {
			e.printStackTrace();
			}
		
	}
	
}