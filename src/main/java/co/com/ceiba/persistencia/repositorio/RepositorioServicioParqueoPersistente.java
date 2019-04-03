package co.com.ceiba.persistencia.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.excepcion.ServicioParqueoExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.persistencia.builder.ServicioParqueoBuilder;
import co.com.ceiba.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioServicioParqueoJPA;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioVehiculoJPA;

@Component()
public class RepositorioServicioParqueoPersistente implements RepositorioServicioParqueo {

	@Autowired
	RepositorioServicioParqueoJPA repositorioServicioParqueoJPA;

	@Autowired
	RepositorioVehiculoJPA repositorioVehiculoJPA;

	@Override
	public void registrarIngresoVehiculo(ServicioParqueo servicioParqueo) {
		repositorioVehiculoJPA.saveAndFlush(VehiculoBuilder.convertirAEntity(servicioParqueo.getVehiculo()));
		repositorioServicioParqueoJPA.saveAndFlush(ServicioParqueoBuilder.convertirAEntity(servicioParqueo));
	}

	@Override
	public void registrarSalidaVehiculo(ServicioParqueo servicioParqueo) {
		int numeroActualizaciones = repositorioServicioParqueoJPA.registrarSalidaVehiculo(
				servicioParqueo.getVehiculo().getPlaca(), servicioParqueo.getFechaSalida(), servicioParqueo.getValor());
		if (numeroActualizaciones != 1) {
			throw new ServicioParqueoExcepcion("Error registrando salida del servicio");
		}
		repositorioServicioParqueoJPA.flush();
	}
 
	@Override
	public void registrarPagoServicio(Boolean pagado, String placa) {
		int numeroActualizaciones = repositorioServicioParqueoJPA.registrarPagoServicio(pagado, placa);
		if (numeroActualizaciones != 1) {
			throw new ServicioParqueoExcepcion("Error registrando pago del servicio");
		}
		repositorioServicioParqueoJPA.flush();
	}

	@Override
	public List<ServicioParqueo> obtenerVehiculosParqueadero() {
		return ServicioParqueoBuilder.convertirADominio(repositorioServicioParqueoJPA.findByPagadoNull());
	}

	@Override
	public ServicioParqueo buscarServicioVehiculo(String placa, Date fechaSalida) {
		return ServicioParqueoBuilder.convertirADominio(repositorioServicioParqueoJPA.findByVehiculoPlacaAndFechaSalida(placa, fechaSalida));
	}

	@Override
	public Integer obtenerNumeroVehiculosParqueados(String tipoVehiculo) {
		return repositorioServicioParqueoJPA.countByVehiculoTipoVehiculoAndPagadoNull(tipoVehiculo);
	}

	@Override
	public ServicioParqueo buscarServicioVehiculo(String placa) {
		return ServicioParqueoBuilder.convertirADominio(repositorioServicioParqueoJPA.findByVehiculoPlacaAndPagadoNull(placa));
	}
}
