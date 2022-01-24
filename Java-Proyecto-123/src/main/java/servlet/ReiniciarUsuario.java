package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DbConnector;
import entities.Usuario;
import logic.UsuarioLogic;

/**
 * Servlet implementation class ReiniciarUsuario
 */
@WebServlet("/ReiniciarUsuario")
public class ReiniciarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DbConnector instancia;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String host = "us-cdbr-east-05.cleardb.net";
    private String port = "3306";
    private String user = "bac6284df19812";
    private String password = "494ded57";
    private String db = "heroku_2ba32d1f2ee3d5b";
    private int conectados = 0;
    private Connection conn5 = null;
	
    public void releaseConn() throws SQLException
    {
	
	try
	{
		conn5.close();
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
    }
    public Usuario getOne(int us) throws SQLException {

		Usuario u = null;
		PreparedStatement stmt4 = null;
		ResultSet rs4 = null;
		try {
			conn5 = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);
			stmt4 = conn5.prepareStatement("select id,nombre_usuario,email,nickname,fecha_nacimiento,telefono,tipo,saldo"
							+ " from usuario where id=? and habilitado=1");

			stmt4.setInt(1, us);
			rs4 = stmt4.executeQuery();
			if (rs4 != null && rs4.next()) {
				u = new Usuario();
				u.setId(rs4.getInt("id"));
				u.setNombreUsuario(rs4.getString("nombre_usuario"));
				u.setEmail(rs4.getString("email"));
				u.setNickname(rs4.getString("nickname"));
				u.setFechaNacimiento(rs4.getObject("fecha_nacimiento", LocalDate.class));
				u.setTelefono(rs4.getString("telefono"));
				u.setTipo(rs4.getString("tipo"));
				u.setSaldo(rs4.getDouble("saldo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (rs4 != null) {
					rs4.close();
				}
				if (stmt4 != null) {
					stmt4.close();
				}
				releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}

		return u;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReiniciarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			try {
				UsuarioLogic usrLogic = new UsuarioLogic();
				Usuario usrActual = (Usuario) request.getSession().getAttribute("usuario");
				usr = usrLogic.getOne(usrActual.getId());
				request.getSession().setAttribute("usuario", usr);
			} catch (SQLException e) {
				request.getSession().invalidate();
				e.printStackTrace();
				request.setAttribute("result", "Los servidores estan caidos");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
