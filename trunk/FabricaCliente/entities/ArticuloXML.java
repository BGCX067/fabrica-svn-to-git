package entities;

import java.util.ArrayList;

public class ArticuloXML {

	private String codigo;
	private String linea;
	private String descripcion;
	private String talle;
	private String color;
	private String seccion;
	private Integer pvu;
	private String origen;
	private ArrayList<CentroDistribucionXML> centros;
	
	
	
	public ArticuloXML() {
		super();
		centros = new ArrayList<CentroDistribucionXML>();
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getLinea() {
		return linea;
	}



	public void setLinea(String linea) {
		this.linea = linea;
	}



	public String getOrigen() {
		return origen;
	}



	public void setOrigen(String origen) {
		this.origen = origen;
	}



	public Integer getPvu() {
		return pvu;
	}



	public void setPvu(Integer pvu) {
		this.pvu = pvu;
	}



	public String getSeccion() {
		return seccion;
	}



	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}



	public String getTalle() {
		return talle;
	}



	public void setTalle(String talle) {
		this.talle = talle;
	}
	
	
	public String paqueteToString()
	{
		String paqueteInformacion=String.valueOf(this.codigo)+this.linea+this.descripcion+this.talle+this.color+this.seccion+this.pvu.toString()+this.origen;
		return paqueteInformacion;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public ArrayList<CentroDistribucionXML> getCentros() {
		return centros;
	}



	public void setCentros(ArrayList<CentroDistribucionXML> centros) {
		this.centros = centros;
	}

	

	
}

