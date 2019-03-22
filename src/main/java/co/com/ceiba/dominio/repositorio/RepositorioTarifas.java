package co.com.ceiba.dominio.repositorio;

import java.util.List;
import co.com.ceiba.dominio.Tarifa;

public interface RepositorioTarifas {
	
	/**
	 * Permite buscar las tarifas que aplican a cierto vehiculo para calcular el valor del servicio de estacionamiento
	 * @param tipoVehiculo
	 * @return Lista de tarifas aplicables al vehiculo.
	 */
	List<Tarifa> obtenerTarifasPorTipoVehiculo (String tipoVehiculo);
}
