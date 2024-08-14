package mx.com.sgah.enumerado.movimiento;

public enum CatCategoria {

	SELECCIONAR_OPCION(1),
	FREE(2), 
	SPEND(3), 
	KEEP(4),
	BORROW(5),
	OTROS(15),
	TODOS(19);
	
	private int id;
	
	private CatCategoria(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
