package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CompraView;
import entities.Desarrollador;
import entities.Usuario;
import logic.CompraViewLogic;
import logic.DesarrolladorLogic;
import logic.UsuarioLogic;

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
		// Verifica que el usuario sea admin
		int success = 0;
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				if ("create".equals(request.getParameter("action3"))) {
					try {
						DesarrolladorLogic devLogic = new DesarrolladorLogic();
						Desarrollador newDev = new Desarrollador();
						newDev.setNombre(request.getParameter("InputDesarrollador"));
						if (!devLogic.DeveloperNameExist(newDev.getNombre())) {
							devLogic.add(newDev);
							success = 3;
						} else
							success = 5;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("delete".equals(request.getParameter("action"))) {
					try {
						DesarrolladorLogic devLogic = new DesarrolladorLogic();
						int idDesarrollador = Integer.parseInt(request.getParameter("hiddenId"));
						devLogic.delete(idDesarrollador);
						success = 1;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("edit".equals(request.getParameter("action2"))) {
					try {
						DesarrolladorLogic devLogic = new DesarrolladorLogic();
						Desarrollador devEdit = new Desarrollador();
						devEdit.setId(Integer.parseInt(request.getParameter("desarrolladorId")));
						devEdit.setNombre(request.getParameter("InputDesarrollador"));
						String nombre = devLogic.getOne(devEdit.getId()).getNombre();
						if (!request.getParameter("InputDesarrollador").equals(nombre)) {
						if (!devLogic.DeveloperNameExist(devEdit.getNombre())) 
						{
						devLogic.update(devEdit);
						success = 2;
						}
						else success =5;
						}	
						else success = 4;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				response.sendRedirect("ListadoDesarrolladoresDisplay.do?s=" + success);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}

}
