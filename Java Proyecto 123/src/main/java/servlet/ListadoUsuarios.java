package servlet;

import java.io.IOException;
import java.time.LocalDate;

import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.CompraLogic;
import logic.ReembolsoLogic;
import logic.UsuarioLogic;
import entities.Compra;
import entities.Reembolso;
import entities.Usuario;
import java.util.*;

/**
 * Servlet implementation class ListadoUsuarios
 */
@WebServlet("/ListadoUsuarios")
public class ListadoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoUsuarios() {
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
		// Verifica que el usuario sea admin
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				UsuarioLogic usrLogic = new UsuarioLogic();
				LinkedList<Usuario> usrs = usrLogic.getAll();
				request.setAttribute("listaUsuarios", usrs);
				request.getRequestDispatcher("/WEB-INF/UserManagement.jsp").forward(request, response);
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
		int success = 0;
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				if ("delete".equals(request.getParameter("action"))) {
					try {
						UsuarioLogic usrLogic = new UsuarioLogic();
						int idUsuario = Integer.parseInt(request.getParameter("hiddenId"));
						usrLogic.delete(idUsuario);
						success = 1;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				if ("edit".equals(request.getParameter("action2"))) {
					try {
						UsuarioLogic usrLogic = new UsuarioLogic();
						Usuario usrEdit = new Usuario();

						usrEdit.setId(Integer.parseInt(request.getParameter("usuarioId")));
						usrEdit.setEmail(request.getParameter("InputEmail"));
						usrEdit.setNickname(request.getParameter("InputNickname"));
						usrEdit.setTelefono(request.getParameter("InputTelefono"));
						usrEdit.setNombreUsuario(request.getParameter("InputUsuario"));
						usrEdit.setTipo(request.getParameter("InputUsuarioTipo"));
						LocalDate date = LocalDate.parse(request.getParameter("InputFechaNacimiento"));
						usrEdit.setFechaNacimiento(date);

						usrLogic.update(usrEdit);
						success = 2;
					} catch (Exception e) {
						request.setAttribute("error", e.getMessage());
						success = 0;
					}
				}
				response.sendRedirect("ListadoUsuariosDisplay.do?s=" + success);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
