package com.ordencompra;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrdenCompraVO implements Serializable{
	private long ID;
	private Date fecha;
	private long estadoid;
	private String estadoNombre;
//	private Proveedor proveedor;
	private Set<OrdenCompraItemVO> items;

	
	public OrdenCompraVO(Date fecha, long estadoid, String estadoNombre) {
		this.fecha = fecha;
		this.estadoid = estadoid;
		this.estadoNombre = estadoNombre;
		this.items = new HashSet<OrdenCompraItemVO>();
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
	public Set<OrdenCompraItemVO> getItems() {
		return items;
	}
	public void setItems(Set<OrdenCompraItemVO> items) {
		this.items = items;
	}
	
	
}
