package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	
	private VehiculoBuilder() {}
	
	public static Vehiculo convertirADominio (VehiculoEntity vehiculoEntity) {
		return new Vehiculo(
				vehiculoEntity.getPlaca(),
				TipoVehiculoBuilder.convertirADominio(vehiculoEntity.getTipoVehiculo())
				);		
	}
	
	public static VehiculoEntity convertirAEntity (Vehiculo vehiculo) {
		return new VehiculoEntity(
				vehiculo.getPlaca(),
				TipoVehiculoBuilder.convertirAEntity(vehiculo.getTipoVehiculo())
				);
	}
}
