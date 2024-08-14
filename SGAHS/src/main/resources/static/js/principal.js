const checkAhorro = document.querySelector('#inversionAhorro');
const checkPrestamo = document.querySelector('#checkPrestamo');

const divInversionAhorro = document.querySelector('#divInversionAhorro');
const divPrestamo = document.querySelector('#divPrestamo');

// Referencia a prestamos
const selectPrestamo = document.querySelector('#selectPrestamo');
const montoPrestadoSpan = document.querySelector('#montoPrestadoSpan');
const textareaDescripcionPrestamo = document.querySelector('#descripcionPrestamo');
const inputCdEstatus = document.querySelector('#cdEstatus');
const inputFechaCreacion = document.querySelector('#fechaCreacion');
const inputMontoPrestado = document.querySelector('#montoPrestado');
const inputMontoPagado = document.querySelector('#montoPagado')
const formPrestamo = document.querySelector('#formPrestamo');


const eventos = () => {
	checkAhorro.addEventListener('click', () => {
		if (checkAhorro.checked) {
			divInversionAhorro.classList.remove('contenedor__formulario--desactivado');
		} else {
			divInversionAhorro.classList.add('contenedor__formulario--desactivado');
		}
	});

	checkPrestamo.addEventListener('click', () => {
		if (checkPrestamo.checked) {
			divPrestamo.classList.remove('contenedor__formulario--desactivado');
		} else {
            divPrestamo.classList.add('contenedor__formulario--desactivado');
        }

	});

	// Obtiene registro del prestamo seleccionado en el elemento select
	// Imprime el valor del registro obtenido en los elementos input, span, textarea, para que sean actualizados
	selectPrestamo.addEventListener('change', async (event) => {

		const folio = event.target.value;
		const uri = `/http/cargar-prestamo/${folio}`;

		try {
			const resp = await fetch(uri);

			if(resp.ok) {
				const {cdEstatus, descripcion, fechaCreacion, montoPagado, montoPrestado} = await resp.json();

				montoPrestadoSpan.innerText = montoPrestado - montoPagado;
				inputCdEstatus.setAttribute('value', cdEstatus);
				inputFechaCreacion.setAttribute('value', fechaCreacion);
				inputMontoPrestado.setAttribute('value', montoPrestado);
				textareaDescripcionPrestamo.innerText = descripcion;
				inputMontoPagado.setAttribute('value', montoPagado);
			}

		} catch(err) {
			throw err;
		}
	});
};

eventos();
