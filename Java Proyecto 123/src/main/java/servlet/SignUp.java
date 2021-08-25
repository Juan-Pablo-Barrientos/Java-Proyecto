package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.*;

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
		//int year=Integer.valueOf(request.getParameter("yearDdl"));
		//int month=Integer.valueOf(request.getParameter("monthDdl"))+1;
		//int day=Integer.valueOf(request.getParameter("dayDdl"));
		//LocalDate date = LocalDate.of(year,month,day);
		LocalDate date = LocalDate.parse(request.getParameter("InputFechaNacimiento"));
		usr.setFechaNacimiento(date);
		
		usrLogic.add(usr);
		request.getSession().setAttribute("usuario", usr);
		request.getRequestDispatcher("Homepage.jsp").forward(request, response);
		
	}

}
