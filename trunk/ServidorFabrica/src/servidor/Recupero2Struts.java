package servidor;

public class Recupero2Struts {
	private long codigoFabricacion;
	private long codigoDeProducto;
	private String nombre;
	private long cantidad;
	
	public Recupero2Struts()
	{
		
	}
	public Recupero2Struts(long codigoFabricacion,long codigo,String nombre,long cantidad)
	{
		this.codigoFabricacion=codigoFabricacion;
		this.codigoDeProducto=codigoDeProducto;
		this.nombre=nombre;
		this.cantidad=cantidad;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public long getCodigoDeProducto() {
		return codigoDeProducto;
	}
	public void setCodigoDeProducto(long codigoDeProducto) {
		this.codigoDeProducto = codigoDeProducto;
	}
	public long getCodigoFabricacion() {
		return codigoFabricacion;
	}
	public void setCodigoFabricacion(long codigoFabricacion) {
		this.codigoFabricacion = codigoFabricacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
	