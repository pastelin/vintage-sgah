package com.sgahs.springboot.app.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.sgahs.springboot.app.entity.Inversion;

@Repository
public class CustomMovimientoRepositoryImpl implements ICustomMovimientoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Double obtenerAhorroTotal() {

		BigDecimal ahorroTotal = (BigDecimal) entityManager
				.createNativeQuery("select SUM(monto) from ahorros where cd_estatus = 1").getSingleResult();
		Double inversionTotal = obtenerInversionTotal();
		Double ahorro = ahorroTotal != null ? ahorroTotal.doubleValue() : 0.0;
//		Double inversion = inversionTotal != null ? inversionTotal.doubleValue() : 0.0;

		return ahorro - inversionTotal;
	}

	@Override
	public Double obtenerPrestamoTotal() {

		BigDecimal prestamoTotal = (BigDecimal) entityManager
				.createNativeQuery("select SUM(monto_prestado - monto_pagado) from prestamos where cd_estatus = 1")
				.getSingleResult();

		return prestamoTotal != null ? prestamoTotal.doubleValue() : 0.0;

	}

	@Override
	public Double obtenerInversionTotal() {

		BigDecimal inversionTotal = (BigDecimal) entityManager
				.createNativeQuery("select SUM(monto) from inversiones where cd_estatus = 1")
				.getSingleResult();

		return inversionTotal != null ? inversionTotal.doubleValue() : 0.0;
	}

	@Override
	public void actualizarInversion(Inversion inversion) {

		StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("call spu_inversion");
		sp.setParameter(1, inversion.getFolio());
		sp.setParameter(2, inversion.getMonto());
		sp.setParameter(3, inversion.getDescripcion());
		sp.setParameter(4, inversion.getFechaCreacion());
		sp.setParameter(5, inversion.getCdEstatus());
		sp.setParameter(6, inversion.getCdAppInversion());

		sp.execute();
	}

}
