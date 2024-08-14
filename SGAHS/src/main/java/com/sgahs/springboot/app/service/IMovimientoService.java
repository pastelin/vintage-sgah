package com.sgahs.springboot.app.service;

import java.util.List;

import com.sgahs.springboot.app.entity.Ahorro;
import com.sgahs.springboot.app.entity.CatalogoAppInversion;
import com.sgahs.springboot.app.entity.Inversion;
import com.sgahs.springboot.app.entity.Prestamo;

public interface IMovimientoService {
	
	List<Inversion> findAllInversion();
	void saveInversion(Inversion inversion);
	void updateInversion(Inversion inversion);
	
	List<Ahorro> findAllAhorro();
	void saveAhorro(Ahorro ahorro);
	
	List<Prestamo> findAllPrestamo();
	
	Prestamo findPrestamoByFolio(String folio);
	Inversion findInversionByFolio(String folio);
	
	void savePrestamo(Prestamo prestamo);
	
	List<CatalogoAppInversion> findAllCatalogoInversion();
}
