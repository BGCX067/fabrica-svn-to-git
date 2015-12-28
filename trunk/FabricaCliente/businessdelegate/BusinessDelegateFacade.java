package businessdelegate;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.articulo.Articulo;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
import com.ordencompra.OrdenCompra;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

import facade.BusinessFacade;
import framework.modelo.BusinessDelegate;

public class BusinessDelegateFacade extends BusinessDelegate{
	
	private BusinessFacade bf=null;
	
	public BusinessDelegateFacade() {
		inicializarContexto();
	}

	private void inicializarContexto() {
		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		//Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
		//Objeto del tipo InitialContext
		try {
			InitialContext initialContect = new InitialContext(props);
			String naming = "Fabrica/BusinessFacadeBean/remote";
			bf = (BusinessFacade)initialContect.lookup(naming);
			System.out.println("Connected to:"+ props.get(InitialContext.PROVIDER_URL));
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes() {
		return bf.getOrdenesFabricacionPendientes();		
	}


	public void getOrdenFabricacionByPK(Long id) {
		bf.getOrdenFabricacionByPK(id);
	}

	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion() {
		return bf.chequearEjecucion();
	}

	public Set<OrdenFabricacionStockVO> validarStock(ArrayList<OrdenFabricacionVO> list)
	
	{
		return bf.validarStock(list);
	}
	
	public OrdenCompra inicializarFabricacion(ArrayList<OrdenFabricacionVO> list)
	{
		return bf.inicializarFabricacion(list);
	}

	
	public void tramitarRecepcionSolicitud(SolicitudFabricacionXML solicitud)
	{
		bf.tramitarRecepcionSolicitud(solicitud);
	}

	public Map<Long, MateriaPrima> getMateriasPrimas() {
		return bf.getMateriasPrimas();
		
	}

	public void insertarArticulo(Articulo articuloNuevo, Set<String> centros, boolean fabricacion, long cantidad) {
		bf.insertarArticulo(articuloNuevo, centros, fabricacion, cantidad);
		
	}


	public Set<OrdenReposicionVO> getOrdenesReposicion() {
		return bf.getOrdenesReposicion();
	}

	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados() {
		return bf.getOrdenesFabricacionTerminados();
	}

	public Set<OrdenReposicionVO> getOrdenesReposicionPendiente() {
		return bf.getOrdenesReposicionPendiente();
	}

	public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes() {
		return bf.getSolicitudesFabricacionPendientes();
	}

	public Long ingresarStockMateriaPrima(String codigo, Long cantidad) {
		// TODO Auto-generated method stub
		return bf.ingresarStockMateriaPrima(codigo,cantidad);
	}

	public String getMateriaPrimaDescripcion(String codigo) {
		// TODO Auto-generated method stub
		return bf.getMateriaPrimaDescripcion(codigo);
	}

	public void crearOrdenesReposicion(Set<OrdenReposicionVO> orSet, Set<SolicitudFabricacionVO> solFabList, Set<OrdenFabricacionVO> ordenes) {
		bf.crearOrdenesReposicion(orSet,solFabList,ordenes);
		
	}


	public void insertarMateriaPrimaCantidad()
	{
		MateriaPrimaCantidad mpc = new MateriaPrimaCantidad();
		bf.insertarMateriaPrimaCantidad(mpc);
	}

	public boolean validarCodigo(String text) {
		return bf.validarCodigo(text);
	}

	public Set<OrdenReposicionVO> actualizarOrdenReposicion() {
		// TODO Auto-generated method stub
		return bf.actualizarOrdenReposicion();
	}
	

}
