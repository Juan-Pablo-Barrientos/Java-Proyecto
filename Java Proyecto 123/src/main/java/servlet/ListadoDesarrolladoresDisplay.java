package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Desarrollador;
import entities.Usuario;
import logic.DesarrolladorLogic;
import logic.UsuarioLogic;

/**
 * Servlet implementation class ListadoDesarrolladoresDisplay
 */
@WebServlet("/ListadoDesarrolladoresDisplay")
public class ListadoDesarrolladoresDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoDesarrolladoresDisplay() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
					request.setAttribute("result", "Desarrollador borrado con exito!");
					break;
				case 2:
					request.setAttribute("result", "Desarrollador editado con exito!");
					break;
				case 3:
					request.setAttribute("result", "Desarrollador creado con exito!");
					break;
				case 4:
					request.setAttribute("result", "");
					break;
				case 5:
					request.setAttribute("result", "El nombre ingresado ya esta en uso");
					break;
				}
				DesarrolladorLogic devLogic = new DesarrolladorLogic();
				LinkedList<Desarrollador> devs = devLogic.getAll();
				request.setAttribute("listaDesarrollador", devs);
				request.getRequestDispatcher("/WEB-INF/ListadoDesarrolladores.jsp").forward(request, response);
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
	}

}
