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
	jgo = jgoLogic.getOne(Integer.parseInt(request.getParameter("game")));

	// Busqueda de rese�as del juego
	Rese�aViewLogic rese�aViewLogic = new Rese�aViewLogic();
	LinkedList<Rese�aView> rese�asViewJuego;
	try {
	    rese�asViewJuego = rese�aViewLogic.getAllByJuego(jgo.getJuego());
	} catch (SQLException e) {
	    throw new ServletException(e);
	}

	// Comprobaci�n - Si el usuario tiene el juego visitado e hizo rese�a
	Rese�aView rese�aViewUsuario = null;
	if (request.getSession().getAttribute("usuario") != null) {
	    Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	    if (compraLogic.NumeroDeComprasHabilitadas(usuario.getId(), jgo.getJuego().getId()) == 1) {
		tieneGame = true;
		try {
		    rese�aViewUsuario = rese�aViewLogic.getByJuegoYUsuario(jgo.getJuego(), usuario);
		} catch (SQLException e) {
		    throw new ServletException(e);
		}
	    }
	}

	request.setAttribute("tieneGame", tieneGame);
	request.setAttribute("game", jgo);
	request.setAttribute("rese�asJuego", rese�asViewJuego);
	request.setAttribute("rese�aViewUsuario", rese�aViewUsuario);
	request.getRequestDispatcher("/Game.jsp").forward(request, response);

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
