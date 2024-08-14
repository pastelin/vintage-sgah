<section class="overlay" id="overlay-categoria">
	<div class="contenedor__formulario w-500 b-dfdfdf popup"
		id="popup-categoria">
		<a href="#" class="btn-cerrar-popup" id="btn-cerrar-popup-categoria">
			<em class="fas fa-times"></em>
		</a>
		<h1>Seleccione una opcion</h1>
		<div class="btn__grupo">
			<s:iterator value="listaCategorias">
				<s:if test="idCatCategoria != 1 && idCatCategoria != 15">
					<s:a action="buscarPorCategoria">
						<s:property value="nombre" />
						<s:param name="idCategoria" value="idCatCategoria" />
					</s:a>
				</s:if>
			</s:iterator>
		</div>
	</div>
</section>