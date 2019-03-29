package co.com.ceiba.dominio.integracion.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.com.ceiba.dominio.TasaRepresentativaMercado;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.ceiba.framework.springboot.CeibaEstacionamientoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CeibaEstacionamientoApplication.class)
public class TasaRepresentativaMercadoTest {
	
	@Autowired
	RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado;
	
	@Test
	public void obtenerTasaRepresentativaMercadoTest () {
		try {
			TasaRepresentativaMercado tasaMercado = repositorioTasaRepresentativaMercado.obtenerTasaRepresentativaMercadoActual();
			Calendar cValidoDesde = Calendar.getInstance();
			cValidoDesde.setTime(tasaMercado.getValidoDesde());
			assertEquals(cValidoDesde.get(Calendar.DAY_OF_YEAR), Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
		} catch (VigilanteExcepcion ve) {
			fail();
		}
		
	}
} 
