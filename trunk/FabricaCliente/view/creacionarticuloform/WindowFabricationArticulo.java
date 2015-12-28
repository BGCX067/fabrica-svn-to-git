package view.creacionarticuloform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import view.common.UIWindowConstant;
import controlador.ControladorArticulo;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;


public class WindowFabricationArticulo extends Vista{
	private Display display;
	private Shell shell;
	private CrearArticulo ventanaArticulo;

	
	
	public WindowFabricationArticulo(Shell fatherShell, Display display, BusinessDelegate businessDelegate)
	{
		super(businessDelegate);
		this.addControlador(new ControladorArticulo(this.getBusinessDelegate(),this));
		crearVentana(fatherShell, display);
		crearVentanaArticulo();
		articuloState(true);
	}
	public void crearVentana(Shell fatherShell, Display display)
	{			
		this.display =display;
		shell=new Shell(fatherShell);
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD,UIWindowConstant.WINDOW_HEIGHT_BIG);
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
	}
	public Display getDisplay()
	{
		return this.display;
	}
	public void setDisplay(Display d)
	{
		this.display=d;
	}
	public Shell getShell()
	{
		return this.shell;
	}
	public void setShell(Shell s)
	{
		this.shell=s;
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
	public void crearVentanaArticulo()
	{
		ventanaArticulo=new CrearArticulo(this);
	}
	public void articuloState(boolean t) 
	{
		
		ventanaArticulo.setVisible(t);
		
	}
	
	public CrearArticulo getVentanaArticulo() {
		return ventanaArticulo;
	}
	public void setVentanaArticulo(CrearArticulo ventanaArticulo) {
		this.ventanaArticulo = ventanaArticulo;
	}
	
	
	
	
	
	
}
