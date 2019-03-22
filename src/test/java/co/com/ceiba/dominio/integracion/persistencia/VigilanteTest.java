package co.com.ceiba.dominio.integracion.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class VigilanteTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	
	@Autowired
	RepositorioServicioParqueo repositorioServicioParqueo;
	
	@Autowired
	RepositorioRestriccion repositorioRestriccion;
	
	@Autowired
	RepositorioTarifas repositorioTarifas;
	
	@BeforeClass
	public static void iniciarServicioParqueoTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
	}
	
	@Test
	public void registrarIngresoVehiculoITest () {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		Vigilante vigilante = new Vigilante(repositorioServicioParqueo, repositorioRestriccion, repositorioTarifas);
		vigilante.registrarIngresoVehiculo(vehiculo);
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarServicioVehiculo(vehiculo.getPlaca(), null);
		assertEquals(vehiculo.getPlaca(), servicioParqueoGuardado.getVehiculo().getPlaca());
	}
	
	@Test
	public void registrarSalidaVehiculoITest () {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		Vigilante vigilante = new Vigilante(repositorioServicioParqueo, repositorioRestriccion, repositorioTarifas);
		vigilante.registrarIngresoVehiculo(vehiculo);
		ServicioParqueo servicioParqueo = vigilante.registrarSalidaVehiculo(vehiculo.getPlaca());
		vigilante.registrarPagoServicio(vehiculo.getPlaca());
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarServicioVehiculo(vehiculo.getPlaca(), servicioParqueo.getFechaSalida());
		assertEquals(vehiculo.getPlaca(), servicioParqueoGuardado.getVehiculo().getPlaca());
	}
}
