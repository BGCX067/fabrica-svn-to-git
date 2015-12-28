<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<html:html>
	<head>
		<title>Sincro de Ordenes de Reposicion</title>
		<link rel="stylesheet" type="text/css" href="estilos.css">
	</head>

	<body>
		<html:form action="SincroPantalla.do" focus="name" >
		 <tr>
		   <td class="textos">Sincronizacion</td>
		   <td><html:text property="texto" size="45" maxlength="45" styleClass="CajaForm"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><font color=red><html:errors property="texto"/></font></td>
		</tr>
	</tbody>
			<input type=submit value="Enviar" class="botonAccion">
		</html:form>
		
	</body>
	
</html:html>