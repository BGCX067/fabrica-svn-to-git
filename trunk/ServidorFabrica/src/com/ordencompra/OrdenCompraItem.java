package com.ordencompra;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.materiaprima.MateriaPrima;
import com.ordencompra.estado.OrdenCompraItemEstado;


@Entity
@Table(name="ordencompraitem")
public class OrdenCompraItem implements Serializable{
	private long ID;
	private MateriaPrima materiaPrima;
	private long cantidad;
	private OrdenCompraItemEstado estado;
	
	
	public OrdenCompraItem() {
		super();
	
	}

	public OrdenCompraItem(MateriaPrima materiaPrima, long cantidad, OrdenCompraItemEstado estado) {
		
		this.materiaPrima = materiaPrima;
		this.cantidad = cantidad;
		this.estado = estado;
	}

	public OrdenCompraItem(long cantidad, MateriaPrima materiaPrima2, OrdenCompraItemEstado estado) {
		this.cantidad = cantidad;
		this.materiaPrima = materiaPrima;
		this.estado = estado;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	
	@ManyToOne( cascade=CascadeType.PERSIST)
	public OrdenCompraItemEstado getEstado() {
		return estado;
	}

		public void setEstado(OrdenCompraItemEstado estado) {
		this.estado = estado;
	}
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}
	
	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}
	

	public void setID(long id) {
		ID = id;
	}
	
}
