package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Compra;
import entities.CompraView;
import entities.Desarrollador;
import entities.Reembolso;
import entities.Usuario;
import logic.CompraLogic;
import logic.CompraViewLogic;
import logic.DesarrolladorLogic;
import logic.ReembolsoLogic;
import logic.UsuarioLogic;

/**
 * Servlet implementation class Biblioteca
 */
@WebServlet("/Biblioteca")
public class Biblioteca extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    private ReembolsoLogic remLogic =new ReembolsoLogic();
    private UsuarioLogic usrLogic = new UsuarioLogic();
    private CompraLogic comLogic = new CompraLogic();
  
    public Biblioteca() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr = (Usuario) request.getSession().getAttribute("usuario");
		CompraViewLogic compraViewLogic = new CompraViewLogic();
		LinkedList<CompraView> rems= compraViewLogic.getAllByUserId(usr.getId());
		request.setAttribute("listaCompraView", rems); 
		request.getRequestDispatcher("/WEB-INF/Biblioteca.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!"create".equals(request.getParameter("action1"))) {
            return;
        }
        
        Usuario usuario= (Usuario) request.getSession().getAttribute("usuario");
        Compra compra = this.comLogic.getOne(Integer.parseInt(request.getParameter("idCompra")));
        String razon = request.getParameter("razon");
        if (this.comLogic.NumeroDeCompras(compra.getId_usuario(), compra.getId_juego()) == 1) 
        {
            try {
                int result = compra.fueReembolsado() 
                    ? this.informarCompraYaReembolsada(compra)
                    : this.reembolsarCompra(compra, usuario,razon);

                sendResponseRedirect(response, result);
            }
            catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                sendResponseRedirect(response, 0);
            }
        }
        else {
            //El juego fue comprado y reembolsado anteriormente.
            sendResponseRedirect(response, 5);
        } 
    }

    private int informarCompraYaReembolsada(Compra compra) {
        Reembolso reembolso = this.remLogic.getOne(compra.getId_reembolso());

        if (reembolso.getEstado().equals("Rechazado")) {
            return 2;
        } 
        
        //El reembolso esta Pendiente
        return 3; 
    }

    private int reembolsarCompra(Compra compra, Usuario usuario, String razon){
        int success;

        Reembolso reembolso = new Reembolso();
        
        reembolso.setRazon(razon);

        if (compra.getHoras_jugadas()<2) {					
            reembolso.setEstado("Aprobado");				
            usuario.setSaldo(usuario.getSaldo() + compra.getImporte());
			usrLogic.update(usuario);
            this.comLogic.delete(compra);
            success = 6;
        }
        else {
            reembolso.setEstado("Pendiente");
            success = 1;
        }
        reembolso = this.remLogic.add(reembolso);
        compra.setId_reembolso(reembolso.getId());
        this.comLogic.updateIdReembolso(compra);

        return success;
    }

    private void sendResponseRedirect(HttpServletResponse response, int success) throws IOException {
        response.sendRedirect("BibliotecaDisplay.do?s=" + success);
    }

}

