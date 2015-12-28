package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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


public class ArticuloFabricarAction extends Action {

	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();

	
	
	public ArticuloFabricarAction()
	{
		
	}
	 @Override
	
	 public ActionForward execute(ActionMapping mapping,	 ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
			  	
			  ActionErrors errors = null;
			  
	  		try
	  		{
	  		
	  			
	  			// guardar en el servidor
	  			/*llamar al business delegate y enviarle el articulo*/
	  				
	  			
	  			return mapping.findForward("articulofabricarsuccess");
	  		}
	  		
		catch (Exception e)
		{
			return (mapping.findForward("articulofabricarfailure"));
		}
   }
}
