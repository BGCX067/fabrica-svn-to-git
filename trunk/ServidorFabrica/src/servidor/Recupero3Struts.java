package servidor;

public class Recupero3Struts {
	private String centroDistribucion=null;
	private String producto=null;
	private int cantidad;
	
	public Recupero3Struts()
	{
		
	}
	public Recupero3Struts(String centroDistribucion,String producto,int cantidad)
	{
		this.centroDistribucion=centroDistribucion;
		this.producto=producto;
		this.cantidad=cantidad;	
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getCentroDistribucion() {
		return centroDistribucion;
	}
	public void setCentroDistribucion(String centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
}