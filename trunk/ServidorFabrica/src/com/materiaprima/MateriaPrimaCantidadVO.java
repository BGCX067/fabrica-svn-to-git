package com.materiaprima;

public class MateriaPrimaCantidadVO {
		private long ID;
		private long cantidad;
		
		
		public MateriaPrimaCantidadVO(long id, long cantidad) {
			super();
			ID = id;
			this.cantidad = cantidad;
		}
		public long getCantidad() {
			return cantidad;
		}
		public void setCantidad(long cantidad) {
			this.cantidad = cantidad;
		}
		public long getID() {
			return ID;
		}
		public void setID(long id) {
			ID = id;
		}
		
		
}
