package servidor;

import java.util.Date;


public class BusquedaOrdenesStruts {
	
	private boolean chk;
	private long id;
	//private Date fecha;
	private String articulo;
	private String estado;
	private long cantidad;
	private long cantidadFabricada;
	private long fabricado;
	private long distribuido;
	
	public BusquedaOrdenesStruts()
	{
	
	}

	public BusquedaOrdenesStruts(Boolean chk,long id,String articulo,String estado,long cantidad,long cantidadFabricada,long fabricado,long distribuido)
	{
		this.chk=chk;
		this.id=id;
		this.articulo=articulo;
		this.estado=estado;
		this.cantidad=cantidad;
		this.cantidadFabricada=cantidadFabricada;
		this.fabricado=fabricado;
		this.distribuido=distribuido;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public long getCantidadFabricada() {
		return cantidadFabricada;
	}
	public void setCantidadFabricada(long cantidadFabricada) {
		this.cantidadFabricada = cantidadFabricada;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	public long getDistribuido() {
		return distribuido;
	}
	public void setDistribuido(long distribuido) {
		this.distribuido = distribuido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public long getFabricado() {
		return fabricado;
	}
	public void setFabricado(long fabricado) {
		this.fabricado = fabricado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}
