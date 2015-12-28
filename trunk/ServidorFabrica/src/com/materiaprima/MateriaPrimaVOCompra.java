package com.materiaprima;

public class MateriaPrimaVOCompra {

		private Long ID;
		private Long cantidad;
	
		
		public MateriaPrimaVOCompra(Long id, Long cantidad) {
			ID = id;
			this.cantidad = cantidad;
		}
		public Long getCantidad() {
			return cantidad;
		}
		public void setCantidad(Long cantidad) {
			this.cantidad = cantidad;
		}
		public Long getID() {
			return ID;
		}
		public void setID(Long id) {
			ID = id;
		}
		
		
}
