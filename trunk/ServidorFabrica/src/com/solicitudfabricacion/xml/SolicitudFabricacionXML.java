package com.solicitudfabricacion.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SolicitudFabricacionXML implements Serializable{
	
	private String centroDistribucion;
	private Date fecha;
	private Set<ArticuloSolicitud> items = new HashSet<ArticuloSolicitud>();
	
	public String getCentroDistribucion() {
		return centroDistribucion;
	}
	public void setCentroDistribucion(String centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Set<ArticuloSolicitud> getItems() {
		return items;
	}
	public void setItems(Set<ArticuloSolicitud> items) {
		this.items = items;
	}
	
	

}
