<section class="overlay" id="overlay-ahorro">
	<div class="contenedor__formulario popup" id="popup-ahorro">
		<a href="#" class="btn-cerrar-popup" id="btn-cerrar-popup-ahorro">
			<em class="fas fa-times"></em>
		</a>
		<h1><s:text name="form.tituloAhorro" /></h1>
		<form action="agregarMovimiento" method="post" class="formulario">
			<div class="formulario__grupo">
				<label class="formulario__label" for="asignacion">
					<s:text name="form.asignacionAhorro" />
				</label>
				<s:select cssClass="formulario__select" name="movimiento.idCatCategoria" list="catFormAhorro" />
			</div>

			<div class="formulario__grupo">
				<label class="formulario__label" for="monto">
					<s:text name="form.monto" />	
				</label> 
				<s:textfield
					type="number" cssClass="formulario__input" name="movimiento.monto" id="monto"
					placeholder="0.0" min="0" step="0.01" autocomplete="off" />
			</div>

			<div class="formulario__grupo-descripcion">
				<label class="formulario__label" for="descripcion">
					<s:text name="form.descripcion" />
				</label>
				<s:textarea cssClass="formulario__textarea" name="movimiento.descripcion"
					id="descripcion"/>
			</div>
	
			<div class="formulario__grupo-btn-enviar">
				<s:submit type="button" cssClass="formulario__btn-enviar" key="btn.enviar"/>
			</div>
		</form>
	</div>
</section>