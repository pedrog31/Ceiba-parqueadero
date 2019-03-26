package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;


import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.UnitTest;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.testdatabuilder.ServicioParqueoTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@UnitTest
public class VehiculoTest {
	
	private static final Date FECHA_INGRESO = new Date(1552998096L);
	private static final Date FECHA_SALIDA = new Date(1552998131L);
	
	@Test
	public void crearVehiculoTest () {
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		ServicioParqueoTestDataBuilder servicioParqueoTestDataBuilder = new ServicioParqueoTestDataBuilder()
				.conFechaIngreso(FECHA_INGRESO)
				.conFechaSalida(FECHA_SALIDA)
				.conVehiculo(vehiculo);
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		assertEquals(vehiculo, servicioParqueo.getVehiculo());
		assertEquals(FECHA_INGRESO, servicioParqueo.getFechaIngreso());
		assertEquals(FECHA_SALIDA, servicioParqueo.getFechaSalida());
	}
}
