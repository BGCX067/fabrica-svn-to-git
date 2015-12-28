package com.ordencompra;

import java.util.Date;
import java.util.Set;

import javax.ejb.Local;

import com.materiaprima.AdministradorMateriaPrima;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaVOCompra;
import com.ordencompra.estado.OrdenCompraEstado;
import com.ordencompra.estado.OrdenCompraItemEstado;

@Local
public interface AdministradorOrdenCompra {
	
	public OrdenCompra  getOrdenCompraByPK(long id);
	public OrdenCompra  getInstancia();
	public OrdenCompra  getOrdenCompraByFecha(Date fecha);
	public OrdenCompra  insertarOrdenCompra(OrdenCompra ordenCompra);
	public void crearItem(OrdenCompra ordenCompra, MateriaPrima materia,long cantidad);
	public OrdenCompraEstado findEstadoByPK(long id) ;
	public OrdenCompraItemEstado findItemEstadoByPK(long id) ;
	public OrdenCompra crearCompraFabrica(Set<MateriaPrimaVOCompra> name, AdministradorMateriaPrima administradorMateriaPrima);
	

}
