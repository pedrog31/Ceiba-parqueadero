package co.com.ceiba.dominio;

public class Vehiculo {
	private Integer cilindraje;
	private String placa;
	private String tipo;
	
	public Vehiculo() {
		
	}

	public Vehiculo(String placa, Integer cilindraje, String tipo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
