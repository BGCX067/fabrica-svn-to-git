package view.productoreplacement.ui;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import util.DateFormatParser;
import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionVO;

import controlador.ControladorReposicionProducto;



public class SearchProductReplacementWindow {
	/* Composite */
	private Composite composite=null;
	private ProductReplacementWindow window=null;
	/* Search Panel */
	private Group frm_search=null;
	private Group frm_searchButtons=null;
	
	/* Search Panel Table */
	private Table grd_ReplacementOrder=null;
	
	/* Search Panel Table Columns  */
	private TableColumn col_id=null;
	private TableColumn col_producto=null;
	private TableColumn col_cantidad=null;
	
	
	
	/* Search Panel Buttons */
	private Button btn_generarReposicion=null;
	private Button btn_consultarEstado=null;
	private final String IMAGENEWPATH = "C:/imagenes/nuevo.gif";

	
	public SearchProductReplacementWindow(ProductReplacementWindow window) {
		super();
		this.window=window;
		this.composite=window.getComposite();
		createFields();
		createTable();
		/*createFilter();*/
		createButtons();
		createListeners();
		fillDataGrid();
	}

	private void createFields() {
		frm_search=new Group(composite,SWT.NONE);
		frm_search.setText("Pedidos de Reposicion Disponibles");
		frm_search.setLocation(UIWindowConstant.TITLE_X, UIWindowConstant.TITLE_Y);
		frm_search.setSize(UIWindowConstant.GROUP_WIDTH_STANDARD,UIWindowConstant.GROUP_HEIGHT_BIG);
		frm_search.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		frm_searchButtons=new Group(frm_search,SWT.NONE);
		frm_searchButtons.setText("Opciones");
		frm_searchButtons.setSize(UIWindowConstant.BUTTONS_WIDTH_STANDARD,UIWindowConstant.BUTTONS_HEIGHT_STANDARD);
		frm_searchButtons.setLocation(UIWindowConstant.BUTTONS_X, UIWindowConstant.BUTTONS_Y);
		frm_searchButtons.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		

	}
	private void createTable() {
		grd_ReplacementOrder=new Table(frm_search,SWT.BORDER);
		grd_ReplacementOrder.setSize(UIWindowConstant.TABLE_WIDTH_STANDARD, 350);
		grd_ReplacementOrder.setLocation(UIWindowConstant.TABLE_X_FIRSTCOLUMN,UIWindowConstant.TABLE_Y_FIRSTLINE);
		grd_ReplacementOrder.setLayoutData(new GridData(GridData.FILL_BOTH));
		grd_ReplacementOrder.setLinesVisible(true);
		grd_ReplacementOrder.setHeaderVisible(true);
	    
		col_id=new TableColumn(grd_ReplacementOrder,SWT.LEFT);
		col_id.setText("Codigo Pedido Rep.");
		col_id.setWidth(120);
		
		col_producto=new TableColumn(grd_ReplacementOrder,SWT.NULL);
		col_producto.setText("Fecha");
		col_producto.setWidth(90);
		
		col_cantidad=new TableColumn(grd_ReplacementOrder,SWT.NULL);
		col_cantidad.setText("Estado");
		col_cantidad.setWidth(220);
	}
	
	
	
	private void createButtons() {
		btn_generarReposicion=new Button(frm_searchButtons,SWT.NONE);
		btn_generarReposicion.setToolTipText("Nuevo Pedido de Rep.");
		btn_generarReposicion.setLocation(70, 13);	
		btn_generarReposicion.setSize(30,30);
		
		btn_generarReposicion.setImage(new Image(window.getDisplay(), IMAGENEWPATH));
		
		
		btn_consultarEstado=new Button(frm_searchButtons,SWT.NONE);
		btn_consultarEstado.setText("Ver Detalle");
		btn_consultarEstado.setLocation(165, 20);	//<-------------------------------Fix me
		btn_consultarEstado.setSize(UIWindowConstant.BUTTON_WIDHT_BIG, UIWindowConstant.BUTTON_HEIGHT_STANDARD);
		btn_consultarEstado.setVisible(false);
		
		
		
		
		
		
	}
	private void createListeners() {
		btn_generarReposicion.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				Set<OrdenFabricacionVO> ordenesFabricacion=(Set<OrdenFabricacionVO>) ((ControladorReposicionProducto)window.getVista().getControlador()).getOrdenesFabricacionTerminados();
				System.out.println("\n*** Busco productos fabricados  ***");
				window.getEditPanel().fillTable(ordenesFabricacion);
				window.editState();	
				//window.getEditPanel().cleanPanel();
			//	((ControladorReposicionProducto)vista.getControlador()).getProductosFabricados();/*dame los productos terminados y los pedidos*/
				
      }

		});
	
	btn_consultarEstado.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				window.reportState();
				window.getReportPanel().cleanPanel();
				//	((ControladorReposicionProducto)vista.getControlador()).getPedido();/*dame los productos terminados y los pedidos*/
				/*dame todos los productos de ese pedido y dame el estado de cada uno*/
			}
			
				
	});
	

		
	}

	private void fillDataGrid() {
		// TODO Auto-generated method stub
		Set<OrdenReposicionVO> reposicion=(Set<OrdenReposicionVO>) ((ControladorReposicionProducto)window.getVista().getControlador()).getOrdenesReposicion();
		System.out.println("Traje los productos");
		fillTable(reposicion);
	}
	public void fillTable(Set<OrdenReposicionVO> reposicion) {
		Iterator iter=reposicion.iterator();
		while(iter.hasNext()){
			OrdenReposicionVO reposicionVO = (OrdenReposicionVO) iter.next();
			TableItem row=new TableItem(grd_ReplacementOrder,SWT.NONE);
			row.setText(new String[]{reposicionVO.getID(), util.DateFormatParser.parseDate(reposicionVO.getFecha()),reposicionVO.getEstado()});
		}
		
	}

	public void cleanPanel(){
		grd_ReplacementOrder.removeAll();
	}

	public void setVisible(boolean b) {
		frm_search.setVisible(b);
		
	}
}
