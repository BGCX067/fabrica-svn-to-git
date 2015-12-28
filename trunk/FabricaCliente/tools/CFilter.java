package tools;

import java.io.File;


public class CFilter extends javax.swing.filechooser.FileFilter {
    
	@Override
	public boolean accept(File f) 
	{
        return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
    }
    
    @Override
	public String getDescription() 
    {
        return ".xml files";
    }
}
