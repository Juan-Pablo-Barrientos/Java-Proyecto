package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CompraView;
import entities.Desarrollador;
import entities.Publicador;
import entities.Usuario;
import logic.CompraViewLogic;
import logic.DesarrolladorLogic;
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
				PublicadorLogic PublicadorLogic = new PublicadorLogic();
				LinkedList<Publicador> Publicador = null;
				try {
					Publicador = PublicadorLogic.getAll();
				} catch (SQLException e) {
					request.getSession().invalidate();
					e.printStackTrace();
					request.setAttribute("result", "Los servidores estan caidos");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				request.setAttribute("listapublicadores", Publicador);
				request.getRequestDispatcher("/WEB-INF/ListadoPublicadores.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage?load");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int success = 0;
			Usuario usr;
			if (request.getSession().getAttribute("usuario") != null) {
				usr = (Usuario) request.getSession().getAttribute("usuario");
				if (usr.getTipo().equals("admin")) {
					if ("create".equals(request.getParameter("action3"))) {
						PublicadorLogic pubLogic = new PublicadorLogic();
						Publicador pubNew = new Publicador();
						pubNew.setNombre(request.getParameter("InputPublicador"));
						if (!pubLogic.PublisherNameExist(pubNew.getNombre())) {
							pubLogic.add(pubNew);
							success = 3;
						} else
							success = 5;
					}
					if ("delete".equals(request.getParameter("action"))) {
						PublicadorLogic pubLogic = new PublicadorLogic();
						int idPublicador = Integer.parseInt(request.getParameter("hiddenId"));
						pubLogic.delete(idPublicador);
						success = 1;
					}
					if ("edit".equals(request.getParameter("action2"))) {
						PublicadorLogic pubLogic = new PublicadorLogic();
						Publicador pubEdit = new Publicador();
						pubEdit.setId(Integer.parseInt(request.getParameter("publicadorId")));
						pubEdit.setNombre(request.getParameter("InputPublicador"));
						String nombre = pubLogic.getOne(pubEdit.getId()).getNombre();
						if (!request.getParameter("InputPublicador").equals(nombre)) {
							if (!pubLogic.PublisherNameExist(pubEdit.getNombre())) {
								success = 2;
								pubLogic.update(pubEdit);
							} else
								success = 5;
						} else
							success = 4;
					}
					response.sendRedirect("ListadoPublicadoresDisplay.do?s=" + success);
				} else {
					response.sendRedirect(request.getContextPath() + "/Homepage");

				}
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage?load");
			}
		} catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
