package com.ordenreposicion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.centrodistribucion.CentroDistribucion;
import com.solicitudfabricacion.SolicitudFabricacionVO;


public class OrdenReposicionVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ID;
	private Date fecha;
	private String estado;
	private Set<OrdenReposicionItemVO> items=null;
	private String comentario;
	private Long centroDistribucionid;
	private String centroDistribucion;
	
	public OrdenReposicionVO(long id, Date fecha, String estado, Set<OrdenReposicionItemVO> items,String comentario,CentroDistribucion centroDistribucion) {
		super();
		ID = Long.toString(id);
		this.fecha = fecha;
		this.estado = estado;
		this.comentario=comentario;
		this.items=items;
		this.centroDistribucionid=centroDistribucion.getID();
		this.centroDistribucion=centroDistribucion.getNombre();
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
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

	public Set<OrdenReposicionItemVO> getItems() {
		return items;
	}

	public void setItems(Set<OrdenReposicionItemVO> items) {
		this.items = items;
	}

	public String getCentroDistribucion() {
		return centroDistribucion;
	}

	public void setCentroDistribucion(String centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public static Set<OrdenReposicionItemVO> parseSetToVO(Set<OrdenReposicionItem> name)
	{
		Set<OrdenReposicionItemVO> set =new HashSet<OrdenReposicionItemVO>();
		Iterator iter=name.iterator();
		while(iter.hasNext())
		{
			OrdenReposicionItem sf=(OrdenReposicionItem) iter.next();
			OrdenReposicionItemVO sfVO=new OrdenReposicionItemVO(sf.getID(),(sf.getArticulo().getCodigo()),(new Long(sf.getArticulo().getID())),new Long(sf.getCantidad()),SolicitudFabricacionVO.parseSetToVO(sf.getSolicitudesFabricacion()));
			set.add(sfVO);
		}
		return set;
	}

	public void actualizarEstado() {
		// TODO Auto-generated method stub
		
	}

	public Long getCentroDistribucionid() {
		return centroDistribucionid;
	}

	public void setCentroDistribucionid(Long centroDistribucionid) {
		this.centroDistribucionid = centroDistribucionid;
	}
	
	
}
