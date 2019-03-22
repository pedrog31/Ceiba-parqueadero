package co.com.ceiba.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.testdatabuilder.ServicioParqueoTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

public class ServicioParqueoTest {

	private static ServicioParqueoTestDataBuilder servicioParqueoTestDataBuilder;
	private static VehiculoTestDataBuilder vehiculoTestDataBuilder;
	private Calendar calendarInstance = Calendar.getInstance();
	private static Date fechaActual;
	private static final String TIPO_CARRO = "Carro";
	private static final String TIPO_MOTO = "Moto";
	private static final String TIPO_MOTOAC = "MotoAC";

	@BeforeClass
	public static void iniciarServicioParqueoTest() {
		servicioParqueoTestDataBuilder = new ServicioParqueoTestDataBuilder();
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		fechaActual = new Date();
	}

	@Before
	public void iniciarTest() {
		calendarInstance.setTime(fechaActual);
	}

	@Test
	public void calcularValorParqueaderoCarroMenosNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 5);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_CARRO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 5000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoCarroMasNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 10);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_CARRO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 8000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoCarroMenosVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 23);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_CARRO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 8000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoCarroMasVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 27);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_CARRO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 11000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoMenosNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 5);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 2500;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoMasNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 10);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 4000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoMenosVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 23);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 4000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoMasVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 27);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTO).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 5500;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoACMenosNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 5);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTOAC).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 4500;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoACMasNueveHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 10);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTOAC).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 6000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoACMenosVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 23);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTOAC).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 6000;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

	@Test
	public void calcularValorParqueaderoMotoACMasVeinticuatroHorasTest() {
	    calendarInstance.add(Calendar.HOUR_OF_DAY, 27);
		servicioParqueoTestDataBuilder.conVehiculo(
				vehiculoTestDataBuilder.conTipo(TIPO_MOTOAC).build())
				.conFechaIngreso(fechaActual).conFechaSalida(calendarInstance.getTime());
		ServicioParqueo servicioParqueo = servicioParqueoTestDataBuilder.build();
		servicioParqueo.calcularValorServicio();
		int valorEsperado = 7500;
		assertEquals(valorEsperado, servicioParqueo.getValor());
	}

}
