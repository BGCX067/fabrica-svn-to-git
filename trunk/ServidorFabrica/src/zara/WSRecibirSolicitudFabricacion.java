package zara;

import javax.ejb.Local;

@Local
public interface WSRecibirSolicitudFabricacion
{
	public boolean recibirSolicitudFabricacion(String solicitudFabricacion);
}
