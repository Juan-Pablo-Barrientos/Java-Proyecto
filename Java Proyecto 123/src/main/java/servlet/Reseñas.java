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
 * Servlet implementation class Rese�a
 */
@WebServlet("/Rese�as")
public class Rese�as extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Rese�as() {
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
		Rese�aViewLogic rvlogic = new Rese�aViewLogic();

		// Verifica que el usuario est� logueado
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			// Rese�aView rese�aView = (Rese�aView)
			// request.getAttribute("rese�aViewUsuario");
			Compra compra;
			//Juego juego = ((JuegoView) request.getAttribute("game")).getJuego();
			JuegoLogic jlogic = new JuegoLogic();
			Juego juego = jlogic.getOne(Integer.parseInt(request.getParameter("hiddenIdJuego")));
			

			// Busqueda de una posible rese�a del usuario
			Rese�a rese�aUsuario;
			try {
				rese�aUsuario = rvlogic.getByJuegoYUsuario(juego, usr).getRese�a();
			} catch (SQLException e) {
				throw new ServletException(e);
			}

			// Verifica que la compra siga siendo v�lida y no haya review
			if ((clogic.NumeroDeCompras(usr.getId(), juego.getId()) == 1) && (rese�aUsuario.getId() == 0)) {
				// Si la acci�n es crear
				if ("create".equals(request.getParameter("hiddenAction"))) {
					try {
						// Rese�a
						Rese�aLogic rLogic = new Rese�aLogic();
						rese�aUsuario = new Rese�a();
						rese�aUsuario.setTitulo(request.getParameter("inputTitulo"));
						rese�aUsuario.setDescripcion(request.getParameter("inputDescripcion"));
						rese�aUsuario.setPuntuacion(Integer.parseInt(request.getParameter("inputPuntuacion")));

						rese�aUsuario = rLogic.add(rese�aUsuario);

						// Compra
						compra = clogic.getOne(Integer.parseInt(request.getParameter("hiddenNroSerieCompra")));
						// rese�aView.getCompra().setId_rese�a(rese�aUsuario.getId());
						compra.setId_rese�a(rese�aUsuario.getId());
						clogic.updateIdRese�a(compra);
						success = 1;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				// Si la acci�n es editar
				if ("edit".equals(request.getParameter("action2"))) {
					try {
						UsuarioLogic usrLogic = new UsuarioLogic();
						Usuario usrEdit = new Usuario();

						usrEdit.setTipo(request.getParameter("InputUsuarioTipo"));

						usrLogic.update(usrEdit);
						success = 2;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				// Redirecci�n a la p�gina que muestra si la acci�n fue exitosa o fallida
				// response.sendRedirect("ListadoUsuariosDisplay.do?s=" + success);
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
			} else {
				// Redireccion si el usuario no es admin
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else {
			// Redireccion si el usuario no est� logueado
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}

}
