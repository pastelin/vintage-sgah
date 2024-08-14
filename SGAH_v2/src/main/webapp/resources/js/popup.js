// Referencia del html
const btnAddAhorro 		= document.querySelector('#addAhorro');
const btnAddGasto	 		= document.querySelector('#addGasto');
const btnAddPrestamo 	= document.querySelector('#addPrestamo');
const btnCategoria 		= document.querySelector('#btnCategoria');

const btnCerrarPopupAhorro = document.querySelector('#btn-cerrar-popup-ahorro');
const btnCerrarPopupGasto = document.querySelector('#btn-cerrar-popup-gasto');
const btnCerrarPopupPrestamo = document.querySelector('#btn-cerrar-popup-prestamo');
const btnCerrarPopupCategoria = document.querySelector('#btn-cerrar-popup-categoria');


// ahorro
const overlayAhorro = document.querySelector('#overlay-ahorro');
const popupAhorro = document.querySelector('#popup-ahorro');

// gastos
const overlayGasto = document.querySelector('#overlay-gasto');
const popupGasto = document.querySelector('#popup-gasto');

// prestamos
const overlayPrestamo = document.querySelector('#overlay-prestamo');
const popupPrestamo = document.querySelector('#popup-prestamo');

// categorias
const overlayCategoria = document.querySelector('#overlay-categoria');
const popupCategoria = document.querySelector('#popup-categoria');

// eventos para ahorro
btnAddAhorro.addEventListener('click', () => {
	_addClassActive(overlayAhorro, popupAhorro);
});

btnCerrarPopupAhorro.addEventListener('click', () => {
	_removeClassActive(overlayAhorro, popupAhorro);
});

// eventos para gastos
btnAddGasto.addEventListener('click', () => {
	_addClassActive(overlayGasto, popupGasto);
});

btnCerrarPopupGasto.addEventListener('click', () => {
	_removeClassActive(overlayGasto, popupGasto);
});

// eventos para prestamos
btnAddPrestamo.addEventListener('click', () => {
	_addClassActive(overlayPrestamo, popupPrestamo);
});

btnCerrarPopupPrestamo.addEventListener('click', () => {
	_removeClassActive(overlayPrestamo, popupPrestamo);
});

// eventos para las categorias
btnCategoria.addEventListener('click', () => {
	_addClassActive(overlayCategoria, popupCategoria);
});

btnCerrarPopupCategoria.addEventListener('click', () => {
	_removeClassActive(overlayCategoria, popupCategoria);
});

// const _addClassActive = (overlay, popup) => {
// 	overlay.classList.add('active');
// 	popup.classList.add('active');
// };

// const _removeClassActive = (overlay, popup) => {
// 	overlay.classList.remove('active');
// 	popup.classList.remove('active');
// };
