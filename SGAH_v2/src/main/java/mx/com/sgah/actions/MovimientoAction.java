package mx.com.sgah.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.com.sgah.capadatos.domain.Categoria;
import mx.com.sgah.capadatos.domain.Movimiento;
import mx.com.sgah.capaservicio.MovimientoServiceI;
import mx.com.sgah.enumerado.movimiento.CatCategoria;
import mx.com.sgah.enumerado.movimiento.TipoMovimiento;
import mx.com.sgah.utileria.Fecha;

public class MovimientoAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Movimiento> listaMovimientos;
	private List<Movimiento> listaMovimientosPorCategoria;
	private List<Categoria> listaCategorias;
	private List<Float> totales;
	private Map<String, String> catFormAhorro;
	private Map<String, String> catFormGastos;

	private float totalSpend;
	private float totalBorrow;
	private float totalFree;
	private float totalKeep;
	private float total;
	private float montoAnterior;
	private Double sumaGastoMensual;

	private Movimiento movimiento;
	private String descripcionTotales;
	private int idCategoria;

	private boolean formUpdateActive;

	@Autowired
	MovimientoServiceI movimientoService;

	@Action(value = "/mostrarDatos", results = {
			@Result(name = "success", location = "/WEB-INF/content/movimientos.jsp") })
	public String initExecute() {
		obtenerTotales();
		inicializaListas();
		return "success";
	}
	
	@Action(value = "/agregarMovimiento", results = {
			@Result(name = "agregadoExitoso", location = "mostrarDatos", type = "redirectAction"),
			@Result(name = "input", location="/WEB-INF/content/popup/form-ahorro.jsp")
			})
	public String agregarMovimiento() {
		if (this.movimiento.getIdCatTipoMovimiento() == null) {
			if (this.movimiento.getIdCatCategoria() == 4 || this.movimiento.getIdCatCategoria() == 2) {
				this.movimiento.setIdCatTipoMovimiento(TipoMovimiento.AHORRO.getId());
			} else {
				this.movimiento.setIdCatTipoMovimiento(TipoMovimiento.GASTO.getId());
			}
		}
		this.movimiento.setFecha(Fecha.getCurrentDate());
		this.movimientoService.agregarMovimiento(this.movimiento);
		return "agregadoExitoso";
	}

	@Action(value = "modificarMovimiento", results = {
			@Result(name = "modificadoExitoso", location = "/WEB-INF/content/lista-movimientos.jsp") })
	public String modificarMovimiento() {
		float montoNuevo = montoAnterior - movimiento.getMonto();
		this.movimiento.setMonto(montoNuevo);
		this.movimientoService.actualizarMovimiento(this.movimiento);
		obtenerMovimientosPorCategoria();
		return "modificadoExitoso";
	}

	@Action(value = "bucarMovimiento", results = {
			@Result(name = "busquedaPorIdExitosa", location = "/WEB-INF/content/lista-movimientos.jsp") })
	public String buscarMovimientoPorId() {
		this.movimiento = this.movimientoService.encontrarPorId(movimiento);
		this.montoAnterior = this.movimiento.getMonto();
		this.formUpdateActive = true;
		obtenerMovimientosPorCategoria();
		obtenerTotales();
		return "busquedaPorIdExitosa";
	}

	@Action(value = "buscarPorCategoria", results = {
			@Result(name = "busquedaExitosa", location = "/WEB-INF/content/lista-movimientos.jsp") })
	public String buscarPorCategoria() {
		obtenerMovimientosPorCategoria();
		return "busquedaExitosa";
	}
	
	private void obtenerMovimientosPorCategoria() {
		this.listaMovimientosPorCategoria = new ArrayList<>();

		if (this.listaMovimientos == null) {
			this.listaMovimientos = this.movimientoService.listarMovimiento();
		}

		if (idCategoria == CatCategoria.TODOS.getId()) {
			this.listaMovimientosPorCategoria = this.listaMovimientos;
		} else {
			for (Movimiento mov : this.listaMovimientos) {
				if (mov.getIdCatCategoria() == idCategoria && mov.getMonto() != 0) {
					this.listaMovimientosPorCategoria.add(mov);
				}
			}
		}
	}
	
	private void obtenerTotales() {
		listaMovimientos = this.movimientoService.listarMovimiento();
		int categoria = 0;

		for (Movimiento objMovimiento : listaMovimientos) {
			categoria = objMovimiento.getIdCatCategoria();

			if (categoria == CatCategoria.KEEP.getId()) {
				this.totalKeep += objMovimiento.getMonto();
			} else if (categoria == CatCategoria.FREE.getId()) {
				this.totalFree += objMovimiento.getMonto();
			} else if (categoria == CatCategoria.BORROW.getId()) {
				this.totalBorrow += objMovimiento.getMonto();
			} else if (categoria != CatCategoria.KEEP.getId() && categoria != CatCategoria.SELECCIONAR_OPCION.getId()
					&& categoria != CatCategoria.BORROW.getId() && categoria != CatCategoria.OTROS.getId()) {
				this.totalSpend += convertirMontoPositivo(objMovimiento.getMonto());
			}
		}
		calcularTotales();
	}

	private float convertirMontoPositivo(float monto) {
		float nuevoMonto = monto;
		float operandoNegativo = -1.0f;

		if (monto < 0) {
			nuevoMonto = operandoNegativo * monto;
		}
		return nuevoMonto;
	}

	private void calcularTotales() {
		this.totalKeep = this.totalKeep - this.totalBorrow;
		this.totalFree = this.totalFree + this.totalBorrow - this.totalSpend;
		this.total = this.totalFree + this.totalKeep;

		this.totales = Arrays.asList(this.totalKeep, this.totalFree, this.totalBorrow);
	}

	public void inicializaListas() {
		obtenerDescripcionTotales();
		obtenerOpcionesAhorro();
		obtenerOpcionesGasto();
		obtenerGastoMensual();
	}

	private void obtenerDescripcionTotales() {
		this.descripcionTotales = "Total ahorrado, Total libre para gastos, Total de prestamos por pagar";
	}

	private void obtenerOpcionesAhorro() {
		this.catFormAhorro = new HashMap<>();
		this.catFormAhorro.put("1", "Seleccionar una opción");
		this.catFormAhorro.put("2", "Free");
		this.catFormAhorro.put("4", "keep");
	}

	private void obtenerOpcionesGasto() {
		catFormGastos = new HashMap<>();
		
		if (this.listaCategorias == null || this.listaCategorias.isEmpty()) {
			this.listaCategorias = movimientoService.listarGategoria();
		}
		for (int i = 0; i < this.listaCategorias.size(); i++) {
			int id = this.listaCategorias.get(i).getIdCatCategoria();
			if (id != CatCategoria.FREE.getId() && id != CatCategoria.KEEP.getId() && id != CatCategoria.BORROW.getId()
					&& id != CatCategoria.SPEND.getId() && id != CatCategoria.OTROS.getId()
					&& id != CatCategoria.TODOS.getId()) {

				catFormGastos.put(this.listaCategorias.get(i).getIdCatCategoria().toString(),
						this.listaCategorias.get(i).getNombre());
			}
		}
		this.setCatFormGastos(catFormGastos);
	}
	
	private void obtenerGastoMensual() {
		this.sumaGastoMensual = movimientoService.obtenerSumaGastoMensual(Fecha.getCurrenDateYMD());
	}
	
//	public void validate() {
//		
//		validateCategoria(movimiento.getIdCatCategoria());
//		
//		
//	}
	
	public void validateCategoria(Integer categoria) {
		
		if(categoria == null || categoria == 1) {
			addFieldError("movimiento.idCatCategoria", "Indique una categoría válida");
		}
		
	}
	

	public List<Movimiento> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<Movimiento> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}

	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public float getTotalSpend() {
		return totalSpend;
	}

	public void setTotalSpend(float totalSpend) {
		this.totalSpend = totalSpend;
	}

	public float getTotalBorrow() {
		return totalBorrow;
	}

	public void setTotalBorrow(float totalBorrow) {
		this.totalBorrow = totalBorrow;
	}

	public float getTotalFree() {
		return totalFree;
	}

	public void setTotalFree(float totalFree) {
		this.totalFree = totalFree;
	}

	public float getTotalKeep() {
		return totalKeep;
	}

	public void setTotalKeep(float totalKeep) {
		this.totalKeep = totalKeep;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public float getMontoAnterior() {
		return montoAnterior;
	}

	public void setMontoAnterior(float montoAnterior) {
		this.montoAnterior = montoAnterior;
	}

	public Map<String, String> getCatFormAhorro() {
		return catFormAhorro;
	}

	public void setCatFormAhorro(Map<String, String> catFormAhorro) {
		this.catFormAhorro = catFormAhorro;
	}

	public Map<String, String> getCatFormGastos() {
		return catFormGastos;
	}

	public void setCatFormGastos(Map<String, String> catFormGastos) {
		this.catFormGastos = catFormGastos;
	}

	public List<Float> getTotales() {
		return totales;
	}

	public void setTotales(List<Float> totales) {
		this.totales = totales;
	}

	public String getDescripcionTotales() {
		return descripcionTotales;
	}

	public void setDescripcionTotales(String descripcionTotales) {
		this.descripcionTotales = descripcionTotales;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<Movimiento> getListaMovimientosPorCategoria() {
		return listaMovimientosPorCategoria;
	}

	public void setListaMovimientosPorCategoria(List<Movimiento> listaMovimientosPorCategoria) {
		this.listaMovimientosPorCategoria = listaMovimientosPorCategoria;
	}

	public boolean isFormUpdateActive() {
		return formUpdateActive;
	}

	public void setFormUpdateActive(boolean formUpdateActive) {
		this.formUpdateActive = formUpdateActive;
	}

	public Double getSumaGastoMensual() {
		return sumaGastoMensual;
	}

	public void setSumaGastoMensual(Double sumaGastoMensual) {
		this.sumaGastoMensual = sumaGastoMensual;
	}
	
}
