package co.com.ceiba.dominio;

public class Vehiculo {
	private String placa;
	private TipoVehiculo tipo;
	
	public Vehiculo(TipoVehiculo tipo, String placa) {
		super();
		this.tipo = tipo;
		this.placa = placa;
	}
	
	public TipoVehiculo getTipo() {
		return tipo;
	}
	
	public String getPlaca() {
		return placa;
	}
}
