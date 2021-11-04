package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.*;
import logic.*;

/**
 * Servlet implementation class Reseña
 */
@WebServlet("/Reseñas")
public class Reseñas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reseñas() {
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
		int success = 0;
		Usuario usr;
		CompraLogic clogic = new CompraLogic();
		ReseñaViewLogic rvlogic = new ReseñaViewLogic();

		// Verifica que el usuario estñ logueado
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			// ReseñaView reseñaView = (ReseñaView)
			// request.getAttribute("reseñaViewUsuario");
			Compra compra;
			// Juego juego = ((JuegoView) request.getAttribute("game")).getJuego();
			JuegoLogic jlogic = new JuegoLogic();
			Juego juego = jlogic.getOne(Integer.parseInt(request.getParameter("hiddenIdJuego")));

			// Busqueda de una posible reseña del usuario
			Reseña reseñaUsuario;
			try {
				reseñaUsuario = rvlogic.getByJuegoYUsuario(juego, usr).getReseña();
			} catch (SQLException e) {
				throw new ServletException(e);
			}

			// Verifica que la compra siga siendo vñlida y no haya review
			if ((clogic.NumeroDeCompras(usr.getId(), juego.getId()) == 1) && (reseñaUsuario.getId() == 0)) {
				// Si la acciñn es crear
				if ("create".equals(request.getParameter("hiddenAction"))) {
					try {
						// Reseña
						ReseñaLogic rLogic = new ReseñaLogic();
						reseñaUsuario = new Reseña();
						reseñaUsuario.setTitulo(request.getParameter("inputTitulo"));
						reseñaUsuario.setDescripcion(request.getParameter("inputDescripcion"));
						reseñaUsuario.setPuntuacion(Integer.parseInt(request.getParameter("inputPuntuacion")));

						reseñaUsuario = rLogic.add(reseñaUsuario);

						// Compra
						compra = clogic.getOne(Integer.parseInt(request.getParameter("hiddenNroSerieCompra")));
						// reseñaView.getCompra().setId_reseña(reseñaUsuario.getId());
						compra.setId_reseña(reseñaUsuario.getId());
						clogic.updateIdReseña(compra);
						success = 1;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				// Redirecciñn a la pñgina que muestra si la acciñn fue exitosa o fallida
				// response.sendRedirect("ListadoUsuariosDisplay.do?s=" + success);
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
			} else
				
				
			// Si la compra es valida, hay reseña y la accion es editar
			if ((clogic.NumeroDeCompras(usr.getId(), juego.getId()) == 1) && (reseñaUsuario.getId() != 0)
					&& ("edit".equals(request.getParameter("action2")))) {
				try {
					ReseñaLogic rLogic = new ReseñaLogic();
					reseñaUsuario.setTitulo(request.getParameter("inputTitulo"));
					reseñaUsuario.setDescripcion(request.getParameter("inputDescripcion"));
					reseñaUsuario.setPuntuacion(Integer.parseInt(request.getParameter("inputPuntuacion")));

					rLogic.update(reseñaUsuario);
					success = 2;
					response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;
				}
			} else {
				// Redireccion si no se dan las condiciones para editar o crear reseña
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else {
			// Redireccion si el usuario no esta logueado
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}

}
