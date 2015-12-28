package controlador;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import businessdelegate.BusinessDelegateFacade;

import com.ordencompra.OrdenCompra;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;

import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class ControladorFabricacion extends Controlador{
	
		
		
	
	public ControladorFabricacion(BusinessDelegate businessDelegate, Vista vista) {
		super(businessDelegate, vista);
		// TODO Auto-generated constructor stub
	}


	public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes() {
		return ((BusinessDelegateFacade) businessDelegate).getOrdenesFabricacionPendientes();
	}


	public void getOrdenFabricacionByPK(Long id) {
		((BusinessDelegateFacade) businessDelegate).getOrdenFabricacionByPK(id);
		
	}

	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion() {
		
		return ((BusinessDelegateFacade) businessDelegate).chequearEjecucion();
	}

	public Set<OrdenFabricacionStockVO> validarStock(ArrayList<OrdenFabricacionVO> list)
	{
		return ((BusinessDelegateFacade) businessDelegate).validarStock(list);
	}
	
	public OrdenCompra inicializarFabricacion(ArrayList<OrdenFabricacionVO> list)
	{
		return ((BusinessDelegateFacade) businessDelegate).inicializarFabricacion(list);
	}

}
