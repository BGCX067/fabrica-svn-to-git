/*
 * Created on 18-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package view.aplicationWindow;

/**
 * @author mdellano
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


/*
* Shell example snippet: create a splash screen
*
* For a list of all SWT example snippets see
* http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
*/
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Splash {

public Splash(String imagen,int tiempo) {
	
	
	final Display display=new Display();
	
	Shell shell=new Shell(display,SWT.BORDER|SWT.TOOL|SWT.NO_TRIM );
	
	Image image = new Image(display, imagen);
	ImageData imdata = image.getImageData();
	shell.setSize(imdata.width+3, imdata.height+3);
	Rectangle r = display.getBounds();
	int shellX = (r.width - imdata.width)/2;
	int shellY = (r.height - imdata.height)/2;
	shell.setLocation(shellX, shellY);
	
	//splash.setImage(ss.getImage());
	
shell.open();
GC gc = new GC(shell);
gc.drawImage(image, 0, 0);

display.update();
	
		
			// TODO Auto-generated method stub
			try {Thread.sleep(tiempo);
			
			image.dispose();
			shell.dispose();
			display.dispose();
			
			} catch (Throwable e) {}
	

}
}