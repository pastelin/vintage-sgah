package com.sgahs.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sgahs.springboot.app.entity.AdministrarIngreso;
import com.sgahs.springboot.app.entity.Ahorro;
import com.sgahs.springboot.app.entity.CatalogoAppInversion;
import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.entity.GastoRecurrente;
import com.sgahs.springboot.app.entity.Inversion;
import com.sgahs.springboot.app.entity.Prestamo;
import com.sgahs.springboot.app.service.IGastoService;
import com.sgahs.springboot.app.service.IMovimientoService;

@Controller
@SessionAttributes("administrarIngreso")
public class MovimientoController {

	private static final Logger LOGGER = LogManager.getLogger(MovimientoController.class);

	private static final String MOVIMIENTO_PRINCIPAL = "principal";
	private static final String REDIRECT_ADMINISTRAR_INGRESOS = "redirect:/administrar-ingresos";
	private static final String REDIRECT_DASHBOARD = "redirect:/dashboard/resumen";

	private Double montoPagadoActual;

	@Autowired
	IMovimientoService movimientoService;
	
	@Autowired
	IGastoService gastoService;

	@GetMapping({ "/index", "/home" })
	public String inicio(Model model) {
		model.addAttribute("administrarIngreso", new AdministrarIngreso());
		return MOVIMIENTO_PRINCIPAL;
	}

	@GetMapping({ "/administrar-ingresos" })
	public String inicio() {
		return MOVIMIENTO_PRINCIPAL;
	}

	@PostMapping("agregarMonto")
	public String agregarMonto(@Valid AdministrarIngreso administrarIngreso, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("administrarIngreso", administrarIngreso);
			return MOVIMIENTO_PRINCIPAL;
		}

		model.addAttribute("administrarIngreso", administrarIngreso);
		return REDIRECT_ADMINISTRAR_INGRESOS;
	}

	public boolean isMontoValido(AdministrarIngreso administrarIngreso, Double monto) {
		if (monto > administrarIngreso.getMontoInicial()) {
			LOGGER.error("EL monto para gastos no puede ser mayor al monto disponible");
			return false;
		}

		administrarIngreso.setMontoInicial(administrarIngreso.getMontoInicial() - monto);
		return true;
	}

	@PostMapping("/ahorro/guardar")
	public String guardarAhorro(
			@SessionAttribute(name = "administrarIngreso", required = false) AdministrarIngreso administrarIngreso,
			@Valid Ahorro ahorro, BindingResult result, Model model, SessionStatus status) {

		// Valida que el entity ahorro no tenga errores basados en las anotaciones
		// asignadas, Si este tiene un error regresa a la pagina de envio con los
		// mensajes definidos
		if (result.hasErrors()) {
			model.addAttribute("ahorro", ahorro);
			return MOVIMIENTO_PRINCIPAL;
		}

		// Valida que el monto de ahorro no sobrepase el monto inicial
		if (!isMontoValido(administrarIngreso, ahorro.getMonto())) {
			return MOVIMIENTO_PRINCIPAL;
		}

		// Valida y suma la cantidad total enviada a ahorro
		Double montoAhorro = administrarIngreso.getMontoAhorro();

		if (montoAhorro == 0.0) {
			administrarIngreso.setMontoAhorro(ahorro.getMonto());
		} else {
			administrarIngreso.setMontoAhorro(montoAhorro + ahorro.getMonto());
		}

		// Envio el objeto a la clase service para el guardado del registro
		movimientoService.saveAhorro(ahorro);

		// Si el monto inicial es 0 termina la sesion para administrarIngreso y cambia a
		// dashboard
		if (administrarIngreso.getMontoInicial() == 0) {
			status.setComplete();
			return REDIRECT_DASHBOARD;
		}

		return REDIRECT_ADMINISTRAR_INGRESOS;
	}

	@PostMapping("/gasto/guardar")
	public String guardarGasto(
			@SessionAttribute(name = "administrarIngreso", required = false) AdministrarIngreso administrarIngreso,
			@Valid Gasto gasto, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("gasto", gasto);
			return MOVIMIENTO_PRINCIPAL;
		}

		if (!isMontoValido(administrarIngreso, gasto.getMonto())) {
			return MOVIMIENTO_PRINCIPAL;
		}

		Double montoGasto = administrarIngreso.getMontoGasto();

		if (montoGasto == 0.0) {
			administrarIngreso.setMontoGasto(gasto.getMonto());
		} else {
			administrarIngreso.setMontoGasto(montoGasto + gasto.getMonto());
		}

		gastoService.saveGasto(gasto);

		// Si el monto inicial es 0 termina la sesion para administrarIngreso y cambia a
		// dashboard
		if (administrarIngreso.getMontoInicial() == 0) {
			status.setComplete();
			return REDIRECT_DASHBOARD;
		}

		return REDIRECT_ADMINISTRAR_INGRESOS;
	}

	@PostMapping("/guardar/prestamo")
	public String actualizarPrestamo(
			@SessionAttribute(name = "administrarIngreso", required = false) AdministrarIngreso administrarIngreso,
			@Valid Prestamo prestamo, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("prestamo", prestamo);
			return MOVIMIENTO_PRINCIPAL;
		}

		if (!isMontoValido(administrarIngreso, prestamo.getMontoPagado())) {
			model.addAttribute("prestamo", prestamo);
			return MOVIMIENTO_PRINCIPAL;
		}

		Double montoPrestamo = administrarIngreso.getMontoPrestamo();

		if (montoPrestamo == 0.0) {
			administrarIngreso.setMontoPrestamo(prestamo.getMontoPagado());
		} else {
			administrarIngreso.setMontoPrestamo(montoPrestamo + prestamo.getMontoPagado());
		}

		if (prestamo.getFolio() != null) {
			Prestamo prestamoTemp = movimientoService.findPrestamoByFolio(prestamo.getFolio());
			prestamo.setMontoPagado(prestamo.getMontoPagado() + prestamoTemp.getMontoPagado());
		}

		movimientoService.savePrestamo(prestamo);

		// Si el monto inicial es 0 termina la sesion para administrarIngreso y cambia a
		// dashboard
		if (administrarIngreso.getMontoInicial() == 0) {
			status.setComplete();
			return REDIRECT_DASHBOARD;
		}

		return REDIRECT_ADMINISTRAR_INGRESOS;
	}

	@PostMapping("/ahorro/agregarInversion")
	public String agregarInversion(
			@SessionAttribute(name = "administrarIngreso", required = false) AdministrarIngreso administrarIngreso,
			@Valid Inversion inversion, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("inversion", inversion);
			return MOVIMIENTO_PRINCIPAL;
		}

		if (!isMontoValido(administrarIngreso, inversion.getMonto())) {
			model.addAttribute("inversion", inversion);
			return MOVIMIENTO_PRINCIPAL;
		}

		Double montoInversion = administrarIngreso.getMontoInversion();

		if (montoInversion == 0.0) {
			administrarIngreso.setMontoInversion(inversion.getMonto());
		} else {
			administrarIngreso.setMontoInversion(montoInversion + inversion.getMonto());
		}

		if (inversion.getFolio() == null) {
			inversion.setFolio("2022-08-04-Inversion");
		}

		movimientoService.saveInversion(inversion);

		// Si el monto inicial es 0 termina la sesion para administrarIngreso y cambia a
		// dashboard
		if (administrarIngreso.getMontoInicial() == 0) {
			status.setComplete();
			return REDIRECT_DASHBOARD;
		}

		return REDIRECT_ADMINISTRAR_INGRESOS;
	}

	@ModelAttribute("listaCatalogoInversion")
	public List<CatalogoAppInversion> obtenerListaCatalogoInversion() {
		return movimientoService.findAllCatalogoInversion();
	}

	@ModelAttribute("ahorro")
	public Ahorro initAhorro() {
		return new Ahorro();
	}

	@ModelAttribute("gasto")
	public Gasto initGasto() {
		return new Gasto();
	}

	@ModelAttribute("inversion")
	public Inversion initInversion() {
		return new Inversion();
	}

	@ModelAttribute("prestamos")
	public List<Prestamo> obtenerPrestamos() {
		return movimientoService.findAllPrestamo();
	}

	@ModelAttribute("prestamo")
	public Prestamo initPrestamo() {
		return new Prestamo();
	}

	@ModelAttribute("gastoRecurrente")
	public GastoRecurrente initGastoRecurrente() {
		return new GastoRecurrente();
	}

	public Double getMontoPagadoActual() {
		return montoPagadoActual;
	}

	public void setMontoPagadoActual(Double montoPagadoActual) {
		this.montoPagadoActual = montoPagadoActual;
	}

}
