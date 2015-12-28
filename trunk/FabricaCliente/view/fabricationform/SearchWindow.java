package view.fabricationform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

import util.DateFormatParser;
import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.ordenfabricacion.OrdenFabricacionVO;

import controlador.ControladorFabricacion;

public class SearchWindow {

	private WindowFabrication window;
	private Group mainGroup;
	private Table mainTable;
	private Button next;
	private TableColumn col_chk;
	private TableColumn col_ordenid;
	private TableColumn col_fecha;
	private TableColumn col_producto;
	private TableColumn col_productoid;
	private TableColumn col_cantidadpedida;
	private TableColumn col_cantidadfabricada;
	private TableColumn col_cantidadpendiente;
	private TableColumn col_estado;
	private Map<Long, OrdenFabricacionVO> ordenesPendientes = new HashMap<Long, OrdenFabricacionVO>();
	private Group buttonGroup;
	private final String IMAGEFINDPATH = "C:/imagenes/buscar.gif";
	

	public SearchWindow(WindowFabrication window) {
		this.window = window;
		createContents();
		addListeners();
	}

	private void createContents() {

		mainGroup = new Group(window.getShell(), SWT.NORMAL);
		mainGroup.setText("Busqueda de Ordenes de Fabricacion");
		mainGroup.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD - 15, UIWindowConstant.WINDOW_HEIGHT_STANDARD+70);
		
		mainGroup.setBackground(window.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		mainGroup.setLocation(5,5);
		buttonGroup = new Group(window.getShell(), SWT.NORMAL);
		buttonGroup.setText("Opciones");
		buttonGroup.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD - 15, 50);
		buttonGroup.setBackground(window.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));
		buttonGroup.setLocation(5, 435);
		createTable();
		next = new Button(buttonGroup, SWT.NORMAL);

		next.setImage(new Image(window.getDisplay(),IMAGEFINDPATH));
		next.setSize(30, 30);
		next.setLocation(70, 13);
		next.setToolTipText("Fabricar Productos");
	}

	private void createTable() {
		mainTable = new Table(mainGroup, SWT.BORDER | SWT.CHECK);
		createColumns();
		setTableProperties();
		setColumnProperties();
		fillMainTable();
	}

	private void createColumns() {
		col_chk = new TableColumn(mainTable, SWT.CENTER);
		col_ordenid = new TableColumn(mainTable, SWT.CENTER);
		col_fecha = new TableColumn(mainTable, SWT.CENTER);
		col_producto = new TableColumn(mainTable, SWT.CENTER);
		col_productoid = new TableColumn(mainTable, SWT.CENTER);
		col_cantidadpedida = new TableColumn(mainTable, SWT.CENTER);
		col_cantidadfabricada = new TableColumn(mainTable, SWT.CENTER);
		col_cantidadpendiente = new TableColumn(mainTable, SWT.CENTER);
		col_estado = new TableColumn(mainTable, SWT.CENTER);
	}

	private void setColumnProperties() {
		col_chk.setResizable(false);
		col_ordenid.setResizable(false);
		col_fecha.setResizable(true);
		col_producto.setResizable(true);
		col_productoid.setResizable(false);
		col_cantidadpedida.setResizable(true);
		col_cantidadfabricada.setResizable(true);
		col_cantidadpendiente.setResizable(true);
		col_estado.setResizable(true);
		col_chk.setText(" ");
		col_ordenid.setText("Nº Orden");
		col_fecha.setText("Fecha");
		col_producto.setText("Producto");
		col_productoid.setText("ID");
		col_cantidadpedida.setText("Pedido");
		col_cantidadfabricada.setText("Fabricado");
		col_cantidadpendiente.setText("Pendiente");
		col_estado.setText("Estado");
		col_chk.setWidth(17);
		col_ordenid.setWidth(0);
		col_fecha.setWidth(120);
		col_producto.setWidth(120);
		col_productoid.setWidth(0);
		col_cantidadpedida.setWidth(80);
		col_cantidadfabricada.setWidth(80);
		col_cantidadpendiente.setWidth(80);
		col_estado.setWidth(120);
	}

	private void setTableProperties() {
		mainTable.setLinesVisible(true);
		mainTable.setLocation(10, 15);
		mainTable.setSize(470, 400);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 4;
		mainTable.setLayoutData(gd);
		mainTable.setHeaderVisible(true);
	}

	public void fillMainTable() {
		ordenesPendientes=null;
		mainTable.removeAll();
		
		ordenesPendientes= ((ControladorFabricacion)window.getControlador()).getOrdenesFabricacionPendientes();
		Iterator<OrdenFabricacionVO> i = ordenesPendientes.values().iterator();
		
		while (i.hasNext()) {
			OrdenFabricacionVO vo = (OrdenFabricacionVO) i.next();
			TableItem item = new TableItem(mainTable, SWT.NONE);
			item.setText(new String[] { "", String.valueOf(vo.getID()), DateFormatParser.parseDate(vo.getFecha()), vo.getDescripcionArticulo(), String.valueOf(vo.getArticuloid()), String.valueOf(vo.getCantidad()),
					String.valueOf(vo.getCantidadFabricada()), String.valueOf(vo.getCantidad()-vo.getCantidadFabricada()), vo.getEstadoDescripcion() });
		}
	}

	private void addListeners() {
		next.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent arg0) {
				if (validateCheck())
				{
					obtenerOrdenesSeleccionadas();
					window.reportState();
				}
				else
				{
					Mensaje.mensajeError(window.getShell(), "Error", "Debe seleccionar por lo menos un item");
				}
				
			}

			private boolean validateCheck() {
				TableItem[] items = mainTable.getItems();
				ArrayList<Long> list = new ArrayList<Long>();
				for (int i = 0; i < items.length; i++) {
					if (items[i].getChecked() == true)
						return true;
				}
				return false;
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void obtenerOrdenesSeleccionadas() {
		ArrayList<Long> list = new ArrayList();
		list = getItemsChecked();
	}

	private ArrayList<Long> getItemsChecked() {
		TableItem[] items = mainTable.getItems();
		ArrayList<Long> list = new ArrayList<Long>();
		window.getOrdenesSeleccionadas().clear();
		for (int i = 0; i < items.length; i++) {
			if (items[i].getChecked() == true)
			{
				Long id = new Long(items[i].getText(1));
				list.add(id);
				window.getOrdenesSeleccionadas().add(ordenesPendientes.get(id));
			}
		}
		
		return list;
	}

	public void setVisible(boolean b) {
		mainGroup.setVisible(b);
		buttonGroup.setVisible(b);

	}
}
