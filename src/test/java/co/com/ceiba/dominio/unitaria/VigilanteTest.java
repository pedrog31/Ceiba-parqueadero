package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import co.com.ceiba.dominio.RestriccionIngresoCapacidad;
import co.com.ceiba.dominio.RestriccionIngreso;
import co.com.ceiba.dominio.RestriccionIngresoPlacaA;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.ceiba.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

public class VigilanteTest {

	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	private static RepositorioServicioParqueo respositorioServicioParqueo;
	private static RepositorioTarifas repositorioTarifas;
	private static RepositorioVehiculo repositorioVehiculo;
	private static RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado;
	private Vigilante vigilante;
	private List<RestriccionIngreso> restriccionesIngreso;

	@BeforeClass
	public static void iniciarTests() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		respositorioServicioParqueo = mock(RepositorioServicioParqueo.class);
		repositorioTarifas = mock(RepositorioTarifas.class);
		repositorioVehiculo = mock(RepositorioVehiculo.class);
		repositorioTasaRepresentativaMercado = mock(RepositorioTasaRepresentativaMercado.class);  
	}

	@Before
	public void iniciarTest() {
		this.crearRestricciones(Calendar.getInstance());
		this.crearVigilante();
	}

	private void crearRestricciones(Calendar  calendario) {
		restriccionesIngreso = Arrays.asList(
        		new RestriccionIngresoPlacaA(calendario),
        		new RestriccionIngresoCapacidad(respositorioServicioParqueo, "Carro", 20),
        		new RestriccionIngresoCapacidad(respositorioServicioParqueo, "Moto", 10));
	}

	private void crearVigilante() {
		vigilante = new Vigilante(
				restriccionesIngreso,
				respositorioServicioParqueo,
				repositorioTarifas,
				repositorioVehiculo,
				repositorioTasaRepresentativaMercado);
	}

	@Test
	public void ingresarVehiculoPlacaNoAServicioParqueo() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaNoIniciaA();
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}

	@Test
	public void ingresarVehiculoPlacaAServicioParqueo() {
		Calendar calendario = Calendar.getInstance();
		Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaIniciaA();
		when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(0);
		for (int i=1; i<=7; i++) {
			calendario.set(Calendar.DAY_OF_WEEK, i);
			this.crearRestricciones(calendario);
			this.crearVigilante();
			int dia = calendario.get(Calendar.DAY_OF_WEEK);
			try {
				vigilante.registrarIngresoVehiculo(vehiculo);
				if (dia == Calendar.MONDAY || dia == Calendar.SUNDAY) {
					fail();
				}
			} catch (VigilanteExcepcion ve) {
				if (dia == Calendar.MONDAY || dia == Calendar.SUNDAY) {
					assertEquals(RestriccionIngresoPlacaA.VEHICULO_NO_AUTORIZADO, ve.getMessage());
				} else {
					fail();
				}
			}
		}
	}

	@Test
	public void ingresarMotoServicioParqueoLleno() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(10);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(RestriccionIngresoCapacidad.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarMotoServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(9);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}

	@Test
	public void ingresarMotoACServicioParqueoLleno() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(10);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(RestriccionIngresoCapacidad.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarMotoACServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(9);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}

	@Test
	public void ingresarCarroServicioParqueoLleno() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.CARRO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(20);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(RestriccionIngresoCapacidad.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarCarroServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.CARRO_ESTATICO).conPlaca("").build();
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(19);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}

	}
}
