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

import struts.forms.OrdenReposicionForm;
import struts.model.BusinessDelegateFacade;

public class OrdenReposicionAction extends Action {
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();
	
	public OrdenReposicionAction()
	{
//		businessDelegate=new BusinessDelegate();
		
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
  			return mapping.findForward("ordensuccess");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("ordenfailure"));
  		}
		
	}
}
