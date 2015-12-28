<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<title>Alta articulo</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>
	<body>
		<h3>El Articulo es <bean:write name="AltaArticuloForm" property="descripcion"/> ha sido creado correctamente.</h3>
		<p>Propiedades del producto creado:</p>
		<ul>
			<li>Codigo: <bean:write name="AltaArticuloForm" property="codigo"/></li>
			 <li>Origen: <bean:write name="AltaArticuloForm" property="origen"/></li>
			<li>Color: <bean:write name="AltaArticuloForm" property="color"/></li>
			<li>Linea: <bean:write name="AltaArticuloForm" property="linea"/></li>
			<li>Talle: <bean:write name="AltaArticuloForm" property="talle"/></li>
			<li>Descripcion: <bean:write name="AltaArticuloForm" property="descripcion"/></li>
		</ul>
		<hr>
		 
					<table border="1" class="zara"> 
					<tbody>
					<%--  set the header --%> 
					<tr> <td>Check</td>
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
		<p><a href="index.jsp">Volver al menú principal.</a></p>
	</body>
</html:html>
