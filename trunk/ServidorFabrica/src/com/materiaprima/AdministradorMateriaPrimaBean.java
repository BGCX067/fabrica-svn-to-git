package com.materiaprima;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import legacy.ProcesarArticulosBean;

import org.apache.log4j.Logger;

import util.Log;


@Stateless
public class AdministradorMateriaPrimaBean implements AdministradorMateriaPrima {

	@PersistenceContext(unitName="FABRICA")
	private EntityManager manager;
	Logger logger = Logger.getLogger(AdministradorMateriaPrimaBean.class);
	
	public Map<Long, MateriaPrima> getMateriasPrimas() {
		Map<Long, MateriaPrima> map = new HashMap<Long, MateriaPrima>();
		fillMapMateriaPrima(map, manager.createQuery("select mp from MateriaPrima mp" ).getResultList());
		return  map;
	}
	
	public Map<Long, MateriaPrimaStockVO> getMateriasPrimasStock(){
		Map<Long, MateriaPrimaStockVO> map = new HashMap<Long, MateriaPrimaStockVO>();
		fillMapMateriaPrimaStock(map, manager.createQuery("select mp from MateriaPrima mp" ).getResultList());
		return map;
	}
	
	private void fillMapMateriaPrimaStock(Map<Long, MateriaPrimaStockVO> map, List list) {
		for (int i = 0; i < list.size(); i++) {
			MateriaPrima materiaPrima = (MateriaPrima) list.get(i);
			map.put(materiaPrima.getID(), materiaPrima2Stock(materiaPrima));
		}
		
	}

	private MateriaPrimaStockVO materiaPrima2Stock(MateriaPrima materiaPrima) {
		
		return new MateriaPrimaStockVO(materiaPrima.getID(), materiaPrima.getStock());
	}

	private void fillMapMateriaPrima(Map<Long, MateriaPrima> map, List<MateriaPrima> list) {
		for (int i = 0; i < list.size(); i++) {
			MateriaPrima materiaPrima = list.get(i);
			map.put(materiaPrima.getID(), materiaPrima);
		}
	}

	public MateriaPrima borrarMateriaPrima(MateriaPrima materiaPrima) {
		
		// TODO Auto-generated method stub
		
		return null;
	}

	public MateriaPrima getMateriaPrimaByDescripcion(String descripcion) {
		
		// TODO Auto-generated method stub
		
		return null;
	}

	public MateriaPrima getMateriaPrimaByCodigo(String codigo) {
		List list = manager.createQuery("select mp from MateriaPrima mp where mp.codigo='"+codigo+"'").getResultList();

		if (list.size() > 0)
			return (MateriaPrima)list.get(0);
		return null;
		
	}

	public MateriaPrima insertarMateriaPrima(MateriaPrima materiaPrima) {
		manager.persist(materiaPrima);
		return null;
	}

	public void modificarMateriaPrimaCantidad(MateriaPrimaCantidadVO materiaPrimaVO) {
		MateriaPrimaCantidadVO mp=manager.merge(materiaPrimaVO);
		manager.persist(mp);
	}

	public MateriaPrima getInstancia() {
		// TODO Auto-generated method stub
		return null;
	}

	public MateriaPrima ingresarStockMateriaPrima(MateriaPrima materiaPrima, long cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

	public MateriaPrima modificarMateriaPrima(MateriaPrima materiaPrima) {
		
		return manager.merge(materiaPrima);
	}

	public MateriaPrima getMateriaPrimaByPK(long id) {
		
		return manager.find(MateriaPrima.class, id);
	}

	public Long ingresarStockMateriaPrima(String codigo, Long cantidad) {
		
		MateriaPrima mp=getMateriaPrimaByCodigo(codigo);
		if (mp != null)
		{
			mp.setStock(mp.getStock()+cantidad);
			manager.persist(mp);
			logger.info("Materia prima ingresada --> "+codigo);
			return mp.getStock();
		}
		else
			logger.error("Materia prima inexistente --> "+codigo);
		
			
		
		return null;
	}

	public String getMateriaPrimaDescripcion(String codigo) {
		// TODO Auto-generated method stub
		MateriaPrima mp=getMateriaPrimaByCodigo(codigo);
		return mp.getDescripcion();
	}
	
	public void insertarMateriaPrimaCantidad(MateriaPrimaCantidad mpc)
	{
		mpc.setCantidad(25);
		mpc.setMateriaPrima(getMateriaPrimaByPK(1));
		manager.persist(mpc);
	}

}
