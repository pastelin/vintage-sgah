package mx.com.sgah.capadatos;

import java.util.List;

import mx.com.sgah.capadatos.domain.Categoria;
import mx.com.sgah.capadatos.domain.Movimiento;

public interface MovimientoDaoI {
	
	void addMovimiento(Movimiento movimiento);
	
	void updateMovimiento(Movimiento movimiento);
	
	void deleteMovimiento(Movimiento movimiento);
	
	List<Movimiento> findAllMovimiento();
	
	Movimiento findMovimientoById(Movimiento movimiento);
	
	List<Categoria> findAllCategoria();
	
	Double getSumaGastoMensual(String fechaActual);
	
}
