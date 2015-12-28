package com.ordenfabricacion;

import java.io.Serializable;
import java.util.Date;

import util.DateFormatParser;

public class OrdenFabricacionEjecucionVO implements Serializable{
	private long ID;
	private Date fecha;
	private long articuloid;
	private String descripcionArticulo;
	private long cantidad;

	
	public OrdenFabricacionEjecucionVO(long id, Date fecha, long articuloid, String descripcionArticulo, long cantidad) {
		super();
		ID = id;
		this.fecha = fecha;
		this.articuloid = articuloid;
		this.descripcionArticulo = descripcionArticulo;
		this.cantidad = cantidad;
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

	public String toString() {
		return String.valueOf(ID) + ", " + DateFormatParser.parseDate(fecha)
				+ ", " + descripcionArticulo + ", " + String.valueOf(cantidad);
	}
	
}
