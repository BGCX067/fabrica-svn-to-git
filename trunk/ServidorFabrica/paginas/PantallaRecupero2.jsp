<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<html:html>
	<head>
		<title>Busqueda de Ordenes de Reposicion</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		<html:form action="PantallaRecupero2.do" focus="name" >
		
		<table border="1" class="zara"> 
			<tbody>
			<h3>Orden de Reposicion<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Codigo Fabricacion</td>
				 <td>Codigo de producto</td>
			     <td>Nombre</td>
			     <td>Cantidad</td>
			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="Recupero2Form" property="listaRecupero2"> 
			<tr> 
			    <td colspan="5">No Ordenes de busquedas</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="Recupero2Form" property="listaRecupero2">
					<logic:iterate name="Recupero2Form" property="listaRecupero2" id="bingBongR2" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%>  
					 <td><html:text name="bingBongR2" property="codigoFabricacion" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongR2" property="codigoDeProducto" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongR2" property="nombre" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongR2" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
			
			
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>