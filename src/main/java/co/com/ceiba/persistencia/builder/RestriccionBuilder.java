package co.com.ceiba.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.dominio.Restriccion;
import co.com.ceiba.persistencia.entidad.RestriccionEntity;

public class RestriccionBuilder {

	private RestriccionBuilder() {
		throw new IllegalStateException("Utility class");
	}

	public static Restriccion convertirADominio(RestriccionEntity restriccionEntity) {
		return restriccionEntity == null  ? 
				null : new Restriccion (
						restriccionEntity.getCapacidad(),
						restriccionEntity.getDiaAplicacion(),
						restriccionEntity.getExpresionRegular(),
						restriccionEntity.getTipoVehiculo());
	}

	public static List<Restriccion> convertirADominio(List<RestriccionEntity> restriccionesEntity) {
		List<Restriccion> restricciones = new ArrayList<>();
		if (restriccionesEntity == null)
			return restricciones;
		for (RestriccionEntity restriccionEntity : restriccionesEntity) {
			restricciones.add(convertirADominio(restriccionEntity));
		}
		return restricciones;
	}

}
