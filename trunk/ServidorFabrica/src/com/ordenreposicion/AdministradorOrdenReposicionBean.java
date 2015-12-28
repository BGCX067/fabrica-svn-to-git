package com.ordenreposicion;



import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import reposicion.webservices.commons.zara.RecibirReposicion;
import reposicion.webservices.commons.zara.RecibirReposicionService;
import reposicion.webservices.commons.zara.RecibirReposicionServiceLocator;
import util.DateFormatParser;
import zara.commons.dtos.centrodistribucion.CentroDistribucionDTO;
import zara.commons.dtos.reposicion.ItemReposicionDTO;
import zara.commons.dtos.reposicion.ReposicionDTO;
import zara.commons.xml.ZaraXMLConverter;

import com.articulo.Articulo;
import com.centrodistribucion.CentroDistribucion;
import com.centrodistribucion.CentroDistribucionVO;
import com.ordenfabricacion.OrdenFabricacion;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenfabricacion.estado.OrdenFabricacionEstado;
import com.ordenreposicion.estado.OrdenReposicionEstado;
import com.solicitudfabricacion.SolicitudFabricacion;
import com.solicitudfabricacion.SolicitudFabricacionItem;
import com.solicitudfabricacion.SolicitudFabricacionItemVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;
import com.solicitudfabricacion.estado.SolicitudFabricacionEstado;

import facade.BusinessFacade;

@Stateless
public class AdministradorOrdenReposicionBean implements AdministradorOrdenReposicion{
	@PersistenceContext(unitName="FABRICA")
	private EntityManager manager;
	Logger logger = Logger.getLogger(AdministradorOrdenReposicionBean.class);
	public OrdenReposicion getInstancia(Long centrodistribucion, Date date, String comentario) {
		OrdenReposicion or=new OrdenReposicion();
		or.setCentroDistribucion(getCentroDistribucionByPK(new Long(centrodistribucion)));
		or.setEstado(getOrdenReposicionEstadoByPK(1));
		or.setFecha(date);
		or.setComentario(comentario);
		or.setItems(new HashSet<OrdenReposicionItem>());
		return or;
	}

	private OrdenReposicionEstado getOrdenReposicionEstadoByPK(long id) {
		return manager.find(OrdenReposicionEstado.class, id);
	}

	private CentroDistribucion getCentroDistribucionByPK(long id) {
		return manager.find(CentroDistribucion.class, id);
	}

	public OrdenReposicion getOrdenReposicionByFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenReposicion getOrdenReposicionByPK(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenReposicion insertarOrdenReposicion(OrdenReposicion ordenReposicion) {
		manager.persist(ordenReposicion);
		return ordenReposicion;
	}

	public Set<OrdenReposicionVO> getOrdenesReposicion() {
		String SQL = "SELECT ore"
			+ " FROM OrdenReposicion ore";
		return createListOrdenesReposicion(manager.createQuery(SQL).getResultList());
	}

	private Set<OrdenReposicionVO> createListOrdenesReposicion(List resultList) {
		Set<OrdenReposicionVO> set=new HashSet<OrdenReposicionVO>();
		for (int i = 0; i < resultList.size(); i++) {
			OrdenReposicion or=(OrdenReposicion)resultList.get(i);
			OrdenReposicionVO ordenReposicionVO  = new OrdenReposicionVO(or.getID(),or.getFecha(),or.getEstado().getNombre(),OrdenReposicionVO.parseSetToVO(or.getItems()),or.getComentario(),or.getCentroDistribucion());
			set.add(ordenReposicionVO);
		}
		return set;
	}

	public Set<OrdenReposicionVO> getOrdenesReposicionPendiente() {
		String SQL = "SELECT ore"
			+ " FROM OrdenReposicion ore" 
			+ " where ore.estado in (1,2)" 
			+ " order by ore.fecha desc";
		return createListOrdenesReposicion(manager.createQuery(SQL).getResultList());
	}

	public void crearOrdenesReposicion(Set<OrdenReposicionVO> ordenRep, Set<SolicitudFabricacionVO> solFab, Set<OrdenFabricacionVO> ordenFab) {
		// TODO Auto-generated method stub
		/* Actualizar las Solicitudes de Fabricacion*/
		actualizarSolicitudesFabricacion(solFab);
		/* Actualizar las ordenes de Fabricacion */
		actualizarOrdenesFabricacion(ordenFab);
		/* Crear Ordenes de Reposicion*/
		crearOrdenesReposicion(ordenRep);
		
	}

	private void crearOrdenesReposicion(Set<OrdenReposicionVO> ordenRep) {
		// TODO Auto-generated method stub
		Iterator iter=ordenRep.iterator();
		/*recorro el set con los value-object creados desde el cliente*/
		while(iter.hasNext())
		{
			OrdenReposicionVO orVO=(OrdenReposicionVO) iter.next();
			OrdenReposicion or=getInstancia(orVO.getCentroDistribucionid(),orVO.getFecha(),orVO.getComentario());
			/*creo los items*/
			Set<OrdenReposicionItemVO> items=orVO.getItems();
			Set<OrdenReposicionItem> itemsRep=parseToOrdenReposicion(items);
			/*agrego los items de la orden de reposicion*/
			or.setItems(itemsRep);
			insertarOrdenReposicion(or);
		}
		
		
	}

	private Set<OrdenReposicionItem> parseToOrdenReposicion(Set<OrdenReposicionItemVO> items) {
		Set<OrdenReposicionItem> orItem=new HashSet<OrdenReposicionItem>();
		Iterator iter =items.iterator();
		while (iter.hasNext()) {
			/*creo el item de la orden de reposicion*/
			OrdenReposicionItemVO itemVO = (OrdenReposicionItemVO) iter.next();
			OrdenReposicionItem item=getInstanciaItem(itemVO.getArticuloId(),itemVO.getCantidad(),itemVO.getItemFabricacion());
			/*lo agrego al set de ordenes de reposicion*/
			orItem.add(item);
		}
		return orItem;
	}

	private OrdenReposicionItem getInstanciaItem(Long articuloId, Long cantidad, Set<SolicitudFabricacionItemVO> itemFabricacion) {
		OrdenReposicionItem orItem=new OrdenReposicionItem();
		orItem.setArticulo(getArticuloByPK(Long.parseLong(Long.toString(articuloId))));
		orItem.setCantidad(Long.parseLong(Long.toString(cantidad)));
		orItem.setSolicitudesFabricacion(getSolicitudesFabricacion(itemFabricacion));
		return orItem;
	}

	private Set<SolicitudFabricacionItem> getSolicitudesFabricacion(Set<SolicitudFabricacionItemVO> itemFabricacion) {
		Set<SolicitudFabricacionItem> sfSet=new HashSet<SolicitudFabricacionItem>();
		Iterator iter =itemFabricacion.iterator();
		while(iter.hasNext())
		{
			SolicitudFabricacionItemVO sfVO=(SolicitudFabricacionItemVO) iter.next();
			SolicitudFabricacionItem sf=getSolicitudFabricacionItemByPK(sfVO.getID());
			sfSet.add(sf);
		}
		
		return sfSet;
	}

	private SolicitudFabricacionItem getSolicitudFabricacionItemByPK(long id) {
		return manager.find(SolicitudFabricacionItem.class, id);
	}

	private Articulo getArticuloByPK(long id) {
		return manager.find(Articulo.class, id);
	}


	private void actualizarOrdenesFabricacion(Set<OrdenFabricacionVO> ordenFab) {
		// TODO Auto-generated method stub
		Iterator iter=ordenFab.iterator();
		/*Se actualizan todas las ordenes de fabricacion*/
		while(iter.hasNext()){
			OrdenFabricacionVO ofVO=(OrdenFabricacionVO) iter.next();
			/* Se actualiza cada una de las ordenes de fabricacion */
			actualizarOrdenFabricacion(ofVO);
		}
	}

	private void actualizarOrdenFabricacion(OrdenFabricacionVO ofVO) {
		/*Solo se actualiza el estado y la cantidad distribuida*/
		OrdenFabricacion of=getOrdenFabricacionByPK(ofVO.getID());
		of.setDistribuido(ofVO.getCantidadDistribuida());
		of.setEstado(getOrdenFabricacionEstado(ofVO.getEstado()));
	}

	private OrdenFabricacionEstado getOrdenFabricacionEstado(Long id) {
		return manager.find(OrdenFabricacionEstado.class, id);
	}

	private OrdenFabricacion getOrdenFabricacionByPK(long id) {
		return manager.find(OrdenFabricacion.class, id);
	}

	private void actualizarSolicitudesFabricacion(Set<SolicitudFabricacionVO> solFab) {
		// TODO Auto-generated method stub
		Iterator iter=solFab.iterator();
		/*recorro todas las solicitudes*/
		while(iter.hasNext()){
			SolicitudFabricacionVO sfVO=(SolicitudFabricacionVO) iter.next();
			/*actualizo cada una*/
			actualizarSolicitudFabricacion(sfVO);
		}
	}

	private void actualizarSolicitudFabricacion(SolicitudFabricacionVO sfVO) {
		
		SolicitudFabricacion sf=getSolicitudFabricacion(Long.parseLong(sfVO.getID()));
		sf.setEstado(getSolicitudFabricacionEstado(sfVO.getEstado()));
		/*se actualizan lso items de ficha solicitud de fabricacion*/
		actualizarSolicitudesFabricacionItem(sfVO.getItems());
	}
	
	private void actualizarSolicitudesFabricacionItem(Set<SolicitudFabricacionItemVO> items) {
		Iterator iter=items.iterator();
		while(iter.hasNext()){
			/* Solo se actualizan el estado y la cantidad fabricada*/
			SolicitudFabricacionItemVO itemVO=(SolicitudFabricacionItemVO) iter.next();
			SolicitudFabricacionItem item=getSolicitudFabricacionItemByPK(itemVO.getID());
			item.setCantidadFabricada(itemVO.getCantidadFabricada());
		}
		
	}

	private SolicitudFabricacionEstado getSolicitudFabricacionEstado(Long id) {
		return manager.find(SolicitudFabricacionEstado.class, id);
	}

	private SolicitudFabricacion getSolicitudFabricacion(long id) {
		return manager.find(SolicitudFabricacion.class, id);
	}


	private void createOrdenReposicion(SolicitudFabricacionVO SolFab,SolicitudFabricacionItemVO solFabItem, OrdenFabricacionVO ofVO,Set<OrdenReposicionVO> orSet) {
		/* Busco la Orden de Reposicion */
	
		OrdenReposicionVO or=buscarOrdenReposicion(orSet,String.valueOf(SolFab.getCentroDistribucion().getNombre()));
		OrdenReposicionItemVO orItem=null;
		Long cantidad=null;
		/* Si no existe una Orden de Repocision*/
		if(or==null)
		{
			/* Creo la orden de reposicion */
			or=new OrdenReposicionVO(0,(new Date()),"Pendiente",new HashSet(), (DateFormatParser.parseDate(new Date())+" - "+SolFab.getCentroDistribucion().getNombre()),SolFab.getCentroDistribucion());
			/*Calculo la cantidad a reponer*/
			cantidad=calcularcantidadReposicion(solFabItem,ofVO);
			/* Creo el item */
			orItem=new OrdenReposicionItemVO(new Long(0),solFabItem.getArticulo().getCodigo(),new Long(solFabItem.getArticulo().getID()),cantidad,solFabItem);
			/* Lo agrego a la orden de reposicion */
			or.getItems().add(orItem);
			
			orSet.add(or);
		}else
		{
			/* Si existe el item */
			orItem=buscarOrdenReposicionItem(or,String.valueOf(solFabItem.getArticulo().getID()));
			if(orItem==null)
			{
				/* Si no existe el item, lo creo*/
				cantidad=calcularcantidadReposicion(solFabItem,ofVO);
				/* Creo el item */
				orItem=new OrdenReposicionItemVO(new Long(0),solFabItem.getArticulo().getCodigo(),new Long(solFabItem.getArticulo().getID()),cantidad,solFabItem);
				/* Lo agrego a la orden de reposicion */
				or.getItems().add(orItem);
				
			}else
			{
				cantidad=calcularcantidadReposicion(solFabItem,ofVO);
				/* Si existe el item, lo modifico */
				orItem.setCantidad(orItem.getCantidad()+cantidad);
				
				/* Le agrego la nueva orden de solicitud la orden de fabricacion item*/
				orItem.getItemFabricacion().add(solFabItem);
				
			}
		}
		/* Actualizo la Canmtidad fabricada de la Solicitud */
		solFabItem.setCantidadFabricada(solFabItem.getCantidadFabricada()+cantidad);
		/* Modifico la cantidad distribuida*/
		ofVO.setCantidadDistribuida(ofVO.getCantidadDistribuida()+cantidad);
		/* Actualizo el estado de la solicitud de fabricacion*/
		SolFab.actualizarEstado();
		/* actualizo el estado de la orden de Fabricacion*/
		ofVO.actualizarEstado();
		
	}
	private OrdenReposicionItemVO buscarOrdenReposicionItem(OrdenReposicionVO or, String articuloId) {
		Iterator iter= or.getItems().iterator();
		while(iter.hasNext())
		{
			OrdenReposicionItemVO orVO=(OrdenReposicionItemVO)iter.next();
			if(String.valueOf(orVO.getArticuloId()).equals(articuloId))
			{
				return orVO;
			}
			
		}
		return null;
	}

	private OrdenReposicionVO buscarOrdenReposicion(Set<OrdenReposicionVO> orSet, String centroDistribucion) {
		
		Iterator iter=orSet.iterator();
		while (iter.hasNext()) {
			OrdenReposicionVO or = (OrdenReposicionVO) iter.next();
			if(or.getCentroDistribucion().equals(centroDistribucion)){
				return or;
			}
		}
		return null;
	}

	private Long calcularcantidadReposicion(SolicitudFabricacionItemVO solFabItem, OrdenFabricacionVO ofVO) {
		
		Long cantidadReponer=ofVO.getCantidadFabricada()-ofVO.getCantidadDistribuida();
		Long cantidad=solFabItem.getCantidad()-solFabItem.getCantidadFabricada();
		if(cantidadReponer>cantidad)
		{
			return cantidad;
		}
		else
		{
			return cantidadReponer;
		}
	}
	
	private SolicitudFabricacionItemVO buscarSolicitudFabricacionItem(SolicitudFabricacionVO solicitudFabricacion,String articuloId) {
		Iterator iter= solicitudFabricacion.getItems().iterator();
		while(iter.hasNext())
		{
			SolicitudFabricacionItemVO sfVO=(SolicitudFabricacionItemVO)iter.next();
			if((String.valueOf(sfVO.getArticulo().getID())).equals(articuloId))
			{
				return sfVO;
			}
			
		}
		return null;
	}
	

	public Set<OrdenReposicionVO> actualizarOrdenReposicion(BusinessFacade bf) {
		/*Busco los productos terminados*/
		Set<OrdenFabricacionVO> ordenes=(Set<OrdenFabricacionVO>) (bf.getOrdenesFabricacionTerminados());
		
		/* En este map estaran las ordenes de reposicion nuevas */
		Set<OrdenReposicionVO> orSet=new HashSet<OrdenReposicionVO>();
		
		/* Traer todas las solicitudes de fabricacion */
		Set<SolicitudFabricacionVO> solFabList=(Set<SolicitudFabricacionVO>) (bf.getSolicitudesFabricacionPendientes());
		
		/* Se extraen las ordenes de fabricacion en estado pendiente o parcialmente entregado*/
		Iterator ordenesIter=ordenes.iterator();
		/*Se recorren las ordenes de fabricacion */
		System.out.println("ordenes de fabricacion");
		
		while (ordenesIter.hasNext()) {
			OrdenFabricacionVO ofVO = (OrdenFabricacionVO) ordenesIter.next();
			
			/*Se recorren todas las solicitudes de fabricacion*/
			Iterator solFabIter=solFabList.iterator();
			/* Mientras la orden de Fabricacion tenga 
			 * cantidad mayor a cero (falta asiganr producto)
			 * y se hayan recorrido todas las solicitudes de 
			 * reposicion (no hayan mas solicitudes con ese 
			 * producto para satisfacer) */
			while(solFabIter.hasNext()&& (ofVO.getCantidadFabricada()-ofVO.getCantidadDistribuida())>0)
			{
				/* Dentro de cada solicitud debo encontrar
				 * el item que refiera a dicho producto
				 */
				System.out.println("encontrar el item");
				SolicitudFabricacionVO solFab=(SolicitudFabricacionVO) solFabIter.next();
				System.out.println("encontrar el item2");
				SolicitudFabricacionItemVO solFabItem=buscarSolicitudFabricacionItem(solFab,String.valueOf(ofVO.getArticuloid()));
				/* Si alguno de sus items puede ser satisfecho 
				 * por dicha orden de fabricacion*/
				System.out.println("desicion");
				if(solFabItem!=null && solFabItem.getCantidad()-solFabItem.getCantidadFabricada()>0)
				{
					System.out.println("creo orden de rep");
					createOrdenReposicion(solFab,solFabItem,ofVO,orSet);/*creo o modifico la orden de reposicion*/
				}
				
			}
		}
		/*Actualizo los datos en el servidor*/
		bf.crearOrdenesReposicion(orSet,solFabList,ordenes);
		
		return orSet;//---> pedidos pendientes en pantalla principal
		
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
	public boolean tramitarSolicitudesReposicion(Set<OrdenReposicionVO> ordenesReposicionVO,BusinessFacade bf)
	{
		for (Iterator iter = ordenesReposicionVO.iterator(); iter.hasNext();) {
			OrdenReposicionVO element = (OrdenReposicionVO) iter.next();
			enviarSolicitudReposicion(element,bf);
		}
		return true;
	}
	private boolean enviarSolicitudReposicion(OrdenReposicionVO ordenReposicionVO,BusinessFacade bf) {
		// TODO Auto-generated method stub
		try {
			RecibirReposicionService service= new RecibirReposicionServiceLocator();
		
			CentroDistribucionVO centroDist=bf.getCentroDistribucionVOByPK(ordenReposicionVO.getCentroDistribucionid());
			logger.info("Enviando la solicitud de Reposicion al centro de distribucion "+centroDist.getNombre());
			if(centroDist==null){
				logger.error("Error de envio de soicitud de Reposicion, centro de distribucion no encontrado");
	        }else{
	        	
			// Now use the service to get a stub which implements the SDI.
			RecibirReposicion port = service.getRecibirReposicion();
			/* Creo solicitud */
			ReposicionDTO reposicion=new ReposicionDTO();
			reposicion.setCodigo(new Long(1));
			reposicion.setFecha(ordenReposicionVO.getFecha());
			
			Set<ItemReposicionDTO> items=convertItems(ordenReposicionVO.getItems());
			/* Agrego item */
			reposicion.setItems(items);
			
			CentroDistribucionDTO centroDTO=new CentroDistribucionDTO();
			centroDTO.setCodigo(centroDist.getID());
			centroDTO.setNombre(centroDist.getNombre());
			
			logger.info("Comienzo de la transferencia");
			
			reposicion.setCentroDistribucion(centroDTO);
			
			/* convierto a XML*/
			ZaraXMLConverter converter=new ZaraXMLConverter();
			String xml=converter.toXml(reposicion);
			
	        // Make the actual calls
			if(!port.recibirReposicion(xml))
				logger.error("Error durante la transferencia");
			else
				logger.info("Fin de la transferencia");
			}
	        } catch (Exception e) {
	        	logger.error("Error durante el envio de la solicitud:");
	        	logger.error(e.getMessage());
	        	e.printStackTrace();
	        }
		return false;
	}
	private Set<ItemReposicionDTO> convertItems(Set<OrdenReposicionItemVO> items) {
    	Set<ItemReposicionDTO> itemsXML=new HashSet<ItemReposicionDTO>();
    	
    	
    	for (Iterator iter = items.iterator(); iter.hasNext();) {
    		OrdenReposicionItemVO element = (OrdenReposicionItemVO) iter.next();
    		
			/* Item de la solicitud */
			ItemReposicionDTO itemreposicion= new ItemReposicionDTO();
			itemreposicion.setIdArticulo(element.getArticuloId());
			System.out.println(""+String.valueOf(itemreposicion.getIdArticulo()));
			itemreposicion.setCantidad(element.getCantidad().intValue());
			itemsXML.add(itemreposicion);
			}
    	
		return itemsXML;
	}
}