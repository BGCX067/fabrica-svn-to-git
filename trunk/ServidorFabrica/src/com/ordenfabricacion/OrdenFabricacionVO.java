package com.ordenfabricacion;

import java.io.Serializable;
import java.util.Date;


public class OrdenFabricacionVO implements Serializable{
	private long ID;
	private Date fecha;
	private long articuloid;
	private String descripcionArticulo;
	private long ordenFabricacionEstadoid;
	private Long estado;
	private String estadoDescripcion;
	private long cantidad;
	private long cantidadFabricada;
	private long cantidadDistribuida;
	
	public OrdenFabricacionVO() {
		super();
	}
	
	public OrdenFabricacionVO(OrdenFabricacionVO vo, long cantidad) {
		ID = vo.getID();
		this.fecha = vo.getFecha();
		this.articuloid = vo.getArticuloid();
		this.descripcionArticulo = vo.getDescripcionArticulo();
		this.ordenFabricacionEstadoid = vo.getOrdenFabricacionEstadoid();
		this.estado = vo.getEstado();
		this.estadoDescripcion = vo.getEstadoDescripcion();
		this.cantidad = cantidad;
		this.cantidadFabricada=vo.getCantidadFabricada();
		this.cantidadDistribuida=vo.getCantidadDistribuida();
	}
	public OrdenFabricacionVO(OrdenFabricacionVO vo) {
		ID = vo.getID();
		this.fecha = vo.getFecha();
		this.articuloid = vo.getArticuloid();
		this.descripcionArticulo = vo.getDescripcionArticulo();
		this.ordenFabricacionEstadoid = vo.getOrdenFabricacionEstadoid();
		this.estado = vo.getEstado();
		this.estadoDescripcion = vo.getEstadoDescripcion();
		this.cantidad = 1;
		this.cantidadFabricada=0;
		this.cantidadDistribuida=0;
	}

	public long getArticuloid() {
		return articuloid;
	}
	public void setArticuloid(long articuloid) {
		this.articuloid = articuloid;
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
	public String getDescripcionArticulo() {
		return descripcionArticulo;
	}
	public void setDescripcionArticulo(String descripcionArticulo) {
		this.descripcionArticulo = descripcionArticulo;
	}
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
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
	public long getOrdenFabricacionEstadoid() {
		return ordenFabricacionEstadoid;
	}
	public void setOrdenFabricacionEstadoid(long ordenFabricacionEstadoid) {
		this.ordenFabricacionEstadoid = ordenFabricacionEstadoid;
	}

	public long getCantidadDistribuida() {
		return cantidadDistribuida;
	}

	public void setCantidadDistribuida(long cantidadDistribuida) {
		this.cantidadDistribuida = cantidadDistribuida;
	}

	public void actualizarEstado() {
		if(cantidadDistribuida==cantidadFabricada)
		{
			if(cantidadDistribuida==cantidad)
			{
				estadoDescripcion="Repuesto";
				estado=new Long(4);
			}
		}
		
	}

	public Long getEstado() {
		return estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
