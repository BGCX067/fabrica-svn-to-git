package com.articulo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.materiaprima.MateriaPrimaCantidadVO;

public class ArticuloVO implements Serializable{
	private long ID;
	private Set<MateriaPrimaCantidadVO> materiaPrimas = new HashSet<MateriaPrimaCantidadVO>();
	
	
	public ArticuloVO(long id, Set<MateriaPrimaCantidadVO> materiaPrimas) {
		super();
		ID = id;
		this.materiaPrimas = materiaPrimas;
	}
	public long getID() {
		return ID;
	}
	public void setID(long id) {
		ID = id;
	}
	public Set<MateriaPrimaCantidadVO> getMateriaPrimas() {
		return materiaPrimas;
	}
	public void setMateriaPrimas(Set<MateriaPrimaCantidadVO> materiaPrimas) {
		this.materiaPrimas = materiaPrimas;
	}
	
	
}
