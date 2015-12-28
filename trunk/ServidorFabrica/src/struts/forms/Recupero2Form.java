package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.Recupero2Struts;

public class Recupero2Form extends ActionForm {
	private Collection listaRecupero2;

	public Collection getListaRecupero2() {
		return listaRecupero2;
	}

	public void setListaRecupero2(Collection listaRecupero2) {
		this.listaRecupero2 = listaRecupero2;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{ 
		listaRecupero2= new ArrayList();      
    }

	 public Recupero2Struts getBingBongR2(int index)
	 {
		 while(listaRecupero2.size()<=index)
		 {
			 listaRecupero2.add(new Recupero2Struts());
		 }
		 return(Recupero2Struts)((ArrayList) listaRecupero2).get(index);
	 }
	
	

}
