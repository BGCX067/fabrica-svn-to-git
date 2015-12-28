package view.productoreplacement.util;

import java.util.Iterator;
import java.util.Set;

import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionItemVO;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.SolicitudFabricacionItemVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;

public class ProductReplacementWindowLog {
	public static void imprimirOrdenesReposicion(Set<OrdenReposicionVO> orSet) {
		StringBuilder sb=new StringBuilder();
		sb.append("**********************\nOrdenes de Reposicion:");
		Iterator iter=orSet.iterator();
		while (iter.hasNext()) {
			OrdenReposicionVO or = (OrdenReposicionVO) iter.next();
			sb.append("\n Orden de Reposicion ID= "+or.getID()+"\n"+
					"\t Centro de Distribucion= "+or.getCentroDistribucion()+"\n"+
					"\t Estado= "+or.getEstado()+"\n"+
					"\t Fecha"+or.getFecha()+"\n"+
					"\t Items:");
			Iterator iter2=or.getItems().iterator();
			while(iter2.hasNext()){
				OrdenReposicionItemVO orVO=(OrdenReposicionItemVO) iter2.next();
				sb.append("\t\t Articulo= "+orVO.getArticuloId()+"\n"+
						"\t\t Cantidad= "+orVO.getCantidad()+"\n"+
						"\t\t Items Fabricacion= ");
				Iterator iter3=orVO.getItemFabricacion().iterator();
				while(iter3.hasNext())
				{
					SolicitudFabricacionItemVO ofVO=(SolicitudFabricacionItemVO) iter3.next();
					sb.append("\n\t\t\t Solicitud Fabric. ID="+ofVO.getID());
				}
			}
		}
		System.out.println(sb.toString());
	}

	public static void imprimirOrdenesFabricacion(Set<OrdenFabricacionVO> ordenes2) {
		StringBuilder sb=new StringBuilder();
		sb.append("**************************\nOrdenes de Fabricacion existentes:\n");
		Iterator iter=ordenes2.iterator();
		while(iter.hasNext()){
			OrdenFabricacionVO sfVO=(OrdenFabricacionVO) iter.next();
			sb.append("-------------------------\nOrden de Fabricacion ID= "+sfVO.getID()+"\n"+
					"\t Articulo= "+sfVO.getArticuloid()+"\n"+
					"\t Cantidad Fabric.= "+sfVO.getCantidadFabricada()+"\n"+
					"\t Cantidad Distrib.= "+sfVO.getCantidadDistribuida()+"\n"+
					"\t Estado= "+sfVO.getEstadoDescripcion()+"\n");
		}
		System.out.println(sb.toString());
	}

	public static void imprimirSolicitudes(Set<SolicitudFabricacionVO> solFabList) {
		StringBuilder sb=new StringBuilder();
		sb.append("**************************\nSolicitudes existentes:\n");
		Iterator iter=solFabList.iterator();
		while(iter.hasNext()){
			SolicitudFabricacionVO sfVO=(SolicitudFabricacionVO) iter.next();
			sb.append("\t**-----**\nSolicitud ID= "+sfVO.getID()+"\n" +
					"\t Fecha= "+sfVO.getFecha()+"\n" +
					"\t Estado= "+sfVO.getEstado()+"\n" +
					"\t Centro Distribucion= "+sfVO.getCentroDistribucion().getNombre()+"\n" +
					"\t Items:");
			Iterator iter2=sfVO.getItems().iterator();
			while(iter2.hasNext())
			{
				SolicitudFabricacionItemVO sfItemVO=(SolicitudFabricacionItemVO) iter2.next();
				sb.append("\n\t\t Articulo= "+sfItemVO.getArticulo().getDescripcion()+"\n"+
						"\t\t Cantidad= "+sfItemVO.getCantidad()+"\n"+
						"\t\t Cantidad Fabricada= "+sfItemVO.getCantidadFabricada()+"\n\t--------");
			}
		}
		System.out.println(sb.toString());
		
	}
}
