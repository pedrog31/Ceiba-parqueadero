package co.com.ceiba.persistencia.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;

@Component()
public class RepositorioVehiculoPersistente implements RepositorioVehiculo {

	@Autowired
	RepositorioVehiculoJPA repositorioVehiculoJPA;

	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) {
		return VehiculoBuilder.convertirADominio(repositorioVehiculoJPA.findByPlaca(placa));
	}

}
