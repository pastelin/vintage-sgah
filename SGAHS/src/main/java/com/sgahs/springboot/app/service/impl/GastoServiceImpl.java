package com.sgahs.springboot.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgahs.springboot.app.dao.IGastoDao;
import com.sgahs.springboot.app.dao.IGastoRecurrenteDao;
import com.sgahs.springboot.app.dto.DtoGasto;
import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.entity.GastoRecurrente;
import com.sgahs.springboot.app.rm.LocalDateUtil;
import com.sgahs.springboot.app.service.IGastoService;

@Service
public class GastoServiceImpl implements IGastoService {

	@Autowired
	IGastoDao gastoDao;

	@Autowired
	IGastoRecurrenteDao gastoRecurrenteDao;

	public List<DtoGasto> asginarDescripcionGasto(List<Gasto> gastos) {
		List<DtoGasto> dtoGastos = new ArrayList<>();
		List<GastoRecurrente> listGastoRecurrente = findAllGastoRecurrente();

		gastos.stream().forEach(gasto -> {

			String categoriaGasto = (gasto.getCdGastoRecurrente() != 0)
					? listGastoRecurrente.get(gasto.getCdGastoRecurrente() - 1).getNbGasto()
					: "N/A";
			String tipoMovimientoGasto = (gasto.getCdTipoMovimiento() == 1) ? "Ingreso" : "Gasto";

			dtoGastos.add(new DtoGasto(gasto.getFechaCreacion(), gasto.getMonto(), gasto.getDescripcion(),
					categoriaGasto, tipoMovimientoGasto));
		});
		
		return dtoGastos;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DtoGasto> findGastoByCurrentMonth(List<GastoRecurrente> listGastoRecurrente) {
		List<Gasto> gastos = gastoDao.findGastoByCurrentMonth(LocalDateUtil.getCurrentMonth(),
				LocalDateUtil.getCurrentYear());		
		
		return asginarDescripcionGasto(gastos);
	}
	
	@Override
	public List<DtoGasto> findGastoByCategoria(Integer categoria) {
		List<Gasto> gastos = gastoDao.findGastoByCategoria(categoria);
		return asginarDescripcionGasto(gastos);
	}

	@Override
	@Transactional
	public void saveGasto(Gasto gasto) {
		gastoDao.save(gasto);

	}

	@Override
	@Transactional(readOnly = true)
	public List<GastoRecurrente> findAllGastoRecurrente() {
		return (List<GastoRecurrente>) gastoRecurrenteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Double retrieveGastoAvailable() {

		BigDecimal totalIncome = gastoDao.retrieveTotalIncome();
		BigDecimal amountSpend = gastoDao.retrieveAmountSpend();

		if (totalIncome == null) {
			totalIncome = BigDecimal.valueOf(0.0);
		}

		if (amountSpend == null) {
			amountSpend = BigDecimal.valueOf(0.0);
		}

		return totalIncome.doubleValue() - amountSpend.doubleValue();
	}

	@Override
	@Transactional(readOnly = true)
	public Double retrieveCurrentGastoMensual() {
		BigDecimal currentGastoMensual = gastoDao.retrieveCurrentGastoMensual(LocalDateUtil.getCurrentMonth(),
				LocalDateUtil.getCurrentYear());

		return (currentGastoMensual != null) ? currentGastoMensual.doubleValue() : 0.0;
	}

}
