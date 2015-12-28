package com.ordencompra;

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

import com.ordencompra.estado.OrdenCompraEstado;

@Entity
@Table(name="ordencompra")
public class OrdenCompra implements Serializable{
	private long ID;
	private Date fecha;
	private OrdenCompraEstado estado;
	private Set<OrdenCompraItem> items = new HashSet<OrdenCompraItem>();

	
	
	public OrdenCompra(Date fecha, OrdenCompraEstado estado) {
		super();
		this.fecha = fecha;
		this.estado = estado;
		this.items = new HashSet<OrdenCompraItem>();
	}
	
	public OrdenCompra() {
		super();
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	public OrdenCompraEstado getEstado() {
		return estado;
	}
	public void setEstado(OrdenCompraEstado estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	

	public void setID(long id) {
		ID = id;
	}
	
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public Set<OrdenCompraItem> getItems() {
		return items;
	}
	public void setItems(Set<OrdenCompraItem> items) {
		this.items = items;
	}
}
