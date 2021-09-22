package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Publicador;
import logic.PublicadorLogic;

/**
 * Servlet implementation class ListadoPublicadores
 */
@WebServlet("/ListadoPublicadores")
public class ListadoPublicadores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoPublicadores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PublicadorLogic PublicadorLogic = new PublicadorLogic();
		LinkedList<Publicador> Publicador= PublicadorLogic.getAll();
		request.setAttribute("listapublicadores", Publicador); 
		request.getRequestDispatcher("/WEB-INF/ListadoPublicadores.jsp").forward(request, response);	
	}

}
