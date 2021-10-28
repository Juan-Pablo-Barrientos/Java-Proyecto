package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.JuegoView;
import logic.JuegoViewLogic;

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
				request.setAttribute("result", "");
				break;
			}
			JuegoViewLogic jgoLogic = new JuegoViewLogic();
			JuegoView jgo = new JuegoView();
			jgo= jgoLogic.getOne(Integer.parseInt(request.getParameter("game")));
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
