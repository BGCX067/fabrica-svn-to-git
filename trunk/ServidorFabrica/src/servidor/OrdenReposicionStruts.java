package servidor;

public class OrdenReposicionStruts {
	
	private Integer productoId;
	private String producto;
	private Integer ordenFabricacionId;
	private Integer cantidad;
	private boolean chk;
	
	public OrdenReposicionStruts()
	{
		
	}
	public OrdenReposicionStruts(Integer productoId,String producto,Integer ordenFabricacionId,Integer cantidad,boolean chk)
	{
		this.productoId=productoId;
		this.producto=producto;
		this.ordenFabricacionId=ordenFabricacionId;
		this.cantidad=cantidad;
		this.chk=chk;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getOrdenFabricacionId() {
		return ordenFabricacionId;
	}
	public void setOrdenFabricacionId(Integer ordenFabricacionId) {
		this.ordenFabricacionId = ordenFabricacionId;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public Integer getProductoId() {
		return productoId;
	}
	public void setProductoId(Integer productoId) {
		this.productoId = productoId;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	
	

}
