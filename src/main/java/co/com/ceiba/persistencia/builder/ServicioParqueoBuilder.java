package co.com.ceiba.persistencia.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

public final class ServicioParqueoBuilder {

	private ServicioParqueoBuilder() {
	}

	public static ServicioParqueo convertirADominio(Optional<ServicioParqueoEntity> optionalServicioParqueoEntity) {
		ServicioParqueoEntity servicioParqueoEntity = optionalServicioParqueoEntity.orElse(new ServicioParqueoEntity());
		return convertirADominio(servicioParqueoEntity);
	}
	
	public static ServicioParqueo convertirADominio(ServicioParqueoEntity servicioParqueoEntity) {
		return new ServicioParqueo(
				servicioParqueoEntity.getFechaIngreso(), 
				servicioParqueoEntity.getFechaSalida(),
				servicioParqueoEntity.getPagado(),
				servicioParqueoEntity.getValor(),
			    VehiculoBuilder.convertirADominio(servicioParqueoEntity.getVehiculo()));
	}

	public static ServicioParqueoEntity convertirAEntity(ServicioParqueo servicioParqueo) {
		return new ServicioParqueoEntity(
				servicioParqueo.getFechaIngreso(),
				servicioParqueo.getFechaSalida(),
				servicioParqueo.isPagado(),
				servicioParqueo.getValor(),
				VehiculoBuilder.convertirAEntity(servicioParqueo.getVehiculo()));
	}

	public static List<ServicioParqueo> convertirADominio(List<ServicioParqueoEntity> serviciosParqueoEntity) {
		List<ServicioParqueo> serviciosParqueo = new ArrayList<>();
		for (ServicioParqueoEntity servicioParqueoEntity : serviciosParqueoEntity) {
			serviciosParqueo.add(convertirADominio(servicioParqueoEntity));
		}
		return serviciosParqueo;
	}
}