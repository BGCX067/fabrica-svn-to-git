package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import servidor.BusquedaOrdenesStruts;
import struts.forms.BusquedaOrdenesForm;
import struts.model.BusinessDelegateFacade;

import com.ordenfabricacion.OrdenFabricacionVO;

public class CargarBusquedaOrdenesAction extends Action {
	
	public CargarBusquedaOrdenesAction()
	{
		
	}
	 @Override
	
	 public ActionForward execute(ActionMapping mapping,	 ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
			  
		 	BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
			  ActionErrors errors = null;
			  
	  		try
	  		{
	  			BusquedaOrdenesForm busquedaOrdenesFormulario= (BusquedaOrdenesForm) form;
	  			
	  			AbstractCollection busquedaFab=new ArrayList();
	  			busquedaFab=(AbstractCollection) busquedaOrdenesFormulario.getListaBusquedaOrdenes();
	  			/*Articulos pruebas*/
	  			
	  			Map<Long, OrdenFabricacionVO> ordenesPendientes= businessDelegate.getOrdenesFabricacionPendientes();
	  			Iterator<OrdenFabricacionVO> i = ordenesPendientes.values().iterator();
	  			
	  			while (i.hasNext()) {
	  				OrdenFabricacionVO vo = (OrdenFabricacionVO) i.next();
	  				busquedaFab.add(new BusquedaOrdenesStruts(false,vo.getID(),vo.getDescripcionArticulo(),vo.getEstadoDescripcion(),vo.getCantidad(),vo.getCantidadFabricada(),vo.getCantidad()-vo.getCantidadFabricada(),vo.getCantidadDistribuida()));
	  			}
	  			
	  			busquedaOrdenesFormulario.setListaBusquedaOrdenes(busquedaFab);
	  			/*llamar al business delegate y enviarle el articulo*/
	  		//	System.out.println("LLEGO HASTA ENVIO DEL FORMULARIO");
	  			return mapping.findForward("busquedaordenessuccess");
	  		}
	  		
		catch (Exception e)
		{
			return (mapping.findForward("busquedaordenesfailure"));
		}
   }
	

}
