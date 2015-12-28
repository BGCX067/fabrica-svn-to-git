package com.articulo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.centrodistribucion.CentroDistribucion;
import com.materiaprima.MateriaPrimaCantidad;

@Entity
@Table(name="articulo")
public class Articulo implements Serializable{
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
	private Set<CentroDistribucion> centrosDistribucion = new HashSet<CentroDistribucion>();
	private String nuevo;
	
	
	public Articulo() {
		super();
		
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
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	
	@OneToMany( fetch=FetchType.EAGER)
	public Set<CentroDistribucion> getCentrosDistribucion() {
		return centrosDistribucion;
	}
	public void setCentrosDistribucion(Set<CentroDistribucion> centrosDistribucion) {
		this.centrosDistribucion = centrosDistribucion;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	
	
}
