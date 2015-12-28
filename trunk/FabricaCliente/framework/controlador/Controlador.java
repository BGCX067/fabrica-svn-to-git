/*
 * Ingenieria de Sistemas II - 1C2007
 * Marzo/2007
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package framework.controlador;

import framework.modelo.BusinessDelegate;
import framework.vista.Vista;


public abstract class Controlador {
	/**
	 * Referencia a la vista
	 */
	protected Vista vista;
	
	/**
	 * Referencia al delegado del negocio
	 */
	protected BusinessDelegate businessDelegate;
	
	/**
	 * Instancia e inicia un BaseControlador
	 * @param businessDelegate el businessDelegate
	 * @param vista la vista
	 */
	protected Controlador(BusinessDelegate businessDelegate, Vista vista) {
		this.vista = vista;
		this.businessDelegate = businessDelegate;
		vista.addControlador(this);
	}

	
	/**
	 * Obtiene el delegado del negocio
	 * @return el businessDelegate
	 */
	public BusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}
	
	
	/**
	 * Obtiene la vista del controlador
	 * @return
	 */
	public Vista getVista() {
		return vista;
	}	
}
