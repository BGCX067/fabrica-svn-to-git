package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.OrdenReposicionStruts;
import servidor.Recupero1Struts;

public class RecuperoForm extends ActionForm {
	private Collection listaRecupero;

	public Collection getListaRecupero() {
		return listaRecupero;
	}

	public void setListaRecupero(Collection listaRecupero) {
		this.listaRecupero = listaRecupero;
	}
	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{ 
		listaRecupero= new ArrayList();      
    }

	 public Recupero1Struts getBingBong(int index)
	 {
		 while(listaRecupero.size()<=index)
		 {
			 listaRecupero.add(new Recupero1Struts());
		 }
		 return(Recupero1Struts)((ArrayList) listaRecupero).get(index);
	 }
	
	

}
