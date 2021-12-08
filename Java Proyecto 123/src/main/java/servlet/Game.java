package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;
import entities.*;
import java.util.*;

/**
 * Servlet implementation class Game
 */
@WebServlet("/Game")
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JuegoViewLogic jgoLogic = new JuegoViewLogic();
		JuegoView jgo = new JuegoView();
		CompraLogic compraLogic = new CompraLogic();

		boolean tieneGame = false;
		try {
			jgo = jgoLogic.getOne(Integer.parseInt(request.getParameter("game")));

		// Busqueda de reseñas del juego
		ReseñaViewLogic reseñaViewLogic = new ReseñaViewLogic();
		LinkedList<ReseñaView> reseñasViewJuego = null;
		try {
			reseñasViewJuego = reseñaViewLogic.getAllByJuego(jgo.getJuego());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

		// Comprobaciñn - Si el usuario tiene el juego comprado e hizo reseña
		ReseñaView reseñaViewUsuario = null;
		if (request.getSession().getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			if (compraLogic.NumeroDeComprasHabilitadas(usuario.getId(), jgo.getJuego().getId()) == 1) {
				tieneGame = true;
				try {
					reseñaViewUsuario = reseñaViewLogic.getByJuegoYUsuario(jgo.getJuego(), usuario);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					request.getSession().invalidate();
					e.printStackTrace();
					request.setAttribute("result", "Los servidores estan caidos");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
		}

		request.setAttribute("tieneGame", tieneGame);
		request.setAttribute("game", jgo);
		request.setAttribute("reseñasJuego", reseñasViewJuego);
		request.setAttribute("reseñaViewUsuario", reseñaViewUsuario);
		request.getRequestDispatcher("/Game.jsp").forward(request, response);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
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
