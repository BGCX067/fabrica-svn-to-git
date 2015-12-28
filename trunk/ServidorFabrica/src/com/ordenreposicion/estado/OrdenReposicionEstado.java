package com.ordenreposicion.estado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordenreposicionestado")
public  class OrdenReposicionEstado implements Serializable{

	private long ID;
	private String nombre;
	
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
