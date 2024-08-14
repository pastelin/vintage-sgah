package com.sgahs.springboot.app.controllers.gasto;

import static com.sgahs.springboot.app.rm.Constans.DETALLE_GASTO;
import static com.sgahs.springboot.app.rm.Constans.MONTO_GASTADO_MES_ACTUAL;
import static com.sgahs.springboot.app.rm.Constans.OPCION;
import static com.sgahs.springboot.app.rm.Constans.PATH_DASHBOARD;
import static com.sgahs.springboot.app.rm.Constans.TOTAL_GASTO_DISPONIBLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgahs.springboot.app.service.IGastoService;


@Controller
@RequestMapping("/filtro")
public class FiltroGastoController {
	
	@Autowired
	IGastoService gastoService;
	
	@PostMapping("/detalleGasto")
	public String formFiltroGasto(Model model, Integer categoria) {
		
		if(categoria != null) {
			model.addAttribute("gastos", gastoService.findGastoByCategoria(categoria));			
		} else {
//			model.addAttribute("gastos", );
		}
		
		model.addAttribute(OPCION, DETALLE_GASTO);
		model.addAttribute(TOTAL_GASTO_DISPONIBLE, gastoService.retrieveGastoAvailable());
		model.addAttribute(MONTO_GASTADO_MES_ACTUAL, gastoService.retrieveCurrentGastoMensual());
		
		return PATH_DASHBOARD;
	}
	
}