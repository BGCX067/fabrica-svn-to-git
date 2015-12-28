package util;

public class ArticuloCantidad {
	private Long articulo;
	private Long cantidad;
	
	public ArticuloCantidad(Long articulo, Long cantidad) {
	
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
	public Long getArticulo() {
		return articulo;
	}
	public void setArticulo(Long articulo) {
		this.articulo = articulo;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	

}
