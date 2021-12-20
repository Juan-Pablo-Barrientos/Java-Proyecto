package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Juego;
import entities.Usuario;
import logic.JuegoLogic;

/**
 * Servlet implementation class EstaLogeado
 */
@WebServlet("/EstaLogeado")
public class EstaLogeado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EstaLogeado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		PrintWriter out = response.getWriter();
		JuegoLogic jgoLogic = new JuegoLogic();
		try {
			// 1 Logueado y mayor de edad
			// 2 no esta logueado
			// 3 Logueado y menor de edad
			Juego jgo = (Juego) jgoLogic.getOne(Integer.parseInt(request.getParameter("jgId")));
			if(jgo.getReestriccionPorEdad()==null)
			{
				out.println("1");
				out.flush();
			}
			if (usr != null && jgo.getReestriccionPorEdad()!=null) {
				switch (jgo.getReestriccionPorEdad()) {
				case "+18": {
					if (esMayor18(usr.getFechaNacimiento())) {
						out.println("1");
						out.flush();
						break;
					}
					out.println("3");
					out.flush();
					break;
				}
				case "+13": {
					if (esMayor13(usr.getFechaNacimiento())) {
						out.println("1");
						out.flush();
						break;
					}
					out.println("3");
					out.flush();
					break;
				}
				default: {
					out.println("1");
					out.flush();
					break;
				}

				}

			} else if (usr == null && jgo.getReestriccionPorEdad()!=null){
				switch (jgo.getReestriccionPorEdad()) {
				case "+18": {
					out.println("2");
					out.flush();
					break;
				}
				case "+13": {
					out.println("2");
					out.flush();
					break;
				}
				default: {
					out.println("1");
					out.flush();
					break;
				}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.getSession().invalidate();
			e.printStackTrace();
			request.setAttribute("result", "Los servidores estan caidos");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	static boolean esMayor18(LocalDate fechaNacimiento) {
		if (ChronoUnit.YEARS.between(fechaNacimiento,LocalDate.now()) >= 18) {
			return true;
		} else {
			return false;
		}
	}

	static boolean esMayor13(LocalDate fechaNacimiento) {
		if (ChronoUnit.YEARS.between(fechaNacimiento,LocalDate.now()) >= 13) {
			return true;
		} else {
			return false;
		}
	}
}
