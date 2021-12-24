package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DbConnector;

import java.sql.*;

/**
 * Servlet implementation class Images
 */
@WebServlet({ "/Images", "/Images/*" })
public class Images extends HttpServlet {
	private static DbConnector instancia;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String host = "localhost";
    private String port = "3306";
    private String user = "java";
    private String password = "himitsu";
    private String db = "market_tp";
    private int conectados = 0;
    private Connection conn = null;
	
    public void releaseConn() throws SQLException
    {
	
	try
	{
		conn.close();
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
    }
    
    public byte[] getOneImageById(int id) throws SQLException
    {

	PreparedStatement stmt3 = null;
	ResultSet rs3 = null;
    byte[] blobAsBytes = null;
	try
	{
		conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);
	
	    stmt3 = conn.prepareStatement(
		    "select imagen "
			    + " from juego where id=? and habilitado=1");

	    stmt3.setInt(1, id);
	    rs3 = stmt3.executeQuery();
	    if (rs3 != null && rs3.next())
	    {
	    Blob blob = rs3.getBlob("imagen");
	    if (blob!=null) {
		    int blobLength = (int) blob.length();  
		    blobAsBytes = blob.getBytes(1, blobLength);
		    blob.free();
	    }

	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	finally
	{
	    try
	    {
		if (rs3 != null)
		{
		    rs3.close();
		}
		if (stmt3 != null)
		{
		    stmt3.close();
		}
		DbConnector.getInstancia().releaseConn();
	    }
	    catch (SQLException e)
	    {
		e.printStackTrace();
		throw e;
	    }
	}
	return blobAsBytes;
    }
    
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String imageName = request.getPathInfo().substring(1); // Returns "foo.png".
		
		byte[] content=null;
		try {
		content = this.getOneImageById(Integer.parseInt(request.getParameter("game")));
			
		} catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		response.setContentType(getServletContext().getMimeType(imageName));
		response.setContentLength(content.length);
		response.getOutputStream().write(content);
	}

}
