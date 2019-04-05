package co.com.ceiba.framework.springboot;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.ceiba.dominio.RestriccionIngresoCapacidad;
import co.com.ceiba.dominio.RestriccionIngreso;
import co.com.ceiba.dominio.RestriccionIngresoPlacaA;
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
        		new RestriccionIngresoPlacaA(Calendar.getInstance()),
        		new RestriccionIngresoCapacidad(respositorioServicioParqueo, "Carro", 20),
        		new RestriccionIngresoCapacidad(respositorioServicioParqueo, "Moto", 10));
    }

}
