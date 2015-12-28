package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Clase Wrapper para escribir en un archivo
 *
 */
public class FileWriterWrapper {
	private String file;

	public FileWriterWrapper(String file){
		this.file = file;
	}

	

	public void write(String contenido){
		try
		{
			FileOutputStream out = new FileOutputStream(file);
			PrintStream printStream = new PrintStream( out );
			printStream.print (contenido);
			printStream.close();
		}
		catch (Exception e)
		{
			System.err.println ("Error al escribir en el archivo");
		}
	}
}
