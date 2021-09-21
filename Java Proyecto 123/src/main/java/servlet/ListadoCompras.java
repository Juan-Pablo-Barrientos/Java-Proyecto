package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.CompraView;
import logic.CompraViewLogic;

/**
 * Servlet implementation class ListadoCompras
 */
@WebServlet("/ListadoCompras")
public class ListadoCompras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoCompras() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CompraViewLogic compraViewLogic = new CompraViewLogic();
		LinkedList<CompraView> rems= compraViewLogic.getAll();
		request.setAttribute("listaCompraView", rems); 
		request.getRequestDispatcher("/WEB-INF/ListadoCompras.jsp").forward(request, response);
	}

}
