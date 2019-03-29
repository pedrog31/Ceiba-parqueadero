package co.com.ceiba.dominio.repositorio;

import co.com.ceiba.dominio.TasaRepresentativaMercado;

public interface RepositorioTasaRepresentativaMercado {
	
	/**
	 * Permite obtener TRM del dia actual
	 */
	TasaRepresentativaMercado obtenerTasaRepresentativaMercadoActual();
}
