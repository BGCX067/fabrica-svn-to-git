package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.articulo.ArticuloStrutsVO;
import com.materiaprima.MateriaPrimaCantidad;
import com.ordenfabricacion.AdministradorOrdenFabricacionBean;

import servidor.MateriaPrimaStruts;
import struts.forms.AltaArticuloForm;
import struts.model.BusinessDelegateFacade;
public class CargarDatosAction extends Action {
	BusinessDelegateFacade businessDelegate= new BusinessDelegateFacade();
	Logger logger = Logger.getLogger(CargarDatosAction.class);
	
	public CargarDatosAction()
	{
		//businessDelegate=new BusinessDelegate();
		
	}

	  @Override
	public ActionForward execute(ActionMapping mapping,	 ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
		  	
		  ActionErrors errors = null;
		  
  		try
  		{
 			
  			//Invoca al proceso de carga y recoge el valor de vuelta
  			//errors = businessDelegate.crearNuevoArticulo(form);
  	        //ACA DEBO PEDIR LOS DATOS A LA BASE DE DATOS PARA CARGAR TODAS LAS MATERIAS PRIMAS
  			//POR DEFECTO EL CONSTRUSCTOR HACE LA CONEXCION CON EL BUSINESS DELEGATE. Solo debo pedir
  			// LOS DATOS Y LLENAR EL FORM  AltaArticuloForm
  			AltaArticuloForm altaArticuloformulario= (AltaArticuloForm) form;
  			AbstractCollection materia=new ArrayList();
  			
  			ArticuloStrutsVO articulo = businessDelegate.getNewArticulo();
  			
  			if (articulo == null)
  			{
  				logger.error("No existe ningun articulo a registrar");
  				return (mapping.findForward("failure"));
  			}
  			
  			Iterator i = articulo.getMateriaPrimas().iterator();
  			while (i.hasNext()) {
				MateriaPrimaCantidad element = (MateriaPrimaCantidad) i.next();
				materia.add(new MateriaPrimaStruts(element.getMateriaPrima().getDescripcion(),"0",String.valueOf(element.getMateriaPrima().getID()),false));
			}
  			
  			
  			altaArticuloformulario.setCantidad(0);
  			altaArticuloformulario.setCodigo(articulo.getCodigo());
  			altaArticuloformulario.setColor(articulo.getColor());
  			altaArticuloformulario.setDescripcion(articulo.getDescripcion());
  			altaArticuloformulario.setFabricarItem(false);
  			altaArticuloformulario.setLinea(articulo.getLinea());
  			altaArticuloformulario.setOrigen(articulo.getOrigen());
  			altaArticuloformulario.setPrecio(new Integer(String.valueOf(articulo.getPvu())));
  			altaArticuloformulario.setSeccion(articulo.getSeccion());
  			altaArticuloformulario.setTalle(articulo.getTalle());
  			altaArticuloformulario.setListaMateria(materia);
  			altaArticuloformulario.setIdarticulo(articulo.getID());
  			return mapping.findForward("showList");
  			/*
  			if (errors==null ) {//Se ejecutó correctamente
  			    System.out.println(" **********  Todo OK ! ");
  				return (mapping.findForward("success"));
  			}
  			else { //Hubo errores
  			    System.out.println(" **********  Errores ! ");
  				return (mapping.findForward("failure"));
  			}
  			*/	
  		}
  		catch (Exception e)
  		{
  			logger.error("Surgio un error procesando el articulo");
  			logger.error(e.toString());
  			return (mapping.findForward("failure"));
  		}
      }
	  

}