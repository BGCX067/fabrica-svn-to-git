package com.ordenreposicion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.centrodistribucion.CentroDistribucion;
import com.ordenreposicion.estado.OrdenReposicionEstado;

@Entity
@Table(name="ordenreposicion")
public class OrdenReposicion implements Serializable{
	private long ID;
	private Date fecha;
	private OrdenReposicionEstado estado;
	private String comentario;
	private Set<OrdenReposicionItem> items=new HashSet<OrdenReposicionItem>();
	private CentroDistribucion centroDistribucion;
	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}
	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@ManyToOne(cascade=CascadeType.PERSIST)
	public OrdenReposicionEstado getEstado() {
		return estado;
	}
	public void setEstado(OrdenReposicionEstado estado) {
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
    public Set<OrdenReposicionItem> getItems() {
		return items;
	}
	public void setItems(Set<OrdenReposicionItem> items) {
		this.items = items;
	}
}
