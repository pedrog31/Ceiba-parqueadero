package co.com.ceiba.dominio;

import java.util.Calendar;

import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;

public class RestriccionPlacaA implements RestriccionIngreso {

	public static final String VEHICULO_NO_AUTORIZADO = "Vehiculo no habilitado para el ingreso.";
	private Calendar calendario;
	
	public RestriccionPlacaA (Calendar calendario) {
		this.calendario = calendario;
	}
	
	@Override
	public void esValido(Vehiculo vehiculo) {
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		if (vehiculo.getPlaca().startsWith("A") && (diaSemana == Calendar.MONDAY || diaSemana == Calendar.SUNDAY))
			throw new VigilanteExcepcion(VEHICULO_NO_AUTORIZADO);
	}

}
