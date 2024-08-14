package com.sgahs.springboot.app.service;

import java.util.List;

import com.sgahs.springboot.app.dto.DtoGasto;
import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.entity.GastoRecurrente;

public interface IGastoService {

	List<DtoGasto> findGastoByCurrentMonth(List<GastoRecurrente> listGastoRecurrente);
	
	List<DtoGasto> findGastoByCategoria(Integer categoria);
	
	void saveGasto(Gasto gasto);
	
	List<GastoRecurrente> findAllGastoRecurrente();
	
	Double retrieveGastoAvailable();
	
	Double retrieveCurrentGastoMensual();
	
}
