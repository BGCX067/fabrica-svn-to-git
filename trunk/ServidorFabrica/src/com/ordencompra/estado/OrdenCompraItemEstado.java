package com.ordencompra.estado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ordencompraitemestado")
public  class OrdenCompraItemEstado implements Serializable{

		private long ID;
		private String nombre;
		
		
	   public OrdenCompraItemEstado() {
			super();
		
		}
	public OrdenCompraItemEstado(int id) {
			this.ID = id;
			this.nombre = "Pendiente";
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
