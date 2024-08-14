(function () {
	// Referencia al html
	const overlayModificaMovimiento = document.querySelector('#overlay-modifica-movimiento');
	const popupModificaMovimiento = document.querySelector('#popup-mofica-movimiento');
	const btnCerrarPopupModificaMov = document.querySelector('#btn-cerrar-popup-modifica-movimiento');
  
  

	if (formUpdateActive) {
		_addClassActive(overlayModificaMovimiento, popupModificaMovimiento);

		btnCerrarPopupModificaMov.addEventListener('click', () => {
			_removeClassActive(overlayModificaMovimiento, popupModificaMovimiento);
		});
	}

})();
