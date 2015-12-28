package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import servidor.OrdenReposicionStruts;
import struts.forms.OrdenReposicionForm;
import struts.model.BusinessDelegateFacade;

public class CargarOrdenAction extends Action{
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();
	
	public CargarOrdenAction()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	    OrdenReposicionForm ordenReposicionFormulario= (OrdenReposicionForm) form;
		AbstractCollection orden=new ArrayList();
		orden=(AbstractCollection)ordenReposicionFormulario.getListaOrden();
		orden.add(new OrdenReposicionStruts(1,"tela",4,10,false));
		orden.add(new OrdenReposicionStruts(2,"cuero",3,24,false));
		orden.add(new OrdenReposicionStruts(3,"algodon",6,12,false));
		orden.add(new OrdenReposicionStruts(4,"tela",7,23,false));
		ordenReposicionFormulario.setListaOrden(orden);
		
		
			return mapping.findForward("cargarordensuccess");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("ordenfailure"));
  		}
		
	}
}
		