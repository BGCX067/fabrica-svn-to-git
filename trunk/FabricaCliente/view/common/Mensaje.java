package view.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class Mensaje {
	

	public static void mensajeInformacion(Shell shell,String titulo,String mensaje) {
		 MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	        
	        messageBox.setText(titulo);
	        messageBox.setMessage(mensaje);
	        messageBox.open();
	}
	public static boolean mensajeDecision(Shell shell,String titulo,String mensaje) {
		 MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK|SWT.CANCEL);
	        
	        messageBox.setText(titulo);
	        messageBox.setMessage(mensaje);
	        int buttonID = messageBox.open();
	        switch(buttonID) {
	          case  SWT.OK:
	            return true;
	          case SWT.CANCEL:
	            return false;
	        }
			return false;
	}
	public static void mensajeError(Shell shell,String titulo,String mensaje) {
		 MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	        
	        messageBox.setText(titulo);
	        messageBox.setMessage(mensaje);
	        messageBox.open();
	}
		
	
}
