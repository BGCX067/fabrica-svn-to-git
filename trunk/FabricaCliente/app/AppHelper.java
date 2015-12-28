package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.articulo.ArticuloVO;
import com.materiaprima.MateriaPrima;
import com.materiaprima.MateriaPrimaCantidadVO;

public  class AppHelper {
		
	private static Map<Long, ArticuloVO> mapArticulos = new HashMap<Long, ArticuloVO>();
	private static Map<Long, MateriaPrima> mapMateriaPrima = new HashMap<Long, MateriaPrima>();
	
	public static Map<Long, ArticuloVO> llenarMapArticulos()
	{
		ArticuloVO articuloVO = crearNuevoArticuloVO();
		mapArticulos.put(articuloVO.getID(), articuloVO);
		return mapArticulos;
	}
	
	public static Map<Long, MateriaPrima> llenarMapMateriaPrima()
	{
		MateriaPrima materiaPrima = crearNuevaMateriaPrima();
		mapMateriaPrima.put(materiaPrima.getID(), materiaPrima);
		MateriaPrima materiaPrima1 = crearNuevaMateriaPrima1();
		mapMateriaPrima.put(materiaPrima1.getID(), materiaPrima1);
		return mapMateriaPrima;
	}

	private static MateriaPrima crearNuevaMateriaPrima() {
		
		return new MateriaPrima(1,"234", 3, 1);
	}
	private static MateriaPrima crearNuevaMateriaPrima1() {
		
		return new MateriaPrima(2,"234", 3, 1);
	}

	private static ArticuloVO crearNuevoArticuloVO() {
		
		return new ArticuloVO(1, createItemsMateriaPrimaCantidad());
		
	}

	private static  Set<MateriaPrimaCantidadVO> createItemsMateriaPrimaCantidad() {
		Set<MateriaPrimaCantidadVO> items = new HashSet<MateriaPrimaCantidadVO>();
		
		items.add(new MateriaPrimaCantidadVO(1, 1));
		items.add(new MateriaPrimaCantidadVO(2, 1));
		return items;
	}
}
