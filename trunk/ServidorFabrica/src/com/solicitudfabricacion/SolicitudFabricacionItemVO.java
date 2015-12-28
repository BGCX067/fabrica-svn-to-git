package com.solicitudfabricacion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.articulo.Articulo;
import com.centrodistribucion.CentroDistribucion;
import com.ordenreposicion.OrdenReposicionItem;
import com.solicitudfabricacion.estado.SolicitudFabricacionEstado;


public class SolicitudFabricacionItemVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long ID;
	private Articulo articulo;
	private long cantidad;
	private long cantidadFabricada;
	
	public SolicitudFabricacionItemVO(long id, Articulo articulo, long cantidad,long cantidadFabricada) {
		super();
		ID = id;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.cantidadFabricada=cantidadFabricada;
	}

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

	public long getCantidadFabricada() {
		return cantidadFabricada;
	}

	public void setCantidadFabricada(long cantidadFabricada) {
		this.cantidadFabricada = cantidadFabricada;
	}

	public long getID() {
		return ID;
	}

	public void setID(long id) {
		ID = id;
	}
	
	
	
}
