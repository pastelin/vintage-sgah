package com.sgahs.springboot.app.controllers.gasto;

import static com.sgahs.springboot.app.rm.Constans.DETALLE_GASTO;
import static com.sgahs.springboot.app.rm.Constans.FORM_GASTO;
import static com.sgahs.springboot.app.rm.Constans.GASTO;
import static com.sgahs.springboot.app.rm.Constans.HAS_MESSAGE_ERROR;
import static com.sgahs.springboot.app.rm.Constans.MESSAGE_ERROR;
import static com.sgahs.springboot.app.rm.Constans.MONTO_GASTADO_MES_ACTUAL;
import static com.sgahs.springboot.app.rm.Constans.OPCION;
import static com.sgahs.springboot.app.rm.Constans.PATH_DASHBOARD;
import static com.sgahs.springboot.app.rm.Constans.SUCCESS;
import static com.sgahs.springboot.app.rm.Constans.TOTAL_GASTO_DISPONIBLE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgahs.springboot.app.entity.Gasto;
import com.sgahs.springboot.app.service.IGastoService;

@Controller
@RequestMapping("/dashboard")
public class DashboardGastoController {

	@Autowired
	IGastoService gastoService;
	
	@GetMapping("/detalleGasto")
	public String obtenerDetalleGasto(Model model) {
		
		model.addAttribute(OPCION, DETALLE_GASTO);
		model.addAttribute("gastos", gastoService.findGastoByCurrentMonth(gastoService.findAllGastoRecurrente()));
		model.addAttribute(TOTAL_GASTO_DISPONIBLE, gastoService.retrieveGastoAvailable());
		model.addAttribute(MONTO_GASTADO_MES_ACTUAL, gastoService.retrieveCurrentGastoMensual());
		
		return PATH_DASHBOARD;
	}

	@GetMapping("/formGasto")
	public String formGasto(Model model) {

		model.addAttribute(GASTO, new Gasto());
		model.addAttribute(OPCION, FORM_GASTO);
		model.addAttribute("gastosRecurrentes", gastoService.findAllGastoRecurrente());
		model.addAttribute(TOTAL_GASTO_DISPONIBLE, gastoService.retrieveGastoAvailable());
		
		return PATH_DASHBOARD;
	}

	@PostMapping("/agregarGasto")
	public String agregarGasto(@Valid Gasto gasto, BindingResult result, Model model, RedirectAttributes flash) {

		Double montoDisponible = gastoService.retrieveGastoAvailable();

		if (result.hasErrors() || gasto.getMonto() > montoDisponible) {
			
			model.addAttribute(GASTO, gasto);
			model.addAttribute(OPCION, FORM_GASTO);
			model.addAttribute("gastosRecurrentes", gastoService.findAllGastoRecurrente());
			model.addAttribute(TOTAL_GASTO_DISPONIBLE, gastoService.retrieveGastoAvailable());

			String message = (result.hasErrors()) ? "Validar datos ingresados!!"
					: "El monto no debe ser mayor a $" + montoDisponible;
			
			model.addAttribute(HAS_MESSAGE_ERROR, true);
			model.addAttribute(MESSAGE_ERROR, message);
			return PATH_DASHBOARD;
		}

		gasto.setCdTipoMovimiento(2);
		gastoService.saveGasto(gasto);

		flash.addFlashAttribute(SUCCESS, "Gasto agregado correctamente!!");

		return "redirect:/dashboard/detalleGasto";
	}
}
