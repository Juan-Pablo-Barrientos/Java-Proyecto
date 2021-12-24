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
 * Servlet implementation class ReiniciarUsuario
 */
@WebServlet("/ReiniciarUsuario")
public class ReiniciarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReiniciarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			UsuarioLogic ul = new UsuarioLogic();
			try {
				usr = ul.getOne((Usuario) request.getSession().getAttribute("usuario"));
				request.getSession().setAttribute("usuario", usr);
			} catch (SQLException e) {
				request.getSession().invalidate();
				e.printStackTrace();
				request.setAttribute("result", "Los servidores estan caidos");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
