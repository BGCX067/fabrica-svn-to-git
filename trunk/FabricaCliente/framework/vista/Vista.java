/*
 * Ingenieria de Sistemas II - 1C2007
 * Marzo/2007
 * 
 * Ejercicio 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package framework.vista;

import framework.controlador.Controlador;
import framework.modelo.BusinessDelegate;

public abstract class Vista  {
	BusinessDelegate businessDelegate;
	Controlador controlador;
	
	/**
	 * Instancia e inicia un BaseVista
	 * @param businessDelegate
	 */
	public Vista(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	} 
	
	/**
	 * Setea el controlador a la vista
	 * @param controlador
	 */
	public void addControlador(Controlador controlador){
		this.controlador = controlador;
	}
	
	/**
	 * Obtiene el businessDelegate
	 * @return el businessDelegate
	 */
	public BusinessDelegate getBusinessDelegate() {
		return businessDelegate;
	}
	
	/**
	 * Obtiene el controlador
	 * @return el controlador
	 */
	public Controlador getControlador() {
		return controlador;
	}



}
