package view.productoreplacement.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import controlador.ControladorSolicitudFabricacion;
import controlador.ControladorReposicionProducto;



import view.common.Mensaje;
import view.common.UIWindowConstant;
import view.productoreplacement.VistaReposicionProducto;

public class ProductReplacementWindow {
	/* Shell */
	private Display display=null;
	private Shell shell=null; 
	private Composite composite=null;
	/* Group Panel */
	private SearchProductReplacementWindow searchPanel=null;
	private EditProductReplacementWindow editPanel=null;
	private ReportProductReplacementWindow reportPanel =null;
	/* Vista */
	private VistaReposicionProducto vista;

	public ProductReplacementWindow(Shell fatherShell, Display display, VistaReposicionProducto vista) {
		this.vista = vista;
		this.vista.addControlador(new ControladorReposicionProducto(this.vista.getBusinessDelegate(),this.vista));
		createWindow(fatherShell, display); 
		createPanels();
		   
	}
	
	private void createWindow(Shell fatherShell, Display display) {
		this.display = display;
		shell=new Shell(fatherShell);
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD, UIWindowConstant.WINDOW_HEIGHT_BIG);
		shell.setText("Gestion Pedidos de Reposicion a Centros de Ditribucion");
		shell.setLayout(new FillLayout());
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		composite=new Composite(shell,SWT.NONE);
		composite.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
	}
	
	private void createPanels() {
		searchPanel=new SearchProductReplacementWindow(this);
		editPanel=new EditProductReplacementWindow(this);
		reportPanel=new ReportProductReplacementWindow(this);
	}

	public void open() {
		searchState();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
	}

	public void searchState(){
		searchPanel.setVisible(true);
		editPanel.setVisible(false);
		reportPanel.setVisible(false);
	}
	
	public void editState(){
		searchPanel.setVisible(false);
		editPanel.setVisible(true);
		reportPanel.setVisible(false);
	}
	
	public void reportState(){
		searchPanel.setVisible(false);
		editPanel.setVisible(false);
		reportPanel.setVisible(true);
	}
	
	public VistaReposicionProducto getVista() {
		return vista;
	}

	public Composite getComposite() {
		return composite;
	}

	public EditProductReplacementWindow getEditPanel() {
		return editPanel;
	}

	public ReportProductReplacementWindow getReportPanel() {
		return reportPanel;
	}

	public SearchProductReplacementWindow getSearchPanel() {
		return searchPanel;
	}

	public void close() {
		shell.dispose();
		
	}

	public Shell getShell() {
		return shell;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	
	


}
