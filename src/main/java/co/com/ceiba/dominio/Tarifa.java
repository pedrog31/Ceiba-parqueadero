package co.com.ceiba.dominio;

import java.util.Date;

public class Tarifa {
	
	public static final byte HORAS_DIA = 24;
	public static final byte ES_COBRO_DIA = -1;
	public static final int MILISEGUNDOS_HORA = 3600000;

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

	boolean esRecargo() {
		return getHoraCaduca() == getHoraInicio();
	}

	public static long obtenerNumeroHoras(Date fechaIngreso, Date fechaSalida) {
		long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
		return (diferencia / MILISEGUNDOS_HORA) + (diferencia % MILISEGUNDOS_HORA == 0 ? 0:1);
	}

	public boolean aplicaCobroPorHoras(long horas) {
		return this.horaInicio <= horas && horas < this.horaCaduca;
	}

	public boolean aplicaCobroPorDia() {
		return this.horaCaduca == Tarifa.ES_COBRO_DIA;
	}	
}
