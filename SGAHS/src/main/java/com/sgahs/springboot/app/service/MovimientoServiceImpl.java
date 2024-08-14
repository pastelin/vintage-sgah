package com.sgahs.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgahs.springboot.app.dao.IAhorroDao;
import com.sgahs.springboot.app.dao.ICatalogoAppInversion;
import com.sgahs.springboot.app.dao.IGastoDao;
import com.sgahs.springboot.app.dao.IGastoRecurrenteDao;
import com.sgahs.springboot.app.dao.IInversionDao;
import com.sgahs.springboot.app.dao.IPrestamoDao;
import com.sgahs.springboot.app.entity.Ahorro;
import com.sgahs.springboot.app.entity.CatalogoAppInversion;
import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.entity.GastoRecurrente;
import com.sgahs.springboot.app.entity.Inversion;
import com.sgahs.springboot.app.entity.Prestamo;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

	@Autowired
	IAhorroDao ahorroDao;
	
	@Autowired
	IGastoDao gastoDao;
	
	@Autowired
	IGastoRecurrenteDao gastoRecurrenteDao;
	
	@Autowired
	IInversionDao inversionDao;
	
	@Autowired
	IPrestamoDao prestamoDao;
	
	@Autowired
	ICatalogoAppInversion catalogoInversion;
	
	@Override
	public List<Inversion> findAllInversion() {
		return (List<Inversion>) inversionDao.findAll();
	}

	@Override
	@Transactional
	public void saveInversion(Inversion inversion) {
		inversionDao.spuInversion(inversion.getFolio(), inversion.getMonto(), inversion.getDescripcion(), inversion.getFechaCreacion(),
				inversion.getCdEstatus(), inversion.getCdAppInversion());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ahorro> findAllAhorro() {
		return (List<Ahorro>) ahorroDao.findAll();
	}

	@Override
	@Transactional
	public void saveAhorro(Ahorro ahorro) {
		ahorroDao.save(ahorro);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Prestamo> findAllPrestamo() {
		return (List<Prestamo>) prestamoDao.findAll();
	}

	@Override
	@Transactional
	public void savePrestamo(Prestamo prestamo) {
		prestamoDao.save(prestamo);
	}


	@Override
	@Transactional(readOnly=true)
	public List<CatalogoAppInversion> findAllCatalogoInversion() {
		return (List<CatalogoAppInversion>) catalogoInversion.findAll();
	}


	@Override
	@Transactional(readOnly=true)
	public Prestamo findPrestamoByFolio(String folio) {
		return prestamoDao.findById(folio).orElse(null);
	}


	@Override
	public Inversion findInversionByFolio(String folio) {
		return inversionDao.findById(folio).orElse(null);
	}


	@Override
	public void updateInversion(Inversion inversion) {
		inversionDao.save(inversion);
		
	}
	
}