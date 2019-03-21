package co.com.ceiba.testdatabuilder;

import co.com.ceiba.dominio.TipoVehiculo;

public class TipoVehiculoTestDataBuilder {
	
	private static final String NOMBRE_ESTATICO = "Moto";
	private static final String DESCRIPCION_ESTATICA = "Cilindraje <= 500 CC";
	
	private String nombre;
	private String descripcion;
	
	public TipoVehiculoTestDataBuilder () {
		this.nombre = NOMBRE_ESTATICO;
		this.descripcion = DESCRIPCION_ESTATICA;
	}
	
	public TipoVehiculoTestDataBuilder conNombre (String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public TipoVehiculoTestDataBuilder conDescripcion (String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public TipoVehiculo build () {
		return new TipoVehiculo(nombre, descripcion);
	}
	
	public TipoVehiculo buildTipoCarro () {
		this.nombre = "Carro";
		this.descripcion = "Vehiculo con capacidad para 5 personas";
		return this.build();
	}
	
	public TipoVehiculo buildTipoMoto () {
		this.nombre = NOMBRE_ESTATICO;
		this.descripcion = DESCRIPCION_ESTATICA;
		return this.build();
	}
	
	public TipoVehiculo buildTipoMotoAC () {
		this.nombre = "MotoAC";
		this.descripcion = "Cilindraje > 500 CC";
		return this.build();
	}

}
