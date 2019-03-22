package co.com.ceiba.dominio.excepcion;

public class ServicioParqueoExcepcion extends RuntimeException {

	private static final long serialVersionUID = 2L;

	public ServicioParqueoExcepcion(String message) {
		super(message);
	}

}
