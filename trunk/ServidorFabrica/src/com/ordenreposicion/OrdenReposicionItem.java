package com.ordenreposicion;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.articulo.Articulo;
import com.solicitudfabricacion.SolicitudFabricacionItem;

@Entity
@Table(name="ordenreposicionitem")
public class OrdenReposicionItem implements Serializable{
	private long ID;
	private Articulo articulo;
	private long cantidad;
	private Set<SolicitudFabricacionItem> itemFabricacion=new HashSet<SolicitudFabricacionItem>();

	@ManyToOne
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	@OneToMany
	public Set<SolicitudFabricacionItem> getSolicitudesFabricacion() {
		return itemFabricacion;
	}
	public void setSolicitudesFabricacion(Set<SolicitudFabricacionItem> itemsFabricacion) {
		this.itemFabricacion = itemsFabricacion;
	}
	
	
}
