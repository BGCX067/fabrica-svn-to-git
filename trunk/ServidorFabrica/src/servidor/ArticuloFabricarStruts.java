package servidor;

public class ArticuloFabricarStruts {
	
	private String articulo=null;
	private long cantidad;
	private long id;
	private boolean chk;
	
	public ArticuloFabricarStruts()
	{
		
	}
	public ArticuloFabricarStruts(String articulo,long cantidad,long id,boolean chk)
	{
		this.articulo=articulo;
		this.cantidad=cantidad;
		this.id=id;
		this.chk=chk;
	}
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public boolean isChk() {
		return chk;
	}
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
