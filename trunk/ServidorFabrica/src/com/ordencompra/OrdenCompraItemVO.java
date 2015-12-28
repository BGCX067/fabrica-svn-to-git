package com.ordencompra;

import java.io.Serializable;

public class OrdenCompraItemVO implements Serializable{
	private long ID;
	private long materiaPrimaid;
	private String materiaPrimaNombre;
	private long cantidad;
	private long estadoid;
	private String estadoNombre;
	
	
	public OrdenCompraItemVO(long materiaPrimaid, long cantidad, long estadoid) {
		this.materiaPrimaid = materiaPrimaid;
		this.cantidad = cantidad;
		this.estadoid = estadoid;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public long getEstadoid() {
		return estadoid;
	}
	public void setEstadoid(long estadoid) {
		this.estadoid = estadoid;
	}
	public String getEstadoNombre() {
		return estadoNombre;
	}
	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	public long getMateriaPrimaid() {
		return materiaPrimaid;
	}
	public void setMateriaPrimaid(long materiaPrimaid) {
		this.materiaPrimaid = materiaPrimaid;
	}
	public String getMateriaPrimaNombre() {
		return materiaPrimaNombre;
	}
	public void setMateriaPrimaNombre(String materiaPrimaNombre) {
		this.materiaPrimaNombre = materiaPrimaNombre;
	}
	
	
}
