package mx.com.sgah.capaservicio;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.sgah.capadatos.MovimientoDaoI;
import mx.com.sgah.capadatos.domain.Categoria;
import mx.com.sgah.capadatos.domain.Movimiento;

@Service("movimientoServiceI")
@Transactional	
public class MovimientoServiceImpl implements MovimientoServiceI {

	@Autowired
	MovimientoDaoI movimientoDao;
	
	@Override
	public void agregarMovimiento(Movimiento movimiento) {
		movimientoDao.addMovimiento(movimiento);
	}

	@Override
	public void actualizarMovimiento(Movimiento movimiento) {
		movimientoDao.updateMovimiento(movimiento);
		
	}

	@Override
	public void eliminarMovimiento(Movimiento movimiento) {
		movimientoDao.deleteMovimiento(movimiento);
	}

	@Override
	public List<Movimiento> listarMovimiento() {
		return movimientoDao.findAllMovimiento();
	}

	@Override
	public Movimiento encontrarPorId(Movimiento movimiento) {
		return movimientoDao.findMovimientoById(movimiento);
	}

	@Override
	public List<Categoria> listarGategoria() {
		return movimientoDao.findAllCategoria();
	}

	@Override
	public Double obtenerSumaGastoMensual(String fecha) {
		return movimientoDao.getSumaGastoMensual(fecha);
	}

}
