package co.com.ceiba.testdatabuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Tarifa;
import co.com.ceiba.dominio.Vehiculo;

@Component
public class ServicioParqueoTestDataBuilder {
	
	private VehiculoTestDataBuilder vehiculoTestDataBuilder;
	
	private static final Date FECHA_INGRESO_ESTATICO = new Date(1552998998L);
	private static final Date FECHA_SALIDA_ESTATICO = new Date(1552999500L);
	private static final String CARRO_ESTATICO = "Carro";
	private static final String MOTO_ESTATICA = "Moto";
	private static final List<Tarifa> TARIFAS_ESTATICAS = new ArrayList<> (Arrays.asList(
				new Tarifa(null, (byte) 9, (byte) 0, CARRO_ESTATICO, 1000),
				new Tarifa(null, (byte) -1, (byte) 9, CARRO_ESTATICO, 8000),
				new Tarifa(null, (byte) 9, (byte) 0, MOTO_ESTATICA, 500),
				new Tarifa(null, (byte) -1, (byte) 9, MOTO_ESTATICA, 4000),
				new Tarifa(">500", (byte) 0, (byte) 0, MOTO_ESTATICA, 2000)
			));
	private Date fechaIngreso;
	private Date fechaSalida;
	private Vehiculo vehiculo;
	private List<Tarifa> tarifas;
	
	public ServicioParqueoTestDataBuilder () {
		super();
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		this.fechaIngreso = FECHA_INGRESO_ESTATICO;
		this.fechaSalida = FECHA_SALIDA_ESTATICO;
		this.vehiculo = vehiculoTestDataBuilder.build(); 
		this.tarifas = new ArrayList<>();
		this.tarifas.addAll(TARIFAS_ESTATICAS);
	}

	public ServicioParqueo build () {
		ServicioParqueo servicioParqueo = new ServicioParqueo (this.fechaIngreso, this.vehiculo);
		servicioParqueo.setFechaSalida(fechaSalida);
		servicioParqueo.setTarifas(tarifas);
		return servicioParqueo;
	}
	
	public ServicioParqueoTestDataBuilder conFechaIngreso (Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}
	
	public ServicioParqueoTestDataBuilder conFechaSalida (Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public ServicioParqueoTestDataBuilder conVehiculo (Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		this.tarifas.clear();
		this.tarifas.addAll(TARIFAS_ESTATICAS);
		this.tarifas.removeIf(p -> !p.getTipoVehiculo().equals(vehiculo.getTipo()));
		return this;
	}
	
	public boolean sonIguales (ServicioParqueo esperado, ServicioParqueo actual) {
		return safeEquals(esperado.getFechaIngreso(), actual.getFechaIngreso()) &&
				safeEquals(esperado.getFechaSalida(), actual.getFechaSalida()) &&
				safeEquals(esperado.isPagado(), actual.isPagado()) &&
				esperado.getValor() == actual.getValor() &&
				vehiculoTestDataBuilder.sonIguales(esperado.getVehiculo(), actual.getVehiculo());
	}

	private boolean safeEquals(Object esperado, Object actual) {
		return esperado == null ? actual == null : esperado.equals(actual);
	}
}
