package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				CompraViewLogic compraViewLogic = new CompraViewLogic();
				LinkedList<CompraView> rems = null;
				try {
					rems = compraViewLogic.getAll();
				} catch (SQLException e) {
					request.getSession().invalidate();
					e.printStackTrace();
					request.setAttribute("result", "Los servidores estan caidos");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				request.setAttribute("listaCompraView", rems);
				request.getRequestDispatcher("/WEB-INF/Reembolso.jsp").forward(request, response);
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
		try {
			int success = 0;
			Usuario usr;
			if (request.getSession().getAttribute("usuario") != null) {
				usr = (Usuario) request.getSession().getAttribute("usuario");
				if (usr.getTipo().equals("admin")) {
					if ("approve".equals(request.getParameter("approveInput"))) {
						CompraLogic ComLogic = new CompraLogic();
						UsuarioLogic UsrLogic = new UsuarioLogic();
						ReembolsoLogic RemLogic = new ReembolsoLogic();
						Reembolso remEdit = RemLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdApprove")));
						Compra compra = ComLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdGame")));
						Usuario usuario = UsrLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdUser")));
						remEdit.setComentario("");
						remEdit.setEstado("Aprobado");
						RemLogic.update(remEdit);
						usuario.setSaldo(usuario.getSaldo() + compra.getImporte());
						UsrLogic.update(usuario);
						ComLogic.delete(compra);
						success = 2;
					}
					if ("decline".equals(request.getParameter("declineInput"))) {
						ReembolsoLogic RemLogic = new ReembolsoLogic();
						Reembolso remEdit = RemLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdDecline")));
						remEdit.setComentario(request.getParameter("InputComentarioId"));
						remEdit.setEstado("Rechazado");
						RemLogic.update(remEdit);
						success = 1;

					}
					response.sendRedirect("ListadoReembolsoPendienteDisplay.do?s=" + success);
				} else {
					response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

				}
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage.jsp?=load");
			}

		} catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}

	}
}
