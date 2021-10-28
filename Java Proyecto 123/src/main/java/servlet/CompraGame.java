package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Compra;
import entities.Juego;
import entities.Usuario;
import logic.CompraLogic;
import logic.JuegoLogic;
import logic.UsuarioLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class CompraGame
 */
@WebServlet("/CompraGame")
public class CompraGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraGame() {
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
		int success=0;
		if (request.getSession().getAttribute("usuario") != null) {
		Usuario usuario =(Usuario) request.getSession().getAttribute("usuario");
		JuegoLogic juegologic= new JuegoLogic();
		Juego juego = juegologic.getOne(Integer.parseInt(request.getParameter("idJuego")));
		double importe= juego.getPrecioBase() - (juego.getPrecioBase()*juego.getDescuento()); 		
		if (usuario.getSaldo()<importe) {success=1;}//No alcanza el saldo
		else {
			UsuarioLogic usuariologic=new UsuarioLogic();
			CompraLogic compralogic=new CompraLogic();
			Compra compra=new Compra();
			compra.setDateFechaHora(LocalDateTime.now());
			compra.setId_juego(juego.getId());
			compra.setId_usuario(usuario.getId()); 
			compra.setImporte(importe);
			usuario.setSaldo(usuario.getSaldo()-importe);
			usuariologic.update(usuario);
			compralogic.add(compra);
			success=2; // Compra realizada con exito
		}					
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");		
		}
		response.sendRedirect("ListadoDesarrolladoresDisplay.do?s=" + success);
	}
}
