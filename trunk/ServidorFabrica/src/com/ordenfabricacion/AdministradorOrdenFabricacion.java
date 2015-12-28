package com.ordenfabricacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.ejb.Local;

import com.articulo.AdministradorArticulo;
import com.articulo.Articulo;
import com.materiaprima.AdministradorMateriaPrima;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaStockVO;
import com.materiaprima.MateriaPrimaVOCompra;
import com.ordencompra.AdministradorOrdenCompra;
import com.ordencompra.OrdenCompra;
import com.ordenfabricacion.estado.OrdenFabricacionEjecucionEstado;
import com.ordenfabricacion.estado.OrdenFabricacionEstado;
import com.solicitudfabricacion.xml.ArticuloSolicitud;

@Local
public interface AdministradorOrdenFabricacion {
	
	public OrdenFabricacionVO  getOrdenFabricacionByPK(long id);
	public OrdenFabricacion  getOrdenFabricacionPersistByPK(long id);
	public OrdenFabricacionVO  getOrdenFabricacionByFecha(Date fecha);
	public OrdenFabricacion  insertarOrdenFabricacion(OrdenFabricacionVO ordenFabricacion);
	public OrdenFabricacion  actualizarOrdenFabricacion(OrdenFabricacion ordenFabricacion);
    public OrdenFabricacion  getInstancia();
    public Map<Long, OrdenFabricacionVO>  getOrdenesFabricacionPendientes();
    public OrdenFabricacionVO createVO(OrdenFabricacion ordenFabricacion);
    public OrdenFabricacion merge(OrdenFabricacionVO ordenFabricacion);
    public Set<OrdenFabricacionStockVO> validarStock(Map<Long, MateriaPrimaStockVO> mapMateriaPrima, Map<Long, Articulo> mapArticulo, ArrayList<OrdenFabricacionVO> list);
	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion(AdministradorArticulo administradorArticulo);
	public OrdenFabricacionEjecucionEstado findEstadoEjecucionByPK(long id);
	public OrdenFabricacionEstado findEstadoByPK(long id);
	public Set<MateriaPrimaVOCompra> inicializarFabricacion(AdministradorMateriaPrima administradorMateriaPrima, AdministradorArticulo administradorArticulo,  ArrayList<OrdenFabricacionVO> list);
	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados();
	public void fabricarSolicitud(Set<ArticuloSolicitud> items, AdministradorArticulo administradorArticulo);
	public void crearOrden(Articulo articulo, AdministradorArticulo administradorArticulo, long cantidad);
}
