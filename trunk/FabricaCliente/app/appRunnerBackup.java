package app;

import javax.naming.InitialContext;

import view.aplicationWindow.ApplicationWindow;




public class appRunnerBackup {

		private static InitialContext initialContect;

		public static void main(String[] args) {

			/*ArticuloXML articulo= new ArticuloXML();
			articulo.setCodigo("1232");
			articulo.setColor("Rojo");
			articulo.setDescripcion("Camisa");
			articulo.setLinea("Verano");
			articulo.setOrigen("Arg");
			articulo.setPvu(2);
			articulo.setSeccion("sss");
			articulo.setTalle("L");
			
			articulo.getCentros().add(new CentroDistribucionXML("Rosario"));
			articulo.getCentros().add(new CentroDistribucionXML("Buenos Aires"));
			articulo.getCentros().add(new CentroDistribucionXML("Cordoba"));
			
			XStream xstream = new XStream();
			
			
			xstream.alias("articulo", ArticuloXML.class);
			xstream.alias("centro", CentroDistribucionXML.class);
			
			//Parsear el objeto a XML
			String articuloXML = xstream.toXML(articulo);
			String file = "c:/imagenes/articulo.xml";
			
			FileWriterWrapper fileWriter = new FileWriterWrapper(file);
			fileWriter.write(articuloXML);*/
			
		/*	ArticuloSolicitud articulo= new ArticuloSolicitud();
			articulo.setCantidad(15);
			articulo.setCodigo("13");
			articulo.setDescripcion("Saco");
			
			SolicitudFabricacionXML xml = new SolicitudFabricacionXML();
			xml.setCentroDistribucion("Rosario");
			xml.setFecha(new Date());
			xml.getItems().add(articulo);
			
			XStream xstream = new XStream();
			
			
			xstream.alias("solicitud", SolicitudFabricacionXML.class);
			xstream.alias("articulo", ArticuloXML.class);
			
			//Parsear el objeto a XML
			String solicitudXML = xstream.toXML(xml);
			String file = "c:/imagenes/solicitud.xml";
			
			FileWriterWrapper fileWriter = new FileWriterWrapper(file);
			fileWriter.write(solicitudXML);*/ 
			
			//BusinessDelegate businessDelegate = new BusinessDelegate();
			//VistaEditarProducto  vista = new VistaEditarProducto(businessDelegate);
			//ControladorProductos controlador = new ControladorProductos(businessDelegate, vista);

	/*		 Calendar calendar = Calendar.getInstance();
			 Date today = new Date();
			 Date todaymas = new Date();
		     calendar.setTime(todaymas);
		     int hours = todaymas.getHours();
		     if (hours > 12) 
		    	hours = hours -12;
		     calendar.set(Calendar.HOUR, hours+ 2);
		     todaymas = calendar.getTime();
		     
		     System.out.println(todaymas);
		     System.out.println(today);
		     System.out.println(todaymas.before(today)); */


//	    En el primer caso devuelve true si la segunda fecha es menor a la primera.

	//En el segundo caso devuelve true si la segunda fecha es mayor a la primera.

//	                 En el tercer caso devuelve true si las fechas son iguales.

			
		//	WindowFabrication win = new WindowFabrication();
		//	win.open();

		//	WindowSynchronizer win = new WindowSynchronizer();
		//	win.open();

		//	WindowSynchronizer win = new WindowSynchronizer();
		//	win.open();
		//	Splash splash = new Splash("c:/imagenes/splash.jpg", 4000);
						
			ApplicationWindow win = new ApplicationWindow();
			win.open();
				
			
	/*		AdministradorOrdenFabricacion aof= null;
			
			Hashtable props = new Hashtable();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			//Url completa de ubicacion del servidor de aplicaciones
			props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
			//Objeto del tipo InitialContext
			try {
				initialContect = new InitialContext(props);
				String naming = "Fabrica/AdministradorOrdenFabricacionBean/remote";
				aof = (AdministradorOrdenFabricacion)initialContect.lookup(naming);
				
				OrdenFabricacionVO vo = aof.getOrdenFabricacionByPK(1);
				Map<Long, OrdenFabricacionVO> map = aof.getOrdenesFabricacionPendientes();
				
				System.out.println("frf");
				
				
				
			
				
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */

			
		} 
}
