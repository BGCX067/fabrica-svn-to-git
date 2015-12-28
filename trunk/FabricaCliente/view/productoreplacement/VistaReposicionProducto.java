package view.productoreplacement;

import java.util.Observable;

import controlador.ControladorReposicionProducto;

import businessdelegate.BusinessDelegateFacade;

import view.productoreplacement.ui.ProductReplacementWindow;
import view.rawmaterialform.ui.UIInputProviderRawMaterial;

import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class VistaReposicionProducto extends Vista{
	ProductReplacementWindow uiReposicionProducto;
	
	/**
	 * Instancia e inicia un VistaEditarProducto
	 * @param businessDelegate
	 */
	public VistaReposicionProducto(BusinessDelegateFacade businessDelegateFacade) {
		super(businessDelegateFacade);
	}



	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


}
