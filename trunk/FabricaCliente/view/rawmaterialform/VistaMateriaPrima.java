package view.rawmaterialform;

import java.util.Observable;

import view.rawmaterialform.ui.UIInputProviderRawMaterial;
import businessdelegate.BusinessDelegateFacade;
import framework.vista.Vista;

public class VistaMateriaPrima extends Vista{
UIInputProviderRawMaterial uiMateriaPrima;
	

	public VistaMateriaPrima(BusinessDelegateFacade businessDelegateFacade) {
		super(businessDelegateFacade);
		//this.addControlador(new ControladorMateriaPrima(businessDelegate,this));
	}

	public void actualizar() {
		//uiMateriaPrima.actualizar();
		
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


}
