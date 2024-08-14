package mx.com.sgah.capaservicio;

import java.util.List;

import mx.com.sgah.capadatos.domain.Categoria;
import mx.com.sgah.capadatos.domain.Movimiento;

public interface MovimientoServiceI {

	void agregarMovimiento(Movimiento movimiento);
	
	void actualizarMovimiento(Movimiento movimiento);
	
	void eliminarMovimiento(Movimiento movimiento);
	
	List<Movimiento> listarMovimiento();
	
	Movimiento encontrarPorId(Movimiento movimiento);
	
	List<Categoria> listarGategoria();
	
	Double obtenerSumaGastoMensual(String fecha);
	
}
