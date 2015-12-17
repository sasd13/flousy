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

import flousy.beans.Categorie;
import flousy.beans.Utilisateurs;

/**
 * Servlet implementation class SelectCategorie
 */
@WebServlet("/allCategories")
public class AllCategories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllCategories() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Categorie> allcategories = new ArrayList<>();
		
		try{
			allcategories = Categorie.getAllCategorie();
			String jsonR= gson.toJson(allcategories);
			
			writer.write(jsonR);
		}catch (Throwable e) {	
			e.printStackTrace();
			}
		
	}

}
