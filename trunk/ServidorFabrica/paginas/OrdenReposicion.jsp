<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<html:html>
<head>
		<title>Alta de un nuevo Orden de reposicion</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
</head>
	<body>
		<html:form action="OrdenReposicion.do" focus="name" >
		
		
		<table border="1" class="zara"> 
			<tbody>
			<h3>Orden de Reposicion<h3>
			<%--  set the header --%> 
			<tr> 
				 <td>Check</td>
				 <td>Producto</td>
			     <td>Cantidad</td>
			    
			</tr>
			<%-- check if book exists and display message or iterate over books  --%>
			<logic:empty name="OrdenReposicionForm" property="listaOrden"> 
			<tr> 
			    <td colspan="5">No hay Ordenes Reposicion Cargadas</td> 
			</tr>
			</logic:empty> 
				<logic:notEmpty name="OrdenReposicionForm" property="listaOrden">
					<logic:iterate name="OrdenReposicionForm" property="listaOrden" id="bingBong" indexId="index"> 
					<tr>
					 <%-- print out the book informations --%> 
					 <td><html:checkbox name="bingBong" property="chk"  indexed="true"/></td> 
					 <td><html:text name="bingBong" property="producto" size="20" maxlength="20" indexed="true" styleClass="SinRecuadroForm" /></td>
		 			 <td><html:text name="bingBong" property="cantidad" size="3" maxlength="3" indexed="true" styleClass="SinRecuadroForm" /></td>
		 			 <html:hidden name="bingBong" property="productoId" indexed="true" disabled="true"/>
		 			 <html:hidden name="bingBong" property="ordenFabricacionId" indexed="true" disabled="true"/>
			 <%-- print out the edit and delete link for each book --%>
					</logic:iterate> 
				</logic:notEmpty>
			</tbody>
			</table>
			
			
		<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>