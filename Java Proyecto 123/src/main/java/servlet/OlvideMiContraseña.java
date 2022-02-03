package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.UsuarioLogic;

/**
 * Servlet implementation class OlvideMiContraseña
 */
@WebServlet("/OlvideMiContraseña")
public class OlvideMiContraseña extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OlvideMiContraseña() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UsuarioLogic usrLogic = new UsuarioLogic();
		Usuario usr = new Usuario();
		usr.setEmail("InputEmail");
		try {
			usr = usrLogic.getOneByEmail(request.getParameter("InputEmail"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", "gamesclawgames@gmail.com");
		properties.put("mail.smtp.password", "ProyectoJava2");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gamesclawgames@gmail.com", "ProyectoJava2");
			}
		});

		// Set response content type
		response.setContentType("text/html");

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress("gamesclawgames@gmail.com"));
			message.addRecipient(Message.RecipientType.BCC,
					new InternetAddress(request.getParameter("InputEmail")));
			message.addRecipient(Message.RecipientType.BCC,
					new InternetAddress("assacreed100@gmail.com"));

			// Set Subject: header field
			message.setSubject("Contraseña de Claw Games");
			// Now set the actual message
			message.setText("Su contraseña es: "+usr.getContraseña());

			// Send message
			Transport.send(message);

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		request.setAttribute("result", "Email enviado a: "+ request.getParameter("InputEmail"));
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
