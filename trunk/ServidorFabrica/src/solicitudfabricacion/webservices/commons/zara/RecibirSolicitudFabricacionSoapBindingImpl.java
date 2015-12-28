/**
 * RecibirSolicitudFabricacionSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package solicitudfabricacion.webservices.commons.zara;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import zara.commons.webservices.WebServices;
import zara.WSRecibirSolicitudFabricacion;

public class RecibirSolicitudFabricacionSoapBindingImpl implements solicitudfabricacion.webservices.commons.zara.RecibirSolicitudFabricacion{
	 public boolean recibirSolicitudFabricacion(String solicitudFabricacion) throws java.rmi.RemoteException
	    {
	    	try
	    	{
				Context context = new InitialContext();
				WSRecibirSolicitudFabricacion bean = (WSRecibirSolicitudFabricacion)context.lookup(WebServices.SOLICITUD_FABICACION_BEAN);
				
				return bean.recibirSolicitudFabricacion(solicitudFabricacion);
			}
	    	catch (NamingException e)
	    	{
				return false;
			}
	    }
}
