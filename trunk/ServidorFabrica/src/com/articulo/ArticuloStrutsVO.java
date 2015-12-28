package com.articulo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import com.materiaprima.MateriaPrimaCantidad;

public class ArticuloStrutsVO implements Serializable{
	private long ID;
	private String codigo;
	private String talle;
	private String descripcion;
	private String color;
	private String seccion;
	private long pvu;
	private String origen;
	private String linea;
	private Set<MateriaPrimaCantidad> materiaPrimas = new HashSet<MateriaPrimaCantidad>();
	private String nuevo;
	
	public ArticuloStrutsVO(Articulo articulo) {
		super();
		ID = articulo.getID();
		this.codigo = articulo.getCodigo();
		this.talle = articulo.getTalle();
		this.descripcion = articulo.getDescripcion();
		this.color = articulo.getColor();
		this.seccion = articulo.getSeccion();
		this.pvu = articulo.getPvu();
		this.origen = articulo.getOrigen();
		this.linea = articulo.getLinea();
		this.materiaPrimas = articulo.getMateriaPrimas();
		this.nuevo = articulo.getNuevo();
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public String getNuevo() {
		return nuevo;
	}
	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}
	
	public Set<MateriaPrimaCantidad> getMateriaPrimas() {
		return materiaPrimas;
	}
	public void setMateriaPrimas(Set<MateriaPrimaCantidad> materiaPrimas) {
		this.materiaPrimas = materiaPrimas;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public long getPvu() {
		return pvu;
	}
	public void setPvu(long pvu) {
		this.pvu = pvu;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}
	

	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	

	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	
	
}
