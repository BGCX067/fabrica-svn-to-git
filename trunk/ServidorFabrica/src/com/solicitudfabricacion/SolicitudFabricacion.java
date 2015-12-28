package com.solicitudfabricacion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.articulo.AdministradorArticulo;
import com.centrodistribucion.CentroDistribucion;
import com.solicitudfabricacion.estado.SolicitudFabricacionEstado;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

@Entity
@Table(name="solicitudfabricacion")
public class SolicitudFabricacion implements Serializable{
	private long ID;
	private Date fecha;
	private SolicitudFabricacionEstado estado;
	private CentroDistribucion centroDistribucion;
	private Set<SolicitudFabricacionItem> items = new HashSet<SolicitudFabricacionItem>();

	@ManyToOne
	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}
	
	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}
	@ManyToOne(cascade=CascadeType.PERSIST)
	public SolicitudFabricacionEstado getEstado() {
		return estado;
	}
	public void setEstado(SolicitudFabricacionEstado estado) {
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
   
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<SolicitudFabricacionItem> getItems() {
		return items;
	}
	public void setItems(Set<SolicitudFabricacionItem> items) {
		this.items = items;
	}

	

	
}
