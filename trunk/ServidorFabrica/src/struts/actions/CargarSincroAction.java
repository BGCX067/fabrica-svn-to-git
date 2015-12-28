package struts.actions;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.forms.SincroForm;
import struts.model.BusinessDelegateFacade;

import com.materiaprima.AdministradorMateriaPrimaBean;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;

public class CargarSincroAction extends Action {
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();

	public CargarSincroAction()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	    SincroForm sincroFormulario= (SincroForm) form;
	    /*TEST */ /*Ir al servidor y traer los datos */
	    
	    Set<OrdenFabricacionEjecucionVO> list = businessDelegate.chequearEjecucion();
		
	    
	    
		
	
			return mapping.findForward("success");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("failure"));
  		}
		
	}
	
	private void actualizarPanel(Set<OrdenFabricacionEjecucionVO> list, SincroForm form) {
		
		
		StringBuffer buffer = new StringBuffer("");
		buffer.append(new Date().toString());
		
		
		buffer.append("\r\n");

		if (list == null)
			buffer.append("No hay nada para actualizar");
		else {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				OrdenFabricacionEjecucionVO vo = (OrdenFabricacionEjecucionVO) iter
						.next();
				buffer.append("Actualizacion ---->" + vo.toString() + " \r\n");
			}

		}

	}

}
