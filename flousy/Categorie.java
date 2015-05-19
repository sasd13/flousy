package flousy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.IMCBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class Categorie
 */
@WebServlet("/Categorie")
public class Categorie extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imcGson = request.getParameter("simoCate");
		String imcGsonTest = request.getParameter("test");

		PrintWriter writer = response.getWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		IMCBean imcBean = gson.fromJson(imcGson, IMCBean.class);
		
		int p = imcBean.getPoids();
		int t = imcBean.getTaille();
		
		int res = p/t;
		
		imcBean.setResult(res);
		
		String jsonR= gson.toJson(imcBean);
		writer.write(jsonR);

	}


}
