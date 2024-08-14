package mx.com.sgah.capadatos.view;

public class Totales {

	private Float total;
	
	private String descripcion;

	public Totales(Float total, String descripcion) {
		super();
		this.total = total;
		this.descripcion = descripcion;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
