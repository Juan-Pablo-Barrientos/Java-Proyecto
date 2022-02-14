package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import org.apache.commons.fileupload.*;
import org.apache.commons.io.*;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import logic.*;
import entities.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet implementation class ListadoJuegos
 */
@WebServlet("/ListadoJuegos")
@MultipartConfig
public class ListadoJuegos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoJuegos() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usr;
		if (request.getSession().getAttribute("usuario") != null) {
			usr = (Usuario) request.getSession().getAttribute("usuario");
			if (usr.getTipo().equals("admin")) {
				try {
					JuegoViewLogic juegoviewlogic = new JuegoViewLogic();
					LinkedList<JuegoView> juegosview = juegoviewlogic.getAll();
					DesarrolladorLogic devLogic = new DesarrolladorLogic();
					LinkedList<Desarrollador> devs = devLogic.getAll();
					PublicadorLogic pubLogic = new PublicadorLogic();
					LinkedList<Publicador> pubs = pubLogic.getAll();
					request.setAttribute("listajuegosview", juegosview);
					request.setAttribute("listadevs", devs);
					request.setAttribute("listapubs", pubs);
				} catch (SQLException e) {
					request.getSession().invalidate();
					e.printStackTrace();
					request.setAttribute("result", "Los servidores estan caidos");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				request.getRequestDispatcher("/WEB-INF/ListadoJuegos.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage");

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/Homepage?load");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Verifica que el usuario sea admin
		int success = 0;
		try {
			Usuario usr;
			if (request.getSession().getAttribute("usuario") != null) {
				usr = (Usuario) request.getSession().getAttribute("usuario");
				if (usr.getTipo().equals("admin")) {
					if ("delete".equals(request.getParameter("actionDelete"))) {
						JuegoLogic jgoLogic = new JuegoLogic();
						int idJuego = Integer.parseInt(request.getParameter("hiddenId"));
						jgoLogic.delete(idJuego);
						success = 1;
					}
					if ("edit".equals(request.getParameter("action"))) {
						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						LocalDate date = LocalDate.parse(request.getParameter("juegoFechaId"));
						jgoEdit.setFecha_publicacion(date);
						jgoEdit.setIdPublicador(Integer.parseInt(request.getParameter("publicadorNombreId")));
						jgoEdit.setIdDesarrollador(Integer.parseInt(request.getParameter("desarrolladorNombreId")));
						jgoEdit.setNombre(request.getParameter("juegoNombreId"));
						jgoEdit.setPrecioBase(Double.parseDouble(request.getParameter("juegoPrecioBaseId")));
						jgoEdit.setGenero(request.getParameter("juegoGeneroId"));
						jgoEdit.setReestriccionPorEdad(request.getParameter("juegoReestriccionId"));
						jgoEdit.setUrl(request.getParameter("juegoUrlId"));
							Part filePart = request.getPart("file");
							Cloudinary cdn = cdnLogic.getCdn();
							InputStream fileContent = filePart.getInputStream();
							byte[] image = IOUtils.toByteArray(fileContent);
							File tempFile = File.createTempFile("temp", null, null);
							FileOutputStream fos = new FileOutputStream(tempFile);
							fos.write(image);
							if (!(tempFile.length()==0)) {
							String tiempo = "v"+Long.toString(Instant.now().getEpochSecond());
							Map params = ObjectUtils.asMap("public_id", "myfolder/images/" + jgoEdit.getId() + "/1",
									"overwrite", true, "notification_url", "http://claww.herokuapp.com/notify_endpoint",
									"resource_type", "image",
									"invalidate",true,
									"timestamp",tiempo);
							jgoEdit.setTimeImage(tiempo);
							Map uploadResult = cdn.uploader().upload(tempFile, params);
							fileContent.close();
							fos.close();
							tempFile.delete();
						}
						if (request.getParameter("juegoNombreId").equals(
								(jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId"))).getNombre()))) {
							jgoLogic.update(jgoEdit);
							success = 2;
						} else {
							if (!jgoLogic.GameNameExist(jgoEdit.getNombre())) {
								jgoLogic.update(jgoEdit);
								success = 2;
							} else {
								success = 7;
							}

						}
					}
					if ("new".equals(request.getParameter("action"))) {

						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = new Juego();
						LocalDate date = LocalDate.parse(request.getParameter("juegoFechaId"));
						jgoEdit.setFecha_publicacion(date);
						jgoEdit.setIdPublicador(Integer.parseInt(request.getParameter("publicadorNombreId")));
						jgoEdit.setIdDesarrollador(Integer.parseInt(request.getParameter("desarrolladorNombreId")));
						jgoEdit.setNombre(request.getParameter("juegoNombreId"));
						jgoEdit.setPrecioBase(Double.parseDouble(request.getParameter("juegoPrecioBaseId")));
						jgoEdit.setGenero(request.getParameter("juegoGeneroId"));
						jgoEdit.setReestriccionPorEdad(request.getParameter("juegoReestriccionId"));
						jgoEdit.setDescripcion(request.getParameter("juegoDescripcionId"));
						jgoEdit.setUrl(request.getParameter("juegoUrlId"));
						jgoEdit.setDescuento(Double.parseDouble(request.getParameter("juegoDescuentoId2")) / 100);
						String tiempo = "v"+Long.toString(Instant.now().getEpochSecond());
						jgoEdit.setTimeImage(tiempo);
						
						if (!jgoLogic.GameNameExist(jgoEdit.getNombre())) {
							Juego jgoNuevo =jgoLogic.add(jgoEdit);
							Part filePart = request.getPart("file");
							Cloudinary cdn = cdnLogic.getCdn();
							InputStream fileContent = filePart.getInputStream();
							byte[] image = IOUtils.toByteArray(fileContent);
							File tempFile = File.createTempFile("temp", null, null);
							FileOutputStream fos = new FileOutputStream(tempFile);
							fos.write(image);
							if (!(tempFile.length()==0)) {
							Map params = ObjectUtils.asMap("public_id", "myfolder/images/" + jgoNuevo.getId() + "/1",
									"overwrite", true, "notification_url", "http://claww.herokuapp.com/notify_endpoint",
									"resource_type", "image",
									"invalidate",true,
									"timestamp",tiempo);
							Map uploadResult = cdn.uploader().upload(tempFile, params);
							fileContent.close();
							fos.close();
							tempFile.delete();
							}
							success = 3;
						} else
							success = 7;
					}
					if ("descuento".equals(request.getParameter("actionDiscount"))) {

						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						jgoEdit.setDescuento(Double.parseDouble(request.getParameter("juegoDescuentoId")) / 100);
						UsuarioLogic usrLogic = new UsuarioLogic();
						LinkedList<Usuario> usrs = usrLogic.getAll();
						jgoLogic.update(jgoEdit);
						success = 4;
						if ((Integer.parseInt(request.getParameter("juegoId")) != 0)) {
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
										new InternetAddress("assacreed100@gmail.com"));

								for (Usuario u : usrs) {
									message.addRecipient(Message.RecipientType.BCC, new InternetAddress(u.getEmail()));
								}

								// Set Subject: header field
								message.setSubject("Nuevo descuento de " + jgoEdit.getNombre() + "!");
								String juegoDescuento = (Double.toString(jgoEdit.getDescuento() * 100)).substring(0, 2);
								// Now set the actual message
								message.setText(jgoEdit.getNombre() + " esta en con un descuento de " + juegoDescuento
										+ "%, con un precio final de: $" + (jgoEdit.getPrecioBase()
												- (jgoEdit.getPrecioBase() * jgoEdit.getDescuento())));

								// Send message
								Transport.send(message);

							} catch (MessagingException mex) {
								mex.printStackTrace();
								success = 8;
							}
						}
					}
					if ("desc".equals(request.getParameter("actionDesc"))) {

						JuegoLogic jgoLogic = new JuegoLogic();
						Juego jgoEdit = jgoLogic.getOne(Integer.parseInt(request.getParameter("juegoId")));
						jgoEdit.setDescripcion(request.getParameter("juegoDescripcionId2"));

						jgoLogic.update(jgoEdit);
						success = 5;
					}
					response.sendRedirect("ListadoJuegosDisplay.do?s=" + success);
				} else {
					response.sendRedirect(request.getContextPath() + "/Homepage");

				}
			} else {
				response.sendRedirect(request.getContextPath() + "/Homepage?load");
			}
		} catch (SQLException e) {
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (IOException e) {
			success = 9;
			response.sendRedirect("ListadoJuegosDisplay.do?s=" + success);
		}
	}
}
