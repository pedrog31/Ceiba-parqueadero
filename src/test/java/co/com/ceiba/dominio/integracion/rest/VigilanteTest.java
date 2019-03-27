package co.com.ceiba.dominio.integracion.rest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;
import co.com.ceiba.testdatabuilder.ServicioParqueoTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class VigilanteTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private Vigilante vigilante;
	
	@Autowired
    private WebApplicationContext webAppContext;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	private VehiculoTestDataBuilder vehiculoTestDataBuilder;

	@Before
	public void iniciarServicioParqueoTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		objectMapper = new ObjectMapper();
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void registrarIngresoVehiculoITest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.put("/vigilante/ingreso")
					.content(vehiculoTestDataBuilder.buildJsonString())
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void registrarSalidaVehiculoITest() throws Exception {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		vigilante.registrarIngresoVehiculo(vehiculo);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders
					.post("/vigilante/salida")
					.param("placa", vehiculo.getPlaca()))
				.andExpect(status().isOk()).andReturn();
		ServicioParqueo servicioParqueo = objectMapper.readValue(
	            mvcResult.getResponse().getContentAsString(), ServicioParqueo.class);
	    assertTrue(vehiculoTestDataBuilder.sonIguales(vehiculo, servicioParqueo.getVehiculo()));
	}

	@Test
	public void registrarPagoVehiculoITest() throws Exception {
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		vigilante.registrarIngresoVehiculo(vehiculo);
		vigilante.registrarSalidaVehiculo(vehiculo.getPlaca());
		mockMvc.perform(
				MockMvcRequestBuilders
					.post("/vigilante/pago")
					.param("pagado", "true")
					.param("placa", vehiculo.getPlaca()))
				.andExpect(status().isOk());
	}

	@Test
	public void verificarEstadoParqueaderoITest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.get("/vigilante/estado"))
				.andExpect(status().isOk());
	}

}
