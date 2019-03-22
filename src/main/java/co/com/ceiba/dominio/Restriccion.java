package co.com.ceiba.dominio;

public class Restriccion {

	private Integer capacidad;
	private Integer diaAplicacion;
	private String expresionRegular;
	private String tipoVehiculo;
	
	public Restriccion(Integer capacidad, Integer diaAplicacion, String expresionRegular, String tipoVehiculo) {
		super();
		this.capacidad = capacidad;
		this.diaAplicacion = diaAplicacion;
		this.expresionRegular = expresionRegular;
		this.tipoVehiculo = tipoVehiculo;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getDiaAplicacion() {
		return diaAplicacion;
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
}
