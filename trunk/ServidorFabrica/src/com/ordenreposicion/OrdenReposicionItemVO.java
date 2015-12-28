package com.ordenreposicion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.solicitudfabricacion.SolicitudFabricacionItem;
import com.solicitudfabricacion.SolicitudFabricacionItemVO;


public class OrdenReposicionItemVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private Long articuloId;
	private String articulo;
	private Long cantidad;
	private Set<SolicitudFabricacionItemVO> itemFabricacion;
	
	public OrdenReposicionItemVO(long id,String articulo, Long articuloId, Long cantidad,SolicitudFabricacionItemVO solFabItem) {
		super();
		ID = Long.toString(id);
		this.articuloId = articuloId;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.itemFabricacion=new HashSet<SolicitudFabricacionItemVO>();
		itemFabricacion.add(solFabItem);
	}

	public OrdenReposicionItemVO(long id,String articulo, Long articuloId, Long cantidad, Set<SolicitudFabricacionItemVO> items) {
		super();
		ID = Long.toString(id);
		this.articuloId = articuloId;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.itemFabricacion=items;
	}

	public Long getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Long articuloId) {
		this.articuloId = articuloId;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	public Set<SolicitudFabricacionItemVO> getItemFabricacion() {
		return itemFabricacion;
	}

	public void setItemFabricacion(Set<SolicitudFabricacionItemVO> itemFabricacion) {
		this.itemFabricacion = itemFabricacion;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
	
	
}
