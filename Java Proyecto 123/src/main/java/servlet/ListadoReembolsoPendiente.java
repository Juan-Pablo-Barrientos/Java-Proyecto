package servlet;

import java.io.IOException;
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
		// TODO Auto-generated method stub
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		if (usr.getTipo().equals("admin")) {
			CompraViewLogic compraViewLogic = new CompraViewLogic();
			LinkedList<CompraView> rems = compraViewLogic.getAll();
			request.setAttribute("listaCompraView", rems);
			request.getRequestDispatcher("/WEB-INF/Reembolso.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Verifica que el usuario sea admin
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		int success = 0;
		if (usr.getTipo().equals("admin")) {
			if ("approve".equals(request.getParameter("approveInput"))) {
				try {
					ReembolsoLogic RemLogic = new ReembolsoLogic();
					Reembolso remEdit = RemLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdApprove")));
					remEdit.setComentario("");
					remEdit.setEstado("Aprobado");
					RemLogic.update(remEdit);

					success = 2;
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;
				}
			}
			if ("decline".equals(request.getParameter("declineInput"))) {
				try {
					ReembolsoLogic RemLogic = new ReembolsoLogic();
					Reembolso remEdit = RemLogic.getOne(Integer.parseInt(request.getParameter("hiddenIdDecline")));
					remEdit.setComentario(request.getParameter("InputComentarioId"));
					remEdit.setEstado("Rechazado");
					RemLogic.update(remEdit);
					success = 1;
				} catch (Exception e) {
					request.setAttribute("error", e.getMessage());
					success = 0;
				}
				
			}
			response.sendRedirect("ListadoReembolsoPendienteDisplay.do?s=" + success);
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");

		}
	}
}
