package co.com.ceiba.framework.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

@Configuration
public class ConfiguracionParqueadero {
	
	@Bean
	public Vigilante crearVigilante(RepositorioServicioParqueo respositorioServicioParqueo, RepositorioRestriccion repositorioRestricciones, RepositorioTarifas repositorioTarifas) {
		return new Vigilante(respositorioServicioParqueo, repositorioRestricciones, repositorioTarifas);
	}

}
