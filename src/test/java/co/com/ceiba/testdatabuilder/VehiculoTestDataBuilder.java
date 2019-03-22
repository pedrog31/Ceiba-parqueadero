package co.com.ceiba.testdatabuilder;

import co.com.ceiba.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	private static final String PLACA_ESTATICA = "PYJ41D";
	public static final String CARRO_ESTATICO = "Carro";
	public static final String MOTO_ESTATICO = "Moto";
	public static final String MOTOAC_ESTATICO = "MotoAC";
	
	private String tipo;
	private String placa;
	
	public VehiculoTestDataBuilder() {
		super();
		this.tipo = CARRO_ESTATICO;
		this.placa = PLACA_ESTATICA;
	}
	
	public Vehiculo build () {
		return new Vehiculo (this.placa, this.tipo);
	}
	
	public VehiculoTestDataBuilder conTipo (String tipo) {
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
