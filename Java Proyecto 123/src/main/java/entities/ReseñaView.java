package entities;

public class ReseñaView {
    private Usuario usuario;
    private Compra compra;
    private Reseña reseña;

    public Compra getCompra() {
	return compra;
    }

    public void setCompra(Compra compra) {
	this.compra = compra;
    }

    public Reseña getReseña() {
	return reseña;
    }

    public void setReseña(Reseña reseña) {
	this.reseña = reseña;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
