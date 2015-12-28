package com.ordenreposicion;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.ejb.Local;

import com.centrodistribucion.CentroDistribucionVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;

import facade.BusinessFacade;

@Local
public interface AdministradorOrdenReposicion {
	public OrdenReposicion  getOrdenReposicionByPK(long id);
	public OrdenReposicion  getOrdenReposicionByFecha(Date fecha);
	public OrdenReposicion  insertarOrdenReposicion(OrdenReposicion ordenReposicion);
    public OrdenReposicion getInstancia(Long centrodistribucion, Date date, String comentario);
    public Set<OrdenReposicionVO> getOrdenesReposicion();
	public Set<OrdenReposicionVO> getOrdenesReposicionPendiente();
	public void crearOrdenesReposicion(Set<OrdenReposicionVO> ordenRep, Set<SolicitudFabricacionVO> solFab, Set<OrdenFabricacionVO> ordenFab);
	public Set<OrdenReposicionVO> actualizarOrdenReposicion(BusinessFacade bf);
	public CentroDistribucionVO getCentroDistribucionVOByPK(Long centrodistribucion);
	public boolean tramitarSolicitudesReposicion(Set<OrdenReposicionVO> ordenesReposicionVO,BusinessFacade bf);
}
