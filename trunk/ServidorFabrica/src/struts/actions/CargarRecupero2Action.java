package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import servidor.Recupero2Struts;
import struts.forms.Recupero2Form;
import struts.model.BusinessDelegateFacade;

import com.ordenfabricacion.OrdenFabricacionVO;

public class CargarRecupero2Action extends Action {
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();
	public CargarRecupero2Action()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	    Recupero2Form recupero2Formulario= (Recupero2Form) form;
		AbstractCollection recup=new ArrayList();
		recup=(AbstractCollection)recupero2Formulario.getListaRecupero2();
		
		Set<OrdenFabricacionVO> ordenesFabricacion=(Set<OrdenFabricacionVO>) businessDelegate.getOrdenesFabricacionTerminados();
		for (Iterator iter = ordenesFabricacion.iterator(); iter.hasNext();) {
			OrdenFabricacionVO element = (OrdenFabricacionVO) iter.next();
			recup.add(new Recupero2Struts(element.getID(),element.getArticuloid(),element.getDescripcionArticulo(),element.getCantidad()));
			
		}
		
		recupero2Formulario.setListaRecupero2(recup);
		
		
			return mapping.findForward("recupero3success");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("recupero3failure"));
  		}
		
	}

}
