package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CompraView;
import entities.Usuario;
import logic.CompraViewLogic;

/**
 * Servlet implementation class BibliotecaDisplay
 */
@WebServlet("/BibliotecaDisplay")
public class BibliotecaDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BibliotecaDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int success = Integer.parseInt(request.getParameter("s"));
		switch (success) {
		case 0:
			request.setAttribute("result", "Ha ocurrido un error: " + request.getAttribute("error"));
			break;
		case 1:
			request.setAttribute("result", "Solicitud enviada exitosamente!");
			break;
		case 2:
			request.setAttribute("result", "La solicitud fue rechazada anteriormente.");
			break;
		case 3:
			request.setAttribute("result", "La solicitud de reembolso esta pendiente de aprobacion");
			break;
		case 4:
			request.setAttribute("result", "");
			break;
		case 5:
			request.setAttribute("result", "El juego ya fue reembolsado en una compra anterior");
			break;
		case 6:
			request.setAttribute("result", "El reebolso fue aprobado!");
			break;
		}
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		CompraViewLogic compraViewLogic = new CompraViewLogic();
		LinkedList<CompraView> rems= compraViewLogic.getAllByUserId(usr.getId());
		request.setAttribute("listaCompraView", rems); 
		request.getRequestDispatcher("/WEB-INF/Biblioteca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
