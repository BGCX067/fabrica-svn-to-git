package com.solicitudfabricacion;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import legacy.ProcesarMateriaPrimaBean;

import org.apache.log4j.Logger;

import zara.commons.dtos.centrodistribucion.CentroDistribucionDTO;
import zara.commons.dtos.solicitudfabricacion.ItemSolicitudFabricacionDTO;
import zara.commons.dtos.solicitudfabricacion.SolicitudFabricacionDTO;
import zara.commons.xml.ZaraXMLConverter;

import com.articulo.AdministradorArticulo;
import com.articulo.Articulo;
import com.centrodistribucion.CentroDistribucion;
import com.centrodistribucion.CentroDistribucionVO;
import com.solicitudfabricacion.estado.SolicitudFabricacionEstado;
import com.solicitudfabricacion.xml.ArticuloSolicitud;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;
@Stateless
public class AdministradorSolicitudFabricacionBean implements AdministradorSolicitudFabricacion{
	@PersistenceContext(unitName="FABRICA")
	private EntityManager manager;
	Logger logger = Logger.getLogger(AdministradorSolicitudFabricacionBean.class);
	public SolicitudFabricacion getInstancia() {
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricacion getSolicitudFabricacionByFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricacion getSolicitudFabricacionByPK(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public SolicitudFabricacion insertarSolicitudFabricacion(SolicitudFabricacion solicitudFabricacion) {
		manager.persist(solicitudFabricacion);
		return solicitudFabricacion;
	}
	
	public Set<SolicitudFabricacionVO> getSolicitudFabricacion() {
		String SQL = "SELECT ore"
			+ " FROM OrdenReposicion ore";
		return createListOrdenesFabricacion(manager.createQuery(SQL).getResultList());
	}
	private Set<SolicitudFabricacionVO> createListOrdenesFabricacion(List resultList) {
		Set<SolicitudFabricacionVO> set=new HashSet<SolicitudFabricacionVO>();
		for (int i = 0; i < resultList.size(); i++) {
			SolicitudFabricacion sf=(SolicitudFabricacion)resultList.get(i);
			SolicitudFabricacionVO solicitudFabricacionVO  = new SolicitudFabricacionVO(sf.getID(),sf.getFecha(),sf.getEstado(),sf.getCentroDistribucion(),SolicitudFabricacionVO.parseSetToVO(sf.getItems()));
			set.add(solicitudFabricacionVO);
		}
		return set;
	}

	public void crearSolicitud(SolicitudFabricacionXML solicitud, AdministradorArticulo administradorArticulo) {
		CentroDistribucion centro=findCentroDistribucion(solicitud.getCentroDistribucion());
		SolicitudFabricacion solicitudFabricacion = new SolicitudFabricacion();
		solicitudFabricacion.setEstado(findEstadoByPk(1));
		solicitudFabricacion.setFecha(solicitud.getFecha());
		solicitudFabricacion.setCentroDistribucion(findCentroDistribucion(solicitud.getCentroDistribucion()));
		addArticulos(solicitudFabricacion, solicitud, administradorArticulo);
		insertarSolicitudFabricacion(solicitudFabricacion);
	}

	private void addArticulos(SolicitudFabricacion solicitudFabricacion, SolicitudFabricacionXML solicitud, AdministradorArticulo administradorArticulo) {
		Iterator<ArticuloSolicitud> i = solicitud.getItems().iterator();
		
		while (i.hasNext()) {
			ArticuloSolicitud element = (ArticuloSolicitud) i.next();
			Articulo articulo = administradorArticulo.getArticuloByPK(Long.parseLong(element.getCodigo()));
			solicitudFabricacion.getItems().add(new SolicitudFabricacionItem(articulo, element.getCantidad()));
		}
	}

	private CentroDistribucion findCentroDistribucion(String centroDistribucion) {
		String SQL = "Select cd FROM CentroDistribucion cd where cd.ID = '"+centroDistribucion+"'";
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return (CentroDistribucion)list.get(0);
		return null;
	}

	private SolicitudFabricacionEstado findEstadoByPk(long estado) {
		return manager.find(SolicitudFabricacionEstado.class, estado);
	}

	public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes() {
		String SQL = "SELECT solfa"
			+ " FROM SolicitudFabricacion solfa" +
				" ORDER BY solfa.fecha desc";
		return createListOrdenesReposicion(manager.createQuery(SQL).getResultList());
	}
	

	private Set<SolicitudFabricacionVO> createListOrdenesReposicion(List resultList) {
		Set<SolicitudFabricacionVO> set=new HashSet<SolicitudFabricacionVO>();
		for (int i = 0; i < resultList.size(); i++) {
			SolicitudFabricacion or=(SolicitudFabricacion)resultList.get(i);
			if(or.getEstado().getID()!=3) /* 3 es el estado terminado */
			{
			SolicitudFabricacionVO solicitudFabricacionVO  = new SolicitudFabricacionVO(or.getID(),or.getFecha(),or.getEstado(),or.getCentroDistribucion(),SolicitudFabricacionVO.parseSetToVO(or.getItems()));
			set.add(solicitudFabricacionVO);
			}
		}
		return set;
	}

	public Set<CentroDistribucion> obtenerCentros(Set<CentroDistribucionDTO> centros) {
		Set<CentroDistribucion> centrosObjects = new HashSet<CentroDistribucion>();
		
		if (centros == null)
			return centrosObjects;
		
		Iterator<CentroDistribucionDTO> i = centros.iterator();
		
		while (i.hasNext()) {
			CentroDistribucionDTO element = (CentroDistribucionDTO) i.next();
			CentroDistribucion centroObject = findCentroDistribucion(element.getNombre());
			if (centroObject != null)
				centrosObjects.add(centroObject);
		}
		
		return centrosObjects;
	}

	public CentroDistribucionVO getCentroDistribucionVOByPK(Long centrodistribucion) {
		String SQL = "SELECT cdist"
			+ " FROM CentroDistribucion cdist" 
			+ " where cdist.ID ="+ centrodistribucion;
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return (createVO((CentroDistribucion)list.get(0)));
		return null;
	}
	private CentroDistribucionVO createVO(CentroDistribucion centro) {
		if (centro != null)
		{
			return new CentroDistribucionVO(centro.getID(), centro.getNombre(),centro.getIp());	

		}	
		return null;
	}
	public SolicitudFabricacionXML recibirSolicitud(String in0, AdministradorArticulo administradorArticulo) {
		logger.info("Nueva Solicitud de Fabricacion:\n"+in0);
		/* Se Parsea el mensaje */
	    ZaraXMLConverter converter=new ZaraXMLConverter();
	    SolicitudFabricacionDTO solFa=(SolicitudFabricacionDTO)converter.fromXml(in0);
	    logger.info("Procesando Solicitud de Fabricacion Nº "+String.valueOf(solFa.getCodigo()));
	    /* 
	     * Se Instancia la clase que requiere el metodo en el servidor 
	     * para almacenar la solicitud de fabricacion
	     */
	    SolicitudFabricacionXML solFaXML=new SolicitudFabricacionXML();
	    /*
	     * Comienza el traspaso de los datosç
	     */
	    solFaXML.setFecha(solFa.getFecha());
	    /*
	     * Se realiza el traspaso del centro de distribucion 
		 */	
	    if(getCentroDistribucionVOByPK(solFa.getCentroDistribucion().getCodigo())==null)
	    {
	    	logger.error("El centro de distribucion de la solicitud de Fabricacion Nº "+String.valueOf(solFa.getCodigo())+" no se encuentra en el sistema");
    		logger.info("A causa de errores previos, la solicitud Nº "+String.valueOf(solFa.getCodigo())+" no puede ingresada al sistema");
	    	return null;		
	    }
	    
	    solFaXML.setCentroDistribucion(String.valueOf(solFa.getCentroDistribucion().getCodigo()));
	    /*
	     * Se realiza el traspaso de los items
	     */ 
	    Set<ArticuloSolicitud> articulos=convertItems(solFa.getItems(),administradorArticulo);
	    if(articulos!=null)
	    {
	    	solFaXML.setItems(articulos);
	    }else
	    {
	    	logger.error("La lista se encuentra vacia o ninguno de sus articulos no se encuentra dentro del sistema");
	    }/*
	     * Se Invoca al metodo para almacenar el pedido
	     */
	    logger.info("Fin del Procesamiento de Solicitud de Fabricacion Nº "+String.valueOf(solFa.getCodigo()));
	    return solFaXML;
		
	}
	private Set<ArticuloSolicitud> convertItems(Set<ItemSolicitudFabricacionDTO> items,AdministradorArticulo administradorArticulo) {
    	Set<ArticuloSolicitud> itemsXML=new HashSet<ArticuloSolicitud>();
    	
    	
    	for (Iterator iter = items.iterator(); iter.hasNext();) {
			ItemSolicitudFabricacionDTO element = (ItemSolicitudFabricacionDTO) iter.next();
			
			ArticuloSolicitud articuloSolicitud=new ArticuloSolicitud();
			/*
			 * Verifico la existencia del articulo
			 */
			Articulo articulo=administradorArticulo.getArticuloByCodigo(String.valueOf(element.getIdArticulo()));
			if(articulo==null)
			{
			  logger.error("Articulo "+String.valueOf(element.getIdArticulo())+" Inexistente, no será agregado como parte de la solicitud de fabricacion");
			}else{
			articuloSolicitud.setCodigo(String.valueOf(articulo.getID()));
			articuloSolicitud.setDescripcion(articulo.getDescripcion());
			articuloSolicitud.setCantidad(element.getCantidad());
			itemsXML.add(articuloSolicitud);
			}
		}
    	if(itemsXML.size()==0)
    	{
    		return null;
    	}
		return itemsXML;
	}

}
