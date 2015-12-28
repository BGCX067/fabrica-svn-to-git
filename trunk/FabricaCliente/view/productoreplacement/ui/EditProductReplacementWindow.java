package view.productoreplacement.ui;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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

import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionItemVO;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.SolicitudFabricacionItemVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;
import com.thoughtworks.xstream.XStream;

import controlador.ControladorReposicionProducto;

import util.DateFormatParser;
import util.FileSaver;
import util.FileWriterWrapper;
import view.common.Mensaje;
import view.common.UIWindowConstant;
import view.productoreplacement.util.OrdenReposicionItemMap;
import view.productoreplacement.util.OrdenReposicionListMap;
import view.productoreplacement.util.OrdenReposicionMap;
import view.productoreplacement.util.ProductReplacementWindowLog;

public class EditProductReplacementWindow {
	/* Composite */
	private Composite composite=null;
	private ProductReplacementWindow window=null;
	/* Edit Panel*/
	private Group frm_edit=null;
	private Group frm_editButtons=null;
	/* Edit Panel Table*/
	private Table grd_FinishedProducts=null;
	/* edit Panel Columns*/
	private TableColumn col_productoCodigo=null;
	private TableColumn col_producto=null;
	private TableColumn col_ordenFabricacionID=null;
	private TableColumn col_cantidad=null;
	/* Edit Panel Buttons */
	private Button btn_guardarPedido=null;
	private Button btn_edit_volver=null;
	private Set<OrdenFabricacionVO> ordenes=null;
	private final String IMAGESAVEPATH ="C:/imagenes/guardar.gif";
	private final String IMAGECANCELPATH ="C:/imagenes/atras.gif";
	
	public EditProductReplacementWindow(ProductReplacementWindow window) {
		super();
		this.window=window;
		this.composite=window.getComposite();
		createContents();
		createTable();
		createButtons();
		createListeners();
		
	}

	private void createTable() {
		grd_FinishedProducts=new Table(frm_edit,SWT.BORDER);
		grd_FinishedProducts.setSize(UIWindowConstant.TABLE_WIDTH_STANDARD, 350);
		grd_FinishedProducts.setLocation(UIWindowConstant.TABLE_X_FIRSTCOLUMN,UIWindowConstant.TABLE_Y_FIRSTLINE);
		grd_FinishedProducts.setLayoutData(new GridData(GridData.FILL_BOTH));
		grd_FinishedProducts.setLinesVisible(true);
		grd_FinishedProducts.setHeaderVisible(true);
	    
		col_ordenFabricacionID=new TableColumn(grd_FinishedProducts,SWT.NULL);
		col_ordenFabricacionID.setText("Cod. de Fab.");
		col_ordenFabricacionID.setWidth(80);
		
		col_productoCodigo=new TableColumn(grd_FinishedProducts,SWT.LEFT);
		col_productoCodigo.setText("Cod. de Producto");
		col_productoCodigo.setWidth(90);
		
		col_producto=new TableColumn(grd_FinishedProducts,SWT.NULL);
		col_producto.setText("Producto");
		col_producto.setWidth(160);
		
		col_cantidad=new TableColumn(grd_FinishedProducts,SWT.NULL);
		col_cantidad.setText("Cantidad");
		col_cantidad.setWidth(90);
		
	}

	private void createContents() {
		frm_edit=new Group(composite,SWT.NONE);
		frm_edit.setText("Crear Ordenes de Reposicion");
		frm_edit.setLocation(UIWindowConstant.TITLE_X, UIWindowConstant.TITLE_Y);
		frm_edit.setSize(UIWindowConstant.GROUP_WIDTH_STANDARD,UIWindowConstant.GROUP_HEIGHT_BIG);
		frm_edit.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		frm_editButtons=new Group(frm_edit,SWT.NONE);
		frm_editButtons.setText("Opciones");
		frm_editButtons.setSize(UIWindowConstant.BUTTONS_WIDTH_STANDARD,UIWindowConstant.BUTTONS_HEIGHT_STANDARD);
		frm_editButtons.setLocation(UIWindowConstant.BUTTONS_X, UIWindowConstant.BUTTONS_Y);
		frm_editButtons.setBackground(window.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	}
	private void createButtons() {
		btn_guardarPedido=new Button(frm_editButtons,SWT.NONE);
		btn_guardarPedido.setToolTipText("Generar Pedidos de Rep.");
		btn_guardarPedido.setLocation(70, 13);	
		btn_guardarPedido.setSize(30,30);
		btn_guardarPedido.setImage(new Image(window.getDisplay(), IMAGESAVEPATH));
		
		btn_edit_volver=new Button(frm_editButtons,SWT.NONE);
		btn_edit_volver.setToolTipText("Volver");
		btn_edit_volver.setLocation(105, 13);
		btn_edit_volver.setSize(30,30);
		btn_edit_volver.setImage(new Image(window.getDisplay(), IMAGECANCELPATH));
		
	}
	
	private void createListeners() {
		
		btn_guardarPedido.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				if(Mensaje.mensajeDecision(composite.getShell(), "Guardar Información", "¿Está seguro de guardar los cambios?")){
					
					/* Actualizar informaticon en el servidor*/
					Set<OrdenReposicionVO> ordenesReposicion=actualizarOrdenReposicion();
					
					Mensaje.mensajeInformacion(composite.getShell(), "Guardar Información", "Las información ha sido almacenada exitosamente");
					
					/*Armo los Archivo XML*/
					crearArchivoXML(ordenesReposicion);
					
					/*Mostrar el Reporte de las Ordenes de Reposicion*/
					window.getReportPanel().fillReport(ordenesReposicion);
					window.reportState();
					
					
				}
			}


			private void crearArchivoXML(Set<OrdenReposicionVO> ordenesReposicion) {
				OrdenReposicionListMap list =new OrdenReposicionListMap(ordenesReposicion);
				XStream xstream =  new XStream();
				xstream.alias("ordenreposicion", OrdenReposicionMap.class);
				xstream.alias("ordenreposicionitem", OrdenReposicionItemMap.class);
				//Parsear el objeto a XML
				Iterator iter=list.getOrdenesReposicion().iterator();

				while(iter.hasNext()){
					OrdenReposicionMap orMAp=(OrdenReposicionMap) iter.next();

					/*Creo el archivo para cada orden de reposicion*/
					String ordenReposicionXML = xstream.toXML(orMAp);
					String file="c:/"+orMAp.getCentrodistribucion()+".xml";
					FileWriterWrapper fileWriter = new FileWriterWrapper(file);
					fileWriter.write(ordenReposicionXML);
				}
			}
				
		});
		btn_edit_volver.addListener(SWT.Selection, new Listener(){
			public void handleEvent(Event event) {
				if(Mensaje.mensajeDecision(composite.getShell(), "Volver al Menú Principal", "¿Está seguro que desea volver?")){
					window.getSearchPanel().cleanPanel();//((ControladorReposicionProducto)vista.getControlador()).getPedidos();/* dame todos los pedidos existentes que tengan algun producto en pendiente o parcialmente fabricado*/
					Set<OrdenReposicionVO> reposicion=(Set<OrdenReposicionVO>) ((ControladorReposicionProducto)window.getVista().getControlador()).getOrdenesReposicion();
					window.getSearchPanel().fillTable(reposicion);
					window.searchState();
					
				}
			}});
		
	}

	public void cleanPanel(){
		grd_FinishedProducts.removeAll();
	}

	public void setVisible(boolean b) {
		frm_edit.setVisible(b);
		
	}
	
	public void fillTable(Set<OrdenFabricacionVO> ordenesFabricacion) {
		Iterator iter=ordenesFabricacion.iterator();
		grd_FinishedProducts.removeAll();
		while(iter.hasNext()){
			OrdenFabricacionVO producto = (OrdenFabricacionVO) iter.next();
			TableItem item=new TableItem(grd_FinishedProducts,SWT.NONE);
			item.setText(new String[]{String.valueOf(producto.getID()),String.valueOf(producto.getArticuloid()),producto.getDescripcionArticulo(),String.valueOf(producto.getCantidadFabricada()-producto.getCantidadDistribuida())});
		}
		ordenes=ordenesFabricacion;

	}
	

	private Set<OrdenReposicionVO> actualizarOrdenReposicion()
	{
		/* En este map estaran las ordenes de reposicion nuevas */
		Set<OrdenReposicionVO> orSet=new HashSet<OrdenReposicionVO>();
		
		/* Traer todas las solicitudes de fabricacion */
		Set<SolicitudFabricacionVO> solFabList=(Set<SolicitudFabricacionVO>) ((ControladorReposicionProducto)window.getVista().getControlador()).getSolicitudesFabricacionPendientes();
		
		/* Se extraen las ordenes de fabricacion en estado pendiente o parcialmente entregado*/
		Iterator ordenesIter=ordenes.iterator();
		/*Se recorren las ordenes de fabricacion */
		
		ProductReplacementWindowLog.imprimirSolicitudes(solFabList);
		ProductReplacementWindowLog.imprimirOrdenesFabricacion(ordenes);
		
		while (ordenesIter.hasNext()) {
			OrdenFabricacionVO ofVO = (OrdenFabricacionVO) ordenesIter.next();
			
			/*Se recorren todas las solicitudes de fabricacion*/
			Iterator solFabIter=solFabList.iterator();
			/* Mientras la orden de Fabricacion tenga 
			 * cantidad mayor a cero (falta asiganr producto)
			 * y se hayan recorrido todas las solicitudes de 
			 * reposicion (no hayan mas solicitudes con ese 
			 * producto para satisfacer) */
			while(solFabIter.hasNext()&& (ofVO.getCantidadFabricada()-ofVO.getCantidadDistribuida())>0)
			{
				/* Dentro de cada solicitud debo encontrar
				 * el item que refiera a dicho producto
				 */
				SolicitudFabricacionVO solFab=(SolicitudFabricacionVO) solFabIter.next();
				SolicitudFabricacionItemVO solFabItem=buscarSolicitudFabricacionItem(solFab,String.valueOf(ofVO.getArticuloid()));
				/* Si alguno de sus items puede ser satisfecho 
				 * por dicha orden de fabricacion*/
				if(solFabItem!=null && solFabItem.getCantidad()-solFabItem.getCantidadFabricada()>0)
				{
					createOrdenReposicion(solFab,solFabItem,ofVO,orSet);/*creo o modifico la orden de reposicion*/
				}
				
			}
		}
		/*Actualizo los datos en el servidor*/
		((ControladorReposicionProducto)window.getVista().getControlador()).crearOrdenesReposicion(orSet,solFabList,ordenes);
		
		/*Mostrar Por pantalla el Reporte de Ordenes*/
		ProductReplacementWindowLog.imprimirSolicitudes(solFabList);
		ProductReplacementWindowLog.imprimirOrdenesFabricacion(ordenes);
		ProductReplacementWindowLog.imprimirOrdenesReposicion(orSet);
		return orSet;
	}
	

	private void createOrdenReposicion(SolicitudFabricacionVO SolFab,SolicitudFabricacionItemVO solFabItem, OrdenFabricacionVO ofVO,Set<OrdenReposicionVO> orSet) {
			/* Busco la Orden de Reposicion */
		
			OrdenReposicionVO or=buscarOrdenReposicion(orSet,String.valueOf(SolFab.getCentroDistribucion().getNombre()));
			OrdenReposicionItemVO orItem=null;
			Long cantidad=null;
			/* Si no existe una Orden de Repocision*/
			if(or==null)
			{
				/* Creo la orden de reposicion */
				or=new OrdenReposicionVO(0,(new Date()),"Pendiente",new HashSet(), (DateFormatParser.parseDate(new Date())+" - "+SolFab.getCentroDistribucion().getNombre()),SolFab.getCentroDistribucion());
				/*Calculo la cantidad a reponer*/
				cantidad=calcularcantidadReposicion(solFabItem,ofVO);
				/* Creo el item */
				orItem=new OrdenReposicionItemVO(new Long(0),solFabItem.getArticulo().getCodigo(),new Long(solFabItem.getArticulo().getID()),cantidad,solFabItem);
				/* Lo agrego a la orden de reposicion */
				or.getItems().add(orItem);
				
				orSet.add(or);
			}else
			{
				/* Si existe el item */
				orItem=buscarOrdenReposicionItem(or,String.valueOf(solFabItem.getArticulo().getID()));
				if(orItem==null)
				{
					/* Si no existe el item, lo creo*/
					cantidad=calcularcantidadReposicion(solFabItem,ofVO);
					/* Creo el item */
					orItem=new OrdenReposicionItemVO(new Long(0),solFabItem.getArticulo().getCodigo(),new Long(solFabItem.getArticulo().getID()),cantidad,solFabItem);
					/* Lo agrego a la orden de reposicion */
					or.getItems().add(orItem);
					
				}else
				{
					cantidad=calcularcantidadReposicion(solFabItem,ofVO);
					/* Si existe el item, lo modifico */
					orItem.setCantidad(orItem.getCantidad()+cantidad);
					
					/* Le agrego la nueva orden de solicitud la orden de fabricacion item*/
					orItem.getItemFabricacion().add(solFabItem);
					
				}
			}
			/* Actualizo la Canmtidad fabricada de la Solicitud */
			solFabItem.setCantidadFabricada(solFabItem.getCantidadFabricada()+cantidad);
			/* Modifico la cantidad distribuida*/
			ofVO.setCantidadDistribuida(ofVO.getCantidadDistribuida()+cantidad);
			/* Actualizo el estado de la solicitud de fabricacion*/
			SolFab.actualizarEstado();
			/* actualizo el estado de la orden de Fabricacion*/
			ofVO.actualizarEstado();
			
		}
	private OrdenReposicionVO buscarOrdenReposicion(Set<OrdenReposicionVO> orSet, String centroDistribucion) {
		
		Iterator iter=orSet.iterator();
		while (iter.hasNext()) {
			OrdenReposicionVO or = (OrdenReposicionVO) iter.next();
			if(or.getCentroDistribucion().equals(centroDistribucion)){
				return or;
			}
		}
		return null;
	}

	private Long calcularcantidadReposicion(SolicitudFabricacionItemVO solFabItem, OrdenFabricacionVO ofVO) {
		
		Long cantidadReponer=ofVO.getCantidadFabricada()-ofVO.getCantidadDistribuida();
		Long cantidad=solFabItem.getCantidad()-solFabItem.getCantidadFabricada();
		if(cantidadReponer>cantidad)
		{
			return cantidad;
		}
		else
		{
			return cantidadReponer;
		}
	}
	

	private OrdenReposicionItemVO buscarOrdenReposicionItem(OrdenReposicionVO or, String articuloId) {
		Iterator iter= or.getItems().iterator();
		while(iter.hasNext())
		{
			OrdenReposicionItemVO orVO=(OrdenReposicionItemVO)iter.next();
			if(String.valueOf(orVO.getArticuloId()).equals(articuloId))
			{
				return orVO;
			}
			
		}
		return null;
	}

	private SolicitudFabricacionItemVO buscarSolicitudFabricacionItem(SolicitudFabricacionVO solicitudFabricacion,String articuloId) {
		Iterator iter= solicitudFabricacion.getItems().iterator();
		while(iter.hasNext())
		{
			SolicitudFabricacionItemVO sfVO=(SolicitudFabricacionItemVO)iter.next();
			if((String.valueOf(sfVO.getArticulo().getID())).equals(articuloId))
			{
				return sfVO;
			}
			
		}
		return null;
	}
	
	
}
