package co.com.ceiba.dominio;

import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;

public class RestriccionIngresoCapacidad implements RestriccionIngreso {
	
	public static final String PARQUEADERO_LLENO = "No se encuentran espacios disponibles para parquear.";

	private RepositorioServicioParqueo repositorioServicioParqueo;
	private String tipoVehiculo;
	private Integer capacidad;
	public RestriccionIngresoCapacidad(RepositorioServicioParqueo repositorioServicioParqueo, String tipoVehiculo,
			Integer capacidad) {
		this.repositorioServicioParqueo = repositorioServicioParqueo;
		this.tipoVehiculo = tipoVehiculo;
		this.capacidad = capacidad;
	}

	@Override
	public void esValido(Vehiculo vehiculo) {
		if (this.tipoVehiculo.equals(vehiculo.getTipo())) {
			int numeroVehiculos = this.repositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo());
			if (capacidad <= numeroVehiculos)
				throw new VigilanteExcepcion(PARQUEADERO_LLENO);
		}
	}

}
