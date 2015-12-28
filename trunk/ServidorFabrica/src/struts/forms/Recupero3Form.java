package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.Recupero3Struts;

public class Recupero3Form extends ActionForm {
	private Collection listaRecupero3;

	public Collection getListaRecupero3() {
		return listaRecupero3;
	}

	public void setListaRecupero3(Collection listaRecupero3) {
		this.listaRecupero3 = listaRecupero3;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{ 
		listaRecupero3= new ArrayList();      
    }

	 public Recupero3Struts getBingBongR3(int index)
	 {
		 while(listaRecupero3.size()<=index)
		 {
			 listaRecupero3.add(new Recupero3Struts());
		 }
		 return(Recupero3Struts)((ArrayList) listaRecupero3).get(index);
	 }
	
	

}
