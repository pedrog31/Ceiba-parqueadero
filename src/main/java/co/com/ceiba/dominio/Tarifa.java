package co.com.ceiba.dominio;

import java.util.Date;

public class Tarifa {
	
	public static final byte HORAS_DIA = 24;
	public static final byte ES_COBRO_DIA = -1;
	public static final int MILISEGUNDOS_HORA = 3600000;

	private String cilindraje;
	private byte horaCaduca;
	private byte horaInicio;
	private String tipoVehiculo;
	private int valor;
	
	public Tarifa() {
		super();
	}

	public Tarifa(String cilindraje, byte horaCaduca, byte horaInicio, String tipoVehiculo, int valor) {
		super();
		this.horaCaduca = horaCaduca;
		this.horaInicio = horaInicio;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
		this.valor = valor;
	}
	
	public byte getHoraCaduca() {
		return horaCaduca;
	}

	public void setHoraCaduca(byte horaCaduca) {
		this.horaCaduca = horaCaduca;
	}

	public byte getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(byte horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public static byte getHorasDia() {
		return HORAS_DIA;
	}

	public static byte getEsCobroDia() {
		return ES_COBRO_DIA;
	}

	public static int getMilisegundosHora() {
		return MILISEGUNDOS_HORA;
	}

	boolean esRecargo(Integer cilindraje) {
		boolean recargoCilindraje = false;
		if (this.cilindraje != null ) {
			Integer cilindrajeTarifa = Integer.parseInt(this.getCilindraje().substring(1));
			switch (this.cilindraje.charAt(0)) {
				case '<':
					recargoCilindraje = cilindraje < cilindrajeTarifa;
					break;
				case '>':
					recargoCilindraje = cilindraje > cilindrajeTarifa;
					break;
				case '=':
					recargoCilindraje = cilindraje.equals(cilindrajeTarifa);
					break;
				default:
					return false;
			}
		}
		return this.horaCaduca == this.horaInicio && recargoCilindraje;
	}

	public static long obtenerNumeroHoras(Date fechaIngreso, Date fechaSalida) {
		long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
		return (diferencia / MILISEGUNDOS_HORA) + (diferencia % MILISEGUNDOS_HORA == 0 ? 0:1);
	}

	public boolean aplicaCobroPorHoras(long horas) {
		return this.horaInicio <= horas && horas < this.horaCaduca;
	}

	public boolean aplicaCobroPorDia(long horas) {
		return this.horaCaduca == Tarifa.ES_COBRO_DIA && horas >= this.horaInicio;
	}	
}
