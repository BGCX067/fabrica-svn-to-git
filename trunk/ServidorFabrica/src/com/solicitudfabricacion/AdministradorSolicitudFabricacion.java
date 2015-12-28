package com.solicitudfabricacion;

import java.util.Date;
import java.util.Set;

import javax.ejb.Local;

import zara.commons.dtos.centrodistribucion.CentroDistribucionDTO;

import com.articulo.AdministradorArticulo;
import com.centrodistribucion.CentroDistribucion;
import com.ordenreposicion.AdministradorOrdenReposicion;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

@Local
public interface AdministradorSolicitudFabricacion {
	public SolicitudFabricacion  getSolicitudFabricacionByPK(long id);
	public SolicitudFabricacion  getSolicitudFabricacionByFecha(Date fecha);
	public SolicitudFabricacion  insertarSolicitudFabricacion(SolicitudFabricacion solicitudFabricacion);
    public SolicitudFabricacion  getInstancia();
	public void crearSolicitud(SolicitudFabricacionXML solicitud, AdministradorArticulo administradorArticulo);
	public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes();
	public Set<CentroDistribucion> obtenerCentros(Set<CentroDistribucionDTO> centros);
	public SolicitudFabricacionXML recibirSolicitud(String in0, AdministradorArticulo administradorArticulo);
}
