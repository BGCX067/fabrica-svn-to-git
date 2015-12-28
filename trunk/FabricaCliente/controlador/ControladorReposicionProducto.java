package controlador;

import java.util.Map;
import java.util.Set;

import view.productoreplacement.VistaReposicionProducto;
import businessdelegate.BusinessDelegateFacade;

import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;

import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;

public class ControladorReposicionProducto extends Controlador{
	
	public ControladorReposicionProducto(BusinessDelegate businessDelegate,VistaReposicionProducto vista) {
		super(businessDelegate, vista);
		// TODO Auto-generated constructor stub
	}



	public Set<OrdenReposicionVO> getOrdenesReposicion() {
		// TODO Auto-generated method stub
		return ((BusinessDelegateFacade)businessDelegate).getOrdenesReposicion();
	}



	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados() {
		return ((BusinessDelegateFacade)businessDelegate).getOrdenesFabricacionTerminados();
	}



	public Set<OrdenReposicionVO> getOrdenesReposicionPendiente() {
		return ((BusinessDelegateFacade)businessDelegate).getOrdenesReposicionPendiente();
	}



	public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes() {
		return ((BusinessDelegateFacade)businessDelegate).getSolicitudesFabricacionPendientes();
	}



	public void crearOrdenesReposicion(Set<OrdenReposicionVO> orSet, Set<SolicitudFabricacionVO> solFabList, Set<OrdenFabricacionVO> ordenes) {
		((BusinessDelegateFacade)businessDelegate).crearOrdenesReposicion( orSet,  solFabList, ordenes);
	}



	



	

	

	
	

}
