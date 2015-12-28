<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<title>Error en el envio de la orden de reposicion/title>
	</head>
	<body>
		<h3>La orden <bean:write name="OrdenReposicionForm" property="producto"/> no ha podido ser enviada correctamente</h3>
		<hr>
		<p><a href="index.jsp">Volver al menú principal.</a></p>
	</body>
</html:html>