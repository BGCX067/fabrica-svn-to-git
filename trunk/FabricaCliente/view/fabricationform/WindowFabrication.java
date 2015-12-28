package view.fabricationform;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import util.FileSaver;
import util.FileWriterWrapper;
import view.common.Mensaje;
import view.common.UIWindowConstant;

import com.ordencompra.OrdenCompra;
import com.ordencompra.OrdenCompraItem;
import com.ordencompra.estado.OrdenCompraEstado;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.thoughtworks.xstream.XStream;

import controlador.ControladorFabricacion;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class WindowFabrication extends Vista{
	private Display display;
	private Shell shell;
	private SearchWindow searchWindow;
	private ReportWindow reportWindow;
	private ArrayList<OrdenFabricacionVO> ordenesSeleccionadas = new ArrayList<OrdenFabricacionVO>();
	private XStream xstream;
	private Set<OrdenFabricacionStockVO> seed = null;


	public WindowFabrication(Shell fatherShell, Display display, BusinessDelegate businessDelegate) {
		
		super(businessDelegate);
		this.addControlador(new ControladorFabricacion(businessDelegate, this));
		this.display = display;
		shell = new Shell(fatherShell);
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD, UIWindowConstant.WINDOW_HEIGHT_STANDARD+160);
		
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		searchWindow = new SearchWindow(this);

		reportWindow = new ReportWindow(this);
		searchState();
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Shell getShell() {
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public void open() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	public void searchState() {
		searchWindow.setVisible(true);
		reportWindow.setVisible(false);
	}
	
	public void inicializarFabricacion() {
		Set<OrdenFabricacionStockVO> list = ((ControladorFabricacion)this.getControlador()).validarStock(ordenesSeleccionadas);
		
		if (areEquals(list))
		{
			OrdenCompra ordenCompra = ((ControladorFabricacion)this.getControlador()).inicializarFabricacion(ordenesSeleccionadas);
			if(ordenCompra.getItems().size() >0)
			{
			xstream =  new XStream();
			xstream.alias("ordencompra", OrdenCompra.class);
			xstream.alias("ordencompraitem", OrdenCompraItem.class);
			xstream.alias("ordencompraestado", OrdenCompraEstado.class);
			//Parsear el objeto a XML
			String ordenCompraXML = xstream.toXML(ordenCompra);
			String file;
			do{
				file = FileSaver.openSaveDialog(shell,"*.xml");
				if (file == null)
					Mensaje.mensajeError(shell, "Error", "Debe ingresar un archivo de destino");
			}while (file == null);
			FileWriterWrapper fileWriter = new FileWriterWrapper(file);
			fileWriter.write(ordenCompraXML);
			}
		

		}
		else
		{
			Mensaje.mensajeInformacion(shell, "Información", "La fabricación fue ejecutada por otro usuario, por favor chequee las cantidades");
		}
		cancel();
	}

	private boolean areEquals(Set<OrdenFabricacionStockVO> list) {
		Iterator<OrdenFabricacionStockVO> iterSeed = seed.iterator();
		Iterator<OrdenFabricacionStockVO> iterList = list.iterator();
		
		if (list.size() != seed.size())
			return false;
		
		while (iterSeed.hasNext()) {
			OrdenFabricacionStockVO elementSeed = (OrdenFabricacionStockVO) iterSeed.next();
			OrdenFabricacionStockVO elementList = (OrdenFabricacionStockVO) iterList.next();
			
			if (elementSeed.getArticuloid() != elementList.getArticuloid() || elementSeed.getCantidad() != elementList.getCantidad())
				return true;
			
		}
		
		return true;
	}

	public void reportState() {
		Set<OrdenFabricacionStockVO> list = ((ControladorFabricacion)this.getControlador()).validarStock(ordenesSeleccionadas);
		seed = list;
		searchWindow.setVisible(false);
		reportWindow.setVisible(true);
		reportWindow.fillSuccessfulTable(getFabricados(list));
	    reportWindow.fillUnresolvedTable(getPendientes(list));
	}


	private ArrayList<OrdenFabricacionStockVO> getPendientes(Set<OrdenFabricacionStockVO> list) {
		Iterator<OrdenFabricacionStockVO> i = list.iterator();
		ArrayList<OrdenFabricacionStockVO> finalList = new ArrayList<OrdenFabricacionStockVO>();
		while (i.hasNext()) {
			OrdenFabricacionStockVO element = (OrdenFabricacionStockVO) i.next();
			if (!element.isFabricado())
				finalList.add(element);
		}
		return finalList;
	}

	private ArrayList<OrdenFabricacionStockVO> getFabricados(Set<OrdenFabricacionStockVO> list) {
		Iterator<OrdenFabricacionStockVO> i = list.iterator();
		ArrayList<OrdenFabricacionStockVO> finalList = new ArrayList<OrdenFabricacionStockVO>();
		while (i.hasNext()) {
			OrdenFabricacionStockVO element = (OrdenFabricacionStockVO) i.next();
			if (element.isFabricado())
				finalList.add(element);
		}
		return finalList;
	}


	
	public ReportWindow getReportWindow() {
		return reportWindow;
	}

	public void setReportWindow(ReportWindow reportWindow) {
		this.reportWindow = reportWindow;
	}

	public SearchWindow getSearchWindow() {
		return searchWindow;
	}

	public void setSearchWindow(SearchWindow searchWindow) {
		this.searchWindow = searchWindow;
	}

	public ArrayList<OrdenFabricacionVO> getOrdenesSeleccionadas() {
		return ordenesSeleccionadas;
	}

	public void setOrdenesSeleccionadas (
			ArrayList<OrdenFabricacionVO> ordenesSeleccionadas) {
		this.ordenesSeleccionadas = ordenesSeleccionadas;
	}
	
	public void cancel() {
		this.getSearchWindow().fillMainTable();
		searchState();
	}


}
