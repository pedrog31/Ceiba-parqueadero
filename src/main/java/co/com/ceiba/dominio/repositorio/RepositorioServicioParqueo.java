package co.com.ceiba.dominio.repositorio;

import java.util.Date;
import java.util.List;
import co.com.ceiba.dominio.ServicioParqueo;

public interface RepositorioServicioParqueo {
	
	/**
	 * Permite registrar un ingreso de vehiculo
	 * @param vehiculo
	 */
	void registrarIngresoVehiculo (ServicioParqueo servicio);
	
	/**
	 * Permite registrar la salida del vehiculo
	 * @param vehiculo
	 */
	void registrarSalidaVehiculo (ServicioParqueo servicio);
	
	/**
	 * Permite buscar un servicio activo con base en la placa del vehiculo
	 * @param placa vehiculo a buscar
	 */
	ServicioParqueo buscarServicioVehiculo (String placa, Date fechaSalida);
	
	/**
	 * Retorna todos los servicios que se encuentran actualmente activos en el parqueadero
	 */
	List<ServicioParqueo> obtenerVehiculosParqueadero ();

	/**
	 * Retorna el numero de vehiculos parqueados actualmente
	 */
	Integer obtenerNumeroVehiculosParqueados(String tipo);

	/**
	 * Permite registrar el pago del servicio
	 * @param servicioParqueo
	 */
	void registrarPagoServicio(Boolean pagado, String placa);
}
