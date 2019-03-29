package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;

public final class VehiculoBuilder {
	
	private VehiculoBuilder() {}
	
	public static Vehiculo convertirADominio (VehiculoEntity vehiculoEntity) {
		return vehiculoEntity == null  ? 
				null : new Vehiculo(
				vehiculoEntity.getPlaca(),
				vehiculoEntity.getCilindraje(),
				vehiculoEntity.getTipoVehiculo()
				);		
	}
	
	public static VehiculoEntity convertirAEntity (Vehiculo vehiculo) {
		return vehiculo == null  ? 
				null : new VehiculoEntity(
				vehiculo.getPlaca(),
				vehiculo.getCilindraje(),
				vehiculo.getTipo()
				);
	}
}
