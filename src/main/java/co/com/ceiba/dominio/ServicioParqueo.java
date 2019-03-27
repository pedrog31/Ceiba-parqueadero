package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;

public class ServicioParqueo {
	
	private Date fechaIngreso;
	private Date fechaSalida;
	private Boolean pagado;
	private List<Tarifa> tarifas;
	private long valor;
	private Vehiculo vehiculo;

	public ServicioParqueo() {
		super();
	}

	public ServicioParqueo(Date fechaIngreso, Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.vehiculo = vehiculo;
		this.pagado = null;
	}

	public ServicioParqueo(Date fechaIngreso, Date fechaSalida, Boolean pagado, long valor, Vehiculo vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.pagado = pagado;
		this.valor = valor;
		this.vehiculo = vehiculo;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Boolean isPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public long getValor() {
		return valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void calcularValorServicio() {
		long horas = Tarifa.obtenerNumeroHoras(fechaIngreso, fechaSalida);
		this.valor = calcularValorTarifa(horas);
	}

	private long calcularValorTarifa(long horas) {
		long valorDia = 0;
		Tarifa tarifaActual = null;
		for (int i=0; i < tarifas.size(); i++) {
			tarifaActual = tarifas.get(i);
			if (tarifaActual.esRecargo()) {
				tarifas.remove(i);
				return tarifaActual.getValor() + calcularValorTarifa(horas);
			} else if (tarifaActual.aplicaCobroPorHoras(horas)) {
				tarifas.remove(i);
				return horas * tarifaActual.getValor();
			} else if (tarifaActual.aplicaCobroPorDia()) {
				tarifas.remove(i);
				horas -= Tarifa.HORAS_DIA;
				valorDia = tarifaActual.getValor() + calcularValorTarifa(horas);
			}
		}
		return valorDia;
	}
}
