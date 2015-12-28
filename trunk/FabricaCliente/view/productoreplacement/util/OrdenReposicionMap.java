package view.productoreplacement.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ordenreposicion.OrdenReposicionItemVO;

public class OrdenReposicionMap {
	private Date fecha;
	private String comentario;
	private String centrodistribucion;
	private Set<OrdenReposicionItemMap> items=null;
	public OrdenReposicionMap(Date fecha, String comentario,String centro) {
		super();
		this.fecha = fecha;
		this.comentario = comentario;
		this.centrodistribucion = centro;
		this.items = new HashSet<OrdenReposicionItemMap>();
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Set<OrdenReposicionItemMap> getItems() {
		return items;
	}
	public void setItems(Set<OrdenReposicionItemMap> items) {
		this.items = items;
	}
	public String getCentrodistribucion() {
		return centrodistribucion;
	}
	public void setCentrodistribucion(String centrodistribucion) {
		this.centrodistribucion = centrodistribucion;
	}
	
}
