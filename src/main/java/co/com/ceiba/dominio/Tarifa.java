package co.com.ceiba.dominio;

public class Tarifa {

	private byte horaCaduca;
	private byte horaInicio;
	private String tipoVehiculo;
	private int valor;
	
	public Tarifa(byte horaCaduca, byte horaInicio, String tipoVehiculo, int valor) {
		super();
		this.horaCaduca = horaCaduca;
		this.horaInicio = horaInicio;
		this.tipoVehiculo = tipoVehiculo;
		this.valor = valor;
	}

	public byte getHoraCaduca() {
		return horaCaduca;
	}

	public byte getHoraInicio() {
		return horaInicio;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}	
}
