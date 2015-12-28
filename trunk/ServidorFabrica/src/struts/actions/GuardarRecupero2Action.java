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

import struts.forms.Recupero2Form;

public class GuardarRecupero2Action extends Action {
	
	public GuardarRecupero2Action()
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
		/*Enviar datos de la coleccion recup al business delegate*/
		
		
			return mapping.findForward("recupero4success");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("recupero4failure"));
  		}
		
	}

}
