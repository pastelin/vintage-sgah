package com.sgahs.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sgahs.springboot.app.entity.Prestamo;
import com.sgahs.springboot.app.service.IMovimientoService;

@Controller
@RequestMapping("/http")
public class SolicitudHttp {

	@Autowired
	IMovimientoService movimientoService;

	@GetMapping(value = "/cargar-prestamo/{folio}", produces = { "application/json" })
	public @ResponseBody Prestamo cargarPrestamo(@PathVariable String folio) {

		return movimientoService.findPrestamoByFolio(folio);
	}

}
