package co.com.ceiba.dominio.integracion.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class VigilanteTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
    private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	private VehiculoTestDataBuilder vehiculoTestDataBuilder;

	@Before
	public void iniciarServicioParqueoTest() {
		vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	public void registrarIngresoVehiculoITest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders
					.post("/vigilante/registrar-ingreso")
					.content(vehiculoTestDataBuilder.buildJsonString())
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
