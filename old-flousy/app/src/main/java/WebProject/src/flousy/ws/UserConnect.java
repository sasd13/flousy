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

import flousy.beans.Utilisateurs;

/**
 * Servlet implementation class UserConnect
 */
@WebServlet("/UserConnect")
public class UserConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserConnect() {
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
		String utilisateurGson= request.getParameter(Utilisateurs.JSON_USER_PARAMETER_NAME);

		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Utilisateurs utilisateur = gson.fromJson(utilisateurGson, Utilisateurs.class);
		
		String email= utilisateur.getEmail();
		String password = utilisateur.getPassword();
		try{
			utilisateur = Utilisateurs.connect(email, password);
			String jsonR= gson.toJson(utilisateur);
			writer.write(jsonR);
		}catch (Throwable e) {
			e.printStackTrace();
			}

		
	}
	
}
