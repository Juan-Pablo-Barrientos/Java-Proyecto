package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.UsuarioLogic;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
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
	}
	catch (SQLException e)
	{
		e.printStackTrace();
		request.setAttribute("result", "Los servidores estan caidos");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	if (usr!=null) { 
	request.getSession().setAttribute("usuario", usr);
		//JedisPool pool = new JedisPool("redis://redistogo:57f5f0014b7dbf0eea1f663fb3fc57b3@hammerjaw.redistogo.com:11795/", 6379);
		//  try (Jedis jedis = pool.getResource()) {
		//	  jedis.set("clientId", Integer.toString(usr.getId()));
		//}
		//pool.close();
	if (request.getParameter("game")!="")
	{
		response.sendRedirect("Game?game="+request.getParameter("game"));
	}else{
	response.sendRedirect(request.getContextPath() + "/Homepage");
	}
				}
	else {
		response.sendRedirect("SigninDisplay?game="+request.getParameter("game"));
	}
    }

}
