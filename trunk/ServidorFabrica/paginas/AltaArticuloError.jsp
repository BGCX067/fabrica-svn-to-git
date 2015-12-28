<%@ page language="java" %>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>
	<head>
		<title>Error en Alta de un Articulo</title>
	</head>
	<body>
		<h3>El Articulo <bean:write name="AltaArticuloForm" property="descripcion"/> no ha podido ser creado correctamente.</h3>
		<hr>
		<p><a href="index.jsp">Volver al menú principal.</a></p>
	</body>
</html:html>