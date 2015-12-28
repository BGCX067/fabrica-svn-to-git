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

import servidor.Recupero1Struts;
import struts.forms.RecuperoForm;

public class GuardarRecuperoAction extends Action {
	
	public GuardarRecuperoAction()
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
		
			return mapping.findForward("recupero2success");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("recupero2failure"));
  		}
	}
}
