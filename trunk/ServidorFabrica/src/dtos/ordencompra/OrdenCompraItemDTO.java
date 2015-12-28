package dtos.ordencompra;







public class OrdenCompraItemDTO {
	
	private String codigo;
	private long cantidad;
	
	
	


	public OrdenCompraItemDTO(String codigo, long cantidad) {
		super();
		this.codigo = codigo;
		this.cantidad = cantidad;
	}



	public long getCantidad() {
		return cantidad;
	}



	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	
}
