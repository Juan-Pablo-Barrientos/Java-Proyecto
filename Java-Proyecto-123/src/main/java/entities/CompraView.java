package entities;

public class CompraView {
	
	private Usuario usuario;
    private Juego juego;
    private Reembolso reembolso;
    private Compra compra;
    
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	public Reembolso getReembolso() {
		return reembolso;
	}
	public void setReembolso(Reembolso reembolso) {
		this.reembolso = reembolso;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}
