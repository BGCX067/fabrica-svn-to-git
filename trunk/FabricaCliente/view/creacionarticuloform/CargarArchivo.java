package view.creacionarticuloform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import view.common.Mensaje;

public class CargarArchivo {
	
	WindowFabricationArticulo windows;
	public CargarArchivo(WindowFabricationArticulo window)
	{
		this.windows=window;
			
	}
	
	public String getNombreArchivo()
	{
		try
        { 
		String archivo="";
		      
		    	do{
		    		FileDialog fileChooser = new FileDialog(windows.getShell(), SWT.OPEN);
		            fileChooser.setFilterExtensions(new String[]{"*.xml"});
		            archivo = fileChooser.open();
					if (archivo == null)
						Mensaje.mensajeError(windows.getShell(), "Error", "Debe ingresar un archivo XML");
				}while (archivo == null);
		    		
				
		    	
	            return archivo;
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	            return null;
	        }
	 }
		
}
