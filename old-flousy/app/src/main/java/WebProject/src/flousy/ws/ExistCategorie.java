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

import flousy.beans.Categorie;
import flousy.beans.Utilisateurs;

/**
 * Servlet implementation class ExistCategorie
 */
@WebServlet("/ExistCategorie")
public class ExistCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistCategorie() {
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
		String nom= request.getParameter("nomCategorie");

		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try{
		Boolean exist= Categorie.existCategorie(nom);
		String jsonR= gson.toJson(exist);
		writer.write(jsonR);
		
		}catch (Throwable e) {
			e.printStackTrace();
			}
	
	
	}

}
