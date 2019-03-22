package co.com.ceiba.persistencia.repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.persistencia.builder.ServicioParqueoBuilder;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioServicioParqueoJPA;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;

@Component()
public class RepositorioServicioParqueoPersistente implements RepositorioServicioParqueo {

	@Autowired
	RepositorioServicioParqueoJPA repositorioServicioParqueoJPA;

	@Autowired
	RepositorioVehiculoJPA repositorioVehiculoJPA;
	
	@Override
	public void registrarIngreso(ServicioParqueo servicioParqueo) {
		Optional<VehiculoEntity> vehiculo = repositorioVehiculoJPA.findById(servicioParqueo.getVehiculo().getPlaca());
		if (!vehiculo.isPresent()) {
			repositorioVehiculoJPA.saveAndFlush(VehiculoBuilder.convertirAEntity(servicioParqueo.getVehiculo()));
		}
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
