package co.com.ceiba.dominio.excepcion;

public class VigilanteExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VigilanteExcepcion(String message) {
		super(message);
	}

}
