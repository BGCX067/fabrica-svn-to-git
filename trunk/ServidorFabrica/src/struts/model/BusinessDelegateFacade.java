package struts.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import util.JBossConfig;

import com.articulo.Articulo;
import com.articulo.ArticuloStrutsVO;
import com.materiaprima.MateriaPrima;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionVO;

import facade.BusinessFacade;


public class BusinessDelegateFacade {
	
	private BusinessFacade bf=null;
	
	public BusinessDelegateFacade() {
		inicializarContexto();
	}

	private void inicializarContexto() {
		Hashtable props = new Hashtable();
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		//Url completa de ubicacion del servidor de aplicaciones
		props.put(InitialContext.PROVIDER_URL,"jnp://"+JBossConfig.ip+":1099");
		//Objeto del tipo InitialContext
		try {
			InitialContext initialContect = new InitialContext(props);
			String naming = "zara/BusinessFacadeBean/remote";
			bf = (BusinessFacade)initialContect.lookup(naming);
			System.out.println("Connected to:"+ props.get(InitialContext.PROVIDER_URL));
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public ArticuloStrutsVO getNewArticulo()
	{
		return bf.getNewArticulo();
	}


	public Map<Long, MateriaPrima> getMateriasPrimas() {
		return bf.getMateriasPrimas();
	}

	public void modificarArticulo(Articulo articuloNuevo, boolean fabricacion, long cantidad) {
		bf.modificarArticulo(articuloNuevo, fabricacion, cantidad);
		
	}

	public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes() {
		return bf.getOrdenesFabricacionPendientes();
	}

	public Set<OrdenFabricacionStockVO> validarStock(ArrayList<OrdenFabricacionVO> ordenesSeleccionadas) {
		
		return bf.validarStock(ordenesSeleccionadas);
	}

	public void inicializarFabricacion(ArrayList<OrdenFabricacionVO> ordenesSeleccionadas) {
		bf.inicializarFabricacion(ordenesSeleccionadas);
	}

	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion() {
		
		return bf.chequearEjecucion();
	}

	public Set<OrdenReposicionVO> getOrdenesReposicion() {
		return bf.getOrdenesReposicion();
	}

	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados() {
		return bf.getOrdenesFabricacionTerminados();
	}

	public Set<OrdenReposicionVO> actualizarOrdenReposicion() {
		return bf.actualizarOrdenReposicion();
	}

	public Articulo getArticuloByPK(long idarticulo) {
		
		return bf.getArticuloByPK(idarticulo);
	}
	
	
}
