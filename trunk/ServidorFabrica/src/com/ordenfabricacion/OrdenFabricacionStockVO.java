package com.ordenfabricacion;

import java.io.Serializable;
import java.util.Date;


public class OrdenFabricacionStockVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ID;
	private Date fecha;
	private long articuloid;
	private String descripcionArticulo;
	private long cantidad;
	private boolean fabricado;
	
	public OrdenFabricacionStockVO() {
		super();
	}
	
	public OrdenFabricacionStockVO(OrdenFabricacionVO vo, long cantidad, boolean fabricado) {
		ID = vo.getID();
		this.fecha = vo.getFecha();
		this.articuloid = vo.getArticuloid();
		this.descripcionArticulo = vo.getDescripcionArticulo();
		this.cantidad = cantidad;
		this.fabricado = fabricado;
	}


	public long getArticuloid() {
		return articuloid;
	}
	public void setArticuloid(long articuloid) {
		this.articuloid = articuloid;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getDescripcionArticulo() {
		return descripcionArticulo;
	}
	public void setDescripcionArticulo(String descripcionArticulo) {
		this.descripcionArticulo = descripcionArticulo;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}

	public boolean isFabricado() {
		return fabricado;
	}

	public void setFabricado(boolean fabricado) {
		this.fabricado = fabricado;
	}
	
	
	
	
}
