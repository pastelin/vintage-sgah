<header>
	<div class="contenedor-interno">
		<div class="cabecera">
			<h1><s:text name="m.nombreproyecto" /></h1>
			<nav class="menu">
				<button class="boton btn-style1" id="btnCategoria">
					<span><s:text name="m.categorias" /></span>
				</button>
				<button class="boton btn-style2">
					<div class="icono">
						<svg 
							xmlns="http://www.w3.org/2000/svg" 
							width="16" 
							height="16" 
							fill="currentColor" 
							class="bi bi-box-arrow-right" 
							viewBox="0 0 16 16">
							
						<path 
							fill-rule="evenodd" 
							d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
						<path 
							fill-rule="evenodd" 
							d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
						</svg>
					</div>
					<span>Salir</span>
				</button>
			</nav>
		</div>
	</div>
</header>

 <section class="main">
	<section class="categoria-fecha">
		<div class="contenedor-interno">
			<div class="contenedor-categoria-fecha">
				<div class="fecha">
					<p>
						<fmt:formatDate value="${date}" pattern="dd" />
					 	de
					 	<fmt:formatDate value="${date}" pattern="MMMM" />
					 	del
					 	<fmt:formatDate value="${date}" pattern="yyyy" />
					 </p>
				</div>
			</div>
		</div>
	</section>
	
	<section class="descripcion">
		<div class="contenedor-interno">
			<h1 id="descripcion-total"></h1>
			<hr />
		</div>
	</section>
	
	<section class="total">
		<div class="contenedor-interno">
			<div class="centrar-elementos-total">
				<div class="btn-left" id="btn-left">
					<i class="fas fa-angle-double-left"></i>
				</div>

				<div>
					<p id="total">
					</p>
				</div>
				
				<div class="btn-right" id="btn-right">
					<i class="fas fa-angle-double-right"></i>
				</div>
			</div>
			<hr />
		</div>
	</section>
	
	<section class="btn-opciones">
		<div class="contenedor-interno">
			<nav>
				<button class="boton btn-style1" id="addAhorro">
					<span><s:text name="btn.agregarahorro" /></span>
				</button>
				<button class="boton btn-style1" id="addGasto">
					<span><s:text name="btn.agregargasto" /></span>
				</button>
				<button class="boton btn-style1" id="addPrestamo">
					<span><s:text name="btn.agregarprestamo" /></span>
				</button>
			</nav>
		</div>
	</section>
	
	<section class="gastos-mes">
		<div class="contenedor-gastos-mes">
			<h3>
				<s:text name="msg.totalgastosmes" />
			</h3>
			<h3>
				<fmt:formatNumber value="${sumaGastoMensual}" type="currency" />
			</h3>
		</div>
	</section>
</section>