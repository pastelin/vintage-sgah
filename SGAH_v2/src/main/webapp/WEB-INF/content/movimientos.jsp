<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:useBean id="date" class="java.util.Date" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="es_MX" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>SGAH</title>
		<link rel="preconnect" href="https://fonts.googleapis.com" />
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
		<link
			href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;500;600;700;800&display=swap"
			rel="stylesheet"
		/>
		<link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css" />
		<link rel="stylesheet" href="${path}/resources/css/estilos.css" />
		<link rel="stylesheet" href="${path}/resources/css/estilos-form.css" />
		<link rel="stylesheet" href="${path}/resources/css/btn-estilos.css" />

		<script src="https://kit.fontawesome.com/f346ed96e5.js" crossorigin="anonymous"></script>
	</head>

	<body>
		<%@ include file="./comunes/movimientos-home/cuadro-puntos-superior.jsp" %>
		<div class="contenedor">
			<%@ include file="./comunes/movimientos-home/main.jsp" %>
		</div>

		<%@ include file="./comunes/movimientos-home/cuadro-puntos-inferior.jsp" %>

		<%@ include file="./popup/form-ahorro.jsp" %>
		<%@ include file="./popup/form-gasto.jsp" %>
		<%@ include file="./popup/form-prestamo.jsp" %>
		<%@ include file="./popup/lista-categoria.jsp" %>
		
		<footer>
			<div class="pie-pagina">
				<h1><s:text name="m.piepagina" /></h1>
			</div>
		</footer>
		
		
		<script src="${path}/resources/js/comunes.js"></script>	
		<script src="${path}/resources/js/popup.js"></script>	
		<script>
			const descripcionTotales = "<s:property value="descripcionTotales" />";
			const totales = <s:property value="totales" />;
		</script>
		<script src="${path}/resources/js/carrusel-totales.js"></script>
	</body>
</html>
