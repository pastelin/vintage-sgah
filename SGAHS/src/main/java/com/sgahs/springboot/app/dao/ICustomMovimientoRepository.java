package com.sgahs.springboot.app.dao;

import com.sgahs.springboot.app.entity.Inversion;

public interface ICustomMovimientoRepository {

	Double obtenerAhorroTotal();
	Double obtenerPrestamoTotal();
	Double obtenerInversionTotal();
	void actualizarInversion(Inversion inversion);
	
}
