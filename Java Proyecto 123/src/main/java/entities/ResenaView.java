package entities;

public class ResenaView {
    private Usuario usuario;
    private Compra compra;
    private Resena reseña;

    public Compra getCompra() {
	return compra;
    }

    public void setCompra(Compra compra) {
	this.compra = compra;
    }

    public Resena getReseña() {
	return reseña;
    }

    public void setReseña(Resena reseña) {
	this.reseña = reseña;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
