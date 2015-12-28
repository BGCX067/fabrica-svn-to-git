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

import servidor.ArticuloFabricarStruts;
import servidor.ArticuloNoFabricarStruts;
import servidor.BusquedaOrdenesStruts;
import struts.forms.BusquedaOrdenesForm;
import struts.model.BusinessDelegateFacade;

import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;

public class BusquedaOrdenesAction extends Action{
	
	public BusquedaOrdenesAction()
	{
		
	}
	 @Override
	
	 public ActionForward execute(ActionMapping mapping,	 ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
			  	
			  ActionErrors errors = null;
			BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
			
			  try
		  		{
		  			
		  			
		  			//LLAMO AL SERVIDOR PARA GRABAR GRILLAS
		  			/*llamar al business delegate y enviarle el articulo*/
		  			System.out.println("LLEGO HASTA ENVIO DEL FORMULARIO");
		  			return mapping.findForward("busquedaordenessuccess");
		  		}
		  		
			catch (Exception e)
			{
				return (mapping.findForward("busquedaordenesfailure"));
			}
	   }
		

	}
