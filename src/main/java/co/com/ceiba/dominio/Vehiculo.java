package co.com.ceiba.dominio;

public class Vehiculo {
	private String placa;
	private TipoVehiculo tipoVehiculo;
	
	public Vehiculo(String placa, TipoVehiculo tipoVehiculo) {
		super();
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
}
