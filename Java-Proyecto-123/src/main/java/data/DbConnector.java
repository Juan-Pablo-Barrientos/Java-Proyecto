package data;

import java.sql.*;

public class DbConnector
{

    private static DbConnector instancia;

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String host = "us-cdbr-east-05.cleardb.net";
    private String port = "3306";
    private String user = "bac6284df19812";
    private String password = "494ded57";
    private String db = "heroku_2ba32d1f2ee3d5b";
    private int conectados = 0;
    private Connection conn = null;

    private DbConnector()
    {
	try
	{
	    Class.forName(driver);
	}
	catch (ClassNotFoundException e)
	{
	    e.printStackTrace();
	}
    }

    public static DbConnector getInstancia()
    {
	if (instancia == null)
	{
	    instancia = new DbConnector();
	}
	return instancia;
    }

    public Connection getConn() throws SQLException
    {
	try
	{
	    if (conn == null || conn.isClosed())
	    {
		conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, password);
		conectados = 0;
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
	conectados++;
	return conn;
    }

    public void releaseConn() throws SQLException
    {
		conectados--;
	try
	{
	    if (conectados <=0 )
	    {
		conn.close();
	    }
	}
	catch (SQLException e)
	{
	    e.printStackTrace();
	    throw e;
	}
    }

}
