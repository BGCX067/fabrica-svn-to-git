package com.materiaprima;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="materiaprima")
public class MateriaPrima implements Serializable {
	private long ID;
	private String codigo;
	private String descripcion;
	private long stock;
	private long tiempoFabricacion;
	
	
	public MateriaPrima() {
		super();
	
	}

	public MateriaPrima(long id, String codigo, long stock, long tiempoFabricacion) {
		super();
		ID = id;
		this.codigo = codigo;
		this.stock = stock;
		this.tiempoFabricacion = tiempoFabricacion;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}

	public long getTiempoFabricacion() {
		return tiempoFabricacion;
	}
	public void setTiempoFabricacion(long tiempoFabricacion) {
		this.tiempoFabricacion = tiempoFabricacion;
	}

	public void setID(long id) {
		ID = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
