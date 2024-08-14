package com.sgahs.springboot.app.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgahs.springboot.app.entity.Ahorro;
import com.sgahs.springboot.app.entity.CatalogoAppInversion;
import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.entity.Inversion;
import com.sgahs.springboot.app.entity.Prestamo;
import com.sgahs.springboot.app.service.ICustomMovimientoService;
import com.sgahs.springboot.app.service.IGastoService;
import com.sgahs.springboot.app.service.IMovimientoService;

@Controller
@RequestMapping("/dashboard")
public class DashboarController {

	private static final String PATH_DASHBOARD = "dashboard/dashboard";
	private static final String OPCION = "opcion";
	private static final String AHORRO = "ahorro";
	
	private static final String INVERSION = "inversion";
	private static final String MOVIMIENTO = "movimiento";
	private static final String PRESTAMO = "prestamo";
	private static final String GASTO_DISPONIBLE = "gastoDisponible";
	private static final String TOTAL_AHORRO = "totalAhorro";
	private static final String TOTAL_GASTO_DISPONIBLE = "totalGastoDisponible";
	private static final String TOTAL_INVERSION = "totalInversion";
	private static final String TOTAL_PRESTAMO = "totalPrestamo";
	
	private static final String DETALLE_PRESTAMO = "detallePrestamo";
	private static final String DETALLE_INVERSION = "detalleInversion";
	
	private static final String FORM_INVERSION = "formInversion";
	private static final String FORM_AHORRO = "formAhorro";
	private static final String FORM_PRESTAMO = "formPrestamo";
	private static final String AGREGA = "agrega";
	private static final String ACTUALIZA = "actualiza";
	private static final String SUCCESS = "success";
	private static final String HAS_MESSAGE_ERROR = "hasMessageError";
	private static final String MESSAGE_ERROR = "messageError";
	

	@Autowired
	ICustomMovimientoService customMovimientoService;

	@Autowired
	IMovimientoService movimientoService;
	
	@Autowired
	IGastoService gastoService;

	public Double calcularTotal(String movimiento) {

		Double total;

		switch (movimiento) {

		case AHORRO:
			total = customMovimientoService.obtenerAhorroTotal();
			break;

		case PRESTAMO:
			total = customMovimientoService.obtenerPrestamoTotal();
			break;

		case INVERSION:
			total = customMovimientoService.obtenerInversionTotal();
			break;

		case GASTO_DISPONIBLE:
			total = gastoService.retrieveGastoAvailable();
			break;
		default:
			total = 0.0;
		}

		return total;
	}

	@GetMapping("/resumen")
	public String dashboard(Model model) {

		model.addAttribute(TOTAL_AHORRO, calcularTotal(AHORRO) - calcularTotal(PRESTAMO));
		model.addAttribute(TOTAL_PRESTAMO, calcularTotal(PRESTAMO));
		model.addAttribute(TOTAL_INVERSION, calcularTotal(INVERSION));
		model.addAttribute(TOTAL_GASTO_DISPONIBLE, calcularTotal(GASTO_DISPONIBLE));
		model.addAttribute(OPCION, "resumen");

		return PATH_DASHBOARD;
	}

	/* Inicia procesos para Ahorro */

	@GetMapping("/detalleAhorro")
	public String obtenerDetalleAhorro(Model model) {

		List<Ahorro> ahorros = movimientoService.findAllAhorro();

		model.addAttribute(OPCION, AHORRO);
		model.addAttribute("ahorros", ahorros);
		model.addAttribute(TOTAL_AHORRO, calcularTotal(AHORRO) - calcularTotal(PRESTAMO));

		return PATH_DASHBOARD;
	}

	@GetMapping("/formAhorro")
	public String formAhorro(Model model) {

		model.addAttribute(AHORRO, new Ahorro());
		model.addAttribute(OPCION, FORM_AHORRO);

		return PATH_DASHBOARD;
	}

	@PostMapping("/agregarAhorro")
	public String agregarAhorro(@Valid Ahorro ahorro, BindingResult result, Model model, RedirectAttributes flash) {

		if (result.hasErrors()) {
			model.addAttribute(AHORRO, ahorro);
			model.addAttribute(OPCION, FORM_AHORRO);
			return PATH_DASHBOARD;
		}

		movimientoService.saveAhorro(ahorro);
		flash.addFlashAttribute(SUCCESS, "Ahorro agregado correctamente!!");

		return "redirect:/dashboard/detalleAhorro";
	}

	/* Finaliza procesos para Ahorro */


	/* Inicia procesos para Inversion */

	/**
	 * Metodo que inicializa los valores que se requieren en la vista
	 * 
	 * @param model                         para poder enviar la informacion a la
	 *                                      vista
	 * @param isInversionRequired           booleano que indica si requiere el
	 *                                      objeto Inversion()
	 * @param inversion                     instancia del objeto Inversion(), si es
	 *                                      nulo crea una instancia
	 * @param opcion                        indica la vista que debera ser mostrada
	 *                                      al usuario
	 * @param isDetalle                     boleano que indica si se debe obtener la
	 *                                      lista de gastos en la BD
	 * @param movimiento                    indica si mostrara inputs para agregar o
	 *                                      actualizar un prestamo
	 * @param isCatalogoInversionesRequired
	 * 
	 */
	public void initModelInversion(Model model, boolean isInversionRequired, Inversion inversion, String opcion,
			boolean isDetalle, String movimiento, boolean isCatalogoInversionesRequired) {

		if (isInversionRequired) {
			if (inversion == null) {
				model.addAttribute(INVERSION, new Inversion());
			} else {
				model.addAttribute(INVERSION, inversion);
			}
		}

		model.addAttribute(OPCION, opcion);

		if (isDetalle) {
			List<Inversion> inversiones = movimientoService.findAllInversion();
			model.addAttribute("inversiones", inversiones);
		}

		if (movimiento != null && movimiento.equals(AGREGA)) {
			model.addAttribute(MOVIMIENTO, AGREGA);
		}

		if (movimiento != null && movimiento.equals(ACTUALIZA)) {
			model.addAttribute(MOVIMIENTO, ACTUALIZA);
		}

		if (isCatalogoInversionesRequired) {
			List<CatalogoAppInversion> catalogoInversiones = movimientoService.findAllCatalogoInversion();
			model.addAttribute("catalogoInversiones", catalogoInversiones);
		}

		model.addAttribute(TOTAL_INVERSION, calcularTotal(INVERSION));
	}

	@GetMapping("/detalleInversion")
	public String obtenerDetalleInversion(Model model) {

		initModelInversion(model, false, null, DETALLE_INVERSION, true, null, false);

		return PATH_DASHBOARD;
	}

	@GetMapping("/formInversion/{folio}")
	public String formInversion(@PathVariable String folio, Model model) {

		if (folio.equals("sinFolio")) {
			initModelInversion(model, true, null, FORM_INVERSION, false, AGREGA, true);
		} else {
			Inversion inversion = movimientoService.findInversionByFolio(folio);
			initModelInversion(model, true, inversion, FORM_INVERSION, false, ACTUALIZA, false);
		}

		return PATH_DASHBOARD;
	}

	@PostMapping("agregarInversion")
	public String agregarInversion(@Valid Inversion inversion, BindingResult result, Model model,
			RedirectAttributes flash, Double montoRetirado) {

		String folio = inversion.getFolio();

		if (result.hasErrors()) {
			if (folio == null || folio.isEmpty()) {
				initModelInversion(model, true, inversion, FORM_INVERSION, false, AGREGA, true);
			} else {
				Inversion inversionAnterior = movimientoService.findInversionByFolio(folio);
				initModelInversion(model, true, inversionAnterior, FORM_INVERSION, false, ACTUALIZA, false);
			}
			return PATH_DASHBOARD;
		}

		if (folio == null || folio.isEmpty()) {
			// Si el folio es vacio indica que insertara una inversion
			inversion.setFolio(Long.toString(new Date().getTime()));
			movimientoService.saveInversion(inversion);
			flash.addFlashAttribute(SUCCESS, "Inversion agregada correctamente!!");
		} else {
			// Indica que se realizara un retiro de una inversion especificada

			// Consulta inversion con el folio para tenerlo como base para incrementar el
			// monto retirado
			Inversion inversionAnterior = movimientoService.findInversionByFolio(folio);

			// Si el monto a retirar ingresado en la vista es nulo o 0, enviara un mensaje
			// de error debido a que este valor se usara como parametro para el objeto
			// Ahorro en la variable monto y esta no admite el valor 0
			if (montoRetirado == null || montoRetirado == 0) {
				initModelInversion(model, true, inversionAnterior, FORM_INVERSION, false, ACTUALIZA, false);
				model.addAttribute(HAS_MESSAGE_ERROR, true);
				model.addAttribute(MESSAGE_ERROR, "El monto debe ser mayor a $0 !!");
				return PATH_DASHBOARD;
			}

			// Crea objeto ahorro con el monto retirado en la inversion
//			Ahorro ahorro = new Ahorro();
//			ahorro.setMonto(inversion.getMontoRetirado());
//			ahorro.setDescripcion("Monto retirado de una inversion");

			// Si el monto retirado anterior es mayor a 0 indica que se debe sumar el monto
			// retirado obtenido de la BD con el que se ingresa en la vista
//			Double montoRetiradoAnterior = inversionAnterior.getMontoRetirado();
//			if (montoRetiradoAnterior > 0) {
//				Double montoRetiradoActual = inversion.getMontoRetirado();
//				inversion.setMontoRetirado(montoRetiradoActual + montoRetiradoAnterior);
//			}

			// Valida si el monto retirado supera al invertido, si es el caso mandara un
			// mensaje de error a la vista
			Double monto = inversion.getMonto();
//			Double montoRetirado = inversion.getMontoRetirado();

			if (montoRetirado > monto) {
				initModelInversion(model, true, inversionAnterior, FORM_INVERSION, false, ACTUALIZA, false);
				model.addAttribute(HAS_MESSAGE_ERROR, true);
				model.addAttribute(MESSAGE_ERROR, "El monto a retirar supera el maximo!!");
				return PATH_DASHBOARD;
			}

			// Envia objeto a actualizar en BD para la inversion, y crear un nuevo registro
			// de ahorro que es a donde se iran los retiros de inversion
//			movimientoService.saveAhorro(ahorro);
			inversion.setMonto(monto - montoRetirado);
			movimientoService.updateInversion(inversion);
			flash.addFlashAttribute(SUCCESS, "Retiro de inversion realizada correctamente!!");
		}

		return "redirect:/dashboard/detalleInversion";
	}

	/* Finaliza procesos para Inversion */

	/* Inicia procesos para Prestamo */

	/**
	 * Metodo que inicializa los valores que se requieren en la vista
	 * 
	 * @param model              para poder enviar la informacion a la vista
	 * @param isPrestamoRequired booleano que indica si requiere el objeto
	 *                           Prestamo()
	 * @param prestamo           instancia del objeto Gasto(), si es nulo crea una
	 *                           instancia
	 * @param opcion             indica la vista que debera ser mostrada al usuario
	 * @param isDetalle          boleano que indica si se debe obtener la lista de
	 *                           gastos en la BD
	 * @param movimiento         indica si mostrara inputs para agregar o actualizar
	 *                           un prestamo
	 * 
	 */
	public void initModelPrestamo(Model model, boolean isPrestamoRequired, Prestamo prestamo, String opcion,
			boolean isDetalle, String movimiento) {

		if (isPrestamoRequired) {
			if (prestamo == null) {
				model.addAttribute(PRESTAMO, new Prestamo());
			} else {
				model.addAttribute(PRESTAMO, prestamo);
			}
		}

		model.addAttribute(OPCION, opcion);

		if (isDetalle) {
			List<Prestamo> prestamos = movimientoService.findAllPrestamo();
			model.addAttribute("prestamos", prestamos);
		}

		if (movimiento != null && movimiento.equals(AGREGA)) {
			model.addAttribute(MOVIMIENTO, AGREGA);
		}

		if (movimiento != null && movimiento.equals(ACTUALIZA)) {
			model.addAttribute(MOVIMIENTO, ACTUALIZA);
		}

		model.addAttribute(TOTAL_PRESTAMO, calcularTotal(PRESTAMO));
		model.addAttribute(TOTAL_AHORRO, calcularTotal(AHORRO) - calcularTotal(PRESTAMO));
	}

	@GetMapping("/detallePrestamo")
	public String obtenerDetallePrestamo(Model model) {

		initModelPrestamo(model, false, null, DETALLE_PRESTAMO, true, null);

		return PATH_DASHBOARD;
	}

	@GetMapping("/formPrestamo/{folio}")
	public String formPrestamo(@PathVariable String folio, Model model) {

		if (folio.equals("sinFolio")) {
			initModelPrestamo(model, true, null, FORM_PRESTAMO, false, AGREGA);
		} else {
			Prestamo prestamo = movimientoService.findPrestamoByFolio(folio);
			initModelPrestamo(model, true, prestamo, FORM_PRESTAMO, false, ACTUALIZA);
		}

		return PATH_DASHBOARD;
	}

	@PostMapping("agregarPrestamo")
	public String agregarPrestamo(@Valid Prestamo prestamo, BindingResult result, Model model,
			RedirectAttributes flash) {

		String folio = prestamo.getFolio();

		if (result.hasErrors()) {

			if (folio == null || folio.isEmpty()) {
				initModelPrestamo(model, true, prestamo, FORM_PRESTAMO, false, AGREGA);
			} else {
				initModelPrestamo(model, true, movimientoService.findPrestamoByFolio(folio), FORM_PRESTAMO, false,
						ACTUALIZA);
			}

			return PATH_DASHBOARD;
		}

		Double montoPrestado = prestamo.getMontoPrestado();
		Double ahorroTotal = calcularTotal(AHORRO) - calcularTotal(PRESTAMO);

		if (folio == null || folio.isEmpty()) {

			if (montoPrestado > ahorroTotal) {
				initModelPrestamo(model, true, prestamo, FORM_PRESTAMO, false, AGREGA);
				model.addAttribute(HAS_MESSAGE_ERROR, true);
				model.addAttribute(MESSAGE_ERROR, "El monto tomar prestado supera al maximo permitido!!");
				return PATH_DASHBOARD;
			}

			prestamo.setFolio(Long.toString(new Date().getTime()));
			Gasto gasto = new Gasto();
			gasto.setMonto(montoPrestado);
			gasto.setDescripcion(prestamo.getDescripcion());

			gastoService.saveGasto(gasto);
			flash.addFlashAttribute(SUCCESS, "Prestamo agregado correctamente!!");

		} else {
			Prestamo prestamoAnterior = movimientoService.findPrestamoByFolio(folio);
			Double montoPagadoAnterior = prestamoAnterior.getMontoPagado();

			if (prestamo.getMontoPagado() == null || prestamo.getMontoPagado() == 0) {
				initModelPrestamo(model, true, prestamoAnterior, FORM_PRESTAMO, false, ACTUALIZA);
				model.addAttribute(HAS_MESSAGE_ERROR, true);
				model.addAttribute(MESSAGE_ERROR, "El monto debe ser mayor a $0 !!");
				return PATH_DASHBOARD;
			}

			if (montoPagadoAnterior > 0) {
				prestamo.setMontoPagado(prestamo.getMontoPagado() + montoPagadoAnterior);
			}

			if (prestamo.getMontoPagado() > montoPrestado) {
				initModelPrestamo(model, true, prestamoAnterior, FORM_PRESTAMO, false, ACTUALIZA);
				model.addAttribute(HAS_MESSAGE_ERROR, true);
				model.addAttribute(MESSAGE_ERROR, "El monto a pagar supera al maximo permitido!!");
				return PATH_DASHBOARD;
			}

			if (montoPrestado.equals(prestamo.getMontoPagado())) {
				prestamo.setCdEstatus(2);
			}

			flash.addFlashAttribute(SUCCESS, "Prestamo actualizado correctamente!!");
		}

		movimientoService.savePrestamo(prestamo);

		return "redirect:/dashboard/detallePrestamo";
	}

	/* Finaliza procesos para Prestamo */
}
