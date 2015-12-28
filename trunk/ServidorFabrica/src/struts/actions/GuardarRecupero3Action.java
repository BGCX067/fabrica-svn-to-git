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

public class GuardarRecupero3Action extends Action {
	
	public GuardarRecupero3Action()
	{
		
	}

	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
	  	
	  ActionErrors errors = null;
	  try
		{
	     /*NO HAY QUE HACER NADA SOLO LA REQUIERE LA ESPECIFICACION*/
		
		
			return mapping.findForward("recupero6success");
		}
		catch (Exception e)
  		{
  			return (mapping.findForward("recupero6failure"));
  		}
		
	}

}
