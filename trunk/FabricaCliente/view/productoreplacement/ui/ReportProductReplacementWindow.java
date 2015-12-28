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

import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.ordenreposicion.OrdenReposicionItemVO;
import com.ordenreposicion.OrdenReposicionVO;

import controlador.ControladorReposicionProducto;

public class ReportProductReplacementWindow {
	/* Composite */
	private Composite composite=null;
	private ProductReplacementWindow window=null;
	/*Detail Panel*/
	private Group frm_detail=null;
	private Group frm_detailButtons=null;
		/*Detail Panel Table*/
	private Table grd_Detail=null;
	/* Detail Panel Columns */
	private TableColumn col_productoReponer=null;
	private TableColumn col_centroDistribucion=null;
	private TableColumn col_cantidadReponer=null; 
		/*Detail Panel Buttons */
	private Button btn_detail_volver=null;
	private final String IMAGECANCELPATH ="C:/imagenes/atras.gif";
		
	
	
	public ReportProductReplacementWindow(ProductReplacementWindow window) {
		super();
		this.window=window;
		this.composite=window.getComposite();
		createContents();
		createTable();
		createButtons();
		createListenters();
		
	}

	
	private void createContents() {
		frm_detail=new Group(composite,SWT.NONE);
		frm_detail.setText("Detail");
		frm_detail.setLocation(UIWindowConstant.TITLE_X, UIWindowConstant.TITLE_Y);
		frm_detail.setSize(UIWindowConstant.GROUP_WIDTH_STANDARD,UIWindowConstant.GROUP_HEIGHT_BIG);
		frm_detail.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		
		frm_detailButtons=new Group(frm_detail,SWT.NONE);
		frm_detailButtons.setText("Opciones");
		frm_detailButtons.setSize(UIWindowConstant.BUTTONS_WIDTH_STANDARD,UIWindowConstant.BUTTONS_HEIGHT_STANDARD);
		frm_detailButtons.setLocation(UIWindowConstant.BUTTONS_X, UIWindowConstant.BUTTONS_Y);
		frm_detailButtons.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	

		
	}
	private void createTable() {
		grd_Detail=new Table(frm_detail,SWT.BORDER);
		grd_Detail.setSize(UIWindowConstant.TABLE_WIDTH_STANDARD, 350);
		grd_Detail.setLocation(UIWindowConstant.TABLE_X_FIRSTCOLUMN,UIWindowConstant.TABLE_Y_FIRSTLINE);
		grd_Detail.setLayoutData(new GridData(GridData.FILL_BOTH));
		grd_Detail.setLinesVisible(true);
		grd_Detail.setHeaderVisible(true);
		
		
		col_centroDistribucion=new TableColumn(grd_Detail,SWT.NULL);
		col_centroDistribucion.setText("Centro Distribucion");
		col_centroDistribucion.setWidth(110);
		
		col_productoReponer=new TableColumn(grd_Detail,SWT.NULL);
		col_productoReponer.setText("Producto");
		col_productoReponer.setWidth(200);
		
		col_cantidadReponer=new TableColumn(grd_Detail,SWT.NULL);
		col_cantidadReponer.setText("Cantidad");
		col_cantidadReponer.setWidth(80);
		
	}
	private void createButtons() {
		btn_detail_volver=new Button(frm_detailButtons,SWT.NONE);
		btn_detail_volver.setText("Volver");
		btn_detail_volver.setLocation(70, 13);
		btn_detail_volver.setSize(30,30);
		btn_detail_volver.setImage(new Image(window.getDisplay(), IMAGECANCELPATH));
		
	}
	
	private void createListenters() {
		btn_detail_volver.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				if(Mensaje.mensajeDecision(composite.getShell(), "Volver al Menú Principal", "¿Está seguro que desea volver?")){
					window.getSearchPanel().cleanPanel();
					Set<OrdenReposicionVO> reposicion=(Set<OrdenReposicionVO>) ((ControladorReposicionProducto)window.getVista().getControlador()).getOrdenesReposicion();
					window.getSearchPanel().fillTable(reposicion);
					window.searchState();
				}
			}

		});

	}

	
	
	public void cleanPanel(){
		grd_Detail.removeAll();	
	}


	public void setVisible(boolean b) {
		frm_detail.setVisible(b);
		
	}


	public void fillReport(Set<OrdenReposicionVO> orSet) {
		// TODO Auto-generated method stub
		Iterator iter=orSet.iterator();
		grd_Detail.removeAll();
		while(iter.hasNext()){
			OrdenReposicionVO or = (OrdenReposicionVO) iter.next();
			TableItem item=new TableItem(grd_Detail,SWT.NONE);
			
			Iterator iter2=or.getItems().iterator();
			while(iter2.hasNext()){
				OrdenReposicionItemVO orItem = (OrdenReposicionItemVO) iter2.next();	
				item.setText(new String[]{or.getCentroDistribucion(),String.valueOf(orItem.getArticuloId()),String.valueOf(orItem.getCantidad())});
					
			}
		}
	}


	
}
