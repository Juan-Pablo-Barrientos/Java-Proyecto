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
	private	JuegoViewLogic jgoLogic = new JuegoViewLogic();
	private	JuegoView jgo = new JuegoView();
	private	CompraLogic compraLogic = new CompraLogic();
	private ReseñaViewLogic reseñaViewLogic = new ReseñaViewLogic();

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
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			jgo = jgoLogic.getOne(Integer.parseInt(request.getParameter("game")));
			LinkedList<ReseñaView> reseñasViewJuego = reseñaViewLogic.getAllByJuego(jgo.getJuego());	
			request.setAttribute("game", jgo);
			request.setAttribute("reseñasJuego", reseñasViewJuego);
			request = setReseñaUsuario(request,usuario,jgo);		
			
			if(TieneRestriccion(jgo)) {
				if (usuario==null) {
				request.setAttribute("result", "Este juego cuenta con una reestriccion por edad, por favor inicie sesion");
				request.getRequestDispatcher("/index.jsp?game="+jgo.getJuego().getId()).forward(request, response);	}			
				if (usuario!=null && esMenor(request,usuario,jgo,response))					
					return;					
				}						
		request.getRequestDispatcher("/Game.jsp").forward(request, response);
		
		}catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
		
	private HttpServletRequest setReseñaUsuario(HttpServletRequest request,Usuario usuario,JuegoView juego)  throws SQLException {
		
		if (usuario!= null  && TieneGame(usuario,juego)) {					
			request.setAttribute("tieneGame", true);													
			request.setAttribute("reseñaViewUsuario", reseñaViewLogic.getByJuegoYUsuario(jgo.getJuego(), usuario));		
		}
		else {
			request.setAttribute("tieneGame", false);													
			request.setAttribute("reseñaViewUsuario", null);	
		}	
		return request;
	}
	
	private boolean TieneGame(Usuario usuario,JuegoView juego)  throws SQLException {
		return (compraLogic.NumeroDeComprasHabilitadas(usuario.getId(), jgo.getJuego().getId()) == 1);
	}
	private boolean TieneRestriccion(JuegoView juego)  throws SQLException {
		return !jgo.getJuego().getReestriccionPorEdad().isEmpty();
	}
	
	private boolean esMenor(HttpServletRequest request,Usuario usuario,JuegoView juego,HttpServletResponse response) throws ServletException, IOException {
		switch(jgo.getJuego().getReestriccionPorEdad()) {
		case "+18":{
			if (!EstaLogeado.esMayor18(usuario.getFechaNacimiento())) {
			request.setAttribute("result", "Usted no cuenta con la edad necesaria para ver este juego");
			request.getRequestDispatcher("/BusquedaJuegos.jsp").forward(request, response);		
			return true;
			}
			else return false;		
		}
		case "+13":{
			if (!EstaLogeado.esMayor13(usuario.getFechaNacimiento())) {
			request.setAttribute("result", "Usted no cuenta con la edad necesaria para ver este juego");
			request.getRequestDispatcher("/BusquedaJuegos.jsp").forward(request, response);		
			return true;
			}
			else return false;
		}			
		}
		return false;	
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
