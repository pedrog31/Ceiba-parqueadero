package co.com.ceiba.dominio.repositorio;

import co.com.ceiba.dominio.Vehiculo;

public interface RepositorioVehiculo {

	Vehiculo obtenerVehiculoPorPlaca(String placa);

}
