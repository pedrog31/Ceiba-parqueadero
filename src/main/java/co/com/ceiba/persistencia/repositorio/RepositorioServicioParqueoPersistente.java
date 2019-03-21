package co.com.ceiba.persistencia.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.persistencia.builder.ServicioParqueoBuilder;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioServicioParqueoJPA;

@Component()
public class RepositorioServicioParqueoPersistente implements RepositorioServicioParqueo {

	@Autowired
	RepositorioServicioParqueoJPA repositorioServicioParqueoJPA;
	
	@Override
	public void registrarIngreso(ServicioParqueo servicioParqueo) {
		repositorioServicioParqueoJPA.save(ServicioParqueoBuilder.convertirAEntity(servicioParqueo));
	}

	@Override
	public void registrarSalida(Date fechaFinalizacion, String placa, int valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServicioParqueo buscarVehiculo(String placa) {
		return ServicioParqueoBuilder.convertirADominio(repositorioServicioParqueoJPA.findByVehiculoPlacaAndFechaSalida(placa, null));
	}

	@Override
	public List<ServicioParqueo> obtenerVehiculosParqueadero() {
		// TODO Auto-generated method stub
		return null;
	}

}
