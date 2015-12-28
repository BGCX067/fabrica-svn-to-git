package com.ordencompra;

import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.Stateless;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import util.JBossConfig;
import util.ZaraXMLConverterFabrica;

import com.materiaprima.AdministradorMateriaPrima;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaVOCompra;
import com.ordencompra.estado.OrdenCompraEstado;
import com.ordencompra.estado.OrdenCompraItemEstado;

import dtos.ordencompra.OrdenCompraDTO;
import dtos.ordencompra.OrdenCompraItemDTO;

@Stateless
public class AdministradorOrdenCompraBean implements AdministradorOrdenCompra{

	@PersistenceContext(unitName="FABRICA")
	private EntityManager manager;
	
	public OrdenCompra getOrdenCompraByFecha(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenCompra getOrdenCompraByPK(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrdenCompra insertarOrdenCompra(OrdenCompra ordenCompra) {
		manager.persist(ordenCompra);
		return ordenCompra;
	}

	public void crearItem(OrdenCompra ordenCompra, MateriaPrima materia, long cantidad) {
		boolean exists = false;
		
		Iterator<OrdenCompraItem> iterator = ordenCompra.getItems().iterator();
		while (iterator.hasNext()) {
			OrdenCompraItem item = (OrdenCompraItem) iterator.next();
			if (item.getMateriaPrima().getID() == materia.getID())
			{
				exists = true;
				item.setCantidad(item.getCantidad()+ cantidad);
			}
		}
		
		if (!exists)
		{
			ordenCompra.getItems().add(
					new OrdenCompraItem(materia, cantidad,
							findItemEstadoByPK(1)));
		}
	}

	public OrdenCompra getInstancia() {
		OrdenCompra ordenCompra = new OrdenCompra();
		ordenCompra.setFecha(new Date());
		//Estado Pendiente
		ordenCompra.setEstado(findEstadoByPK(1));
		return ordenCompra;
	}

	public OrdenCompraEstado findEstadoByPK(long id) {
		return manager.find(OrdenCompraEstado.class, id);
	}
	
	public OrdenCompraItemEstado findItemEstadoByPK(long id) {
		
		return manager.find(OrdenCompraItemEstado.class, id);
	}

	public OrdenCompra crearCompraFabrica(Set<MateriaPrimaVOCompra> list, AdministradorMateriaPrima administradorMateriaPrima) {
		OrdenCompra ordenCompra = getInstancia();
		if (list.size() == 0)
			return ordenCompra;
		addItems(list, ordenCompra, administradorMateriaPrima);
		
		//aca tengo que insertar esto asi funciona
		OrdenCompraDTO ordenCompraDTO = new OrdenCompraDTO();
		mergeOrdenes(ordenCompraDTO, ordenCompra);
		return insertarOrdenCompra(ordenCompra);
	}

	private void mergeOrdenes(OrdenCompraDTO ordenCompraDTO, OrdenCompra ordenCompra) {
		ordenCompraDTO.setFecha(ordenCompra.getFecha());
		ordenCompraDTO.setID(ordenCompra.getID());
		Set<OrdenCompraItemDTO> items = new HashSet<OrdenCompraItemDTO>();
		Iterator i = ordenCompra.getItems().iterator();
		while (i.hasNext()) {
			OrdenCompraItem element = (OrdenCompraItem) i.next();
			OrdenCompraItemDTO o = new OrdenCompraItemDTO(String.valueOf(element.getMateriaPrima().getCodigo()), element.getCantidad());
			items.add(o);
		}
		ordenCompraDTO.setItems(items);
		enviarMensaje(ordenCompraDTO);
	}

	private void enviarMensaje(OrdenCompraDTO ordenCompraDTO) {
		Hashtable props = new Hashtable();
		
		props.put(InitialContext.INITIAL_CONTEXT_FACTORY , "org.jnp.interfaces.NamingContextFactory");
		props.put(InitialContext.PROVIDER_URL, "jnp://"+JBossConfig.ip+":1099");
		
		try {
//			InitialContext ctx = new InitialContext(props);
			InitialContext ctx = new javax.naming.InitialContext(props);
			
			// buscar la ConnectionFactory en JNDI
			QueueConnectionFactory qfactory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
			
			// buscar la Cola en JNDI
			Queue queue = (Queue) ctx.lookup("queue/proveedorrecepcion");
			
			// crear la connection y la session a partir de la connection
			QueueConnection qCon = qfactory.createQueueConnection();
			QueueSession qSession = qCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			 
			// crear un producer para enviar mensajes usando la session
			QueueSender qSender = qSession.createSender(queue);
			
			
			
			ZaraXMLConverterFabrica converter = new ZaraXMLConverterFabrica();
			
			String msg = converter.toXml(ordenCompraDTO);
			
			
			
			// crear un mensaje de tipo text y setearle el contenido
			TextMessage message = qSession.createTextMessage();
			message.setText(msg);
			
			// enviar el mensaje
			qSender.send(message);
			
		} catch (Exception e) {
			System.out.println("Error al efectuar envio " + e);
		}
		
	}

	private void addItems(Set<MateriaPrimaVOCompra> list, OrdenCompra ordenCompra, AdministradorMateriaPrima administradorMateriaPrima) {
		Iterator<MateriaPrimaVOCompra> i = list.iterator();
		
		while (i.hasNext()) {
			MateriaPrimaVOCompra element = (MateriaPrimaVOCompra) i.next();
			MateriaPrima materiaPrima = administradorMateriaPrima.getMateriaPrimaByPK(element.getID());
			crearItem(ordenCompra, materiaPrima, element.getCantidad());
		}
		
	}

}
