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

import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionItemVO;
import com.ordenreposicion.OrdenReposicionVO;

import servidor.Recupero2Struts;
import servidor.Recupero3Struts;
import struts.forms.Recupero2Form;
import struts.forms.Recupero3Form;
import struts.model.BusinessDelegateFacade;

public class CargarRecupero3Action  extends Action {
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();
	public CargarRecupero3Action()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	    Recupero3Form recupero3Formulario= (Recupero3Form) form;
		AbstractCollection recup=new ArrayList();
		recup=(AbstractCollection)recupero3Formulario.getListaRecupero3();
		
		Set<OrdenReposicionVO> ordenesReposicion=(Set<OrdenReposicionVO>) businessDelegate.actualizarOrdenReposicion();
		
		for (Iterator iter = ordenesReposicion.iterator(); iter.hasNext();) {
			OrdenReposicionVO element = (OrdenReposicionVO) iter.next();
			for (Iterator iterator = element.getItems().iterator(); iterator.hasNext();) {
				OrdenReposicionItemVO elementItem = (OrdenReposicionItemVO) iterator.next();
				recup.add(new Recupero3Struts(element.getCentroDistribucion(),elementItem.getArticulo(),elementItem.getCantidad().intValue()));
				
			}
			
		}
		
		/*Setea el array en el formulario */
		recupero3Formulario.setListaRecupero3(recup);
		
		
			return mapping.findForward("recupero5success");
		}
		catch (Exception e)
  		{
			System.out.println(e.getMessage());

			System.out.println("-------------------------------\n"+e.getStackTrace());
			return (mapping.findForward("recupero5failure"));
  		}
		
	}

}
