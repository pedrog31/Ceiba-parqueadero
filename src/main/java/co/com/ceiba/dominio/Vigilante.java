package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

public class Vigilante {
	
	public static final String VEHICULO_NO_AUTORIZADO = "Vehiculo no habilitado para el ingreso.";
	
	private RepositorioServicioParqueo respositorioServicioParqueo;
	private RepositorioRestriccion repositorioRestricciones;
	private RepositorioTarifas repositorioTarifas;

	public Vigilante(RepositorioServicioParqueo respositorioServicioParqueo,RepositorioRestriccion repositorioRestricciones, RepositorioTarifas repositorioTarifas) {
		this.respositorioServicioParqueo = respositorioServicioParqueo;
		this.repositorioRestricciones = repositorioRestricciones;
		this.repositorioTarifas = repositorioTarifas;
	}

	public void registrarIngresoVehiculo(Vehiculo vehiculo) {
		List<Restriccion> restricciones = this.repositorioRestricciones
				.obtenerRestriccionesActivas(vehiculo.getTipo());
		for (Restriccion restriccion: restricciones) {
			Pattern pat = Pattern.compile(restriccion.getExpresionRegular(), Pattern.CASE_INSENSITIVE);
			Matcher match = pat.matcher(vehiculo.getPlaca());
			if (match.find()) {
				throw new VigilanteExcepcion(VEHICULO_NO_AUTORIZADO);
			}
		}
		ServicioParqueo servicio = new ServicioParqueo(new Date(), this.repositorioTarifas, vehiculo);
		respositorioServicioParqueo.registrarIngreso(servicio);
	}
 }