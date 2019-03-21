package co.com.ceiba.persistencia.builder;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

public class ServicioParqueoBuilder {
	
	@Autowired
	static RepositorioTarifas repositorioTarifas;
	
	private ServicioParqueoBuilder() {}
	
	public static ServicioParqueo convertirADominio (ServicioParqueoEntity servicioParqueoEntity) {
		return new ServicioParqueo (
				servicioParqueoEntity.getFechaIngreso(),
				servicioParqueoEntity.getFechaSalida(),
				repositorioTarifas,
				servicioParqueoEntity.getValor(),
				VehiculoBuilder.convertirADominio(servicioParqueoEntity.getVehiculo()));
	}
	
	public static ServicioParqueoEntity convertirAEntity (ServicioParqueo servicioParqueo) {
		return new ServicioParqueoEntity(
				servicioParqueo.getFechaIngreso(),
				servicioParqueo.getFechaSalida(),
				servicioParqueo.getValor(),
				VehiculoBuilder.convertirAEntity(servicioParqueo.getVehiculo()));
	}
}
