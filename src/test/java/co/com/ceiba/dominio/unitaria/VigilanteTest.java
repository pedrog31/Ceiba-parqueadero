package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.UnitTest;
import co.com.ceiba.dominio.Restriccion;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
public class VigilanteTest {

	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	private static RepositorioRestriccion repositorioRestricciones;
	private static RepositorioServicioParqueo respositorioServicioParqueo;
	private static RepositorioTarifas repositorioTarifas;
	private Vigilante vigilante;
	static List<Restriccion> restricciones;

	private static final List<Restriccion> RESTRICCIONES_ESTATICAS = new ArrayList<>(
			Arrays.asList(new Restriccion(null, Calendar.MONDAY, "^[A]{1}", null),
					new Restriccion(10, null, null, VehiculoTestDataBuilder.MOTO_ESTATICO),
					new Restriccion(20, null, null, VehiculoTestDataBuilder.CARRO_ESTATICO)));

	@BeforeClass
	public static void iniciarTests() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		repositorioRestricciones = mock(RepositorioRestriccion.class);
		respositorioServicioParqueo = mock(RepositorioServicioParqueo.class);
		repositorioTarifas = mock(RepositorioTarifas.class);
		restricciones = new ArrayList<>();
	}

	@Before
	public void iniciarTest() {
		vigilante = new Vigilante(respositorioServicioParqueo, repositorioRestricciones, repositorioTarifas);
	}
	
	private void actualizarRestricciones(String tipoVehiculo) {
		restricciones.clear();
		restricciones.add(RESTRICCIONES_ESTATICAS.get(0));
		if (tipoVehiculo.startsWith(VehiculoTestDataBuilder.MOTO_ESTATICO)) {
			restricciones.add(RESTRICCIONES_ESTATICAS.get(1));
		} else {
			restricciones.add(RESTRICCIONES_ESTATICAS.get(2));
		}
		when(repositorioRestricciones
				.obtenerRestriccionesActivas(vehiculoTestDataBuilder.buildPlacaIniciaA().getTipo()))
						.thenReturn(restricciones);
		
	}

	@Test
	public void ingresarVehiculoAutorizadoServicioParqueo() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaNoIniciaA();
			this.actualizarRestricciones(vehiculo.getTipo());
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}

	@Test
	public void ingresarVehiculoNoAutorizadoServicioParqueo() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaIniciaA();
			this.actualizarRestricciones(vehiculo.getTipo());
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(Vigilante.VEHICULO_NO_AUTORIZADO, ve.getMessage());
		}
	}

	@Test
	public void ingresarMotoServicioParqueoLleno() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			this.actualizarRestricciones(vehiculo.getTipo());
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(10);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(Vigilante.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarMotoServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTO_ESTATICO).conPlaca("").build();
			this.actualizarRestricciones(vehiculo.getTipo());
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(9);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}

	@Test
	public void ingresarMotoACServicioParqueoLleno() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTOAC_ESTATICO).conPlaca("").build();
			this.actualizarRestricciones(vehiculo.getTipo());
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(10);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(Vigilante.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarMotoACServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.MOTOAC_ESTATICO).conPlaca("").build();
			this.actualizarRestricciones(vehiculo.getTipo());
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
			this.actualizarRestricciones(vehiculo.getTipo());
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(20);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(Vigilante.PARQUEADERO_LLENO, ve.getMessage());
		}
	}

	@Test
	public void ingresarCarroServicioParqueoDisponible() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.conTipo(VehiculoTestDataBuilder.CARRO_ESTATICO).conPlaca("").build();
			this.actualizarRestricciones(vehiculo.getTipo());
			when(respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo())).thenReturn(19);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}

	}
}
