package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.*;
import entities.*;
import java.util.*;

/**
 * Servlet implementation class ListadoJuegos
 */
@WebServlet("/ListadoJuegos")
public class ListadoJuegos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoJuegos() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				JuegoViewLogic juegoviewlogic = new JuegoViewLogic();
				LinkedList<JuegoView> juegosview = juegoviewlogic.getAll();
				DesarrolladorLogic devLogic = new DesarrolladorLogic();
				LinkedList<Desarrollador> devs = devLogic.getAll();
				PublicadorLogic pubLogic = new PublicadorLogic();
				LinkedList<Publicador> pubs = pubLogic.getAll();
				request.setAttribute("listajuegosview", juegosview);
				request.setAttribute("listadevs", devs);
				request.setAttribute("listapubs", pubs);
				request.getRequestDispatcher("/WEB-INF/ListadoJuegos.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Verifica que el usuario sea admin
		Usuario usr;
		int success = 0;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				if ("delete".equals(request.getParameter("actionDelete"))) {
					try {
						JuegoLogic jgoLogic = new JuegoLogic();
						int idJuego = Integer.parseInt(request.getParameter("hiddenId"));
						jgoLogic.delete(idJuego);
						success = 1;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("edit".equals(request.getParameter("action"))) {
					try {
						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						LocalDate date = LocalDate.parse(request.getParameter("juegoFechaId"));
						jgoEdit.setFecha_publicacion(date);
						jgoEdit.setIdPublicador(Integer.parseInt(request.getParameter("publicadorNombreId")));
						jgoEdit.setIdDesarrollador(Integer.parseInt(request.getParameter("desarrolladorNombreId")));
						jgoEdit.setNombre(request.getParameter("juegoNombreId"));
						jgoEdit.setPrecioBase(Double.parseDouble(request.getParameter("juegoPrecioBaseId")));
						jgoEdit.setGenero(request.getParameter("juegoGeneroId"));
						jgoEdit.setReestriccionPorEdad(request.getParameter("juegoReestriccionId"));
						jgoEdit.setUrl(request.getParameter("juegoUrlId"));
						if (request.getParameter("juegoNombreId").equals((jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId"))).getNombre()))) {
							jgoLogic.update(jgoEdit);
							success = 2;
						}
						else {
							if (!jgoLogic.GameNameExist(jgoEdit.getNombre())) {
								jgoLogic.update(jgoEdit);
								success = 2;
							} else {
								success = 7;
							}

						}
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("new".equals(request.getParameter("action"))) {
					try {
						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = new Juego();
						LocalDate date = LocalDate.parse(request.getParameter("juegoFechaId"));
						jgoEdit.setFecha_publicacion(date);
						jgoEdit.setIdPublicador(Integer.parseInt(request.getParameter("publicadorNombreId")));
						jgoEdit.setIdDesarrollador(Integer.parseInt(request.getParameter("desarrolladorNombreId")));
						jgoEdit.setNombre(request.getParameter("juegoNombreId"));
						jgoEdit.setPrecioBase(Double.parseDouble(request.getParameter("juegoPrecioBaseId")));
						jgoEdit.setGenero(request.getParameter("juegoGeneroId"));
						jgoEdit.setReestriccionPorEdad(request.getParameter("juegoReestriccionId"));
						jgoEdit.setDescripcion(request.getParameter("juegoDescripcionId"));
						jgoEdit.setUrl(request.getParameter("juegoUrlId"));
						jgoEdit.setDescuento(Double.parseDouble(request.getParameter("juegoDescuentoId2")) / 100);

						if (!jgoLogic.GameNameExist(jgoEdit.getNombre())) {
							jgoLogic.add(jgoEdit);
							success = 3;
						} else
							success = 7;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("descuento".equals(request.getParameter("actionDiscount"))) {
					try {
						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						jgoEdit.setDescuento(Double.parseDouble(request.getParameter("juegoDescuentoId")) / 100);

						jgoLogic.update(jgoEdit);
						success = 4;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("desc".equals(request.getParameter("actionDesc"))) {
					try {
						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						jgoEdit.setDescripcion(request.getParameter("juegoDescripcionId2"));

						jgoLogic.update(jgoEdit);
						success = 5;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				response.sendRedirect("ListadoJuegosDisplay.do?s=" + success);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else

		{
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}
}
