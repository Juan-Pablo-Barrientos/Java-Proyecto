package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.JuegoView;
import entities.Usuario;
import logic.JuegoViewLogic;
import logic.UsuarioLogic;

/**
 * Servlet implementation class ListadoJuegosDisplay
 */
@WebServlet("/ListadoJuegosDisplay")
public class ListadoJuegosDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoJuegosDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		if (usr.getTipo().equals("admin")) {

			int success = Integer.parseInt(request.getParameter("s"));
			switch (success) {
			case 0:
				request.setAttribute("result", "Ha ocurrido un error: " + request.getAttribute("error"));
				break;
			case 1:
				request.setAttribute("result", "Juego borrado con exito!");
				break;
			case 2:
				request.setAttribute("result", "Juego editado con exito!");
				break;
			case 3:
				request.setAttribute("result", "Juego creado con exito!");
				break;
			case 4:
				request.setAttribute("result", "Descuento del juego editado con exito");
				break;
			case 5:
				request.setAttribute("result", "");
				break;
			}
			JuegoViewLogic juegoviewlogic = new JuegoViewLogic();
			LinkedList<JuegoView> juegosview= juegoviewlogic.getAll();
			request.setAttribute("listajuegosview", juegosview); 
			request.getRequestDispatcher("/WEB-INF/ListadoJuegos.jsp").forward(request, response);	
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
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
