package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UsuarioLogic;
import entities.Usuario;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		// TODO Auto-generated method stub		
		Usuario usr= new Usuario();		
		UsuarioLogic usrLogic = new UsuarioLogic();				
		usr.setEmail(request.getParameter("InputEmail"));
		usr.setContraseña(request.getParameter("InputPassword"));
		usr.setNickname(request.getParameter("InputNickname"));
		usr.setTelefono(request.getParameter("InputTelefono"));
		usr.setNombreUsuario(request.getParameter("InputUsuario"));
		usr.setTipo("usuario");
		usr.setEmail(request.getParameter("InputEmail"));
		LocalDate date = LocalDate.parse(request.getParameter("InputFechaNacimiento"));
		usr.setFechaNacimiento(date);	
		try {			
			if (!(usrLogic.UserNameExist(usr.getNombreUsuario()) || usrLogic.UserEmailExist(usr.getEmail()))) {
				usrLogic.add(usr);
				request.getSession().setAttribute("usuario", usr);
				request.getRequestDispatcher("Homepage.jsp").forward(request, response);
								}
				else {
					request.setAttribute("User",usr);
					request.getRequestDispatcher("signUp.jsp").forward(request, response);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

}
