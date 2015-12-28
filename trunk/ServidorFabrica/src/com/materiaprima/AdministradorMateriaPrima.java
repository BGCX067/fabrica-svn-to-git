package com.materiaprima;

import java.util.Map;

import javax.ejb.Local;

@Local
public interface AdministradorMateriaPrima{
	
	public MateriaPrima  getMateriaPrimaByPK(long id);
	public MateriaPrima  getMateriaPrimaByCodigo(String codigo);
	public MateriaPrima  getMateriaPrimaByDescripcion(String descripcion);
	public MateriaPrima  insertarMateriaPrima(MateriaPrima materiaPrima);
	public MateriaPrima  modificarMateriaPrima(MateriaPrima materiaPrima);
	public MateriaPrima  borrarMateriaPrima(MateriaPrima materiaPrima);
	public MateriaPrima getInstancia();
	public Map<Long, MateriaPrima> getMateriasPrimas();
	public void modificarMateriaPrimaCantidad(MateriaPrimaCantidadVO materiaPrimaCantidadVO);
	public Map<Long, MateriaPrimaStockVO> getMateriasPrimasStock();
	public Long ingresarStockMateriaPrima(String codigo, Long cantidad);
	public String getMateriaPrimaDescripcion(String codigo);
	public void insertarMateriaPrimaCantidad(MateriaPrimaCantidad mpc);
}
