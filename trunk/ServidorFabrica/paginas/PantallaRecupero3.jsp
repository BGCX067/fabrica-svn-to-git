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
		<html:form action="PantallaRecupero3.do" focus="name" >
		
		<table border="1" class="zara"> 
			<tbody>
			<h3>Orden de Reposicion<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Centro de Distribucion</td>
				 <td>Producto</td>
			     <td>Cantidad</td>
			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="Recupero3Form" property="listaRecupero3"> 
			<tr> 
			    <td colspan="5">No Ordenes de busquedas</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="Recupero3Form" property="listaRecupero3">
					<logic:iterate name="Recupero3Form" property="listaRecupero3" id="bingBongR3" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%>  
		 			 <td><html:text name="bingBongR3" property="centroDistribucion" size="30" maxlength="30" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongR3" property="producto" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 <td><html:text name="bingBongR3" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm"/></td>
		 			 
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
			
			
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>