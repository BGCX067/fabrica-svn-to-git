package view.creacionSolicitudFabricacion;

import java.util.Iterator;

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

import util.FileReaderWrapper;
import view.common.Mensaje;

import com.solicitudfabricacion.xml.ArticuloSolicitud;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;
import com.thoughtworks.xstream.XStream;

import controlador.ControladorSolicitudFabricacion;

public class SolicitudFabricacion {

	private Group completo;
	private Group articuloGroup;
	private Group botonesGroup;
	private WindowFabricationSolicitud window;
	private Button botonAbrirArchivo;
	private Button botonEnviarSolicitud;
	private Table mainTable;
	private TableColumn col_codigo;
	private TableColumn col_descripcion;
	private TableColumn col_cantidad;
	private CargarArchivo archivo;
	private SolicitudFabricacionXML solicitudFabricacion=null;
	private final String IMAGEFINDPATH = "C:/imagenes/abrir.gif";
	private final String IMAGESAVEPATH = "C:/imagenes/guardar.gif";
	
	
	public SolicitudFabricacion(WindowFabricationSolicitud window)
	{
		this.window=window;
		createContents();
		crearBotones();
		createTable();
		addListeners();
		
	}
	
	private void addListeners() {
		
		botonAbrirArchivo.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				 obtenerArticulo();
				
			}});
		
        botonEnviarSolicitud.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				
					if (solicitudFabricacion != null)
					{
						if (Mensaje.mensajeDecision(window.getShell(),"Grabar", "¿Esta seguro que desea actualizar los datos?") )
						{
							((ControladorSolicitudFabricacion)window.getControlador()).tramitarRecepcionSolicitud(solicitudFabricacion);
							Mensaje.mensajeInformacion(window.getShell(), "Confirmación", "La solicitud se tramito con exito") ;
							mainTable.removeAll();
							solicitudFabricacion=null;
						}
					}
					else
						Mensaje.mensajeError(window.getShell(), "Error", "Debe cargar un archivo XML con la solicitud a ser procesada");
				
			}});
        		
	}

	private void createContents() {
		completo = new Group(window.getShell(), SWT.NORMAL);
		completo.setText("Creacion Solicitud");
		completo.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 30,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 50);
		completo.setVisible(true);
		completo.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		completo.setLocation(5,5);

		articuloGroup = new Group(completo,SWT.NORMAL);
		articuloGroup.setText("Articulo de Solicitud");
		articuloGroup.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 50,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 135);
		articuloGroup.setVisible(false);
		articuloGroup.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		articuloGroup.setLocation(10,15);//(x,y)
		
				
		botonesGroup=new Group(completo,SWT.NORMAL);
		botonesGroup.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 50,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 455);
		botonesGroup.setVisible(false);
		botonesGroup.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		botonesGroup.setLocation(10, 395);
		botonesGroup.setText("Opciones");
		
		
	}
	
	public void crearBotones()
    {
    	//boton abrir archivo
    	botonAbrirArchivo=new Button(botonesGroup,0);
    	botonAbrirArchivo.setToolTipText("Cargar Archivo");
    	botonAbrirArchivo.pack();
    	botonAbrirArchivo.setLocation(20,20);
    	botonAbrirArchivo.setVisible(true);
    	botonAbrirArchivo.setImage(new Image(window.getDisplay(), IMAGEFINDPATH));
    	
    	botonEnviarSolicitud=new Button(botonesGroup,0);
    	botonEnviarSolicitud.setToolTipText("Fabricar Articulos");
    	botonEnviarSolicitud.pack();
    	botonEnviarSolicitud.setLocation(370,20);
    	botonEnviarSolicitud.setVisible(true);
    	botonEnviarSolicitud.setImage(new Image(window.getDisplay(), IMAGESAVEPATH));
    	
    	botonAbrirArchivo.setSize(30, 30);
		botonAbrirArchivo.setLocation(70, 13);
		botonEnviarSolicitud.setSize(30, 30);
		botonEnviarSolicitud.setLocation(105, 13);
    	
    }
	
	

	private void createTable() 
	 {
			mainTable = new Table(articuloGroup, SWT.BORDER);
			createColumns();
			setTableProperties();
			setColumnProperties();
		
	 }
	protected void cargarTabla() {
		 
		Iterator<ArticuloSolicitud> iter = solicitudFabricacion.getItems().iterator();
		
		while (iter.hasNext()) {
			ArticuloSolicitud element = (ArticuloSolicitud) iter.next();
			TableItem item1 = new TableItem(mainTable, SWT.NONE);
			item1.setText(new String[] { element.getCodigo(),element.getDescripcion() ,String.valueOf(element.getCantidad())});
		}
		
	
	}
	 private void setTableProperties()
	 {
			mainTable.setLinesVisible(true);
			mainTable.setLocation(10, 15);
			mainTable.setSize(444, 360);
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.horizontalSpan = 4;
			mainTable.setLayoutData(gd);
			mainTable.setHeaderVisible(true);

	}
	 private void createColumns()
	 {
			col_codigo = new TableColumn(mainTable, SWT.CENTER);
			col_descripcion = new TableColumn(mainTable, SWT.CENTER);
			col_cantidad = new TableColumn(mainTable, SWT.CENTER);
	 }
	 private void setColumnProperties() {
			col_codigo.setResizable(true);
			col_descripcion.setResizable(true);
			col_cantidad.setResizable(true);
			
			
			col_codigo.setText("Codigo");
			col_descripcion.setText("Descripcion");
			col_cantidad.setText("Cantidad");
			
			
			col_codigo.setWidth(60);
			col_descripcion.setWidth(200);
			col_cantidad.setWidth(60);
			
			
		}
	 public void obtenerArticulo()
		{	
			archivo=new CargarArchivo(window);
			String file = archivo.getNombreArchivo();
			
			solicitudFabricacion = obtenerXML(file);
			
			
			
			if(solicitudFabricacion !=null)
				cargarTabla();
		}
	
	private SolicitudFabricacionXML obtenerXML(String file) {
		FileReaderWrapper fileReader = new FileReaderWrapper(file);
		String XML = fileReader.obtenerContenido();
		XStream xstream = new XStream();
		//Indico el alias de las clases involucradas
		xstream.alias("solicitud", SolicitudFabricacionXML.class);
		xstream.alias("articulo", ArticuloSolicitud.class);
		
		//Se obtiene el objeto saco a partir de su XML
		SolicitudFabricacionXML solicitud= (SolicitudFabricacionXML)xstream.fromXML(XML);
		return solicitud;
	}

	public void visibilidad(Boolean valor)
	{
		completo.setVisible(valor);
		articuloGroup.setVisible(valor);
		botonesGroup.setVisible(valor);
	}

}
