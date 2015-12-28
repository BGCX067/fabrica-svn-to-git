package util;

import java.io.StringWriter;

import com.thoughtworks.xstream.XStream;

import dtos.materiaprima.MateriaPrimaDTO;
import dtos.materiaprima.MateriaPrimaItemDTO;
import dtos.ordencompra.OrdenCompraDTO;
import dtos.ordencompra.OrdenCompraItemDTO;






public class ZaraXMLConverterFabrica
{
	private XStream xstream;
	
	public ZaraXMLConverterFabrica()
	{
		super();
		
		this.xstream = new XStream();
		

		this.addAlias("MateriaPrima",MateriaPrimaDTO.class);
		this.addAlias("MateriaPrimaItem",MateriaPrimaItemDTO.class);
		this.addAlias("OrdenCompra",OrdenCompraDTO.class);
		this.addAlias("OrdenCompraItem",OrdenCompraItemDTO.class);
	}
	
	public void addAlias(String alias, Class clazz)
	{
		this.xstream.alias(alias,clazz);
	}
	
	public String toXml(Object obj)
	{
		StringWriter buffer = new StringWriter();
		
		this.xstream.toXML(obj,buffer);
		
		return buffer.toString();
	}
	
	public Object fromXml(String xml)
	{
		return this.xstream.fromXML(xml);
	}
}
