package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Compra;
import entities.CompraView;
import entities.Desarrollador;
import entities.Reembolso;
import entities.Usuario;
import logic.CompraLogic;
import logic.CompraViewLogic;
import logic.DesarrolladorLogic;
import logic.ReembolsoLogic;

/**
 * Servlet implementation class Biblioteca
 */
@WebServlet("/Biblioteca")
public class Biblioteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Biblioteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		int success=0;
		
		if ("create".equals(request.getParameter("action1"))) {			
				ReembolsoLogic remLogic = new ReembolsoLogic();
				Reembolso reembolso =new Reembolso();
		 		CompraLogic comLogic = new CompraLogic();
				Compra compra = new Compra();
				compra = comLogic.getOne(Integer.parseInt(request.getParameter("idCompra")));
			
				
				if (comLogic.NumeroDeCompras(compra.getId_usuario(), compra.getId_juego())==1) 
				{													 
				if (compra.getId_reembolso()!=0) 
				{
					try {				
					reembolso = remLogic.getOne(compra.getId_reembolso());
					if (reembolso.getEstado().equals("Rechazado")) {success = 2;} //El reembolso fue rechazado
					else success=3; //El reembolso esta Pendiente	
					}catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;}
									
				}
				if (compra.getId_reembolso()==0)  //Crear el reembolso si no existe
				{	try {					
					reembolso.setRazon(request.getParameter("razon"));
					if (compra.getHoras_jugadas()<2) {reembolso.setEstado("Aprobado");success = 6;}
					else {reembolso.setEstado("Pendiente");success = 1;}
					reembolso = remLogic.add(reembolso);
					compra.setId_reembolso(reembolso.getId());
					comLogic.updateIdReembolso(compra);					
						}catch (Exception e) {
							request.setAttribute("error", e.getMessage());
							success = 0; }
				}
				}
				else {success=5;} //El juego fue comprado y reembolsado anteriormente.
				
				
		response.sendRedirect("BibliotecaDisplay.do?s=" + success);
		}
	}

}
