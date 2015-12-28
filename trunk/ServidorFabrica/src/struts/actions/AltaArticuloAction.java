package struts.actions;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import servidor.MateriaPrimaStruts;
import struts.forms.AltaArticuloForm;
import struts.model.BusinessDelegateFacade;

import com.articulo.Articulo;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
public class AltaArticuloAction extends Action {
	BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
	Logger logger = Logger.getLogger(AltaArticuloAction.class);
	


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
  			
  			Articulo articuloNuevo = businessDelegate.getArticuloByPK(altaArticuloformulario.getIdarticulo());
  			
  			if (articuloNuevo ==null)
  			{
  				logger.info("No existen articulos para actualizar");
  				return (mapping.findForward("failure"));
  			}
  			
  			try
  			{
	  			articuloNuevo.setCodigo(altaArticuloformulario.getCodigo());
				articuloNuevo.setColor(altaArticuloformulario.getColor());
				articuloNuevo.setDescripcion(altaArticuloformulario.getDescripcion());
				articuloNuevo.setOrigen(altaArticuloformulario.getOrigen());
				articuloNuevo.setPvu(altaArticuloformulario.getPrecio());
				articuloNuevo.setSeccion(altaArticuloformulario.getSeccion());
				articuloNuevo.setTalle(altaArticuloformulario.getTalle());
				articuloNuevo.setLinea(altaArticuloformulario.getLinea());
				articuloNuevo.setNuevo("false");
				
				Set<MateriaPrimaCantidad> set = new HashSet<MateriaPrimaCantidad>();
				
	  			AbstractCollection materia=new ArrayList();
	  			materia=(AbstractCollection) altaArticuloformulario.getListaMateria();
	  			Iterator iter = materia.iterator();
	  			Map<Long, MateriaPrima> materiasPrimas = getMateriasPrimas();
	  			while (iter.hasNext()) {
					MateriaPrimaStruts element = (MateriaPrimaStruts) iter.next();
					if (element.isChk())
					{	
						MateriaPrimaCantidad mpcantidad = new MateriaPrimaCantidad();
						mpcantidad.setMateriaPrima(materiasPrimas.get(Long.valueOf(element.getIden())));
						mpcantidad.setID(Long.valueOf(element.getIden()));
						mpcantidad.setCantidad(Long.valueOf(element.getCantidad()));
						set.add(mpcantidad);
					}
				}
	  			articuloNuevo.setMateriaPrimas(set);
  			}catch(Exception e)
  			{
  				logger.error("Se produjo un error durante la carga del articulo, por favor verifique la carga de datos");
  	  			return (mapping.findForward("failure"));
  			}
  			System.out.println("Antes de grabar");
  			modificarArticulo(articuloNuevo, altaArticuloformulario.isFabricarItem(), altaArticuloformulario.getCantidad());
  			System.out.println("Despues de grabar");
  			return mapping.findForward("success");
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
				logger.error("Se produjo un error durante la carga del articulo");
				logger.error(e);
				return (mapping.findForward("failure"));
  		}
      }
	  
		public void modificarArticulo(Articulo articuloNuevo,  boolean fabricacion, long cantidad) {
			
			businessDelegate.modificarArticulo(articuloNuevo,  fabricacion, cantidad);
		}
		
		public Map<Long, MateriaPrima> getMateriasPrimas() {
			return businessDelegate.getMateriasPrimas();
		}
	  

}
