package view.aplicationWindow;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import view.creacionSolicitudFabricacion.WindowFabricationSolicitud;
import view.creacionarticuloform.WindowFabricationArticulo;
import view.fabricationform.WindowFabrication;
import view.productoreplacement.VistaReposicionProducto;
import view.productoreplacement.ui.ProductReplacementWindow;
import view.rawmaterialform.VistaMateriaPrima;
import view.rawmaterialform.ui.UIInputProviderRawMaterial;
import view.syncronizer.WindowSynchronizer;
import businessdelegate.BusinessDelegateFacade;


public class ApplicationWindow {
	private Display display;
	private Shell shell;
	private Menu menuBar,fabricacionMenu;
	private MenuItem fabricacionHeader;
	private MenuItem fabricationForm;
	private MenuItem articuloForm;
	private MenuItem sincronizadorForm;
	private MenuItem ordenCompraForm;
	private MenuItem reposicionForm;
	private MenuItem solicitudForm;
	private Label lblBackground;
	private static final String FABRICACIONHEADERIMAGE = "C:/imagenes/maquinaria.gif";
	private static final String SOLICITUDFORMIMAGE = "C:/imagenes/ico_comercial_logistica_consulta_remitos.gif";
	private static final String FABRICATIONFORMIMAGE = "C:/imagenes/alta-de-orden-de-fabricacio.gif";
	private static final String ARTICULOFORMIMAGE = "C:/imagenes/ico_comercial.gif";
	private static final String COMPRAFORMIMAGE = "C:/imagenes/ico_comercial_logistica_ingreso_mercaderia.gif";
	private static final String REPOSICIONFORMIMAGE = "C:/imagenes/ico_comercial_provee_mant_categoria.gif";
	private static final String SINCRONIZADORFORMIMAGE = "C:/imagenes/table_refresh.gif";
	
	
	public ApplicationWindow()
	{
		display = new Display();
		shell = new Shell(display);
		shell.setMaximized (true);
		shell.setText("MRP Zara");
		createContents();
		addListeners();
	}

	private void addListeners() {
		fabricationForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
				WindowFabrication window = new WindowFabrication(shell, display, businessDelegate);
				window.open();
			}});
		
		articuloForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
				WindowFabricationArticulo  vista = new WindowFabricationArticulo(shell, display,businessDelegate);
				
				
				//WindowFabricationArticulo window = new WindowFabricationArticulo(shell, display);
				vista.open();
				
			}});
		
		sincronizadorForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
				WindowSynchronizer window = new WindowSynchronizer(shell, display, businessDelegate);
				window.open();
				
			}});
		
		solicitudForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade businessDelegate = new BusinessDelegateFacade();
				WindowFabricationSolicitud window = new WindowFabricationSolicitud(shell, display, businessDelegate);
				window.open();
				
			}});
		
		ordenCompraForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade facade=new BusinessDelegateFacade();
				UIInputProviderRawMaterial rmp=new UIInputProviderRawMaterial(shell, display, new VistaMateriaPrima(facade));
				rmp.open();
				
			}});
		
		reposicionForm.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				BusinessDelegateFacade facade=new BusinessDelegateFacade();
				ProductReplacementWindow win=new ProductReplacementWindow(shell, display, new VistaReposicionProducto(facade));
				win.open();
				
			}});
		
	}

	private void createContents() {
		menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
	    fabricacionHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fabricacionHeader.setText("&Fabricacion");
	    fabricacionHeader.setImage(new Image(display, FABRICACIONHEADERIMAGE));
	    
	    fabricacionMenu = new Menu(shell, SWT.DROP_DOWN);
	    fabricacionHeader.setMenu(fabricacionMenu);
	    
	    solicitudForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    solicitudForm.setText("&Recepción de Solicitudes");
	    solicitudForm.setImage(new Image(display, SOLICITUDFORMIMAGE));
	    
	    fabricationForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    fabricationForm.setText("&Fabricación Articulos");
	    fabricationForm.setImage(new Image(display, FABRICATIONFORMIMAGE));
	    
	    articuloForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    articuloForm.setText("&Alta de Articulos");
	    articuloForm.setImage(new Image(display, ARTICULOFORMIMAGE));

	    ordenCompraForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    ordenCompraForm.setText("&Gestión de Orden de Compra");
	    ordenCompraForm.setImage(new Image(display, COMPRAFORMIMAGE));
	    
	    reposicionForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    reposicionForm.setText("&Gestión de Orden de Reposición");
	    reposicionForm.setImage(new Image(display, REPOSICIONFORMIMAGE));
	    
	    sincronizadorForm = new MenuItem(fabricacionMenu, SWT.PUSH);
	    sincronizadorForm.setText("&Sincronizador de Producción");
	    sincronizadorForm.setImage(new Image(display, SINCRONIZADORFORMIMAGE));
		
	    
	    lblBackground = new Label(shell, SWT.CENTER);
	    lblBackground.setBounds(shell.getClientArea());
	  
	}
	
	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
}
