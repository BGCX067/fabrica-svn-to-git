package controlador;

import java.util.Map;
import java.util.Set;

import businessdelegate.BusinessDelegateFacade;

import com.articulo.Articulo;
import com.materiaprima.MateriaPrima;

import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class ControladorArticulo extends Controlador{

	public ControladorArticulo(BusinessDelegate delegate, Vista vista) {
		super(delegate, vista);
		// TODO Auto-generated constructor stub
	}



	public Map<Long, MateriaPrima> getMateriasPrimas() {
		return ((BusinessDelegateFacade) businessDelegate).getMateriasPrimas();
	
	}

	public void insertarArticulo(Articulo articuloNuevo, Set<String> centros, boolean fabricacion, long cantidad) {
		((BusinessDelegateFacade) businessDelegate).insertarArticulo(articuloNuevo, centros, fabricacion, cantidad);
		
	}
	
	public void insertarMateriaPrimaCantidad()
	{
		((BusinessDelegateFacade) businessDelegate).insertarMateriaPrimaCantidad();
	}

	public boolean validarCodigo(String text) {
		
		return ((BusinessDelegateFacade) businessDelegate).validarCodigo( text);
	}
	
	

	
}
