package com.materiaprima;

public class MateriaPrimaStockVO {
	private long ID;
	private long stock;
	
	
	
	public MateriaPrimaStockVO(long id, long stock) {
		super();
		ID = id;
		this.stock = stock;
	}
	
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	
	
}
