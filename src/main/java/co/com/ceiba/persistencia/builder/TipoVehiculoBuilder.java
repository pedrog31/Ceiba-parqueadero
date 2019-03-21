package co.com.ceiba.persistencia.builder;

import co.com.ceiba.dominio.TipoVehiculo;
import co.com.ceiba.persistencia.entidad.TipoVehiculoEntity;

public class TipoVehiculoBuilder {
	
	private TipoVehiculoBuilder() {}
	
	public static TipoVehiculo convertirADominio(TipoVehiculoEntity tipoVehiculoEntity) {
		return tipoVehiculoEntity == null  ? 
				null : new TipoVehiculo (tipoVehiculoEntity.getNombre(), tipoVehiculoEntity.getDescripcion());
	}
	
	public static TipoVehiculoEntity convertirAEntity(TipoVehiculo tipoVehiculo) {
		return tipoVehiculo == null  ? 
				null : new TipoVehiculoEntity (tipoVehiculo.getNombre(), tipoVehiculo.getDescripcion());
	}
}
