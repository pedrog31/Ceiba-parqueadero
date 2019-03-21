package co.com.ceiba.testdatabuilder;

import co.com.ceiba.dominio.TipoVehiculo;
import co.com.ceiba.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	private static final TipoVehiculo TIPO_ESTATICO = new TipoVehiculo("Moto bajo cilindraje", "Cilindraje <= 500 CC");
	private static final String PLACA_ESTATICA = "PYJ41D";
	
	private TipoVehiculo tipo;
	private String placa;
	
	public VehiculoTestDataBuilder() {
		super();
		this.tipo = TIPO_ESTATICO;
		this.placa = PLACA_ESTATICA;
	}
	
	public Vehiculo build () {
		return new Vehiculo (this.tipo, this.placa);
	}
	
	public VehiculoTestDataBuilder conTipo (TipoVehiculo tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder conPlaca (String placa) {
		this.placa = placa;
		return this;
	}
	
	public Vehiculo buildPlacaIniciaA () {
		this.placa = "AYJ891";
		return this.build();
	}
	
	public Vehiculo buildPlacaNoIniciaA () {
		this.placa = "SaJ891";
		return this.build();
	}
}
