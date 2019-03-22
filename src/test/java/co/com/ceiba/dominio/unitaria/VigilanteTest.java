package co.com.ceiba.dominio.unitaria;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.ceiba.dominio.Restriccion;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(MockitoJUnitRunner.class)
public class VigilanteTest {
	
	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	private static RepositorioRestriccion repositorioRestricciones;
	private static RepositorioServicioParqueo respositorioServicioParqueo;
	private static RepositorioTarifas repositorioTarifas;

	private static final List<Restriccion> RESTRICCIONES_ESTATICAS = new ArrayList<> (Arrays.asList(
			new Restriccion(null, Calendar.MONDAY, "^[A]{1}", null)
		));
	
	@BeforeClass
	public static void iniciarTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		repositorioRestricciones = mock(RepositorioRestriccion.class);
		when(repositorioRestricciones.obtenerRestriccionesActivas(vehiculoTestDataBuilder.buildPlacaIniciaA().getTipo())).thenReturn(RESTRICCIONES_ESTATICAS);
		respositorioServicioParqueo = mock(RepositorioServicioParqueo.class);
		repositorioTarifas = mock(RepositorioTarifas.class);
	}
	
	@Test
	public void ingresarVehiculoAutorizadoServicioParqueo () {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaNoIniciaA();
			Vigilante vigilante = new Vigilante(respositorioServicioParqueo, repositorioRestricciones, repositorioTarifas);
			vigilante.registrarIngresoVehiculo(vehiculo);
		} catch (VigilanteExcepcion ve) {
			fail();
		}
	}
	
	@Test
	public void ingresarVehiculoNoAutorizadoServicioParqueo () {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.buildPlacaIniciaA();
			Vigilante vigilante = new Vigilante(respositorioServicioParqueo, repositorioRestricciones, repositorioTarifas);
			vigilante.registrarIngresoVehiculo(vehiculo);
			fail();
		} catch (VigilanteExcepcion ve) {
			assertEquals(Vigilante.VEHICULO_NO_AUTORIZADO, ve.getMessage());
		}
	}
	
	@Test
	public void ingresarMotoServicioParqueoLleno () {
		
	}
	
	@Test
	public void ingresarMotoServicioParqueoDisponible () {
		
	}
	
	@Test
	public void ingresarCarroServicioParqueoLleno () {
		
	}
	
	@Test
	public void ingresarCarroServicioParqueoDisponible () {
		
	}
}
