package struts.forms;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import servidor.ArticuloFabricarStruts;
import servidor.ArticuloNoFabricarStruts;

public class ArticuloFabricarForm extends ActionForm{
	private Collection listaArticuloFabricar;
	private Collection listaArticuloNoFabricar;
	
	public Collection getListaArticuloFabricar() {
		return listaArticuloFabricar;
	}
	public void setListaArticuloFabricar(Collection listaArticuloFabricar) {
		this.listaArticuloFabricar = listaArticuloFabricar;
	}
	public Collection getListaArticuloNoFabricar() {
		return listaArticuloNoFabricar;
	}
	public void setListaArticuloNoFabricar(Collection listaArticuloNoFabricar) {
		this.listaArticuloNoFabricar = listaArticuloNoFabricar;
	}
	
	 public void reset(ActionMapping arg0, HttpServletRequest arg1)
	 { 
		 listaArticuloFabricar= new ArrayList();
		 listaArticuloNoFabricar=new ArrayList();
     }

	 public ArticuloFabricarStruts getBingBong(int index)
	 {
		 while(listaArticuloFabricar.size()<=index)
		 {
			 listaArticuloFabricar.add(new ArticuloFabricarStruts());
		 }
		 return(ArticuloFabricarStruts)((ArrayList) listaArticuloFabricar).get(index);

	 }
	 
	 public ArticuloNoFabricarStruts getBingBongNo(int index)
	 {
		 while(listaArticuloNoFabricar.size()<=index)
		 {
			 listaArticuloNoFabricar.add(new ArticuloNoFabricarStruts());
		 }
		 return(ArticuloNoFabricarStruts)((ArrayList) listaArticuloNoFabricar).get(index);
	 }

}
