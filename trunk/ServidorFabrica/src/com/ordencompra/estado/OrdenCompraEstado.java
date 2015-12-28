package com.ordencompra.estado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordencompraestado")
public class OrdenCompraEstado implements Serializable{

	private long ID;
	private String nombre;
	
	
	public OrdenCompraEstado() {
		super();
		
	}
	public OrdenCompraEstado(int id) {
		this.ID=id;
		this.nombre ="Pendiente";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
