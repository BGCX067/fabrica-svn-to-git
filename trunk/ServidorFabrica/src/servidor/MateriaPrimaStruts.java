package servidor;

public class MateriaPrimaStruts {
	
	private String descripcion=null;
	private String cantidad=null;
	private String iden=null;
	private boolean chk;
	
	
	
	public MateriaPrimaStruts()
	{ 
		
	}    
	// Contructor para propiedades iniciales 
	public MateriaPrimaStruts(String descripcion, String cantidad,String id,boolean chk) 
	{
		this.descripcion =descripcion;
		this.cantidad = cantidad;
		this.iden = id;
		this.chk=chk;
		
	}
	
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIden() {
		return iden;
	}
	public void setIden(String id) {
		this.iden = id;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	

}
