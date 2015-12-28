<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>

<head>
<title>Prueba de listado</title>
</head>
<body>
<table border="1"> 
<tbody>
<%--  set the header --%> 
<tr> <td>Descripcion</td>
     <td>Cantidad</td> 
     <td>id</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
</tr>
<%-- check if book exists and display message or iterate over books  --%>
<logic:empty name="AltaArticuloForm" property="listaMateria"> 
<tr> 
    <td colspan="5">No hay materias primas cargadas</td> 
</tr>
</logic:empty> 

	<logic:notEmpty name="AltaArticuloForm" property="listaMateria">
		<logic:iterate name="AltaArticuloForm" property="listaMateria" id="BingBong" indexId="index"> 
		<tr>
		 <%-- print out the book informations --%> 
		 <td><bean:write name="BingBong" property="descripcion" /></td>
		 <td><html:text name="BingBong" property="cantidad" size="45" maxlength="45"/></td>
		 <td><bean:write name="BingBong" property="id"/></td>
		 <td><html:checkbox name="BingBong" property="chk" /></td> 
 <%-- print out the edit and delete link for each book --%>
		</logic:iterate> 
	</logic:notEmpty>
</tbody>
</table>

	<p><a href="AltaArticulo.jsp">Alta un nuevo Cliente.</a></p>
	<p><a href="index.jsp">Volver al menú principal.</a></p>
	
</body>
</html>
