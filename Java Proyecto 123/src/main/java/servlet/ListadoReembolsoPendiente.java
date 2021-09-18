package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;
import entities.*;
import java.util.*;

/**
 * Servlet implementation class ListadoReembolsoPendiente
 */
@WebServlet("/ListadoReembolsoPendiente")
public class ListadoReembolsoPendiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoReembolsoPendiente() {
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
		request.getRequestDispatcher("/WEB-INF/Reembolso.jsp").forward(request, response);		
	}

}
