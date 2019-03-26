package co.com.ceiba.dominio;

public class Vehiculo {
	private String placa;
	private String tipo;
	
	public Vehiculo() {
		
	}
	
	public Vehiculo(String placa, String tipo) {
		super();
		this.placa = placa;
		this.tipo = tipo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public String getTipo() {
		return tipo;
	}
}
