package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.MateriaPrimaStruts;

public class AltaArticuloForm extends ActionForm{
	
	private Collection listaMateria;	
	private long idarticulo;
	private String codigo;
	private long cantidad;
	private long precio;
	private String origen=null;
	private String color=null;
	private String linea=null;
	private String talle=null;
	private String descripcion=null;
	private String seccion=null;
	private String listaMateriaPrima=null;
	private boolean fabricarItem;
	private long contador;
	
	public String getListaMateriaPrima() {
		return listaMateriaPrima;
	}
	public void setListaMateriaPrima(String listaMateriaPrima) {
		this.listaMateriaPrima = listaMateriaPrima;
	}
	public long getContador() {
		return contador;
	}
	public void setContador(long contador) {
		this.contador = contador;
	}
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
	public long getPrecio() {
		return precio;
	}
	public void setPrecio(long precio) {
		this.precio = precio;
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
	public Collection getListaMateria() {
		return listaMateria;
	}
	public void setListaMateria(Collection listaMateria) {
		this.listaMateria = listaMateria;
	}
	
	 public void reset(ActionMapping arg0, HttpServletRequest arg1)
	 { 
		 listaMateria= new ArrayList();      
     }

	 public MateriaPrimaStruts getBingBong(int index)
	 {
		 while(listaMateria.size()<=index)
		 {
			 listaMateria.add(new MateriaPrimaStruts());
		 }
		 return(MateriaPrimaStruts)((ArrayList) listaMateria).get(index);

	 }
	public boolean isFabricarItem() {
		return fabricarItem;
	}
	public void setFabricarItem(boolean fabricarItem) {
		this.fabricarItem = fabricarItem;
	}
	public long getIdarticulo() {
		return idarticulo;
	}
	public void setIdarticulo(long idarticulo) {
		this.idarticulo = idarticulo;
	}
	
	

}