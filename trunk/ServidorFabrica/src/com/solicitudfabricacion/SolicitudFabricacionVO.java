package com.solicitudfabricacion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.centrodistribucion.CentroDistribucion;
import com.ordenreposicion.OrdenReposicionItem;
import com.solicitudfabricacion.estado.SolicitudFabricacionEstado;


public class SolicitudFabricacionVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ID;
	private Date fecha;
	private CentroDistribucion centroDistribucion;
	private Long estado;
	private Set<SolicitudFabricacionItemVO> items=new HashSet<SolicitudFabricacionItemVO>();
	
	public SolicitudFabricacionVO(long id, Date fecha, SolicitudFabricacionEstado estado,CentroDistribucion centroDistribucion, Set<SolicitudFabricacionItemVO> items) {
		super();
		ID = Long.toString(id);
		this.fecha = fecha;
		this.centroDistribucion=centroDistribucion;
		this.estado = estado.getID();
		this.items=items;
	}
	
	public static Set<SolicitudFabricacionItemVO> parseSetToVO(Set<SolicitudFabricacionItem> name)
	{
		Set<SolicitudFabricacionItemVO> set =new HashSet<SolicitudFabricacionItemVO>();
		Iterator iter=name.iterator();
		while(iter.hasNext())
		{
			SolicitudFabricacionItem sf=(SolicitudFabricacionItem) iter.next();
			SolicitudFabricacionItemVO sfVO=new SolicitudFabricacionItemVO(sf.getID(),sf.getArticulo(),sf.getCantidad(),sf.getCantidadFabricada());
			set.add(sfVO);
		}
		return set;
	}

	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}

	public Set<SolicitudFabricacionItemVO> getItems() {
		return items;
	}

	public void setItems(Set<SolicitudFabricacionItemVO> items) {
		this.items = items;
	}

	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}

	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}

	public void actualizarEstado() {
		/*Validar items*/
		int estadoParcial=validarItemsEstado();
		if(estadoParcial==2)
		{
			estado=new Long(3);
		}else{
			estado=new Long(2);
		}
	}

	private int validarItemsEstado() {
		int repuesto=0;
		Iterator iter=items.iterator();
		while(iter.hasNext())
		{
			SolicitudFabricacionItemVO item=(SolicitudFabricacionItemVO) iter.next();
			if(item.getCantidad()==item.getCantidadFabricada()){
				repuesto++;
			}
		}
		if(repuesto==items.size()){
			/*si todos estan pendientes*/
			return 2;
		}else
		{
			/* lo pongo en parialmente repuesto*/
			return 1;
		}
	}
	
}
