package co.com.ceiba.dominio.repositorio;

import java.util.List;

import co.com.ceiba.dominio.Restriccion;

public interface RepositorioRestriccion {
	
	/**
	 * Permite obtener las restricciones activas para un vehiculo
	 * @param vehiculo
	 */
	List<Restriccion> obtenerRestriccionesActivas (String tipoVehiculo);

}
