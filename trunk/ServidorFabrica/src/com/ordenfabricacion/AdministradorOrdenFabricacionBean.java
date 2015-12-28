
package com.ordenfabricacion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import struts.actions.CargarSincroAction;

import com.articulo.AdministradorArticulo;
import com.articulo.Articulo;
import com.materiaprima.AdministradorMateriaPrima;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
import com.materiaprima.MateriaPrimaStockVO;
import com.materiaprima.MateriaPrimaVOCompra;
import com.ordenfabricacion.estado.OrdenFabricacionEjecucionEstado;
import com.ordenfabricacion.estado.OrdenFabricacionEstado;
import com.solicitudfabricacion.xml.ArticuloSolicitud;

@Stateless
public class AdministradorOrdenFabricacionBean implements
		AdministradorOrdenFabricacion {

	Logger logger = Logger.getLogger(AdministradorOrdenFabricacionBean.class);
	@PersistenceContext(unitName = "FABRICA")
	private EntityManager manager;

	public OrdenFabricacion getInstancia() {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenFabricacionVO getOrdenFabricacionByFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenFabricacionVO getOrdenFabricacionByPK(long id) {

		return createVO(manager.find(OrdenFabricacion.class, id));

	}

	public OrdenFabricacion getOrdenFabricacionPersistByPK(long id) {

		return manager.find(OrdenFabricacion.class, id);

	}

	public OrdenFabricacion insertarOrdenFabricacion(
			OrdenFabricacion ordenFabricacion) {
		manager.persist(ordenFabricacion);
		return ordenFabricacion;
	}

	public OrdenFabricacionVO createVO(OrdenFabricacion ordenFabricacion) {
		OrdenFabricacionVO vo = new OrdenFabricacionVO();

		if (ordenFabricacion != null) {

			vo.setArticuloid(ordenFabricacion.getArticulo().getID());
			vo.setCantidad(ordenFabricacion.getCantidad());
			vo.setDescripcionArticulo(ordenFabricacion.getArticulo()
					.getDescripcion());
			vo.setEstado(ordenFabricacion.getEstado().getID());
			vo.setEstadoDescripcion(ordenFabricacion.getEstado().getNombre());
			vo.setFecha(ordenFabricacion.getFecha());
			vo.setID(ordenFabricacion.getID());
			vo.setOrdenFabricacionEstadoid(ordenFabricacion.getEstado().getID());
			vo.setCantidadFabricada(ordenFabricacion.getCantidadFabricada());
			return vo;
		}
		return null;

	}

	public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes() {
		String SQL = "SELECT ofa.ID "
				+ "FROM OrdenFabricacion ofa where ofa.estado in (1,2) and (ofa.cantidad-ofa.cantidadFabricada) > 0";
		return createMapPendientes(manager.createQuery(SQL).getResultList());
	}

	private Map<Long, OrdenFabricacionVO> createMapPendientes(List resultList) {
		Map<Long, OrdenFabricacionVO> map = new HashMap<Long, OrdenFabricacionVO>();
		for (int i = 0; i < resultList.size(); i++) {
			long idOriginal = Long.parseLong(resultList.get(i).toString());
			Long id = new Long(idOriginal);
			map.put(id, getOrdenFabricacionByPK(idOriginal));
		}
		return map;
	}

	public OrdenFabricacion insertarOrdenFabricacion(
			OrdenFabricacionVO ordenFabricacion) {
		OrdenFabricacion of = merge(ordenFabricacion);
		manager.persist(of);
		return of;
	}

	public OrdenFabricacion merge(OrdenFabricacionVO ordenFabricacionVO) {
		return getOrdenFabricacionPersistByPK(ordenFabricacionVO.getID());

	}

	public Set<OrdenFabricacionStockVO> validarStock(
			Map<Long, MateriaPrimaStockVO> mapMateriaPrima,
			Map<Long, Articulo> mapArticulo, ArrayList<OrdenFabricacionVO> list) {
		Set<OrdenFabricacionStockVO> fabricacionSet = new HashSet<OrdenFabricacionStockVO>();
		Set<OrdenFabricacionStockVO> pendienteSet = new HashSet<OrdenFabricacionStockVO>();

		for (int i = 0; i < list.size(); i++) {
			validarItemFabricado(mapMateriaPrima, mapArticulo, list.get(i),
					fabricacionSet, pendienteSet);
		}
		fabricacionSet.addAll(pendienteSet);
		return fabricacionSet;
	}

	private void validarItemFabricado(Map<Long, MateriaPrimaStockVO> mapMateriaPrima,
			Map<Long, Articulo> mapArticulo, OrdenFabricacionVO fabricacionVO,
			Set<OrdenFabricacionStockVO> fabricacionSet,
			Set<OrdenFabricacionStockVO> pendienteSet) {
		long cantidad = fabricacionVO.getCantidad()
				- fabricacionVO.getCantidadFabricada();
		Articulo articulo = mapArticulo.get(fabricacionVO.getArticuloid());

		while (cantidad != 0) {
			Iterator i = articulo.getMateriaPrimas().iterator();

			boolean ok = chequearFabricacion(i, mapMateriaPrima);
			if (ok) {
				descontarStock(fabricacionVO, mapArticulo, mapMateriaPrima);
				addItem(fabricacionVO, fabricacionSet);
			} else {
				addItemPendiente(fabricacionVO, cantidad, pendienteSet);
				return;
			}
			cantidad--;
		}
	}

	private void addItemPendiente(OrdenFabricacionVO fabricacionVO,
			long cantidad, Set<OrdenFabricacionStockVO> pendienteSet) {

		OrdenFabricacionStockVO stockVO = validarExistencia(fabricacionVO,
				pendienteSet);

		if (stockVO == null) {
			stockVO = new OrdenFabricacionStockVO(fabricacionVO, cantidad,
					false);
			pendienteSet.add(stockVO);
		} else
			stockVO.setCantidad(stockVO.getCantidad() + cantidad);
	}

	private void addItem(OrdenFabricacionVO fabricacionVO,
			Set<OrdenFabricacionStockVO> fabricacionSet) {

		OrdenFabricacionStockVO stockVO = validarExistencia(fabricacionVO,
				fabricacionSet);

		if (stockVO == null) {
			stockVO = new OrdenFabricacionStockVO(fabricacionVO, 1, true);
			fabricacionSet.add(stockVO);
		} else
			stockVO.setCantidad(stockVO.getCantidad() + 1);

	}

	private OrdenFabricacionStockVO validarExistencia(
			OrdenFabricacionVO fabricacionVO, Set<OrdenFabricacionStockVO> set) {
		Iterator i = set.iterator();
		while (i.hasNext()) {
			OrdenFabricacionStockVO element = (OrdenFabricacionStockVO) i
					.next();
			if (fabricacionVO.getArticuloid() == element.getArticuloid())
				return element;
		}

		return null;
	}

	private boolean chequearFabricacion(Iterator i,
			Map<Long, MateriaPrimaStockVO> mapMateriaPrima) {

		while (i.hasNext()) {
			MateriaPrimaCantidad materiaPrimaCantidad = (MateriaPrimaCantidad) i
					.next();
			MateriaPrimaStockVO stockvo = mapMateriaPrima.get(materiaPrimaCantidad.getMateriaPrima().getID());
			if (materiaPrimaCantidad.getCantidad() > stockvo.getStock())
				return false;
		}
		return true;
	}

	private void descontarStock(OrdenFabricacionVO fabricacionVO,
			Map<Long, Articulo> mapArticulo,
			Map<Long, MateriaPrimaStockVO> mapMateriaPrima) {
		Articulo articulo = mapArticulo.get(fabricacionVO.getArticuloid());
		Iterator i = articulo.getMateriaPrimas().iterator();
		while (i.hasNext()) {
			MateriaPrimaCantidad materiaPrimaCantidad = (MateriaPrimaCantidad) i
					.next();
			MateriaPrimaStockVO materiaPrima = mapMateriaPrima
					.get(materiaPrimaCantidad.getMateriaPrima().getID());
			materiaPrima.setStock(materiaPrima.getStock()
					- materiaPrimaCantidad.getCantidad());
		}
	}

	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion(
			AdministradorArticulo administradorArticulo) {
		Set<OrdenFabricacionEjecucionVO> list = getEjecucionesPendientes(administradorArticulo);

		if (!list.isEmpty())
			return list;
		else
		{
			logger.info("No se registraron productos para sincronizar");
			return null;
		}
	}

	private Set<OrdenFabricacionEjecucionVO> getEjecucionesPendientes(
			AdministradorArticulo administradorArticulo) {
		String SQL = "select ofa from OrdenFabricacionEjecucion ofa where ofa.estado = 1";
		List list = manager.createQuery(SQL).getResultList();
		return chequearFinalizacionFabricacion(list, administradorArticulo);
	}

	private Set<OrdenFabricacionEjecucionVO> chequearFinalizacionFabricacion(
			List list, AdministradorArticulo administradorArticulo) {
		
		Set<OrdenFabricacionEjecucionVO> terminados = new HashSet<OrdenFabricacionEjecucionVO>();
		for (int i = 0; i < list.size(); i++) {
			OrdenFabricacionEjecucion item = (OrdenFabricacionEjecucion) list.get(i);
			OrdenFabricacion orden = item.getOrdenFabricacion();
			//long tiempoFabricacionItem = administradorArticulo.getUltimoTiempoFabricacion(orden.getArticulo());
			long tiempoFabricacionItem=0;
			if (comprobarTiempo((int) tiempoFabricacionItem, item.getFecha())) {
				addItemFinalizado(terminados, item, orden);
				// Obtengo el estado ejecutado
				item.setEstado(findEstadoEjecucionByPK(2));
				updateEjecucion(item);
				saveOrden(orden, item);
			}
		}
			
		
		return terminados;
	}

	private void saveOrden(OrdenFabricacion orden,
			OrdenFabricacionEjecucion item) {
		orden.setFabricado(orden.getFabricado() + item.getCantidad());
		if (orden.getFabricado() == orden.getCantidadFabricada())
			// finalizo la orden
			orden.setEstado(findEstadoByPK(3));

		actualizarOrdenFabricacion(orden);

	}

	private void updateEjecucion(OrdenFabricacionEjecucion item) {
		manager.merge(item);
	}

	private void saveEjecucion(OrdenFabricacionEjecucion item) {
		manager.persist(item);
	}

	private void addItemFinalizado(Set<OrdenFabricacionEjecucionVO> terminados,
			OrdenFabricacionEjecucion item, OrdenFabricacion orden) {
		
		logger.info("Producto fabricado ---> "+item.getOrdenFabricacion().getArticulo().getDescripcion()+" Cantidad: "+item.getCantidad());
		terminados.add(createOrdenFabricacionEjecucionVO(item, orden));

	}

	private OrdenFabricacionEjecucionVO createOrdenFabricacionEjecucionVO(
			OrdenFabricacionEjecucion item, OrdenFabricacion orden) {

		return new OrdenFabricacionEjecucionVO(orden.getID(), orden.getFecha(),
				orden.getArticulo().getID(), orden.getArticulo()
						.getDescripcion(), item.getCantidad());
	}

	private boolean comprobarTiempo(int tiempoFabricacionItem, Date fecha) {
		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		calendar.setTime(fecha);
		int hours = fecha.getHours();
		if (hours > 12)
			hours = hours - 12;
		calendar.set(Calendar.HOUR, hours + tiempoFabricacionItem);

		if (calendar.getTime().before(today))
			return true;
		else
			return false;
	}

	private long getUltimoTiempo(Set<MateriaPrimaCantidad> materiaPrimas,
			Map<Long, MateriaPrima> mapMateriaPrima) {
		Iterator<MateriaPrimaCantidad> iter = materiaPrimas.iterator();

		long tiempo = 0;

		while (iter.hasNext()) {
			MateriaPrimaCantidad materiaPrimaCantidad = (MateriaPrimaCantidad) iter
					.next();
			MateriaPrima materiaPrima = mapMateriaPrima
					.get(materiaPrimaCantidad.getMateriaPrima().getID());
			if (tiempo < materiaPrima.getTiempoFabricacion())
				tiempo = materiaPrima.getTiempoFabricacion();
		}
		System.out.println(tiempo);
		return tiempo;
	}

	public OrdenFabricacionEjecucionEstado findEstadoEjecucionByPK(long id) {

		return manager.find(OrdenFabricacionEjecucionEstado.class, id);
	}

	public OrdenFabricacionEstado findEstadoByPK(long id) {
		return manager.find(OrdenFabricacionEstado.class, id);
	}

	public Set<MateriaPrimaVOCompra> inicializarFabricacion(
			AdministradorMateriaPrima administradorMateriaPrima,
			AdministradorArticulo administradorArticulo,
			ArrayList<OrdenFabricacionVO> list) {
		Set<MateriaPrimaVOCompra> listCompras = new HashSet<MateriaPrimaVOCompra>();
		for (int i = 0; i < list.size(); i++) {
			OrdenFabricacionVO ordenFabricacion = list.get(i);
			fabricarOrden(ordenFabricacion, administradorArticulo,
					administradorMateriaPrima,listCompras);
		}
		
		
		return listCompras;
	}

	private void fabricarOrden(OrdenFabricacionVO ordenFabricacion,
			AdministradorArticulo administradorArticulo,
			AdministradorMateriaPrima administradorMateriaPrima, Set<MateriaPrimaVOCompra> listCompras) {
		Articulo articulo = administradorArticulo
				.getArticuloByPK(ordenFabricacion.getArticuloid());

		if (fabricacionOK(ordenFabricacion, articulo,
				administradorMateriaPrima))
			fabricarOrdenCompleta(ordenFabricacion, administradorMateriaPrima);
		else
			fabricarOrdenParcial(ordenFabricacion, administradorMateriaPrima, listCompras);
	}

	private void fabricarOrdenParcial(OrdenFabricacionVO ordenFabricacionVO,
			AdministradorMateriaPrima administradorMateriaPrima, Set<MateriaPrimaVOCompra> listCompras) {
		OrdenFabricacion ordenFabricacion = merge(ordenFabricacionVO);

		long cantidadAFabricar = ordenFabricacion.getCantidad()
				- ordenFabricacion.getCantidadFabricada();
		boolean stock = true;
		long cantidadFabricada = 0;

		while (cantidadAFabricar > 0 && stock == true) {
			stock = chequearStockFabricacionParcial(ordenFabricacion
					.getArticulo().getMateriaPrimas(),
					administradorMateriaPrima, listCompras, cantidadAFabricar - cantidadFabricada);
			if (stock)
				cantidadFabricada++;

		}

		if (cantidadFabricada > 0) {
			fabricarItem(ordenFabricacion, cantidadFabricada);
			crearItemSincronizador(ordenFabricacion, cantidadFabricada);
		
		}
	}

	


	private void fabricarItem(OrdenFabricacion ordenFabricacion,
			long cantidadFabricada) {
		ordenFabricacion.setCantidadFabricada(ordenFabricacion
				.getCantidadFabricada()
				+ cantidadFabricada);
		ordenFabricacion.setEstado(findEstadoByPK(2));
		// actualizo la orden
		actualizarOrdenFabricacion(ordenFabricacion);

	}

	private boolean chequearStockFabricacionParcial(
			Set<MateriaPrimaCantidad> materiaPrimas,
			AdministradorMateriaPrima administradorMateriaPrima, Set<MateriaPrimaVOCompra> listCompras, long cantidadTomada) {
		Iterator<MateriaPrimaCantidad> iter = materiaPrimas.iterator();
		boolean flag=true;
		while (iter.hasNext()) {
			MateriaPrimaCantidad element = (MateriaPrimaCantidad) iter.next();
			MateriaPrima materiaPrima = administradorMateriaPrima
					.getMateriaPrimaByPK(element.getMateriaPrima().getID());
			if (element.getCantidad() > materiaPrima.getStock())
			{
				addItemCompra(listCompras, element.getCantidad()*cantidadTomada, materiaPrima);
				flag= false;
			}
		}
		if (flag == true)
			descontarStockMateriasPrimas(administradorMateriaPrima, materiaPrimas);
		return flag;
	}
	

	private void descontarStockMateriasPrimas(
			AdministradorMateriaPrima administradorMateriaPrima, Set<MateriaPrimaCantidad> materiaPrimas) {
		Iterator<MateriaPrimaCantidad> iter = materiaPrimas.iterator();
		
		while (iter.hasNext()) {
			MateriaPrimaCantidad element = (MateriaPrimaCantidad) iter.next();
			MateriaPrima materiaPrima = administradorMateriaPrima.getMateriaPrimaByPK(element.getMateriaPrima().getID());
			materiaPrima.setStock(materiaPrima.getStock()-element.getCantidad());
		}
	}

	private void addItemCompra(Set<MateriaPrimaVOCompra> listCompras, long cantidadNecesaria, MateriaPrima materiaPrima) {
		long cantidad = cantidadNecesaria - materiaPrima.getStock();
		Iterator i = listCompras.iterator();
		while (i.hasNext()) {
			MateriaPrimaVOCompra element = (MateriaPrimaVOCompra) i.next();
			if (element.getID() == materiaPrima.getID())
			{
				element.setCantidad(element.getCantidad()+cantidad);
				return;
			}
		}
		listCompras.add(new MateriaPrimaVOCompra(materiaPrima.getID(), cantidad));
	}



	private void fabricarOrdenCompleta(OrdenFabricacionVO ordenFabricacionVO,
			AdministradorMateriaPrima administradorMateriaPrima) {
		OrdenFabricacion ordenFabricacion = merge(ordenFabricacionVO);
		ordenFabricacion.setCantidadFabricada(ordenFabricacionVO.getCantidad()
				- ordenFabricacionVO.getCantidadFabricada());
		ordenFabricacion.setEstado(findEstadoByPK(2));
		// actualizo la orden
		actualizarOrdenFabricacion(ordenFabricacion);

		// mando el item al sincronizador
		crearItemSincronizador(ordenFabricacion, ordenFabricacionVO
				.getCantidad()
				- ordenFabricacionVO.getCantidadFabricada());

		Iterator<MateriaPrimaCantidad> iter = ordenFabricacion.getArticulo()
				.getMateriaPrimas().iterator();
		while (iter.hasNext()) {
			// Descuento el Stock
			MateriaPrimaCantidad element = (MateriaPrimaCantidad) iter.next();
			MateriaPrima materiaPrima = administradorMateriaPrima
					.getMateriaPrimaByPK(element.getMateriaPrima().getID());
			materiaPrima.setStock(materiaPrima.getStock()
					- element.getCantidad()
					* (ordenFabricacionVO.getCantidad() - ordenFabricacionVO
							.getCantidadFabricada()));
			administradorMateriaPrima.modificarMateriaPrima(materiaPrima);
		}
	}

	private void crearItemSincronizador(OrdenFabricacion ordenFabricacion,
			long cantidad) {
		OrdenFabricacionEjecucion ordenFabricacionEjecucion = new OrdenFabricacionEjecucion(
				ordenFabricacion, cantidad, new Date(),
				findEstadoEjecucionByPK(1));
		saveEjecucion(ordenFabricacionEjecucion);
	}

	private boolean fabricacionOK(OrdenFabricacionVO ordenFabricacion,
			Articulo articulo,
			AdministradorMateriaPrima administradorMateriaPrima) {
		boolean resultado = true;
		Iterator<MateriaPrimaCantidad> iter = articulo.getMateriaPrimas()
				.iterator();
		while (iter.hasNext()) {
			MateriaPrimaCantidad element = (MateriaPrimaCantidad) iter.next();
			MateriaPrima materiaPrima = administradorMateriaPrima
					.getMateriaPrimaByPK(element.getMateriaPrima().getID());
			long cantidadResultado = materiaPrima.getStock()
					- ordenFabricacion.getCantidad() * element.getCantidad();
			if (cantidadResultado < 0) {
				resultado = false;
			}
		}
		return resultado;
	}

	

	public OrdenFabricacion actualizarOrdenFabricacion(
			OrdenFabricacion ordenFabricacion) {
		manager.merge(ordenFabricacion);
		return ordenFabricacion;
	}
	
	
	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados() {
		String SQL = "SELECT ofa.ID "
				+ "FROM OrdenFabricacion ofa where ofa.estado in (2,3) " +
						"AND (ofa.cantidadFabricada-ofa.distribuido)>0";
		return createSetFabricados(manager.createQuery(SQL).getResultList());
	}

	private Set<OrdenFabricacionVO> createSetFabricados(List resultList) {
		Set<OrdenFabricacionVO> set = new HashSet<OrdenFabricacionVO>();
		for (int i = 0; i < resultList.size(); i++) {
			String id=resultList.get(i).toString();
			long idOriginal = Long.parseLong(id);
			OrdenFabricacionVO ofVO= getOrdenFabricacionByPK(idOriginal);
			set.add(ofVO);
		}
		return set;
	}

	public void fabricarSolicitud(Set<ArticuloSolicitud> items, AdministradorArticulo administradorArticulo) {
		Iterator<ArticuloSolicitud> iter = items.iterator();
		while (iter.hasNext()) {
			ArticuloSolicitud element = (ArticuloSolicitud) iter.next();
			Articulo articulo = administradorArticulo.getArticuloByPK(Long.parseLong(element.getCodigo()));
			OrdenFabricacion orden = getOrdenByArticulo(articulo.getID());
			if (orden == null)
			{
				orden = new OrdenFabricacion();
				orden.setArticulo(articulo);
				orden.setCantidad(element.getCantidad());
				orden.setCantidadFabricada(0);
				orden.setDistribuido(0);
				orden.setEstado(findEstadoByPK(1));
				orden.setFabricado(0);
				orden.setFecha(new Date());
				insertarOrdenFabricacion(orden);
			}
			else
			{
				orden.setCantidad(orden.getCantidad()+element.getCantidad());
				actualizarOrdenFabricacion(orden);
			}
		}
		
	}

	private OrdenFabricacion getOrdenByArticulo(long id) {
		String SQL = "Select ofa FROM OrdenFabricacion ofa where ofa.estado=1 and ofa.articulo = "+id;
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return (OrdenFabricacion)list.get(0);
		return null;
	}

	public void crearOrden(Articulo articulo, AdministradorArticulo administrador, long cantidad) {
		OrdenFabricacion orden = new OrdenFabricacion();
		orden.setArticulo(administrador.getArticuloByPK(articulo.getID()));
		orden.setCantidad(cantidad);
		orden.setCantidadFabricada(0);
		orden.setDistribuido(0);
		orden.setFecha(new Date());
		orden.setFabricado(0);
		orden.setEstado(findEstadoByPK(1));
		insertarOrdenFabricacion(orden);
	}


}
