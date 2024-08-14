package com.sgahs.springboot.app.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sgahs.springboot.app.entity.Gasto;

public interface IGastoDao extends CrudRepository<Gasto, Long> {

	@Query(value = "select * from gastos where cd_estatus = 1 and month(fecha_creacion) = ?1 and year(fecha_creacion) = ?2", nativeQuery = true)
	List<Gasto> findGastoByCurrentMonth(int month, int year);

	@Query(value = "select * from gastos where cd_gasto_recurrente = ?1", nativeQuery = true)
	List<Gasto> findGastoByCategoria(Integer categoria);
	
	@Query(value = "select SUM(monto) from gastos where cd_estatus = 1 and cd_tipo_movimiento_gasto = 1", nativeQuery = true)
	BigDecimal retrieveTotalIncome();
	
	@Query(value = "select SUM(monto) from gastos where cd_estatus = 1 and cd_tipo_movimiento_gasto = 2", nativeQuery = true)
	BigDecimal retrieveAmountSpend();
	
	@Query(value = "select sum(monto) from gastos where cd_tipo_movimiento_gasto = 2 and cd_estatus = 1 and month(fecha_creacion) = ?1 and year(fecha_creacion) = ?2", nativeQuery = true)
	BigDecimal retrieveCurrentGastoMensual(int month, int year);
	
}