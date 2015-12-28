package view.fabricationform;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.ordenfabricacion.OrdenFabricacionStockVO;

public class ReportWindow {
	private WindowFabrication window;
	private Group reportGroup;
	private Table successfulArticleTable;
	private Table unresolvedArticleTable;
	private TableColumn col_articuloSuccessful;
	private TableColumn col_articuloidSuccessful;
	private TableColumn col_cantidadSuccessful;
	private TableColumn col_articuloUnresolved;
	private TableColumn col_articuloidUnresolved;
	private TableColumn col_cantidadUnresolved;
	private Button update;
	private Button cancel;
	private Group buttonGroup;
	private final String IMAGESAVEPATH ="C:/imagenes/guardar.gif";
	private final String IMAGECANCELPATH ="C:/imagenes/atras.gif";
	
	public ReportWindow(WindowFabrication window)
	{
			this.window = window;
			createContents();
			addListeners();
	}
		
	private void addListeners() {
		cancel.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {

				
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				unresolvedArticleTable.removeAll();
				successfulArticleTable.removeAll();
				window.cancel();
				
			}});
		
		update.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				if (Mensaje.mensajeDecision(window.getShell(),"Grabar", "¿Esta seguro que desea actualizar los datos?") )
					window.inicializarFabricacion();
				
			}});
		
	}

	

	private void createContents() {
		    reportGroup = new Group(window.getShell(), SWT.NORMAL);
		    reportGroup.setText("Condiciones de Fabricacion");
		    reportGroup.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD - 15, UIWindowConstant.WINDOW_HEIGHT_STANDARD+70);
		    reportGroup.setVisible(false);
		    reportGroup.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		    reportGroup.setLocation(5,5);
		    buttonGroup = new Group(window.getShell(), SWT.NORMAL);
			buttonGroup.setText("Opciones");
			buttonGroup.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD - 15, 50);
			buttonGroup.setBackground(window.getDisplay().getSystemColor(
					SWT.COLOR_WHITE));
			buttonGroup.setLocation(5, 435);
		    
		    update = new Button(buttonGroup, SWT.NORMAL);
			update.setImage(new Image(window.getDisplay(),IMAGESAVEPATH));
			update.setSize(30, 30);
			update.setLocation(70, 13);
			update.setToolTipText("Guardar Cambios");
			cancel = new Button(buttonGroup, SWT.NORMAL);

			cancel.setImage(new Image(window.getDisplay(),IMAGECANCELPATH));
			cancel.setSize(30, 30);
			cancel.setLocation(105, 13);
			cancel.setToolTipText("Cancelar Cambios");
		    createTables();
	}
	
	private void createTables() {
		  successfulArticleTable = new Table(reportGroup, SWT.BORDER);
		  unresolvedArticleTable = new Table(reportGroup, SWT.BORDER);
		  createSuccessfulColumns();
		  createUnresolvedColumns();
		  setTableSuccessfulProperties();
		  setTableUnresolvedProperties();
		  setSuccessfulColumnProperties();
		  setUnresolvedColumnProperties();
	      
	}
	
	private void createSuccessfulColumns() {
		  col_articuloSuccessful = new TableColumn(successfulArticleTable, SWT.CENTER);
		  col_articuloidSuccessful = new TableColumn(successfulArticleTable, SWT.LEFT);
		  col_cantidadSuccessful = new TableColumn(successfulArticleTable, SWT.LEFT);
	}
	
	private void createUnresolvedColumns() {
		  col_articuloUnresolved = new TableColumn(unresolvedArticleTable, SWT.CENTER);
		  col_articuloidUnresolved = new TableColumn(unresolvedArticleTable, SWT.LEFT);
		  col_cantidadUnresolved = new TableColumn(unresolvedArticleTable, SWT.LEFT);
	}

	private void setSuccessfulColumnProperties() {
		col_articuloSuccessful.setResizable(true);
		col_articuloidSuccessful.setResizable(true);
		col_cantidadSuccessful.setResizable(true);
		col_articuloSuccessful.setText("Articulo");
		col_articuloidSuccessful.setText("Nº");
		col_cantidadSuccessful.setText("Cantidad");
		col_articuloSuccessful.setWidth(120);
	    col_articuloidSuccessful.setWidth(80);
	    col_cantidadSuccessful.setWidth(80);
	}
	
	private void setUnresolvedColumnProperties() {
		col_articuloUnresolved.setResizable(true);
		col_articuloidUnresolved.setResizable(true);
		col_cantidadUnresolved.setResizable(true);
		col_articuloUnresolved.setText("Articulo");
		col_articuloidUnresolved.setText("Nº");
		col_cantidadUnresolved.setText("Cantidad");
		col_articuloUnresolved.setWidth(120);
	    col_articuloidUnresolved.setWidth(80);
	    col_cantidadUnresolved.setWidth(80);
	}

	private void setTableSuccessfulProperties() {
		  successfulArticleTable.setLinesVisible(true);
		  successfulArticleTable.setLocation(10, 20);
		  successfulArticleTable.setSize(470, 185);
	      GridData gd = new GridData(GridData.FILL_BOTH);
	      gd.horizontalSpan = 4;
	      successfulArticleTable.setLayoutData(gd);
	      successfulArticleTable.setHeaderVisible(true);
	}
	
	private void setTableUnresolvedProperties() {
		  unresolvedArticleTable.setLinesVisible(true);
		  unresolvedArticleTable.setLocation(10, 225);
		  unresolvedArticleTable.setSize(470, 180);
	      GridData gd = new GridData(GridData.FILL_BOTH);
	      gd.horizontalSpan = 4;
	      unresolvedArticleTable.setLayoutData(gd);
	      unresolvedArticleTable.setHeaderVisible(true);
	}
	
	public void fillSuccessfulTable(ArrayList<OrdenFabricacionStockVO> list) {
		

		
		successfulArticleTable.setRedraw(false);

	    // We remove all the table entries, sort our
	    // rows, then add the entries
	    successfulArticleTable.removeAll();
	    
	    for (int i=0; i < list.size(); i++) {
	      OrdenFabricacionStockVO ordenFabricacionVO = list.get(i);
	      TableItem item = new TableItem(successfulArticleTable, SWT.NONE);
	      int c = 0;
	      item.setText(c++, ordenFabricacionVO.getDescripcionArticulo());
	      item.setText(c++, String.valueOf(ordenFabricacionVO.getArticuloid()));
	      item.setText(c++, String.valueOf(ordenFabricacionVO.getCantidad()));
	    }

	    // Turn drawing back on
	    successfulArticleTable.setRedraw(true);
	}
	
	public void fillUnresolvedTable(ArrayList<OrdenFabricacionStockVO> list) {
				
		unresolvedArticleTable.setRedraw(false);

	    // We remove all the table entries, sort our
	    // rows, then add the entries
	    unresolvedArticleTable.removeAll();
	    
	    for (int i=0; i < list.size(); i++) {
	      OrdenFabricacionStockVO ordenFabricacionVO = list.get(i);
	      TableItem item = new TableItem(unresolvedArticleTable, SWT.NONE);
	      int c = 0;
	      item.setText(c++, ordenFabricacionVO.getDescripcionArticulo());
	      item.setText(c++, String.valueOf(ordenFabricacionVO.getArticuloid()));
	      item.setText(c++, String.valueOf(ordenFabricacionVO.getCantidad()));
	    }

	    // Turn drawing back on
	    unresolvedArticleTable.setRedraw(true);
	}
	
	public void setVisible(boolean b) {
			reportGroup.setVisible(b);
			buttonGroup.setVisible(b);
	}
}
