package facade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import util.JBossConfig;

import com.articulo.AdministradorArticulo;
import com.articulo.Articulo;
import com.articulo.ArticuloStrutsVO;
import com.articulo.ArticuloVO;
import com.centrodistribucion.CentroDistribucionVO;
import com.materiaprima.AdministradorMateriaPrima;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
import com.materiaprima.MateriaPrimaStockVO;
import com.ordencompra.AdministradorOrdenCompra;
import com.ordencompra.OrdenCompra;
import com.ordenfabricacion.AdministradorOrdenFabricacion;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.AdministradorOrdenReposicion;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.AdministradorSolicitudFabricacion;
import com.solicitudfabricacion.AdministradorSolicitudFabricacionBean;
import com.solicitudfabricacion.SolicitudFabricacionVO;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

@Stateful
public class BusinessFacadeBean implements BusinessFacade {
	
		private AdministradorMateriaPrima administradorMateriaPrima = null;
		private AdministradorOrdenFabricacion administradorOrdenFabricacion= null;
		private AdministradorArticulo administradorArticulo= null;
		private AdministradorOrdenCompra administradorOrdenCompra= null;
		private AdministradorOrdenReposicion administradorOrdenReposicion= null;
		private AdministradorSolicitudFabricacion administradorSolicitudFabricacion= null;

		
		
		@PostConstruct
		public void inicializarBeans()
		{
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			//Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL,"jnp://"+JBossConfig.ip+":1099");
			//Objeto del tipo InitialContext
			
			inicializarAdministradorMateriaPrima(props);
			inicializarAdministradorOrdenFabricacion(props);
			inicializarAdministradorSolicitudFabricacion( props);
			inicializarAdministradorOrdenReposicion(props);
			inicializarAdministradorArticulo(props);
			inicializarAdministradorOrdenCompra( props);
			
		}
		
		public void inicializarAdministradorOrdenReposicion(Hashtable props) {
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorOrdenReposicionBean/local";
				administradorOrdenReposicion = (AdministradorOrdenReposicion)initialContect.lookup(naming);
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
			
		}
		
		public void inicializarAdministradorSolicitudFabricacion(Hashtable props) {
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorSolicitudFabricacionBean/local";
				administradorSolicitudFabricacion= (AdministradorSolicitudFabricacion)initialContect.lookup(naming);
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
			
		}
		public void inicializarAdministradorMateriaPrima(Hashtable props) {
			
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorMateriaPrimaBean/local";
				administradorMateriaPrima = (AdministradorMateriaPrima)initialContect.lookup(naming);
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}
		public void inicializarAdministradorOrdenFabricacion(Hashtable props) {
			
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorOrdenFabricacionBean/local";
				administradorOrdenFabricacion= (AdministradorOrdenFabricacion)initialContect.lookup(naming);
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}
		public void inicializarAdministradorArticulo(Hashtable props) {
			
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorArticuloBean/local";
				administradorArticulo= (AdministradorArticulo)initialContect.lookup(naming);
			
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}
		
		private void inicializarAdministradorOrdenCompra(Hashtable props) {
			
			try {
				InitialContext initialContect = new InitialContext(props);
				String naming = "zara/AdministradorOrdenCompraBean/local";
				administradorOrdenCompra= (AdministradorOrdenCompra)initialContect.lookup(naming);
			
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		} 
		public Map<Long, ArticuloVO> getArticulosByOrdenFabricacion(ArrayList<OrdenFabricacionVO> list)
		{
			return administradorArticulo.getArticulosByOrdenFabricacion(list);
		}

		public Map<Long, MateriaPrima> getMateriasPrimas() {
				return administradorMateriaPrima.getMateriasPrimas();
		}

		public void getOrdenFabricacionByPK(long id) {
			administradorOrdenFabricacion.getOrdenFabricacionByPK(id);
			
		}

		public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes() {
			
			return administradorOrdenFabricacion.getOrdenesFabricacionPendientes();
		}

		public MateriaPrima insertarMateriaPrima(MateriaPrima materiaPrima) {
			return administradorMateriaPrima.insertarMateriaPrima(materiaPrima);
		}

		

		public Set<OrdenFabricacionEjecucionVO> chequearEjecucion() {
			return administradorOrdenFabricacion.chequearEjecucion(administradorArticulo);
		}
		
		
		public Set<OrdenFabricacionStockVO> validarStock(ArrayList<OrdenFabricacionVO> list) {
			Map<Long, MateriaPrimaStockVO> mapMateriaPrima = administradorMateriaPrima.getMateriasPrimasStock();
			Map<Long, Articulo> mapArticulo = administradorArticulo.getArticulos();
			return administradorOrdenFabricacion.validarStock(mapMateriaPrima, mapArticulo, list);
		}
		public Set<OrdenReposicionVO> getOrdenesReposicion() {
			return administradorOrdenReposicion.getOrdenesReposicion();
		}
		
		public OrdenCompra inicializarFabricacion(ArrayList<OrdenFabricacionVO> list) {
			return administradorOrdenCompra.crearCompraFabrica(administradorOrdenFabricacion.inicializarFabricacion(administradorMateriaPrima, administradorArticulo,  list), administradorMateriaPrima);
		}
		public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados() {
			return administradorOrdenFabricacion.getOrdenesFabricacionTerminados();
		}
		public Set<OrdenReposicionVO> getOrdenesReposicionPendiente() {
			return administradorOrdenReposicion.getOrdenesReposicion();
		}
		public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes() {
			return administradorSolicitudFabricacion.getSolicitudesFabricacionPendientes();
		}
	
		
		public void tramitarRecepcionSolicitud(SolicitudFabricacionXML solicitud)
		{
			administradorSolicitudFabricacion.crearSolicitud(solicitud, administradorArticulo);
			administradorOrdenFabricacion.fabricarSolicitud(solicitud.getItems(), administradorArticulo);
		}

		public void insertarArticulo(Articulo articuloNuevo,  boolean fabricado, long cantidad) {
			
			Articulo articulo = administradorArticulo.insertarArticulo(articuloNuevo);
			if (fabricado)
				administradorOrdenFabricacion.crearOrden(articulo, administradorArticulo, cantidad);
		}

		public Long ingresarStockMateriaPrima(String codigo, Long cantidad) {
			// TODO Auto-generated method stub
			return administradorMateriaPrima.ingresarStockMateriaPrima(codigo,cantidad);
		}

		public String getMateriaPrimaDescripcion(String codigo) {
			// TODO Auto-generated method stub
			return administradorMateriaPrima.getMateriaPrimaDescripcion(codigo);
		}

		public void crearOrdenesReposicion(Set<OrdenReposicionVO> ordenRep, Set<SolicitudFabricacionVO> SolFab, Set<OrdenFabricacionVO> ordenFab) {
			administradorOrdenReposicion.crearOrdenesReposicion(ordenRep, SolFab, ordenFab);
			
		}

		public void insertarMateriaPrimaCantidad(MateriaPrimaCantidad mpc) {
			
			administradorMateriaPrima.insertarMateriaPrimaCantidad(mpc);
			
		}

		public boolean validarCodigo(String text) {
			
			return administradorArticulo.validarCodigo(text);
		}

		public Set<OrdenReposicionVO> actualizarOrdenReposicion() {
			// TODO Auto-generated method stub
			Logger logger = Logger.getLogger(BusinessFacadeBean.class);
			logger.info("Administrando las reposiciones");
			Set<OrdenReposicionVO> set=administradorOrdenReposicion.actualizarOrdenReposicion(this);
			logger.info("Enviando las solicitudes de Reposicion a Los centros de distribucion");
			administradorOrdenReposicion.tramitarSolicitudesReposicion(set,this);
			return set;
		}


		public ArticuloStrutsVO getNewArticulo() {
			Map<Long, MateriaPrima> map = administradorMateriaPrima.getMateriasPrimas();
			ArticuloStrutsVO articulo = administradorArticulo.getNewArticulo();
			
			if (articulo == null)
				return null;
			else
				return addMateriasPrimasArticuloStruts(articulo, map);
			
		}

		private ArticuloStrutsVO addMateriasPrimasArticuloStruts(ArticuloStrutsVO articulo, Map<Long, MateriaPrima> map) {
			
			Set<MateriaPrimaCantidad> set = new HashSet<MateriaPrimaCantidad>();
			Iterator iter = map.values().iterator();
			
			while (iter.hasNext()) {
				MateriaPrima element = (MateriaPrima) iter.next();
				MateriaPrimaCantidad mpcantidad = new MateriaPrimaCantidad();
				mpcantidad.setCantidad(0);
				mpcantidad.setID(element.getID());
				mpcantidad.setMateriaPrima(element);
				set.add(mpcantidad);
			}
			articulo.setMateriaPrimas(set);
			return articulo;
		}

		public void borrarArticulo(Articulo articuloNuevo) {
			administradorArticulo.borrarArticulo(articuloNuevo);
			
		}

		public void modificarArticulo(Articulo articuloNuevo, boolean fabricacion, long cantidad) {
			System.out.println("en el businessfacade bean el id vale: "+articuloNuevo.getID());
			//administradorArticulo.borrarArticulo(articuloNuevo);
			Articulo articulo = administradorArticulo.modificarArticulo(articuloNuevo);
			if (fabricacion)
				administradorOrdenFabricacion.crearOrden(articulo, administradorArticulo, cantidad);
			
		}
		public boolean recibirSolicitud(String in0) {
			// TODO Auto-generated method stub
			
			SolicitudFabricacionXML solFaXML=(administradorSolicitudFabricacion.recibirSolicitud(in0,administradorArticulo));
			if(solFaXML!=null)
			{
				tramitarRecepcionSolicitud(solFaXML);
				return true;
			}else
			{
				return false;
			}
		}
		public CentroDistribucionVO getCentroDistribucionVOByPK(Long codigo) {
			return administradorOrdenReposicion.getCentroDistribucionVOByPK(codigo);
		}

		public Articulo getArticuloByPK(long id) {
			
			return administradorArticulo.getArticuloByPK(id);
		}
		
		
		
		
		
}
