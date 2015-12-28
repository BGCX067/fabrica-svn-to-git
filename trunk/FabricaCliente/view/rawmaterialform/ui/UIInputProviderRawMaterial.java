package view.rawmaterialform.ui;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import view.common.Mensaje;
import view.common.UIWindowConstant;
import view.rawmaterialform.VistaMateriaPrima;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import controlador.ControladorMateriaPrima;


public class UIInputProviderRawMaterial {
	private Display display;
	private Shell shell; 
	private Group frm_busqueda;
	private Group frm_busquedaBotones;

	private Table grd_materiaPrima;
	private Button btn_cargarArchivo;
	private Button btn_ingresarMateriaPrima;
	private VistaMateriaPrima vista;
	private final String IMAGEFINDPATH = "C:/imagenes/abrir.gif";
	private final String IMAGESAVEPATH = "C:/imagenes/guardar.gif";

	public UIInputProviderRawMaterial(Shell fatherShell, Display display, VistaMateriaPrima vista) {
		this.vista = vista;
		this.vista.addControlador(new ControladorMateriaPrima(this.vista.getBusinessDelegate(),this.vista));
		createWindow(fatherShell, display); 
		createContents();
		    
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		

	}

	private void createContents() {
		Composite composite=new Composite(shell,SWT.NONE);
		frm_busqueda=new Group(composite,SWT.NONE);
		frm_busqueda.setText("Pedidos Disponibles");
		frm_busqueda.setLocation(UIWindowConstant.TITLE_X, UIWindowConstant.TITLE_Y);
		frm_busqueda.setSize(UIWindowConstant.GROUP_WIDTH_STANDARD,UIWindowConstant.GROUP_HEIGHT_MEDIUM);

		frm_busquedaBotones=new Group(frm_busqueda,SWT.NONE);
		frm_busquedaBotones.setText("Opciones");
		frm_busquedaBotones.setSize(UIWindowConstant.BUTTONS_WIDTH_STANDARD,UIWindowConstant.BUTTONS_HEIGHT_STANDARD);
		frm_busquedaBotones.setLocation(UIWindowConstant.FILTER_X, UIWindowConstant.BUTTONS2_Y);
		composite.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		frm_busqueda.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		frm_busquedaBotones.setBackground(display.getSystemColor(SWT.COLOR_WHITE));		
	/*	createFields();*/
		createTable();
		createButtons();




	}

	private void createButtons() {
		btn_cargarArchivo=new Button(frm_busquedaBotones,SWT.NONE);
		btn_cargarArchivo.setToolTipText("Abrir Archivo");
		btn_cargarArchivo.setLocation(70, 13);	
		btn_cargarArchivo.setSize(30,30);
		btn_cargarArchivo.setImage(new Image(display, IMAGEFINDPATH));

		btn_ingresarMateriaPrima=new Button(frm_busquedaBotones,SWT.NONE);
		btn_ingresarMateriaPrima.setToolTipText("Ingresar Materia Prima");
		btn_ingresarMateriaPrima.setLocation(105, 13);
		btn_ingresarMateriaPrima.setSize(30,30);
		btn_ingresarMateriaPrima.setImage(new Image(display, IMAGESAVEPATH));
		
		createListeners();
	}

	private void createListeners() {
		btn_cargarArchivo.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				/*abrir el filebrowser*/
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setFilterExtensions(new String[]{"*.xml"});
				String fileurl=fd.open();
				if(fileurl!=null)
				{
					grd_materiaPrima.removeAll();
					File file=new File(fileurl);
					InputStream input;
					try {
						input = new FileInputStream(file);


						/*cargar el xml en la grilla*/
						XStream xstream=new XStream(new DomDriver());
						view.rawmaterialform.materiaprima.MateriaPrima mp[]=(view.rawmaterialform.materiaprima.MateriaPrima[]) xstream.fromXML(input);

						for (int i = 0; i < mp.length; i++) {
							view.rawmaterialform.materiaprima.MateriaPrima materiaPrima = mp[i];
							TableItem item=new TableItem(grd_materiaPrima,SWT.NONE);

							String codigo=materiaPrima.getCodigo();
							/* Sacar el nombre de la materia Prima*/
							 String descripcion=((ControladorMateriaPrima)vista.getControlador()).getMateriaPrimaDescripcion(codigo);

							item.setText(new String[]{ materiaPrima.getCodigo(),descripcion,materiaPrima.getCantidad().toString()});
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}});



		btn_ingresarMateriaPrima.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				if(grd_materiaPrima.getItems().length>0){
				if(Mensaje.mensajeDecision(shell, "Ingresar Materia Prima", "¿Está seguro que desea guardar la información?")){

					StringBuilder sb=new StringBuilder();
					for (int i = 0; i < grd_materiaPrima.getItems().length; i++) {
						TableItem tableItem = grd_materiaPrima.getItems()[i];
						String codigo=tableItem.getText(0);
						String descripcion=tableItem.getText(1);
						long cantidad=Long.parseLong(tableItem.getText(2));
						Long stock=((ControladorMateriaPrima)vista.getControlador()).ingresarStockMateriaPrima(codigo, cantidad);
						sb.append("\t"+descripcion+"("+codigo+")="+stock+"\n");
					}
					Mensaje.mensajeInformacion(shell, "Ingresar Materia Prima", "Se han ingresado las materias primas con éxito:\n"+sb.toString());
					grd_materiaPrima.removeAll();
				}}else{
					Mensaje.mensajeError(shell, "Ingresar Materia Prima", "No posee materias primas para ingresar");
				}
			}

		});

		

	}

	private void createTable() {
		grd_materiaPrima=new Table(frm_busqueda,SWT.BORDER);
		grd_materiaPrima.setSize(UIWindowConstant.TABLE_WIDTH_STANDARD, UIWindowConstant.TABLE_HEIGHT_SUPERBIG);
		grd_materiaPrima.setLocation(UIWindowConstant.TABLE_X_FIRSTCOLUMN,UIWindowConstant.TABLE_Y_FIRSTLINE);
		grd_materiaPrima.setLayoutData(new GridData(GridData.FILL_BOTH));
		grd_materiaPrima.setLinesVisible(true);
		grd_materiaPrima.setHeaderVisible(true);

		TableColumn col_id=new TableColumn(grd_materiaPrima,SWT.LEFT);
		col_id.setText("Codigo Producto");
		col_id.setWidth(100);

		TableColumn col_producto=new TableColumn(grd_materiaPrima,SWT.NULL);
		col_producto.setText("Detalle");
		col_producto.setWidth(270);

		TableColumn col_cantidad=new TableColumn(grd_materiaPrima,SWT.NULL);
		col_cantidad.setText("Cantidad");
		col_cantidad.setWidth(60);
	}

	/*private void createFields() {

		lbl_nombre= new Label(frm_busqueda,SWT.NONE );
		lbl_nombre.setSize(UIWindowConstant.LABEL_WIDTH_STANDARD, UIWindowConstant.LABEL_HEIGHT_STANDARD);
		lbl_nombre.setLocation(UIWindowConstant.LABEL_X_FIRSTCOLUMN,UIWindowConstant.LABEL_Y_FIRSTLINE);
		lbl_nombre.setText("Proveedor :");

		lbl_fecha= new Label(frm_busqueda,SWT.NONE);
		lbl_fecha.setSize(UIWindowConstant.LABEL_WIDTH_STANDARD, UIWindowConstant.LABEL_HEIGHT_STANDARD);
		lbl_fecha.setLocation(UIWindowConstant.LABEL_X_SECONDCOLUMN,UIWindowConstant.LABEL_Y_FIRSTLINE);
		lbl_fecha.setText("Fecha :");


		lbl_nombreProveedor= new Label(frm_busqueda,SWT.BOLD);
		lbl_nombreProveedor.setSize(UIWindowConstant.TEXTBOX_WIDTH_STANDARD, UIWindowConstant.TEXTBOX_HEIGHT_STANDARD);
		lbl_nombreProveedor.setLocation(UIWindowConstant.TEXTBOX_X_FIRSTCOLUMN,UIWindowConstant.TEXTBOX_Y_FIRSTLINE);
		lbl_nombreProveedor.setText("Proveedor");

		lbl_fechaProveedor= new Label(frm_busqueda,SWT.NONE);
		lbl_fechaProveedor.setSize(UIWindowConstant.TEXTBOX_WIDTH_STANDARD, UIWindowConstant.TEXTBOX_HEIGHT_STANDARD);
		lbl_fechaProveedor.setLocation(UIWindowConstant.TEXTBOX_X_SECONDCOLUMN,UIWindowConstant.TEXTBOX_Y_FIRSTLINE);
		lbl_fechaProveedor.setText(""(new CalendarDate()).toString());

	}*/

	private void createWindow(Shell fatherShell, Display display) {
		this.display = display;
		shell=new Shell(fatherShell);
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD, UIWindowConstant.WINDOW_HEIGHT_MEDIUM);
		shell.setText("Ingreso de Pedidos a Proveedor");
		shell.setLayout(new FillLayout());
	}


	public VistaMateriaPrima getVista() {
		return vista;
	}

}
