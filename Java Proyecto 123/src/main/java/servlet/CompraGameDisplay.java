package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.JuegoView;
import entities.ReseñaView;
import entities.Usuario;
import logic.CompraLogic;
import logic.JuegoViewLogic;
import logic.ReseñaViewLogic;

/**
 * Servlet implementation class CompraGameDisplay
 */
@WebServlet("/CompraGameDisplay")
public class CompraGameDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraGameDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("usuario") != null) {			
			int success = Integer.parseInt(request.getParameter("s"));
			switch (success) {
			case 0:
				request.setAttribute("result", "Ha ocurrido un error: " + request.getAttribute("error"));
				break;
			case 1:
				request.setAttribute("result", "El saldo es insuficiente.");
				break;
			case 2:
				request.setAttribute("result", "Compra realizada con exito.");
				break;
			case 3:
				request.setAttribute("result", "Ya ha comprado este juego.");
				break;
			case 4:
				request.setAttribute("result", "Reseña creada con éxito.");
				break;
			case 5:
				request.setAttribute("result", "Reseña editada con éxito.");
				break;
			case 6:
				request.setAttribute("result", "Reseña eliminada con éxito.");
				break;
			case 7:
				request.setAttribute("result", "");
				break;
			}
			JuegoViewLogic jgoLogic = new JuegoViewLogic();
			CompraLogic compraLogic = new CompraLogic();
			JuegoView jgo = new JuegoView();
			jgo= jgoLogic.getOne(Integer.parseInt(request.getParameter("game")));
			boolean tieneGame = false;
			if (request.getSession().getAttribute("usuario") != null) {
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				if (compraLogic.NumeroDeComprasHabilitadas(usuario.getId(), jgo.getJuego().getId()) == 1) {
					tieneGame=true;
				}
			}
			
			
			
			
			
			
			
			
			// Busqueda de reseñas del juego
			ReseñaViewLogic reseñaViewLogic = new ReseñaViewLogic();
			LinkedList<ReseñaView> reseñasViewJuego;
			try {
			    reseñasViewJuego = reseñaViewLogic.getAllByJuego(jgo.getJuego());
			} catch (SQLException e) {
			    throw new ServletException(e);
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
				    throw new ServletException(e);
				}
			    }
			}

			request.setAttribute("reseñasJuego", reseñasViewJuego);
			request.setAttribute("reseñaViewUsuario", reseñaViewUsuario);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			request.setAttribute("tieneGame", tieneGame);
			request.setAttribute("game", jgo);
			request.getRequestDispatcher("/Game.jsp").forward(request, response);			
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");		
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
