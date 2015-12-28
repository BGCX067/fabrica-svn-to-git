package view.syncronizer;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import view.common.UIWindowConstant;

import com.ordenfabricacion.OrdenFabricacionEjecucionVO;

import controlador.ControladorFabricacion;
import framework.modelo.BusinessDelegate;
import framework.vista.Vista;

public class WindowSynchronizer extends Vista{
	
		private Display display;
		private Shell shell;
		private Button btnStart;
		private Text resultado;
		private final String IMAGESTARTPATH ="C:/imagenes/comisiones.gif";
		private Group mainGroup;
		
		
		
		public WindowSynchronizer(Shell fatherShell, Display display, BusinessDelegate businessDelegate) {
		
		super(businessDelegate);
		this.addControlador(new ControladorFabricacion(businessDelegate, this));
		this.display = display;
		shell = new Shell(fatherShell);
		shell.setSize(UIWindowConstant.WINDOW_WIDTH_STANDARD, UIWindowConstant.WINDOW_HEIGHT_STANDARD);
		shell.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		createContents();
		addListeners();

	}

	private void addListeners() {

		btnStart.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(SelectionEvent arg0) {
				Set<OrdenFabricacionEjecucionVO> list = ((ControladorFabricacion)getControlador()).chequearEjecucion();
				actualizarPanel(list);

			}
		});

	}

	protected void actualizarPanel(Set<OrdenFabricacionEjecucionVO> list) {

		resultado.setText(new Date().toString());
		resultado.append("\r\n");

		if (list == null)
			resultado.append("No hay nada para actualizar");
		else {
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				OrdenFabricacionEjecucionVO vo = (OrdenFabricacionEjecucionVO) iter
						.next();
				resultado.append("Actualizacion ---->" + vo.toString() + " \r\n");
			}

		}

	}

	public void open() {

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}

	private void createContents() {
		mainGroup = new Group(shell, SWT.NORMAL | SWT.MULTI | SWT.READ_ONLY
				| SWT.V_SCROLL);
		mainGroup.setText("Sincronizador de Fabrica");
		mainGroup.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 15,
				UIWindowConstant.WINDOW_HEIGHT_STANDARD - 40);
		mainGroup.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
		mainGroup.setLocation(5, 5);

		btnStart = new Button(mainGroup, SWT.NORMAL);
		btnStart.setLocation(280, 25);
		btnStart.setSize(30, 30);
		btnStart.setImage(new Image(display, IMAGESTARTPATH));
		btnStart.setToolTipText("Comenzar chequeo de fabricacion");
		btnStart.setToolTipText("Guardar tiempo de reproceso");

		resultado = new Text(mainGroup, SWT.BORDER | SWT.MULTI);
		resultado.setLocation(10, 60);
		resultado.setSize(view.common.UIWindowConstant.WINDOW_WIDTH_STANDARD - 35,
				UIWindowConstant.WINDOW_HEIGHT_STANDARD - 105);
	}

}
