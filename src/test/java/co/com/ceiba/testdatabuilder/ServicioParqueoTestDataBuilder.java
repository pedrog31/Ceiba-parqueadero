package co.com.ceiba.testdatabuilder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Tarifa;
import co.com.ceiba.dominio.TipoVehiculo;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

@Component
public class ServicioParqueoTestDataBuilder {
	
	private static final TipoVehiculoTestDataBuilder tipoVehiculoTestDataBuilder = new TipoVehiculoTestDataBuilder();
	private static final VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
	
	private static final Date FECHA_INGRESO_ESTATICO = new Date(1552998998L);
	private static final Date FECHA_SALIDA_ESTATICO = new Date(1552999500L);
	private static final Vehiculo VEHICULO_ESTATICO = vehiculoTestDataBuilder.build();
	private static final List<Tarifa> TARIFAS_ESTATICAS = new ArrayList<> (Arrays.asList(
				new Tarifa((byte) 9, (byte) 0, tipoVehiculoTestDataBuilder.buildTipoCarro(), 1000),
				new Tarifa((byte) -1, (byte) 9, tipoVehiculoTestDataBuilder.buildTipoCarro(), 8000),
				new Tarifa((byte) 9, (byte) 0, tipoVehiculoTestDataBuilder.buildTipoMoto(), 500),
				new Tarifa((byte) -1, (byte) 9, tipoVehiculoTestDataBuilder.buildTipoMoto(), 4000),
				new Tarifa((byte) 0, (byte) 0, tipoVehiculoTestDataBuilder.buildTipoMotoAC(), 2000),
				new Tarifa((byte) 9, (byte) 0, tipoVehiculoTestDataBuilder.buildTipoMotoAC(), 500),
				new Tarifa((byte) -1, (byte) 9, tipoVehiculoTestDataBuilder.buildTipoMotoAC(), 4000)
			));
	private Date fechaIngreso;
	private Date fechaSalida;
	private Vehiculo vehiculo;
	private List<Tarifa> tarifas;
	
	
	public ServicioParqueoTestDataBuilder () {
		super();
		this.fechaIngreso = FECHA_INGRESO_ESTATICO;
		this.fechaSalida = FECHA_SALIDA_ESTATICO;
		this.vehiculo = VEHICULO_ESTATICO;
		this.tarifas = new ArrayList<>();
		this.tarifas.addAll(TARIFAS_ESTATICAS);
	}

	public ServicioParqueo build () {
		RepositorioTarifas repositorioTarifas = mock(RepositorioTarifas.class);
		when (repositorioTarifas.obtenerTarifasPorTipoVehiculo(this.vehiculo.getTipoVehiculo())).thenReturn(tarifas);
		ServicioParqueo servicioParqueo = new ServicioParqueo (this.fechaIngreso, repositorioTarifas, this.vehiculo);
		servicioParqueo.setFechaSalida(fechaSalida);
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
		this.tarifas.removeIf(p -> !p.getTipoVehiculo().getNombre().equals(vehiculo.getTipoVehiculo().getNombre()));
		return this;
	}
}
