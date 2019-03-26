package co.com.ceiba.persistencia.repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.excepcion.ServicioParqueoExcepcion;
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
	public void registrarIngresoVehiculo(ServicioParqueo servicioParqueo) {
		Optional<VehiculoEntity> vehiculo = repositorioVehiculoJPA.findById(servicioParqueo.getVehiculo().getPlaca());
		if (!vehiculo.isPresent()) {
			repositorioVehiculoJPA.saveAndFlush(VehiculoBuilder.convertirAEntity(servicioParqueo.getVehiculo()));
		}
		repositorioServicioParqueoJPA.saveAndFlush(ServicioParqueoBuilder.convertirAEntity(servicioParqueo));
	}

	@Override
	public void registrarSalidaVehiculo(ServicioParqueo servicioParqueo) {
		int numeroActualizaciones = repositorioServicioParqueoJPA.updateFechaSalidaByPlaca(
				servicioParqueo.getFechaSalida(), servicioParqueo.getValor(), servicioParqueo.getVehiculo().getPlaca());
		repositorioServicioParqueoJPA.flush();
		if (numeroActualizaciones != 1) {
			throw new ServicioParqueoExcepcion("Error registrando salida del vehiculo");
		}
	}

	@Override
	public void registrarPagoServicio(String placa, Date fechaFinalizacion) {
		int numeroActualizaciones = repositorioServicioParqueoJPA.updateValorByPlacaAndFechaSalida(placa, fechaFinalizacion);
		if (numeroActualizaciones != 1) {
			throw new ServicioParqueoExcepcion("Error registrando pago del servicio");
		}
	}

	@Override
	public List<ServicioParqueo> obtenerVehiculosParqueadero() { 
		return ServicioParqueoBuilder.convertirADominio(repositorioServicioParqueoJPA.findByFechaSalidaNull());
	}

	@Override
	public ServicioParqueo buscarServicioVehiculo(String placa, Date fechaSalida) {
		return ServicioParqueoBuilder
				.convertirADominio(repositorioServicioParqueoJPA.findByVehiculoPlacaAndFechaSalida(placa, fechaSalida));
	}

	@Override
	public Integer obtenerNumeroVehiculosParqueados(String tipoVehiculo) {
		return repositorioServicioParqueoJPA.countByVehiculoTipoVehiculoAndFechaSalidaNull(tipoVehiculo);
	}
}
