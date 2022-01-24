package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.UsuarioLogic;

/**
 * Servlet implementation class CargarSaldo
 */
@WebServlet("/CargarSaldo")
public class CargarSaldo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarSaldo() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsuarioLogic usrLogic = new UsuarioLogic();
		Usuario usrCargarSaldo;
		try {
			usrCargarSaldo = usrLogic.getOne(((Usuario) request.getSession().getAttribute("usuario")).getId());
		usrCargarSaldo.setSaldo(usrCargarSaldo.getSaldo()+Double.parseDouble(request.getParameter("Saldo")));
		usrLogic.update(usrCargarSaldo);
		} catch(SQLException e) {
		request.getSession().invalidate();
		e.printStackTrace();
		request.setAttribute("result", "Los servidores estan caidos");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}response.sendRedirect(request.getParameter("URL"));
}
}

