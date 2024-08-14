<section class="overlay" id="overlay-modifica-movimiento">
	<div class="contenedor__formulario popup" id="popup-mofica-movimiento">
		<a href="#" class="btn-cerrar-popup" id="btn-cerrar-popup-modifica-movimiento">
			<em class="fas fa-times"></em>
		</a>
		<h1><s:text name="form.tituloModificaMovimiento" /></h1>
		
		<form action="modificarMovimiento" method="post" class="formulario">
			<div class="formulario__grupo">
				<label class="formulario__label msg__warning" for="monto">
					<s:text name="form.msgGasto" /> &nbsp;
					<fmt:formatNumber value="${totalFree}" type="currency" />
				</label>	
			</div>

			<div class="formulario__grupo">
				<label class="formulario__label" for="monto">
					<s:text name="form.montoAnterior" />
				</label> 
				<s:textfield
					type="number" cssClass="formulario__input" value="%{movimiento.monto}" 
          			id="monto" placeholder="0.0" min="0" step="0.01" autocomplete="off" disabled="true"/>
			</div>

      		<div class="formulario__grupo">
				<label class="formulario__label" for="monto">
					<s:text name="form.montoPago" />
				</label> 
				<s:textfield
					type="number" cssClass="formulario__input" name="movimiento.monto" id="monto"
					placeholder="0.0" min="0" step="0.01" autocomplete="off"/>
			</div>

			<div class="formulario__grupo-descripcion">
				<label class="formulario__label" for="descripcion">
					<s:text name="form.descripcion" />
				</label>
				<s:textarea cssClass="formulario__textarea" name="movimiento.descripcion" id="descripcion"/>
			</div>
	
			<s:textfield type="hidden" name="movimiento.idMovimiento" />
			<s:textfield type="hidden" name="movimiento.fecha"/>
			<s:textfield type="hidden" name="movimiento.idCatTipoMovimiento"/>
			<s:textfield type="hidden" name="movimiento.idCatCategoria"/>
			<s:textfield type="hidden" name="montoAnterior"/>
			<s:textfield type="hidden" name="idCategoria"/>

			<div class="formulario__grupo-btn-enviar">
				<s:submit type="button" cssClass="formulario__btn-enviar" key="btn.enviar"/>
			</div>
		</form>
	</div>
</section>