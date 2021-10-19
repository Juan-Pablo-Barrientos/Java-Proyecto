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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		if (usr.getTipo().equals("admin")) {
			JuegoViewLogic juegoviewlogic = new JuegoViewLogic();
			LinkedList<JuegoView> juegosview= juegoviewlogic.getAll();
			DesarrolladorLogic devLogic = new DesarrolladorLogic();
			LinkedList<Desarrollador> devs=devLogic.getAll();
			PublicadorLogic pubLogic= new PublicadorLogic();
			LinkedList<Publicador> pubs =pubLogic.getAll();
			request.setAttribute("listajuegosview", juegosview); 
			request.setAttribute("listadevs", devs); 
			request.setAttribute("listapubs", pubs); 
			request.getRequestDispatcher("/WEB-INF/ListadoJuegos.jsp").forward(request, response);	
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// TODO Auto-generated method stub
				// Verifica que el usuario sea admin
				Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
				int success = 0;
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
				}
				else  {
					response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
					
				}
	}

}
