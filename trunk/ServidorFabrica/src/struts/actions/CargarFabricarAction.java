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

import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;

import servidor.ArticuloFabricarStruts;
import servidor.ArticuloNoFabricarStruts;
import servidor.BusquedaOrdenesStruts;
import struts.forms.BusquedaOrdenesForm;
import struts.model.BusinessDelegateFacade;






public class CargarFabricarAction extends Action {

	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();

	
	public CargarFabricarAction()
	{
//		businessDelegate=new BusinessDelegate();
	}
	
	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  
		try
		{
			BusquedaOrdenesForm busquedaOrdenesFormulario= (BusquedaOrdenesForm) form;
  			
  			AbstractCollection busquedaOrdenesFab=new ArrayList();
  			busquedaOrdenesFab=(AbstractCollection) busquedaOrdenesFormulario.getListaBusquedaOrdenes();
  			
  			Map<Long, OrdenFabricacionVO> ordenesPendientes= businessDelegate.getOrdenesFabricacionPendientes();
  			
  			
  			//aca tengo que obtener lo de la grilla

  			Iterator iter = busquedaOrdenesFab.iterator();
  			
  			ArrayList<OrdenFabricacionVO> ordenesSeleccionadas = new ArrayList<OrdenFabricacionVO>();
  			
  			while (iter.hasNext()) {
				BusquedaOrdenesStruts element = (BusquedaOrdenesStruts) iter.next();
				if (element.isChk())
				{	
					ordenesSeleccionadas.add(ordenesPendientes.get(new Long(element.getId())));
				}
			}
  			
  			Set<OrdenFabricacionStockVO> list = businessDelegate.validarStock(ordenesSeleccionadas);
  			// aca termina el obtener de la grilla
  			
  			AbstractCollection articuloFab=new ArrayList();
  			AbstractCollection articuloNoFab=new ArrayList();
  			
  			Iterator<OrdenFabricacionStockVO> i = list.iterator();
  			
  			while (i.hasNext()) {
  				OrdenFabricacionStockVO element = (OrdenFabricacionStockVO) i.next();
  				if (element.isFabricado())
  					articuloFab.add(new ArticuloFabricarStruts(element.getDescripcionArticulo(),element.getCantidad(),element.getArticuloid(),true));
  				else
  					articuloNoFab.add(new ArticuloNoFabricarStruts(element.getDescripcionArticulo(),element.getCantidad(),element.getArticuloid(),true));
  			}
  			
  			busquedaOrdenesFormulario.setListaArticuloFabricar(articuloFab);
  			busquedaOrdenesFormulario.setListaArticuloNoFabricar(articuloNoFab);
  			businessDelegate.inicializarFabricacion(ordenesSeleccionadas);
  			return mapping.findForward("articulofabricarsuccess");
		}
		catch (Exception e)
		{
			return (mapping.findForward("articulofabricarfailure"));
		}
	}
  	
}