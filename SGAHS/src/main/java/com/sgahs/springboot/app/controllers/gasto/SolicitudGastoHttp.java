package com.sgahs.springboot.app.controllers.gasto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgahs.springboot.app.entity.GastoRecurrente;
import com.sgahs.springboot.app.service.IGastoService;

@Controller
@RequestMapping("/http")
public class SolicitudGastoHttp {
	
	@Autowired
	IGastoService gastoService;

	@GetMapping(value="/obtener-gastos-recurrentes", produces = {"application/json"})
	public @ResponseBody List<GastoRecurrente>  obtenerGastosRecurrentes() {
		return  gastoService.findAllGastoRecurrente();
	}
	
}
