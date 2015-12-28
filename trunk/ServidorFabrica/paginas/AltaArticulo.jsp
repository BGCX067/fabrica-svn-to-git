<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<html:html>
	<head>
		<title>Alta de un nuevo cliente</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		<html:form action="AltaArticulo.do" focus="name" >
		<table align="left">
		<tr><td colspan="2" class="TitulosTextos">Alta de Articulo</td></tr>
		
		<tr>
		   <td class="textos">Codigo</td>
		   <td><html:text property="codigo" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="codigo"/></font></td>
		</tr>
	
		<tr>
			<td class="textos">Origen</td>
			<td><html:text property="origen" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="color"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Color</td>
			<td><html:text property="color" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="color"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Linea</td>
			<td><html:text property="linea" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="linea"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Talle</td>
			<td><html:text property="talle" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="talle"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Descripcion</td>
			<td><html:text property="descripcion" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="descripcion"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Seccion</td>
			<td><html:text property="seccion" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="seccion"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Cantidad</td>
			<td><html:text property="cantidad" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="cantidad"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Precio Unitario</td>
			<td><html:text property="precio" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="precio"/></font></td>
		</tr>
		
		<tr>
			<td class="textos">Id</td>
			<td><html:text property="idarticulo" size="45" maxlength="45" styleClass="CajaForm" /></td>
		</tr>
		<tr>
			<td><font color=red><html:errors property="idarticulo"/></font></td>
		</tr>
		<tr>
			<td class="textos">Fabricar Item</td>
			<td><html:checkbox property="fabricarItem"/></td>
		</tr>
		<tr>
			<td heigth="10px" colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><p><a href="index.jsp">Volver al menu principal</a></p></td>
		</tr>
		</table>
		<%--Prueba tabla --%>
		<p class="TitulosTextos">Materias Primas</p>
		<table border="1" class="zara">
		
			<tbody>
			<%--  set the header --%> 
			<tr>
				 <td>Check</td> 
				 <td>Descripcion</td>
			     <td>Cantidad</td>      
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="AltaArticuloForm" property="listaMateria"> 
			<tr> 
			    <td colspan="5">No hay materias primas cargadas</td> 
			</tr>
			</logic:empty>  
				<logic:notEmpty name="AltaArticuloForm" property="listaMateria">
					<logic:iterate name="AltaArticuloForm" property="listaMateria" id="bingBong" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%>
					 <td><html:checkbox name="bingBong" property="chk"  indexed="true"/></td>
					 <td><html:text name="bingBong" property="descripcion" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBong" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <html:hidden name="bingBong" property="iden" indexed="true"/>
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
<%--Termina Tabla --%>
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>