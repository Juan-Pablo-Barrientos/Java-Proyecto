package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CompraView;
import entities.Usuario;
import logic.CompraViewLogic;
import logic.UsuarioLogic;

/**
 * Servlet implementation class ListadoUsuariosDisplay
 */
@WebServlet("/ListadoUsuariosDisplay")
public class ListadoUsuariosDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoUsuariosDisplay() {
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
				request.setAttribute("result", "Usuario borrado con exito!");
				break;
			case 2:
				request.setAttribute("result", "Usuario editado con exito!");
				break;
			case 4:
				request.setAttribute("result", "");
				break;
			}
			UsuarioLogic usrLogic = new UsuarioLogic();
			LinkedList<Usuario> usrs = usrLogic.getAll();
			request.setAttribute("listaUsuarios", usrs);
			request.getRequestDispatcher("/WEB-INF/UserManagement.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
