package view.creacionSolicitudFabricacion;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.common.UIWindowConstant;
import controlador.ControladorSolicitudFabricacion;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;
public class WindowFabricationSolicitud extends Vista{
	
	private Display display;
	private Shell shell;
	private SolicitudFabricacion sol;
	
	
	public WindowFabricationSolicitud(Shell fatherShell, Display display, BusinessDelegate businessDelegate)
	{
		super(businessDelegate);
		this.addControlador(new ControladorSolicitudFabricacion(this.getBusinessDelegate(),this));
		crearVentana(fatherShell, display);
		crearSolicitudFabricacion();
		solicitudState(true);
	}
	
	public void crearVentana(Shell fatherShell, Display display)
	{
		this.display=display;
		shell=new Shell(fatherShell);
		shell.setText("Solicitud Fabricacion");
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD, UIWindowConstant.WINDOW_HEIGHT_BIG);
		
	}

	public void open()
	{
		shell.open();
		while(!shell.isDisposed())
		{
			if (!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
		
	}
	public Display getDisplay() {
		return display;
	}
	public void setDisplay(Display display) {
		this.display = display;
	}
	public Shell getShell() {
		return shell;
	}
	public void setShell(Shell shell) {
		this.shell = shell;
	}
	
	public void crearSolicitudFabricacion()
	{
		sol=new SolicitudFabricacion(this);
	}
	
	public void solicitudState(boolean valor)
	{
		sol.visibilidad(valor);
		
	}

	

	public SolicitudFabricacion getSol() {
		return sol;
	}

	public void setSol(SolicitudFabricacion sol) {
		this.sol = sol;
	}
	
	
}
