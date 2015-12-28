package com.materiaprima;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticuloMateriaPrimaPK implements Serializable{
		private long articuloID;
		private long materiaPrimaID;
		
		@Column(name="articuloid")
		public long getArticuloID() {
			return articuloID;
		}
		public void setArticuloID(long articuloID) {
			this.articuloID = articuloID;
		}
		@Column(name="materiaprimaid")
		public long getMateriaPrimaID() {
			return materiaPrimaID;
		}
		public void setMateriaPrimaID(long materiaPrimaID) {
			this.materiaPrimaID = materiaPrimaID;
		}
		
		
}
