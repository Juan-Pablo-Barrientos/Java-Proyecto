package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Desarrollador;
import logic.DesarrolladorLogic;

/**
 * Servlet implementation class ListadoDesarrolladores
 */
@WebServlet("/ListadoDesarrolladores")
public class ListadoDesarrolladores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoDesarrolladores() {
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
		DesarrolladorLogic desarrolladorLogic = new DesarrolladorLogic();
		LinkedList<Desarrollador> Desarrolladores= desarrolladorLogic.getAll();
		request.setAttribute("listadesarrolladores", Desarrolladores); 
		request.getRequestDispatcher("/WEB-INF/ListadoDesarrolladores.jsp").forward(request, response);	
	}

}
