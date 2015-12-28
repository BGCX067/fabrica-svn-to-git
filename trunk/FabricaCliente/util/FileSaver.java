package util;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class FileSaver {

	public static String openSaveDialog(Shell shell,String extensions)
	{
		FileDialog fd=new FileDialog(shell,SWT.SAVE);
		fd.setFilterExtensions(new String[]{extensions});
		String fileurl=fd.open();
		return fileurl;
	}
}
