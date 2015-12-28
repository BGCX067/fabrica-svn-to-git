package webServices;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import reposicion.webservices.commons.zara.RecibirReposicion;
import reposicion.webservices.commons.zara.RecibirReposicionService;
import reposicion.webservices.commons.zara.RecibirReposicionServiceLocator;
import solicituddistribucion.webservices.commons.zara.RecibirSolicitudDistribucion;
import solicituddistribucion.webservices.commons.zara.RecibirSolicitudDistribucionService;
import solicituddistribucion.webservices.commons.zara.RecibirSolicitudDistribucionServiceLocator;
import zara.commons.dtos.centrodistribucion.CentroDistribucionDTO;
import zara.commons.dtos.reposicion.ItemReposicionDTO;
import zara.commons.dtos.reposicion.ReposicionDTO;
import zara.commons.dtos.solicituddistribucion.ItemSolicitudDistribucionDTO;
import zara.commons.dtos.solicituddistribucion.SolicitudDistribucionDTO;
import zara.commons.dtos.tienda.TiendaDTO;
import zara.commons.xml.ZaraXMLConverter;



public class LlamarWS {
	public LlamarWS(){
//Carga RepAF y Soldist
//		xmlsoldist="Hola";
		ReposicionDTO repAF=new ReposicionDTO();
		repAF=cargarRepAF();
		SolicitudDistribucionDTO soldist = new SolicitudDistribucionDTO();
		soldist= cargarSoldist();

//Convierte a String
		ZaraXMLConverter converter = new ZaraXMLConverter();
		String xmlrepAF;
		String xmlsoldist;
		xmlrepAF=converter.toXml(repAF);
		xmlsoldist=converter.toXml(soldist);
		// Make a service
		RecibirReposicionService service = new RecibirReposicionServiceLocator();
		RecibirSolicitudDistribucionService service2 = new RecibirSolicitudDistribucionServiceLocator();
        try {
        // Now use the service to get a stub which implements the SDI.
         RecibirReposicion port = service.getRecibirReposicion();
        RecibirSolicitudDistribucion port2 = service2.getRecibirSolicitudDistribucion();
        // Make the actual calls
        
       boolean result = port.recibirReposicion(xmlrepAF);
      System.out.println("el resultado de repAF es "+result);

        result = port2.recibirSolicitudDistribucion(xmlsoldist);
       System.out.println("el resultado de Soldist es "+result);

		SolicitudDistribucionDTO soldis;
		soldis=(SolicitudDistribucionDTO)converter.fromXml(xmlsoldist);
		
    	System.out.println("Codigo Soldist: "+soldis.getCodigo());
       System.out.println("Fecha Soldist: "+soldis.getFecha());

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        ///////////////////////////////////////
 //       PruebaBean pb= new PruebaBean();
  //      pb.Reposicion(xmlrepAF);
	}
	
	public ReposicionDTO cargarRepAF(){
		CentroDistribucionDTO cd = new CentroDistribucionDTO();
		cd.setCodigo((long)3);
		cd.setNombre("Centro distibucion PEPITO");
		Set<ItemReposicionDTO> items= new HashSet<ItemReposicionDTO>();	
		Object[] it;

		ReposicionDTO rep= new ReposicionDTO();
		
		for(int i=1;i<3;i++){
			rep.setCodigo((long)i);
			rep.setCentroDistribucion(cd);
//			rep.setFecha(Calendar.getInstance());
			rep.setFecha(new Date());
			for(int j=0;j<5;j++){
				ItemReposicionDTO itemrep= new ItemReposicionDTO();
				itemrep.setIdArticulo((long)2001+j);
				itemrep.setCantidad((int)(Math.random()*100));
				items.add(itemrep);
			}
			rep.setItems(items);			
		}
//		it=rep.getItems();
//		System.out.println(((ItemReposicionDTO)it[3]).getIdArticulo());
		return rep;
	}

	public SolicitudDistribucionDTO cargarSoldist(){

		Set <ItemSolicitudDistribucionDTO>listaSoldist=new HashSet<ItemSolicitudDistribucionDTO>();
		SolicitudDistribucionDTO sol= new SolicitudDistribucionDTO();
		
		
//		for(int i=1;i<3;i++){
			sol.setCodigo((long)2);
			sol.setFecha(new Date());
			for(int j=0;j<5;j++){
				TiendaDTO tienda = new TiendaDTO();
				ItemSolicitudDistribucionDTO itemsol= new ItemSolicitudDistribucionDTO();
				itemsol.setIdArticulo((long)4001+j);
				itemsol.setCantidad((int)(Math.random()*100));
				tienda.setCodigo((long)25-j);
				tienda.setNombre("Tienda"+(25-j));
				itemsol.setTienda(tienda);
				listaSoldist.add(itemsol);
//				items[j]=itemsol;
			}
			sol.setItems(listaSoldist);			
//		}
		return sol;
	}

}
