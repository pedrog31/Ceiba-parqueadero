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
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public RepositorioTarifas getRepositorioTarifas() {
		return repositorioTarifas;
	}

	public void setRepositorioTarifas(RepositorioTarifas repositorioTarifas) {
		this.repositorioTarifas = repositorioTarifas;
	}

	public long getValor() {
		return valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void calcularValorServicio() {
		long horas = Tarifa.obtenerNumeroHoras(fechaIngreso, fechaSalida);
		List<Tarifa> tarifas = repositorioTarifas.obtenerTarifasPorTipoVehiculo(vehiculo.getTipo());
		this.valor = calcularValorTarifa(horas, tarifas);
	}

	private long calcularValorTarifa(long horas, List<Tarifa> tarifas) {
		long valorDia = 0;
		Tarifa tarifaActual = null;
		for (int i=0; i < tarifas.size(); i++) {
			tarifaActual = tarifas.get(i);
			if (tarifaActual.esRecargo()) {
				tarifas.remove(i);
				return tarifaActual.getValor() + calcularValorTarifa(horas, tarifas);
			} else if (tarifaActual.aplicaCobroPorHoras(horas)) {
				tarifas.remove(i);
				return horas * tarifaActual.getValor();
			} else if (tarifaActual.aplicaCobroPorDia()) {
				tarifas.remove(i);
				horas -= Tarifa.HORAS_DIA;
				valorDia = tarifaActual.getValor() + calcularValorTarifa(horas, tarifas);
			}
		}
		return valorDia;
	}
}
