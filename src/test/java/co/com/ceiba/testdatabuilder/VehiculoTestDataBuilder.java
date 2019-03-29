package co.com.ceiba.testdatabuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	
	public static final Integer CILINDRAJE_ESTATICO = 600;
	private static final String PLACA_ESTATICA = "PYJ41D";
	public static final String MOTO_ESTATICO = "Moto";
	public static final String CARRO_ESTATICO = "Carro";
	
	private String tipo;
	private String placa;
	private Integer cilindraje;
	private ObjectMapper objectMapper;
	
	public VehiculoTestDataBuilder() {
		super();
		this.tipo = MOTO_ESTATICO;
		this.placa = PLACA_ESTATICA;
		this.cilindraje = CILINDRAJE_ESTATICO;
		objectMapper = new ObjectMapper(); 
	}
	
	public Vehiculo build () {
		return new Vehiculo (this.placa, this.cilindraje, this.tipo);
	}
	
	public String buildJsonString () throws JsonProcessingException {
		return objectMapper.writeValueAsString(this.build());
	}
	
	public VehiculoTestDataBuilder conCilindraje (Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
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

	public boolean sonIguales(Vehiculo esperado, Vehiculo actual) {
		return esperado.getTipo().equals(actual.getTipo()) &&
				esperado.getPlaca().equals(actual.getPlaca());
	}
}
