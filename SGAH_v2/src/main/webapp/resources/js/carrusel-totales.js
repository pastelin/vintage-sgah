(function() {
	let contador = 0;
	let banderaSiguiente = false;
	let banderaAnterior = false;
	let listaDescripcion = descripcionTotales.split(',');

	// referencia de los elementos a cambiar el resultado
	const elementoDescripcionTotal = document.getElementById('descripcion-total');
	const elementoTotal = document.getElementById('total');

	// botones
	const btnAnterior = document.getElementById('btn-left');
	const btnSiguiente = document.getElementById('btn-right');

	const formatearTotal = (number) => {
		return new Intl.NumberFormat('es-MX', {
			style: 'currency',
			currency: 'MXN',
			minimumFractionDigits: 2,
		}).format(number);
	};

	if (listaDescripcion.length && totales.length) {
		elementoDescripcionTotal.innerText = listaDescripcion[0].trim();
		elementoTotal.innerText = formatearTotal(totales[contador]);

		const _funcionalidadAnterior = () => {
			if (banderaSiguiente) {
				contador--;
				banderaSiguiente = false;
			}

			if (contador > 0) {
				contador--;
				elementoDescripcionTotal.innerText = listaDescripcion[contador].trim();
				elementoTotal.innerText = formatearTotal(totales[contador]);
				banderaAnterior = true;
			} else {
				contador = totales.length - 1;
				elementoDescripcionTotal.innerText = listaDescripcion[contador].trim();
				elementoTotal.innerText = formatearTotal(totales[contador]);
				banderaAnterior = true;
			}
		};

		btnAnterior.addEventListener('click', () => {
			_funcionalidadAnterior();
		});

		const _funcionalidadSiguiente = () => {
			if (contador == 0 || banderaAnterior) {
				contador++;
				banderaAnterior = false;
			}

			if (contador < totales.length) {
				elementoDescripcionTotal.innerText = listaDescripcion[contador].trim();
				elementoTotal.innerText = formatearTotal(totales[contador]);
				contador++;
				banderaSiguiente = true;
			} else {
				contador = 0;
				elementoDescripcionTotal.innerText = listaDescripcion[contador].trim();
				elementoTotal.innerText = formatearTotal(totales[contador]);
			}
		};

		btnSiguiente.addEventListener('click', () => {
			_funcionalidadSiguiente();
		});
	}
})();
