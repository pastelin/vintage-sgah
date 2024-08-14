package mx.com.sgah.enumerado.movimiento;

public enum TipoMovimiento {

	AHORRO(1),
	GASTO(2);
	private int id;
	

	private TipoMovimiento(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
