package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Desarrollador;
import entities.Publicador;
import entities.Usuario;
import logic.DesarrolladorLogic;
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
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
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
			}
			PublicadorLogic PublicadorLogic = new PublicadorLogic();
			LinkedList<Publicador> Publicador = PublicadorLogic.getAll();
			request.setAttribute("listapublicadores", Publicador);
			request.getRequestDispatcher("/WEB-INF/ListadoPublicadores.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		// Verifica que el usuario sea admin
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		int success = 0;
		if (usr.getTipo().equals("admin")) {
			if ("create".equals(request.getParameter("action3"))) {
				try {
					PublicadorLogic pubLogic = new PublicadorLogic();
					Publicador pubNew = new Publicador();
					pubNew.setNombre(request.getParameter("InputPublicador"));
					pubLogic.add(pubNew);
					success = 3;
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;
				}
			}
			if ("delete".equals(request.getParameter("action"))) {
				try {
					PublicadorLogic pubLogic = new PublicadorLogic();
					int idPublicador = Integer.parseInt(request.getParameter("hiddenId"));
					pubLogic.delete(idPublicador);
					success = 1;
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;
				}
			response.sendRedirect("ListadoReembolsoPendienteDisplay.do?s=" + success);
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

		}
	}
	}

}
