<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<html:html>
	<head>
		<title>Busqueda de Ordenes Fabricacion</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		<html:form action="BusquedaOrdenes.do" focus="name" >
		
		<table border="1" class="zara"> 
			<tbody>
			<h3>Orden de Fabricacion<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Check</td>
				 <td>Id</td>
			     <td>Articulo</td> 
			     <td>Estado</td>
			     <td>Cantidad</td>
			     <td>Cant Fabricada</td>
			     <td>Pendiente</td>
			     <td>Distribuido</td>
			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="BusquedaOrdenesForm" property="listaBusquedaOrdenes"> 
			<tr> 
			    <td colspan="5">No Ordenes de busquedas</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="BusquedaOrdenesForm" property="listaBusquedaOrdenes">
					<logic:iterate name="BusquedaOrdenesForm" property="listaBusquedaOrdenes" id="bingBong" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%> 
					 <td><html:checkbox name="bingBong" property="chk"  indexed="true"/></td> 
					 <td><html:text name="bingBong" property="id" size="5" maxlength="5" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBong" property="articulo" size="15" maxlength="25" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBong" property="estado" size="15" maxlength="15" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBong" property="cantidad" size="5" maxlength="5" indexed="true" styleClass="SinRecuadroForm" /></td>
		 			 <td><html:text name="bingBong" property="cantidadFabricada" size="5" maxlength="5" indexed="true" styleClass="SinRecuadroForm" /></td>
		 			 <td><html:text name="bingBong" property="fabricado" size="10" maxlength="15" indexed="true"  styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBong" property="distribuido" size="15" maxlength="25" indexed="true"  styleClass="SinRecuadroForm"/></td>
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
			
			
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>