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
import entities.Publicador;
import entities.Usuario;
import logic.CompraViewLogic;
import logic.PublicadorLogic;

/**
 * Servlet implementation class ListadoReembolsoPendienteDisplay
 */
@WebServlet("/ListadoReembolsoPendienteDisplay")
public class ListadoReembolsoPendienteDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoReembolsoPendienteDisplay() {
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
				request.setAttribute("result", "Reembolso denegado con exito!");
				break;
			case 2:
				request.setAttribute("result", "Reembolso aprobado con exito!");
				break;
			case 4:
				request.setAttribute("result", "");
				break;
			}
			CompraViewLogic compraViewLogic = new CompraViewLogic();
			LinkedList<CompraView> rems = null;
			try {
				rems = compraViewLogic.getAll();
			} catch (SQLException e) {
				request.getSession().invalidate();
				e.printStackTrace();
				request.setAttribute("result", "Los servidores estan caidos");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			request.setAttribute("listaCompraView", rems);
			request.getRequestDispatcher("/WEB-INF/Reembolso.jsp").forward(request, response);
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
		doGet(request,response);
	}

}
