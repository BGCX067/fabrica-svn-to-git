package com.solicitudfabricacion;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.articulo.Articulo;

@Entity
@Table(name="solicitudfabricacionitem")
public class SolicitudFabricacionItem implements Serializable{
	private long ID;
	private Articulo articulo;
	private long cantidad;
	private long cantidadFabricada;

	
	public SolicitudFabricacionItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SolicitudFabricacionItem(Articulo articulo, long cantidad) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.cantidadFabricada=0;
	}


	@ManyToOne(cascade=CascadeType.PERSIST)	
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
	public long getCantidadFabricada() {
		return cantidadFabricada;
	}
	public void setCantidadFabricada(long cantidadFabricada) {
		this.cantidadFabricada = cantidadFabricada;
	}
		
}
