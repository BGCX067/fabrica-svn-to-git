package com.solicitudfabricacion.xml;

import java.io.Serializable;

public class ArticuloSolicitud implements Serializable{

		private String codigo;
		private String descripcion;
		private long cantidad;
		
		public long getCantidad() {
			return cantidad;
		}
		public void setCantidad(long cantidad) {
			this.cantidad = cantidad;
		}
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		
}
