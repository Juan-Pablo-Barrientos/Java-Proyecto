package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int success = 0;
			int game = Integer.parseInt(request.getParameter("idJuego"));
			if (request.getSession().getAttribute("usuario") != null) {
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
				UsuarioLogic usrLogic = new UsuarioLogic();
				JuegoLogic juegologic = new JuegoLogic();
				Juego juego = juegologic.getOne(game);
				CompraLogic compraLogic = new CompraLogic();
				double importe = juego.getPrecioBase() - (juego.getPrecioBase() * juego.getDescuento());
				if (usrLogic.getOne(usuario.getId()).getSaldo() < importe) {
					success = 1;
				} // No alcanza el saldo
				else {
					if (compraLogic.NumeroDeComprasHabilitadas(usuario.getId(), game) != 1) {
						UsuarioLogic usuariologic = new UsuarioLogic();
						CompraLogic compralogic = new CompraLogic();
						Compra compra = new Compra();
						compra.setDateFechaHora(LocalDateTime.now());
						compra.setId_juego(juego.getId());
						compra.setId_usuario(usuario.getId());
						compra.setImporte(importe);
						usuario.setSaldo(usuario.getSaldo() - importe);
						usuariologic.update(usuario);
						compralogic.add(compra);
						success = 2;
					} else {
						success = 3;
					}
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
			}
			response.sendRedirect("CompraGameDisplay.do?s=" + success + "&game=" + game);

		} catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
