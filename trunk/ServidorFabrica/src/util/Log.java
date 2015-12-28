package util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Log {
	private static File file = new File("c:/server.log");
	
	public static void writeLog(String info) throws IOException
	{
		
		RandomAccessFile logFile= new RandomAccessFile(file,"rw" );
        // Nos vamos al final del fichero
		logFile.seek( logFile.length() );
        // Incorporamos la cadena al fOichero
		
		logFile.writeBytes( info );
        // Cerramos el fichero
		logFile.close();

	}
		
}
	
	

