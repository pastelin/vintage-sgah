package com.sgahs.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgahs.springboot.app.dao.ICustomMovimientoRepository;
import com.sgahs.springboot.app.entity.Inversion;

@Service
public class CustomMovimientoServiceImpl implements ICustomMovimientoService {

	@Autowired
	ICustomMovimientoRepository customMovimientoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Double obtenerAhorroTotal() {
		return customMovimientoRepository.obtenerAhorroTotal();
	}

	@Override
	@Transactional(readOnly = true)
	public Double obtenerPrestamoTotal() {
		return customMovimientoRepository.obtenerPrestamoTotal();
	}

	@Override
	@Transactional(readOnly = true)
	public Double obtenerInversionTotal() {
		return customMovimientoRepository.obtenerInversionTotal();
	}

	@Override
	@Transactional
	public void actualizarInversion(Inversion inversion) {
		customMovimientoRepository.actualizarInversion(inversion);
	}

}
