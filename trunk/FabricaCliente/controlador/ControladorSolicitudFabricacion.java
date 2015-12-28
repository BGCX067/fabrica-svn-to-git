package controlador;

import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

import businessdelegate.BusinessDelegateFacade;

public class ControladorSolicitudFabricacion extends Controlador{
	
	
	
	public ControladorSolicitudFabricacion(BusinessDelegate businessDelegate, Vista vista) {
		super(businessDelegate, vista);
		// TODO Auto-generated constructor stub
	}

	public void tramitarRecepcionSolicitud(SolicitudFabricacionXML solicitud)
	{
		((BusinessDelegateFacade) businessDelegate).tramitarRecepcionSolicitud(solicitud);
	}

	

	
}
