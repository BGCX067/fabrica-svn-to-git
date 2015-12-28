package view.productoreplacement.util;

public class OrdenReposicionItemMap {
	private String articulo;
	private Long cantidad;
	public String getArticuloId() {
		return articulo;
	}
	public void setArticuloId(String articuloId) {
		this.articulo = articuloId;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public OrdenReposicionItemMap(String articulo, Long cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
	}
}
