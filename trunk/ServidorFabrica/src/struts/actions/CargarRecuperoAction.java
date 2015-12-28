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

import servidor.Recupero1Struts;
import struts.forms.RecuperoForm;
import struts.model.BusinessDelegateFacade;

import com.ordenreposicion.OrdenReposicionVO;

public class CargarRecuperoAction extends Action {
	BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
	public CargarRecuperoAction()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	    RecuperoForm recuperoFormulario= (RecuperoForm) form;
		AbstractCollection recup=new ArrayList();
		recup=(AbstractCollection)recuperoFormulario.getListaRecupero();
		Set<OrdenReposicionVO> reposicion=(Set<OrdenReposicionVO>) businessDelegate.getOrdenesReposicion();
		for (Iterator iter = reposicion.iterator(); iter.hasNext();) {
			OrdenReposicionVO element = (OrdenReposicionVO) iter.next();
			recup.add(new Recupero1Struts(Long.parseLong(element.getID()),element.getCentroDistribucion(),element.getEstado()));
		}
		recuperoFormulario.setListaRecupero(recup);
		
		
			return mapping.findForward("recuperosuccess");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("recuperofailure"));
  		}
		
	}

}
