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
@WebServlet("/Resenas")
public class Resenas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Resenas() {
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
		ResenaViewLogic rvlogic = new ResenaViewLogic();

		// Verifica que el usuario estñ logueado
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			Compra compra = null;
			JuegoLogic jlogic = new JuegoLogic();
			String game = request.getParameter("hiddenIdJuego");
			Juego juego;
			try {
				juego = jlogic.getOne(Integer.parseInt(game));

				// Si la compra sigue siendo valida
				if ((clogic.NumeroDeComprasHabilitadas(usr.getId(), juego.getId()) == 1)) {
					// Busqueda de una posible reseña del usuario
					Resena reseñaUsuario = null;
					
						reseñaUsuario = rvlogic.getByJuegoYUsuario(juego, usr).getReseña();
					

					// Si la accion es crear y no hay reseña para esa compra
					if (("create".equals(request.getParameter("action"))) && (reseñaUsuario.getId() == 0)) {
						
							// Reseña
							ResenaLogic rLogic = new ResenaLogic();
							reseñaUsuario = new Resena();
							reseñaUsuario.setTitulo(request.getParameter("inputTitulo"));
							reseñaUsuario.setDescripcion(request.getParameter("inputDescripcion"));
							reseñaUsuario.setPuntuacion(Integer.parseInt(request.getParameter("inputPuntuacion")));

							reseñaUsuario = rLogic.add(reseñaUsuario);

							// Compra
							compra = clogic.getOne(Integer.parseInt(request.getParameter("hiddenNroSerieCompra")));
							compra.setId_reseña(reseñaUsuario.getId());
							clogic.updateIdReseña(compra);
							success = 4;
					} else

					// Si la la accion es editar y hay reseña
					if ((reseñaUsuario.getId() != 0) && ("edit".equals(request.getParameter("action")))) {
						
							ResenaLogic rLogic = new ResenaLogic();
							reseñaUsuario.setTitulo(request.getParameter("inputTitulo"));
							reseñaUsuario.setDescripcion(request.getParameter("inputDescripcion"));
							reseñaUsuario.setPuntuacion(Integer.parseInt(request.getParameter("inputPuntuacion")));

							rLogic.update(reseñaUsuario);
							success = 5;
					} else

					// Si la accion es borrar y hay reseña
					if ((reseñaUsuario.getId() != 0) && ("delete".equals(request.getParameter("action")))) {
						
							ResenaLogic rLogic = new ResenaLogic();
							// Seteo en null de id_reseña en la compra
							compra = clogic.getOne(Integer.parseInt(request.getParameter("hiddenNroSerieCompra")));
							compra.setId_reseña(0);
							clogic.updateIdReseña(compra);
							rLogic.delete(reseñaUsuario);
							success = 6;
					}
					// Redirección a la página que muestra si la acción fue exitosa o fallida
					response.sendRedirect("CompraGameDisplay.do?s=" + success + "&game=" + game);
				} else {
					request.setAttribute("error", "El usuario no posee una compra válida del juego");
					success = 0;
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				request.getSession().invalidate();
				e.printStackTrace();
				request.setAttribute("result", "Los servidores estan caidos");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}

		} else {
			// Redireccion si el usuario no esta logueado
			response.sendRedirect(request.getContextPath() + "/Homepage?load");
		}
	}

}
