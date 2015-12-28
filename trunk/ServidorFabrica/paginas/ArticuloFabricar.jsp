<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<html:html>
<head>
		<title>Estado de fabricacion</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
</head>
<body>

		<%-- Tabla de Articulo a fabricar COMIENZO --%>
		<html:form action="ArticuloFabricar.do" focus="name" >
		
		
		<table border="1" class="zara"> 
			<tbody>
			<h3>Articulo Fabricados<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Articulo</td>
			     <td>Cantidad</td>
			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="BusquedaOrdenesForm" property="listaArticuloFabricar"> 
			<tr> 
			    <td colspan="5">No hay Articulos Cargados</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="BusquedaOrdenesForm" property="listaArticuloFabricar">
					<logic:iterate name="BusquedaOrdenesForm" property="listaArticuloFabricar" id="bingBongFabricar" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%> 
					 <td><html:text name="bingBongFabricar" property="articulo" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongFabricar" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <html:hidden name="bingBongFabricar" property="id" indexed="true" disabled="true"/>
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
		<%-- Tabla de Articulo a fabricar FIN --%>
		<%-- Tabla de Articulo que no se pueden fabricar COMIENZO --%>
		<table border="1" class="zara"> 
			<tbody>
			<h3>Articulo a Orden de Compra<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Articulo</td>
			     <td>Cantidad</td> 			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="BusquedaOrdenesForm" property="listaArticuloNoFabricar"> 
			<tr> 
			    <td colspan="5">No hay Articulos Cargados</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="BusquedaOrdenesForm" property="listaArticuloNoFabricar">
					<logic:iterate name="BusquedaOrdenesForm" property="listaArticuloNoFabricar" id="bingBongNo" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%> 
					 <td><html:text name="bingBongNo" property="articulo" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongNo" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <html:hidden name="bingBongNo" property="id" indexed="true" disabled="true"/>
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
			<%--Tabla de Articulo que no se pueden fabricar FIN--%>
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
		
	</body>
</html:html>