package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UsuarioLogic;
import entities.Usuario;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/Signin", "/signin", "/SIGNIN" })
public class Signin extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public Signin()
    {
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	// TODO Auto-generated method stub
	response.getWriter().append("get at: ").append(request.getContextPath());
    }

<<<<<<< HEAD
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr= new Usuario();
		UsuarioLogic usrLogic = new UsuarioLogic();
		String email = request.getParameter("InputEmail");
		String password = request.getParameter("InputPass");
			
		//Valida email y password
		usr.setContraseña(password);
		usr.setEmail(email);
		usr.setNombreUsuario(email);
		usr=usrLogic.getOneByUserName(usr);
		request.getSession().setAttribute("usuario", usr);
		response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
		
		
=======
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	// TODO Auto-generated method stub
	Usuario usr = new Usuario();
	UsuarioLogic usrLogic = new UsuarioLogic();
	String email = request.getParameter("InputEmail");
	String password = request.getParameter("InputPass");

	// Valida email y password
	usr.setContraseña(password);
	usr.setEmail(email);
	usr.setNombreUsuario(email);

	try
	{
	    usr = usrLogic.getOneByUserName(usr);
>>>>>>> branch 'main' of git@github.com:Juan-Pablo-Barrientos/Java-Proyecto.git
	}
	catch (SQLException e)
	{
	    throw new ServletException(e);
	}
	request.getSession().setAttribute("usuario", usr);
	response.sendRedirect(request.getContextPath() + "/Homepage.jsp");
	// request.getRequestDispatcher("Homepage.jsp").forward(request, response);

    }

}
