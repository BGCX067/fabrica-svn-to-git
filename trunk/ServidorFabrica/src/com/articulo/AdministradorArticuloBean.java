package com.articulo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.materiaprima.MateriaPrimaCantidad;
import com.materiaprima.MateriaPrimaCantidadVO;
import com.ordenfabricacion.OrdenFabricacionVO;

@Stateless
public class AdministradorArticuloBean implements AdministradorArticulo{
	@PersistenceContext(unitName="FABRICA")
	private EntityManager manager;
	
	public Articulo borrarArticulo(Articulo articulo) {
		manager.remove(getArticuloById(String.valueOf(articulo.getID())));
		return articulo;
	}

	public ArticuloVO getArticuloByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArticuloVO getArticuloByPKVO(long id) {
		
		return createVO(manager.find(Articulo.class, id));
	}
	public Articulo getArticuloByPK(long id) {
		Articulo articulo =manager.find(Articulo.class, id);
		return articulo;
	}

	public Map<Long, Articulo> getArticulos() {
		Map<Long, Articulo> map = new HashMap<Long, Articulo>();
		fillMapArticulo(map, manager.createQuery("select art from Articulo art" ).getResultList());
		return  map;
	}
	
	private void fillMapArticulo(Map<Long, Articulo> map, List<Articulo> list) {
		for (int i = 0; i < list.size(); i++) {
			Articulo articulo = list.get(i);
			map.put(articulo.getID(), articulo);
		}
		
	}

	public Articulo getInstancia() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MateriaPrimaCantidad> getMateriaPrimaByArticulo(Articulo articulo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Articulo insertarArticulo(Articulo articulo) {
		
		manager.persist(articulo);
		return articulo;
	}

	public Articulo modificarArticulo(Articulo articulo) {
		manager.merge(articulo);
		
		return getArticuloByPK(articulo.getID());
	}

	public Map<Long, ArticuloVO> getArticulosByOrdenFabricacion(ArrayList<OrdenFabricacionVO> list) {
		Map<Long, ArticuloVO> map = new HashMap<Long, ArticuloVO>();
		for (int i = 0; i < list.size(); i++) {
			OrdenFabricacionVO ordenFabricacionVO = list.get(i);
			ArticuloVO articuloVO = getArticuloByPKVO(ordenFabricacionVO.getArticuloid());
			map.put(new Long(articuloVO.getID()), articuloVO);
		}
		return map;
	}
	
	public ArticuloVO createVO(Articulo articulo) {
		if (articulo != null)
		{
			ArticuloVO vo = new ArticuloVO(articulo.getID(), createItemCantidadVO(articulo));	

		}	
		return null;
		
	}

	private Set<MateriaPrimaCantidadVO> createItemCantidadVO(Articulo articulo) {
		Set<MateriaPrimaCantidadVO> list = new HashSet<MateriaPrimaCantidadVO>();
		
		Iterator iter = articulo.getMateriaPrimas().iterator();
		
		while (iter.hasNext()) {
			MateriaPrimaCantidad materiaPrima = (MateriaPrimaCantidad) iter.next();
			MateriaPrimaCantidadVO vo = new MateriaPrimaCantidadVO(materiaPrima.getMateriaPrima().getID(), materiaPrima.getCantidad());
		}
		return list;
	}

	public Articulo getArticuloByCodigo(String codigo) {
		String SQL = "Select art FROM Articulo art where art.codigo = '"+codigo+"'";
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return (Articulo)list.get(0);
		return null;
		
	}
	
	public Articulo getArticuloById(String id) {
		String SQL = "Select art FROM Articulo art where art.id = '"+id+"'";
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return (Articulo)list.get(0);
		return null;
		
	}

	public long getUltimoTiempoFabricacion(Articulo articulo) {
		String SQL = "select max(mps.materiaPrima.tiempoFabricacion) from Articulo as art " +
				"inner join  art.materiaPrimas mps " +
				"where art.ID= "+ articulo.getID();
    
     
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return ((Long) list.get(0)).longValue();
		return 0;
	}

	public boolean validarCodigo(String text) {
		String SQL = "select art from Articulo art where art.codigo = '"+text+"'";
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return false;
		else
			return true;
	}

	public ArticuloStrutsVO getNewArticulo() {
		String SQL = "Select art FROM Articulo art where art.nuevo = '"+true+"'";
		List list = manager.createQuery(SQL).getResultList();
		if (list.size() > 0)
			return new ArticuloStrutsVO((Articulo)list.get(0));
		return null;
	}
}
