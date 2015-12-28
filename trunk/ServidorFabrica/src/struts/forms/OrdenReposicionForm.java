package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.MateriaPrimaStruts;
import servidor.OrdenReposicionStruts;

/**
 * @author usuario
 *
 */
public class OrdenReposicionForm extends ActionForm {
	private Collection listaOrden;

	public Collection getListaOrden() {
		return listaOrden;
	}

	public void setListaOrden(Collection listaOrden) {
		this.listaOrden = listaOrden;
	}
	
	 public void reset(ActionMapping arg0, HttpServletRequest arg1)
	 { 
		 listaOrden= new ArrayList();      
     }

	 public OrdenReposicionStruts getBingBong(int index)
	 {
		 while(listaOrden.size()<=index)
		 {
			 listaOrden.add(new OrdenReposicionStruts());
		 }
		 return(OrdenReposicionStruts)((ArrayList) listaOrden).get(index);
	 }

}
