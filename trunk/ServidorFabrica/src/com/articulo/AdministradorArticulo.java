package com.articulo;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import com.centrodistribucion.CentroDistribucion;
import com.materiaprima.MateriaPrimaCantidad;
import com.ordenfabricacion.OrdenFabricacionVO;

@Local
public interface AdministradorArticulo {
	
	public Articulo getInstancia();
	public Map<Long, Articulo> getArticulos();
	public ArticuloVO getArticuloByPKVO(long id);
	public Articulo getArticuloByPK(long id);
	public ArticuloVO getArticuloByNombre(String nombre);
	public Articulo insertarArticulo(Articulo articulo);
	public Articulo modificarArticulo(Articulo articulo);
	public Articulo borrarArticulo(Articulo articulo);
	public ArrayList<MateriaPrimaCantidad> getMateriaPrimaByArticulo(Articulo articulo);
	public Map<Long, ArticuloVO> getArticulosByOrdenFabricacion(ArrayList<OrdenFabricacionVO> list);
	public ArticuloVO createVO(Articulo articulo);
	public Articulo getArticuloByCodigo(String codigo);
	public long getUltimoTiempoFabricacion(Articulo articulo);
	public boolean validarCodigo(String text);
	public ArticuloStrutsVO getNewArticulo();
	
}
