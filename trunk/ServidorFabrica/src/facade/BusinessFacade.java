package facade;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

import com.articulo.Articulo;
import com.articulo.ArticuloStrutsVO;
import com.articulo.ArticuloVO;
import com.centrodistribucion.CentroDistribucionVO;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidad;
import com.ordencompra.OrdenCompra;
import com.ordenfabricacion.OrdenFabricacionEjecucionVO;
import com.ordenfabricacion.OrdenFabricacionStockVO;
import com.ordenfabricacion.OrdenFabricacionVO;
import com.ordenreposicion.OrdenReposicionVO;
import com.solicitudfabricacion.SolicitudFabricacionVO;
import com.solicitudfabricacion.xml.SolicitudFabricacionXML;

@Remote
public interface BusinessFacade {

	public Map<Long, ArticuloVO> getArticulosByOrdenFabricacion(ArrayList<OrdenFabricacionVO> list);
	public void getOrdenFabricacionByPK(long id);
	public Map<Long, OrdenFabricacionVO> getOrdenesFabricacionPendientes();
	public Map<Long, MateriaPrima> getMateriasPrimas();
	public MateriaPrima  insertarMateriaPrima(MateriaPrima materiaPrima);
	public Set<OrdenFabricacionEjecucionVO> chequearEjecucion();
	public Set<OrdenFabricacionStockVO> validarStock(ArrayList<OrdenFabricacionVO> list);
	public OrdenCompra inicializarFabricacion(ArrayList<OrdenFabricacionVO> list);
	public Set<OrdenReposicionVO> getOrdenesReposicion();
	public Set<OrdenFabricacionVO> getOrdenesFabricacionTerminados();
	public Set<OrdenReposicionVO> getOrdenesReposicionPendiente();
	public Set<SolicitudFabricacionVO> getSolicitudesFabricacionPendientes();
	public void tramitarRecepcionSolicitud(SolicitudFabricacionXML solicitud);
	public void insertarArticulo(Articulo articuloNuevo,  boolean fabricacion, long cantidad);
	public Long ingresarStockMateriaPrima(String codigo, Long cantidad);
	public String getMateriaPrimaDescripcion(String codigo);
	public void crearOrdenesReposicion(Set<OrdenReposicionVO> ordenRep, Set<SolicitudFabricacionVO> SolFab, Set<OrdenFabricacionVO> ordenFab);
	public void insertarMateriaPrimaCantidad(MateriaPrimaCantidad mpc);
	public boolean validarCodigo(String text);
	public Set<OrdenReposicionVO> actualizarOrdenReposicion();
	public ArticuloStrutsVO getNewArticulo();
	public void borrarArticulo(Articulo articuloNuevo);
	public void modificarArticulo(Articulo articuloNuevo, boolean fabricacion, long cantidad);
	public boolean recibirSolicitud(String in0);
	public CentroDistribucionVO getCentroDistribucionVOByPK(Long codigo);
	public Articulo getArticuloByPK(long id);
}
