package view.creacionarticuloform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import util.FileReaderWrapper;
import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.articulo.Articulo;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
import com.thoughtworks.xstream.XStream;

import controlador.ControladorArticulo;
import entities.ArticuloXML;
import entities.CentroDistribucionXML;

public class CrearArticulo {
	
	private WindowFabricationArticulo window;
	private Group completo;
	private Group articuloGroup;
	private Group datosGroup;
	private Group botones;
	private Table mainTable;
	private TableColumn col_chk;
	private TableColumn col_id;
	private TableColumn col_materiaprima;
	private TableColumn col_cantidad;
	
	private Button botonAbrirArchivo;
	private Button botonEnviarArticulo;
	private Button checkFabricacion;
	private CargarArchivo archivo;
	private Label labelCodigo;
	private Label labelLinea;
	private Label labelDescripcion;
	private Label labelTalle;
	private Label labelColor;
	private Label labelSeccion;
	private Label labelPvu;
	private Label labelOrigen;
	private Label labelCantidad;
	private Text textCantidad;
	private Text textCodigo;
	private Text textLinea;
	private Text textDescripcion;
	private Text textTalle;
	private Text textColor;
	private Text textSeccion;
	private Text textPvu;
	private Text textOrigen;
	private ArticuloXML articulo;
	private Map<Long, MateriaPrima> materiasPrimas=new HashMap<Long, MateriaPrima>();
	private final String IMAGEFINDPATH = "C:/imagenes/abrir.gif";
	private final String IMAGESAVEPATH = "C:/imagenes/guardar.gif";
	
	
	
	public CrearArticulo(WindowFabricationArticulo window)
	{
			this.window = window;
			createContents();
			setVisible(true);
			createTable();
			setTableEditor();
			crearBotones();
			crearLabels();
			crearTexts();
			addListeners();
			
	}
	
	private void addListeners() {
		
		textPvu.addListener(SWT.Verify, new Listener() {
		      public void handleEvent(Event e) {
		        String string = e.text;
		        char[] chars = new char[string.length()];
		        string.getChars(0, chars.length, chars, 0);
		        for (int i = 0; i < chars.length; i++) {
		          if (!('0' <= chars[i] && chars[i] <= '9')) {
		            e.doit = false;
		            return;
		          }
		        }
		      }
		    });
		
		textCantidad.addListener(SWT.Verify, new Listener() {
		      public void handleEvent(Event e) {
		        String string = e.text;
		        char[] chars = new char[string.length()];
		        string.getChars(0, chars.length, chars, 0);
		        for (int i = 0; i < chars.length; i++) {
		          if (!('0' <= chars[i] && chars[i] <= '9')) {
		            e.doit = false;
		            return;
		          }
		        }
		      }
		    });
		
		col_cantidad.addListener(SWT.Verify, new Listener() {
		      public void handleEvent(Event e) {
			        String string = e.text;
			        char[] chars = new char[string.length()];
			        string.getChars(0, chars.length, chars, 0);
			        for (int i = 0; i < chars.length; i++) {
			          if (!('0' <= chars[i] && chars[i] <= '9')) {
			            e.doit = false;
			            return;
			          }
			        }
			      }
			    });
        botonAbrirArchivo.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				 
				
					obtenerArticulo();
				
			}});
        botonEnviarArticulo.addSelectionListener(new SelectionListener(){

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void widgetSelected(SelectionEvent arg0) {
				
				if (((ControladorArticulo)window.getControlador()).validarCodigo(textCodigo.getText()) && !textCodigo.getText().equals(""))
				{
					if (Mensaje.mensajeDecision(window.getShell(),"Grabar", "¿Esta seguro que desea actualizar los datos?") )
					{
						Articulo articuloNuevo = new Articulo();
						articuloNuevo.setCodigo(textCodigo.getText());
						articuloNuevo.setColor(textColor.getText());
						articuloNuevo.setDescripcion(textDescripcion.getText());
						articuloNuevo.setOrigen(textOrigen.getText());
						articuloNuevo.setPvu(Long.parseLong(textPvu.getText()));
						articuloNuevo.setSeccion(textSeccion.getText());
						articuloNuevo.setTalle(textTalle.getText());
						articuloNuevo.setLinea(textLinea.getText());
						setMateriasPrimas(articuloNuevo);
						Set<String> centros = obtenerCentros(articulo.getCentros());
						boolean fabricacion = checkFabricacion.getSelection();
						((ControladorArticulo)window.getControlador()).insertarArticulo(articuloNuevo, centros, fabricacion, Long.parseLong(textCantidad.getText()));
						view.common.Mensaje.mensajeInformacion(window.getShell(), "Confirmación", "Se grabo con exito el articulo");
					}
				}
				else
					Mensaje.mensajeError(window.getShell(), "Error", "El codigo de producto ya existe o es incorrecto");
				
			}});
        
		
	}

	protected Set<String> obtenerCentros(ArrayList<CentroDistribucionXML> centros) {
		Set<String> nombres = new HashSet<String>();
		for (int i = 0; i < centros.size(); i++) {
			nombres.add(centros.get(i).getNombre());
		}
		return nombres;
	}

	protected void setMateriasPrimas(Articulo articuloNuevo) {
		getItemsChecked(articuloNuevo);
	}

	private void getItemsChecked(Articulo articuloNuevo) {
		TableItem[] items = mainTable.getItems();
		
		for (int i = 0; i < items.length; i++) {
			if (items[i].getChecked() == true)
			{
				Long id = new Long(items[i].getText(1));
				Long cant = new Long(items[i].getText(3));
				MateriaPrima mp = materiasPrimas.get(id);
				MateriaPrimaCantidad mpc = new MateriaPrimaCantidad();
				mpc.setMateriaPrima(mp);
				mpc.setCantidad(cant.longValue());
				articuloNuevo.getMateriaPrimas().add(mpc);
			}
		}
		
		
	}

		
		
	

	private void createContents() {
		completo = new Group(window.getShell(), SWT.NORMAL);
		completo.setText("Creacion Articulo");
		completo.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD - 15, UIWindowConstant.WINDOW_HEIGHT_STANDARD+130);
		completo.setVisible(false);
		completo.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		articuloGroup = new Group(completo,SWT.NORMAL);
		articuloGroup.setText("Articulo");
		articuloGroup.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 50,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 275);
		articuloGroup.setVisible(false);
		articuloGroup.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		articuloGroup.setLocation(10,15);//(x,y)
		
		datosGroup= new Group(completo,SWT.NORMAL);
		datosGroup.setText("Datos");
		datosGroup.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 50,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 360);
		datosGroup.setVisible(false);
		datosGroup.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		datosGroup.setLocation(10, 260);
		
		botones=new Group(completo,SWT.NORMAL);
		botones.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 50,view.common.UIWindowConstant.WINDOW_HEIGHT_BIG - 455);
		botones.setVisible(false);
		botones.setText("Opciones");
		botones.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		botones.setLocation(10, 415);
	    //createTables();
	}
	
    public void setVisible(boolean b) {
    	completo.setVisible(b);
    	articuloGroup.setVisible(b);
    	datosGroup.setVisible(b);
    	botones.setVisible(b);
    
    }
    
    private void createTable() {
		mainTable = new Table(articuloGroup, SWT.BORDER | SWT.CHECK| SWT.Modify);
		createColumns();
		setTableProperties();
		setColumnProperties();
		cargarTabla();
    }
    
    private void setTableProperties()
    {
		mainTable.setLinesVisible(true);
		mainTable.setLocation(10, 15);
		mainTable.setSize(444, 220);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 4;
		mainTable.setLayoutData(gd);
		mainTable.setHeaderVisible(true);

	}
    
    private void setTableEditor()
    {
    	final TableEditor editor= new TableEditor(mainTable);
    	final int COLUMNAEDITABLE=3;
		editor.setColumn(COLUMNAEDITABLE);
	    editor.horizontalAlignment = SWT.CENTER;
	    editor.grabHorizontal = true;
	    
	    mainTable.addMouseListener
	    (new MouseAdapter() 
		    {
		        @Override
				public void mouseDown(MouseEvent event) 
		        {
		          // Dispose any existing editor
		          Control old = editor.getEditor();
		          if (old != null) old.dispose();
	
		          // Determine where the mouse was clicked
		          Point pt = new Point(event.x, event.y);
	
		          // Determine which row was selected
		          final TableItem item = mainTable.getItem(pt);
		          if (item != null)
		          {
		        	    final Text text = new Text(mainTable, SWT.NONE);
		        	    text.setText(item.getText(COLUMNAEDITABLE));
		        	    text.selectAll();
		                text.setFocus();

		                // Recalculate the minimum width for the editor
		                editor.minimumWidth = text.getBounds().width;

		                // Set the control into the editor
		                editor.setEditor(text, item, COLUMNAEDITABLE);

		                // Add a handler to transfer the text back to the cell
		                // any time it's modified
		                final int col = COLUMNAEDITABLE;
		                
		                text.addModifyListener(new ModifyListener() 
		                {
		                  public void modifyText(ModifyEvent event) 
		                  {
		                    // Set the text of the editor's control back into the cell
		                    item.setText(col, text.getText());
		                  }
		                });
		          }
			
		        }
		    }
	    );
	}
    private void setColumnProperties() {
		col_chk.setResizable(false);
		col_id.setResizable(false);
		col_materiaprima.setResizable(true);
		col_cantidad.setResizable(true);
		
		
		col_chk.setText("");
		col_materiaprima.setText("Materia Prima");
		col_cantidad.setText("Cantidad");
		
		
		col_chk.setWidth(17);
		col_id.setWidth(0);
		col_materiaprima.setWidth(200);
		col_cantidad.setWidth(60);
		
		
	}
    public void crearLabels()
    {
    	labelCodigo=new Label(datosGroup,0);
        labelCodigo.setText("Codigo:");
        labelCodigo.pack();
        labelCodigo.setLocation(10,22);
        labelCodigo.setVisible(true);
           
        labelLinea=new Label(datosGroup,0);
        labelLinea.setText("Linea:");
        labelLinea.pack();
        labelLinea.setLocation(10,47);
        labelLinea.setVisible(true);
        
        labelDescripcion=new Label(datosGroup,0);
        labelDescripcion.setText("Descripcion:");
        labelDescripcion.pack();
        labelDescripcion.setLocation(10,72);
        labelDescripcion.setVisible(true);
        
        labelTalle=new Label(datosGroup,0);
        labelTalle.setText("Talle:");
        labelTalle.pack();
        labelTalle.setLocation(10,97);
        labelTalle.setVisible(true);
        
        labelCantidad=new Label(datosGroup,0);
        labelCantidad.setText("Cantidad:");
        labelCantidad.pack();
        labelCantidad.setLocation(10,122);
        labelCantidad.setVisible(true);
        
        labelColor=new Label(datosGroup,0);
        labelColor.setText("Color:");
        labelColor.pack();
        labelColor.setLocation(235,22);
        labelColor.setVisible(true);
        
        labelSeccion=new Label(datosGroup,0);
        labelSeccion.setText("Seccion:");
        labelSeccion.pack();
        labelSeccion.setLocation(235,47);
        labelSeccion.setVisible(true);
        
        labelPvu=new Label(datosGroup,0);
        labelPvu.setText("Precio X U.:");
        labelPvu.pack();
        labelPvu.setLocation(235, 72);
        labelPvu.setVisible(true);
        
        labelOrigen=new Label(datosGroup,0);
        labelOrigen.setText("Origen:");
        labelOrigen.pack();
        labelOrigen.setLocation(235,97);
        labelOrigen.setVisible(true);
        
       // labelCodigo.setBounds(shell.getClientArea());

    }
    
    public void crearTexts()
    {
    	    
    	textCodigo = new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textLinea=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textDescripcion=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textTalle=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textColor=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textSeccion=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textPvu=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textOrigen=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    	textCantidad=new Text(datosGroup, SWT.SINGLE | SWT.BORDER);
    
    	textCodigo.setSize(120, 20);
    	textCodigo.setLocation(80, 20);
    	textCodigo.setVisible(true);
    	
    	textLinea.setSize(120,20);
    	textLinea.setLocation(80,45);
    	
    	textDescripcion.setSize(120,20);
    	textDescripcion.setLocation(80,70);
    	
    	textTalle.setSize(120,20);
    	textTalle.setLocation(80, 95);
    	
    	textCantidad.setSize(120,20);
    	textCantidad.setLocation(80, 120);
    	
    	textColor.setSize(120,20);
    	textColor.setLocation(310,20);
    	
    	textSeccion.setSize(120,20);
    	textSeccion.setLocation(310, 45);
    	
    	textPvu.setSize(120,20);
    	textPvu.setLocation(310,70);
    	
    	textOrigen.setSize(120,20);
    	textOrigen.setLocation(310, 95);
    }
    
    public void crearBotones()
    {
    	//boton abrir archivo
    	botonAbrirArchivo=new Button(botones,0);
    	botonAbrirArchivo.setText("Cargar Archivo ");
    	botonAbrirArchivo.pack();
    	botonAbrirArchivo.setImage(new Image(window.getDisplay(), IMAGEFINDPATH));    	
    	botonAbrirArchivo.setVisible(true);
    	
    	botonEnviarArticulo=new Button(botones,0);
    	botonEnviarArticulo.setText("Enviar Articulo");
    	botonEnviarArticulo.pack();
    	
    	botonEnviarArticulo.setVisible(true);
    	botonEnviarArticulo.setImage(new Image(window.getDisplay(), IMAGESAVEPATH));
    	
    	botonAbrirArchivo.setSize(30, 30);
		botonAbrirArchivo.setLocation(70, 13);
		botonEnviarArticulo.setSize(30, 30);
		botonEnviarArticulo.setLocation(105, 13);
		
		checkFabricacion= new Button(botones, SWT.CHECK);
	    checkFabricacion.setText("Fabricar Item");
	    checkFabricacion.setLocation(150, 16);
	    checkFabricacion.setVisible(true);
	    checkFabricacion.pack();
	    checkFabricacion.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	    	
    	
    }
    
    	
	private void createColumns() {
		col_chk = new TableColumn(mainTable, SWT.CENTER);
		col_id = new TableColumn(mainTable, SWT.CENTER);
		col_materiaprima = new TableColumn(mainTable, SWT.CENTER);
		col_cantidad = new TableColumn(mainTable, SWT.CENTER);
		/*Columnas editables */
		
	}

	private void cargarTabla() {
		
		materiasPrimas = ((ControladorArticulo)window.getControlador()).getMateriasPrimas();
		Iterator<MateriaPrima> i = materiasPrimas.values().iterator();
		
		while (i.hasNext()) {
			MateriaPrima element = (MateriaPrima) i.next();
			TableItem item1 = new TableItem(mainTable, SWT.NONE);
			item1.setText(new String[] { "", String.valueOf(element.getID()), element.getDescripcion(), "1"});
		}
	}
	
	public void obtenerArticulo()
	{	
		archivo=new CargarArchivo(window);
		
		String file = archivo.getNombreArchivo();
		
		articulo = obtenerXML(file);
		
		if(articulo!=null)
			cargarDatosEnTexts();
		
	}
	
	private ArticuloXML obtenerXML(String file) {
		FileReaderWrapper fileReader = new FileReaderWrapper(file);
		String XML = fileReader.obtenerContenido();
		XStream xstream = new XStream();
		//Indico el alias de las clases involucradas
		xstream.alias("articulo", ArticuloXML.class);
		xstream.alias("centro", CentroDistribucionXML.class);
		
		//Se obtiene el objeto saco a partir de su XML
		ArticuloXML articuloXML= (ArticuloXML)xstream.fromXML(XML);
		return articuloXML;
	}

	public void cargarDatosEnTexts()
	{
		textCodigo.setText(articulo.getCodigo());
		textLinea.setText(articulo.getLinea());
		textDescripcion.setText(articulo.getDescripcion());
		textTalle.setText(articulo.getTalle());
		textColor.setText(articulo.getColor());
		textSeccion.setText(articulo.getSeccion());
		textPvu.setText(String.valueOf(articulo.getPvu()));
		textOrigen.setText(articulo.getOrigen());
		textCantidad.setText("0");
	}
	
}
