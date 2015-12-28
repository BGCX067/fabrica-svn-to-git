package controlador;

import businessdelegate.BusinessDelegateFacade;
import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class ControladorMateriaPrima extends Controlador {

public ControladorMateriaPrima(BusinessDelegate businessDelegate, Vista vista) {
		super(businessDelegate, vista);
		// TODO Auto-generated constructor stub
	}

BusinessDelegateFacade bdf = new BusinessDelegateFacade();

public Long ingresarStockMateriaPrima(String codigo,Long cantidad)
{
	return bdf.ingresarStockMateriaPrima(codigo,cantidad);
}

public String getMateriaPrimaDescripcion(String codigo) {
	// TODO Auto-generated method stub
	return bdf.getMateriaPrimaDescripcion(codigo);
}
	
	
	
	

	
}
