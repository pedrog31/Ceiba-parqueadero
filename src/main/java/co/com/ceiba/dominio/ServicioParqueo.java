package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

public class ServicioParqueo {
	
	private Date fechaIngreso;
	private Date fechaSalida;
	private RepositorioTarifas repositorioTarifas;
	private long valor;
	private Vehiculo vehiculo;

	public ServicioParqueo(Date fechaIngreso, RepositorioTarifas repositorioTarifas, Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.repositorioTarifas = repositorioTarifas;
		this.vehiculo = vehiculo;
	}

	public ServicioParqueo(Date fechaIngreso, Date fechaSalida, RepositorioTarifas repositorioTarifas, long valor,
			Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.repositorioTarifas = repositorioTarifas;
		this.valor = valor;
		this.vehiculo = vehiculo;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		this.calcularValor();
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public RepositorioTarifas getRepositorioTarifas() {
		return repositorioTarifas;
	}

	public long getValor() {
		return valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	private void calcularValor() {
		long diferencia = fechaSalida.getTime() - fechaIngreso.getTime();
		long horas = (diferencia / 3600000) + (diferencia % 3600000 == 0 ? 0:1);
		valor = calcularValorTarifa(horas);
	}

	private long calcularValorTarifa(long horas) {
		List<Tarifa> tarifas = repositorioTarifas.obtenerTarifasPorTipoVehiculo(vehiculo.getTipoVehiculo());
		long valorDia = 0;
		Tarifa tarifaActual = null;
		for (int i=0; i < tarifas.size(); i++) {
			tarifaActual = tarifas.get(i);
			if (tarifaActual.getHoraCaduca() == tarifaActual.getHoraInicio()) {
				tarifas.remove(i);
				return tarifaActual.getValor() + calcularValorTarifa(horas);
			} else if (tarifaActual.getHoraInicio() <= horas && horas < tarifaActual.getHoraCaduca()) {
				tarifas.remove(i);
				return horas * tarifaActual.getValor();
			} else if (tarifaActual.getHoraCaduca() == -1) {
				tarifas.remove(i);
				valorDia = tarifaActual.getValor() + calcularValorTarifa(horas - 24);
			}
		}
		return valorDia;
	}
}
