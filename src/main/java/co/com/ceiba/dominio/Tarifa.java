package co.com.ceiba.dominio;

public class Tarifa {

	private byte horaCaduca;
	private byte horaInicio;
	private TipoVehiculo tipoVehiculo;
	private int valor;
	
	public Tarifa(byte horaCaduca, byte horaInicio, TipoVehiculo tipoVehiculo, int valor) {
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

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}	
}
