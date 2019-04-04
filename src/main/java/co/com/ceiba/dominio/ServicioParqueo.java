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
		this.valor = this.buscarRecargos() + calcularValorTarifa(horas);
	}

	private long buscarRecargos() {
		for (Tarifa tarifaActual: this.tarifas)
			if (tarifaActual.esRecargo(this.vehiculo.getCilindraje()))
				return tarifaActual.getValor();
		return 0;
	}

	private long calcularValorTarifa(long horas) {
		for (Tarifa tarifaActual: this.tarifas)
			if (tarifaActual.aplicaCobroPorHoras(horas))
				return horas * tarifaActual.getValor();
			else if (tarifaActual.aplicaCobroPorDia(horas))
				return tarifaActual.getValor() + calcularValorTarifa(horas - Tarifa.HORAS_DIA);
		return 0;
	}

	public boolean esServicioNuevo() {
		return this.getFechaIngreso() == null;
	}
}
