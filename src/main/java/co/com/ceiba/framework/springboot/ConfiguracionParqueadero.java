package co.com.ceiba.framework.springboot;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.dominio.RestriccionCapacidad;
import co.com.ceiba.dominio.RestriccionIngreso;
import co.com.ceiba.dominio.RestriccionPlacaA;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.ceiba.dominio.repositorio.RepositorioVehiculo;

@Configuration
public class ConfiguracionParqueadero {
	
	@Bean
	public Vigilante crearVigilante(
			List<RestriccionIngreso> restriccionesIngreso,
			RepositorioServicioParqueo respositorioServicioParqueo,
			RepositorioTarifas repositorioTarifas, 
			RepositorioVehiculo repositorioVehiiculo, 
			RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado) {
		return new Vigilante(
				restriccionesIngreso,
				respositorioServicioParqueo,
				repositorioTarifas,
				repositorioVehiiculo,
				repositorioTasaRepresentativaMercado);
	}
	
	@Bean
    public List<RestriccionIngreso> crearRestricciones(RepositorioServicioParqueo respositorioServicioParqueo) {
        return Arrays.asList(
        		new RestriccionPlacaA(Calendar.getInstance()),
        		new RestriccionCapacidad(respositorioServicioParqueo, "Carro", 20),
        		new RestriccionCapacidad(respositorioServicioParqueo, "Moto", 10));
    }

}
