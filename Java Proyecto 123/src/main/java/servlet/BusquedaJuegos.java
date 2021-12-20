package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.JuegoLogic;
import entities.Juego;

/**
 * Servlet implementation class busqueda
 */
@WebServlet(name = "busquedaJuegos", description = "Servlet para realizar b�squedas de juegos mediante search bar en navbar.", urlPatterns = {
	"/busquedaJuegos" })
public class BusquedaJuegos extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusquedaJuegos()
    {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//	response.getWriter().append("Served at: ").append(request.getContextPath());
	try
	{
	    JuegoLogic jlogic = new JuegoLogic();
	    String busquedaingresada = request.getParameter("InputBusqueda").toString();
	    LinkedList<Juego> juegos = jlogic.search(busquedaingresada);
	    request.getSession().setAttribute("juegosBusqueda", juegos);
	}
	catch (SQLException e)
	{
		request.getSession().invalidate();
		e.printStackTrace();
		request.setAttribute("result", "Los servidores estan caidos");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	request.getRequestDispatcher("/BusquedaJuegos.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
