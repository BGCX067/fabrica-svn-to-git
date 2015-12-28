package legacy;

import java.util.Hashtable;
import java.util.Iterator;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import util.JBossConfig;
import util.ZaraXMLConverterFabrica;
import zara.commons.dtos.articulo.ArticuloDTO;

import com.articulo.Articulo;

import dtos.materiaprima.MateriaPrimaDTO;
import dtos.materiaprima.MateriaPrimaItemDTO;
import facade.BusinessFacade;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
		@ActivationConfigProperty(propertyName="destination", propertyValue="queue/proveedor")
})

public class ProcesarMateriaPrimaBean implements MessageDrivenBean, MessageListener {

	private BusinessFacade bf=null;
	
	public ProcesarMateriaPrimaBean() {
		// TODO Auto-generated constructor stub
	}

	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	public void setMessageDrivenContext(MessageDrivenContext arg0)
		throws EJBException {
		// TODO Auto-generated method stub

	}

	public void onMessage(Message arg0) {
		// TODO Auto-generated method stub
		TextMessage msg = null;
		Logger logger = Logger.getLogger(ProcesarMateriaPrimaBean.class);
		try {
			if (arg0 instanceof TextMessage) {
				msg = (TextMessage) arg0;
				
				ZaraXMLConverterFabrica xmlConverter = new ZaraXMLConverterFabrica();
				
				MateriaPrimaDTO materiaPrimaDTO = (MateriaPrimaDTO) xmlConverter.fromXml(msg.getText());
				Iterator i = materiaPrimaDTO.getMateriasPrimas().iterator();
				 
				inicializarContexto();
				
				while (i.hasNext())
				{
					MateriaPrimaItemDTO item = (MateriaPrimaItemDTO) i.next(); 
					bf.ingresarStockMateriaPrima(item.getCodigo().toString(), item.getCantidad());
				}
				logger.info("MESSAGE BEAN: Mensaje recibido: "+ msg.getText() );
			} else {
				logger.error("MESSAGE BEAN: Mensaje de tipo incorrecto ");
			}
				
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void crearAtributosArticulo(ArticuloDTO articuloDTO, Articulo articulo) {
		articulo.setCodigo(articuloDTO.getCodigo().toString());
		articulo.setColor(articuloDTO.getColor());
		articulo.setDescripcion(articuloDTO.getDescripcion());
		articulo.setLinea(articuloDTO.getLinea());
		articulo.setNuevo("true");
		articulo.setOrigen(articuloDTO.getOrigen());
		articulo.setPvu(articuloDTO.getPvu().longValue());
		articulo.setSeccion(articuloDTO.getSeccion());
		articulo.setTalle(articuloDTO.getTalle());
	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub
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
}
