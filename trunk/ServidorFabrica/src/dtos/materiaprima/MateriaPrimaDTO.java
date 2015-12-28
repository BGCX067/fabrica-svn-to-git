package dtos.materiaprima;



import java.util.HashSet;
import java.util.Set;

public class MateriaPrimaDTO {

	Set<MateriaPrimaItemDTO> materiasPrimas = new HashSet();

	public Set<MateriaPrimaItemDTO> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(Set<MateriaPrimaItemDTO> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}
	
	
	
	
	
}
