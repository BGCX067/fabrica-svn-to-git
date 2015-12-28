package com.centrodistribucion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class CentroDistribucionVO implements Serializable{
	
	private long ID;
	private String nombre;
	private String ip;
	
	public CentroDistribucionVO(long id, String nombre, String ip) {
		super();
		ID = id;
		this.nombre = nombre;
		this.ip = ip;
	}
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	

}
