package com.ordenfabricacion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.articulo.Articulo;
import com.ordenfabricacion.estado.OrdenFabricacionEstado;

@Entity
@Table(name="ordenfabricacion")
public class OrdenFabricacion implements Serializable{
	private long ID;
	private Date fecha;
	private Articulo articulo;
	private OrdenFabricacionEstado estado;
	private long cantidad;
	private long cantidadFabricada;
	private long fabricado;
	private long distribuido;
	
	
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
	

	@ManyToOne(cascade=CascadeType.PERSIST)
	public OrdenFabricacionEstado getEstado() {
		return estado;
	}
	public void setEstado(OrdenFabricacionEstado estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	public long getFabricado() {
		return fabricado;
	}
	public void setFabricado(long fabricado) {
		this.fabricado = fabricado;
	}
	public long getDistribuido() {
		return distribuido;
	}
	public void setDistribuido(long distribuido) {
		this.distribuido = distribuido;
	}
	
	
	
	
	
}
