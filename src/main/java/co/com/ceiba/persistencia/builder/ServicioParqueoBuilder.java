package co.com.ceiba.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

public class ServicioParqueoBuilder {
	
	@Autowired
	static RepositorioTarifas repositorioTarifas;
	
	private ServicioParqueoBuilder() {}
	
	public static ServicioParqueo convertirADominio (ServicioParqueoEntity servicioParqueoEntity) {
		return servicioParqueoEntity == null  ? 
				null : new ServicioParqueo (
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

	public static List<ServicioParqueo> convertirADominio(List<ServicioParqueoEntity> serviciosParqueoEntity) {
		if (serviciosParqueoEntity == null) return new ArrayList<>();
		List<ServicioParqueo> serviciosParqueo = new ArrayList<>();
		for (ServicioParqueoEntity servicioParqueoEntity: serviciosParqueoEntity) {
				serviciosParqueo.add(convertirADominio(servicioParqueoEntity));
		}
		return serviciosParqueo;
	}
}
