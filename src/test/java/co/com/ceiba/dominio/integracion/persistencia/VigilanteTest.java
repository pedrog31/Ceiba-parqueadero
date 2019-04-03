package co.com.ceiba.dominio.integracion.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.excepcion.ServicioParqueoExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;
import co.com.ceiba.testdatabuilder.ServicioParqueoTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class VigilanteTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	private static ServicioParqueoTestDataBuilder servicioParqueoTestDataBuilder;

	@Autowired
	private Vigilante vigilante;

	@Autowired
	private RepositorioServicioParqueo repositorioServicioParqueo;

	@BeforeClass
	public static void iniciarServicioParqueoTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		servicioParqueoTestDataBuilder = new ServicioParqueoTestDataBuilder();
	}

	@Test
	public void consultarNuevoServicioVehiculoITest() {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		ServicioParqueo servicioParqueo = vigilante.consultarServicio(vehiculo.getPlaca());
		Assert.assertEquals(vehiculo.getPlaca(), servicioParqueo.getVehiculo().getPlaca());
	}

	@Test
	public void registrarIngresoVehiculoITest() {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		ServicioParqueo servicioParqueo = vigilante.registrarIngresoVehiculo(vehiculo);
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarServicioVehiculo(vehiculo.getPlaca(),
				null);
		Assert.assertTrue(servicioParqueoTestDataBuilder.sonIguales(servicioParqueo, servicioParqueoGuardado));
	}

	@Test
	public void registrarDobleIngresoVehiculoSalidaITest() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.build();
			vigilante.registrarIngresoVehiculo(vehiculo);
			ServicioParqueo servicioParqueo = vigilante.registrarIngresoVehiculo(vehiculo);
			repositorioServicioParqueo.registrarSalidaVehiculo(servicioParqueo);
			fail();
		} catch (ServicioParqueoExcepcion spe) {
			assertEquals("Error registrando salida del servicio", spe.getMessage());
		}
	}

	@Test
	public void registrarDobleIngresoVehiculoPagoITest() {
		try {
			Vehiculo vehiculo = vehiculoTestDataBuilder.build();
			vigilante.registrarIngresoVehiculo(vehiculo);
			vigilante.registrarIngresoVehiculo(vehiculo);
			repositorioServicioParqueo.registrarPagoServicio(true, vehiculo.getPlaca());
			fail();
		} catch (ServicioParqueoExcepcion spe) {
			assertEquals("Error registrando pago del servicio", spe.getMessage());
		}
	}

	@Test
	public void registrarSalidaVehiculoITest() {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		vigilante.registrarIngresoVehiculo(vehiculo);
		ServicioParqueo servicioParqueo = vigilante.consultarServicio(vehiculo.getPlaca());
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarServicioVehiculo(vehiculo.getPlaca(),
				servicioParqueo.getFechaSalida());
		Assert.assertTrue(servicioParqueoTestDataBuilder.sonIguales(servicioParqueo, servicioParqueoGuardado));
	}

	@Test
	public void registrarPagoVehiculoITest() {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		vigilante.registrarIngresoVehiculo(vehiculo);
		ServicioParqueo servicioParqueo = vigilante.consultarServicio(vehiculo.getPlaca());
		vigilante.registrarPagoServicio(true, vehiculo.getPlaca());
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarServicioVehiculo(vehiculo.getPlaca(),
				servicioParqueo.getFechaSalida());
		servicioParqueo.setPagado(true);
		Assert.assertTrue(servicioParqueoTestDataBuilder.sonIguales(servicioParqueo, servicioParqueoGuardado));
	}
}
