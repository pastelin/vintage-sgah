package com.sgahs.springboot.app.service;

import com.sgahs.springboot.app.entity.Inversion;

public interface ICustomMovimientoService {

	Double obtenerAhorroTotal();
	Double obtenerPrestamoTotal();
	Double obtenerInversionTotal();
	void actualizarInversion(Inversion inversion);
}
