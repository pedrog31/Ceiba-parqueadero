package co.com.ceiba.dominio.repositorio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.dominio.ServicioParqueo;

public interface RepositorioServicioParqueo {
	
	/**
	 * Permite registrar un ingreso de vehiculo
	 * @param vehiculo
	 */
	void registrarIngreso (ServicioParqueo servicio);
	
	/**
	 * Permite registrar la salida del vehiculo, fecha de salida y valor del servicio
	 * @param vehiculo
	 */
	void registrarSalida (Date fechaFinalizacion, String placa, int valor);
	
	/**
	 * Permite registrar la salida del vehiculo, fecha de salida y valor del servicio
	 * @param vehiculo
	 */
	ServicioParqueo buscarVehiculo (String placa);
	
	/**
	 * Permite registrar la salida del vehiculo, fecha de salida y valor del servicio
	 * @param vehiculo
	 */
	List<ServicioParqueo> obtenerVehiculosParqueadero ();
}
