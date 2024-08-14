// Referencia a etiquetas select
const selectElegirFiltro = document.querySelector('#elegirFiltro');

// Referencia a etiquetas div
const contenedorFechaInicio = document.querySelector('#contenedorFechaInicio');
const contenedorFechaFin = document.querySelector('#contenedorFechaFin');
const contenedorSelectCategoria = document.querySelector('#contenedorSelectCategoria');
const contenedorSelectTipo = document.querySelector('#contenedorSelectTipo');

const opcionesBusqueda = document.querySelectorAll('#formFiltroGasto div');

// URI's
const uriObtenerGastosRecurrentes = '/http/obtener-gastos-recurrentes';
const uriObtenerTipoGasto = '/http/obtener-tipo-gasto';

selectElegirFiltro.addEventListener('change', async (event) => {
	const filtroSeleccionado = event.target.value;

	opcionesBusqueda.forEach((filtro, index) => {
		index != 0 ? filtro.classList.add('hidden') : '';
	});

	if (!!filtroSeleccionado) {
		if (filtroSeleccionado === 'fecha') {
			contenedorFechaInicio.classList.remove('hidden');
			contenedorFechaFin.classList.remove('hidden');
		} else if (filtroSeleccionado === 'categoria') {
            contenedorSelectCategoria.classList.remove('hidden');
			crearSelectCategoria();
		} else {
            contenedorSelectTipo.classList.remove('hidden');
            crearSelectTipoGasto();
		}
	}
});

const crearSelectCategoria = async() => {
    const select = document.createElement('select');
    const data = await obtenerGastosRecurrentes();
    const selectCategoria = document.querySelector('#selectCategoria');

    if (selectCategoria) {
        selectCategoria.remove();
    }

    select.classList.add('campo__field');
    select.name = "categoria"
    select.id = "selectCategoria"
    
    select.innerHTML = '<option value="">Selecciona una categoría</option>';

	data.forEach((categoria) => {
		const optionHtml = `
            <option value="${categoria.cdGasto}">${categoria.nbGasto}</option>
        `;
		select.innerHTML += optionHtml;
	});

	contenedorSelectCategoria.append(select);
};

const obtenerGastosRecurrentes = async () => {
	const response = await fetch(uriObtenerGastosRecurrentes);
	let data;

	try {
		if (response.ok) {
			data = await response.json();
		}
	} catch (err) {
		throw err;
	}

	return data;
};

const crearSelectTipoGasto = async() => {
    const select = document.createElement('select');
    const selectTipoGasto = document.querySelector('#selectTipoGasto');

    if (selectTipoGasto) {
		selectTipoGasto.remove();
	}

    select.classList.add('campo__field');
    select.name = "tipoGasto"
    select.id = 'selectTipoGasto';
    
    select.innerHTML = '<option value="">Selecciona tipo de gasto</option>';
    select.innerHTML += '<option value="1">Ingreso</option>';
    select.innerHTML += '<option value="2">Gasto</option>';

	contenedorSelectTipo.append(select);
};