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
	 * 	 * @param vehiculo
	 */
	void registrarSalidaVehiculo(ServicioParqueo servicioParqueo);
	
	/**
	 * Permite registrar el efectivo pago del servicio
	 * 	 * @param vehiculo
	 */
	void registrarPagoServicio (String placa, Date fechaFinalizacion);
	
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
	 * Obtiene el numero de vehiculos parquedos actualmente segun el tipo de vehiculo
	 * @param placa vehiculo a buscar
	 */
	Integer obtenerNumeroVehiculosParqueados(String tipoVehiculo);
}
