package servidor;

public class Recupero1Struts {
	
	private long codigo;
	private String pedidoRep=null;
	private String estado=null;
	
	public Recupero1Struts()
	{
		
	}
	public Recupero1Struts(long codigo,String pedidoRep,String estado)
	{
		this.codigo=codigo;
		this.pedidoRep=pedidoRep;
		this.estado=estado;
	}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPedidoRep() {
		return pedidoRep;
	}
	public void setPedidoRep(String pedidoRep) {
		this.pedidoRep = pedidoRep;
	}
	
	

}
