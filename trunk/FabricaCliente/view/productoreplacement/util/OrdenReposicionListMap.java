package view.productoreplacement.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ordenreposicion.OrdenReposicionItemVO;
import com.ordenreposicion.OrdenReposicionVO;

public class OrdenReposicionListMap {
	Set<OrdenReposicionMap> ordenesReposicion;

	public OrdenReposicionListMap(Set<OrdenReposicionVO> ordenesRep) {
		super();
		this.ordenesReposicion = new HashSet<OrdenReposicionMap>();
		Iterator iter=ordenesRep.iterator();
		while(iter.hasNext()){
			OrdenReposicionVO or = (OrdenReposicionVO) iter.next();
			OrdenReposicionMap orMap=new OrdenReposicionMap(or.getFecha(),or.getComentario(),or.getCentroDistribucion());
			Iterator iter2=or.getItems().iterator();
			while(iter2.hasNext()){
				OrdenReposicionItemVO oritem = (OrdenReposicionItemVO) iter2.next();
				OrdenReposicionItemMap orItemMap=new OrdenReposicionItemMap(oritem.getArticulo(),oritem.getCantidad());
				orMap.getItems().add(orItemMap);
			}
			this.ordenesReposicion.add(orMap);
		}
		
	}

	public Set<OrdenReposicionMap> getOrdenesReposicion() {
		return ordenesReposicion;
	}

	public void setOrdenesReposicion(Set<OrdenReposicionMap> ordenesReposicion) {
		this.ordenesReposicion = ordenesReposicion;
	}
	
}
