package co.com.ceiba.dominio.integracion.persistencia;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class VigilanteTest {

	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	
	@Autowired
	RepositorioServicioParqueo repositorioServicioParqueo;
	
	@Autowired
	RepositorioTarifas repositorioTarifas;
	
	@BeforeClass
	public static void iniciarServicioParqueoTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
	}
	
	@Test
	public void ingresarVehiculo () {
		/*Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		ServicioParqueo servicio = new ServicioParqueo(new Date(), vehiculo, this.repositorioTarifas);
		repositorioServicioParqueo.registrarIngreso(servicio);
		ServicioParqueo servicioParqueoGuardado = repositorioServicioParqueo.buscarVehiculo(vehiculo.getPlaca());
		assertEquals(servicio.getVehiculo().getPlaca(), servicioParqueoGuardado.getVehiculo().getPlaca());*/
	}
}
