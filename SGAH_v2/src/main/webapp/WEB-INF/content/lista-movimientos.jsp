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
		<link rel="stylesheet" href="${path}/resources/css/estilos-movimiento.css" />
		<link rel="stylesheet" href="${path}/resources/css/estilos-form.css" />
    <link rel="stylesheet" href="${path}/resources/css/btn-estilos.css" />
	</head>

	<body>
    <section class="main-movimiento">
      <div class="contenedor-movimiento">
        <h1>Lista de movimientos</h1>
        <div class="table">
          <div class="row header">
            <div class="cell w-10">Id</div>
            <div class="cell w-20">Monto</div>
            <div class="cell w-20">Fecha</div>
            <div class="cell w-40">Descripci&oacute;n</div>
            <div class="cell w-10">Editar</div>
          </div>
          <s:if test=" listaMovimientosPorCategoria != null && !listaMovimientosPorCategoria.isEmpty()">
            <s:iterator value="listaMovimientosPorCategoria">
              <div class="row">
                <div class="cell w-10"><s:property value="idMovimiento" /></div>
                <div class="cell w-20">
                  <fmt:formatNumber value="${monto}" type="currency" />
                </div>
                <div class="cell w-20"><s:property value="fecha" /></div>
                <div class="cell w-40 scroll"><s:property value="descripcion" /></div>
                <s:if test="idCatCategoria == 5">
                    <div class="cell w-10">
                    <s:a action="bucarMovimiento" cssClass="modificarMovimiento">
                      <i class="fas fa-edit"></i>
                      <s:param name="movimiento.idMovimiento" value="idMovimiento" />
                      <s:param name="idCategoria" value="idCatCategoria" />
                    </s:a>
                  </div>
                </s:if>
              </div>
            </s:iterator>
          </s:if>
          <s:else>
            <div class="row">
              <div class="cell"> No hay datos</div>
            </div>
          </s:else>
        </div>

        <div class="btn-center">
          <a class="boton btn-style2-back" href="<s:url action='mostrarDatos' />">
            <div class="icono">
              <svg 
                xmlns="http://www.w3.org/2000/svg" 
                width="16" height="16" 
                fill="currentColor" 
                class="bi bi-arrow-return-left" 
                viewBox="0 0 16 16">
              <path 
                fill-rule="evenodd" 
                d="M14.5 1.5a.5.5 0 0 1 .5.5v4.8a2.5 2.5 0 0 1-2.5 2.5H2.707l3.347 3.346a.5.5 0 0 1-.708.708l-4.2-4.2a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 8.3H12.5A1.5 1.5 0 0 0 14 6.8V2a.5.5 0 0 1 .5-.5z"/>
              </svg>
            </div>
            <span>Regresar</span>
          </a>
        </div>
      </div>
    </section>
  
    <%@ include file="./popup/form-modifica-movimiento.jsp" %>

    <script src="https://kit.fontawesome.com/f346ed96e5.js" crossorigin="anonymous"></script>
    <script>
      const formUpdateActive = <s:property value="formUpdateActive" />
    </script>
    <script src="${path}/resources/js/comunes.js"></script>	
    <script src="${path}/resources/js/popup-modifica-movimiento.js"></script>	
	</body>
</html>
