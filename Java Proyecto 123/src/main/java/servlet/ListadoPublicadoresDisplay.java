package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Desarrollador;
import entities.JuegoView;
import entities.Publicador;
import entities.Usuario;
import logic.DesarrolladorLogic;
import logic.JuegoViewLogic;
import logic.PublicadorLogic;

/**
 * Servlet implementation class ListadoPublicadoresDisplay
 */
@WebServlet("/ListadoPublicadoresDisplay")
public class ListadoPublicadoresDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoPublicadoresDisplay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	Usuario usr;
	if (request.getSession().getAttribute("usuario") != null) {
		usr = (Usuario) request.getSession().getAttribute("usuario");
		if (usr.getTipo().equals("admin")) {

			int success = Integer.parseInt(request.getParameter("s"));
			switch (success) {
			case 0:
				request.setAttribute("result", "Ha ocurrido un error: " + request.getAttribute("error"));
				break;
			case 1:
				request.setAttribute("result", "Publicador borrado con exito!");
				break;
			case 2:
				request.setAttribute("result", "Publicador editado con exito!");
				break;
			case 3:
				request.setAttribute("result", "Publicador creado con exito!");
				break;
			case 4:
				request.setAttribute("result", "");
				break;
			case 5 :
				request.setAttribute("result", "El nombre ingresado ya esta en uso");
				break;
				
			}
			PublicadorLogic PublicadorLogic = new PublicadorLogic();
			LinkedList<Publicador> Publicador = PublicadorLogic.getAll();
			request.setAttribute("listapublicadores", Publicador);
			request.getRequestDispatcher("/WEB-INF/ListadoPublicadores.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

		}
	} else {
		response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
	}
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
