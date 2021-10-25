package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Compra;
import logic.CompraLogic;

/**
 * Servlet implementation class BibliotecaHoras
 */
@WebServlet("/BibliotecaHoras")
public class BibliotecaHoras extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BibliotecaHoras() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Solo actualiza el tiempo.				
			CompraLogic comLogic = new CompraLogic();
			Compra compra = new Compra();			
			double tiempo= (Double.parseDouble(request.getParameter("segundos"))/3600000);
			compra = comLogic.getOne(Integer.parseInt(request.getParameter("nroCompra")));
			compra.setHoras_jugadas(compra.getHoras_jugadas()+tiempo);
			comLogic.update(compra);							
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
