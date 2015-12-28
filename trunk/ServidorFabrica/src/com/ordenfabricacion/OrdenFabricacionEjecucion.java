package com.ordenfabricacion;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ordenfabricacion.estado.OrdenFabricacionEjecucionEstado;

@Entity
@Table(name="ordenfabricacionejecucion")
public class OrdenFabricacionEjecucion {
	private long ID;
	private OrdenFabricacion ordenFabricacion;
	private long cantidad;
	private Date fecha;
	private OrdenFabricacionEjecucionEstado estado;

	
	
	public OrdenFabricacionEjecucion() {
		super();
		
	}
	public OrdenFabricacionEjecucion(OrdenFabricacion ordenFabricacion, long cantidad, Date fecha, OrdenFabricacionEjecucionEstado estado) {
		super();
		this.ordenFabricacion = ordenFabricacion;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.estado = estado;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
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
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	public OrdenFabricacion getOrdenFabricacion() {
		return ordenFabricacion;
	}
	public void setOrdenFabricacion(OrdenFabricacion ordenFabricacion) {
		this.ordenFabricacion = ordenFabricacion;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	public OrdenFabricacionEjecucionEstado getEstado() {
		return estado;
	}
	public void setEstado(OrdenFabricacionEjecucionEstado estado) {
		this.estado = estado;
	}

}
