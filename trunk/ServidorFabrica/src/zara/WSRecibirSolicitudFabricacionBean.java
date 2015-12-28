package zara;
import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import util.JBossConfig;

import facade.BusinessFacade;


@Stateless
public class WSRecibirSolicitudFabricacionBean implements WSRecibirSolicitudFabricacion{
	BusinessFacade bf =null;
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
	public boolean recibirSolicitudFabricacion(String solicitudFabricacion) {
		 /* Se Inicializa el contexto */
       
      
		inicializarContexto();
        
		
    
        return bf.recibirSolicitud(solicitudFabricacion);
       
	}
}
