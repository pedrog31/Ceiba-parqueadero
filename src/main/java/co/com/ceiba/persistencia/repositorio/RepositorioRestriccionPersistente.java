package co.com.ceiba.persistencia.repositorio;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.Restriccion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.persistencia.builder.RestriccionBuilder;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioRestriccionJPA;

@Component()
public class RepositorioRestriccionPersistente implements RepositorioRestriccion {

	@Autowired
	RepositorioRestriccionJPA repositorioRestriccionJPA;

	@Override
	public List<Restriccion> obtenerRestriccionesActivas(String tipoVehiculo) {
		Integer diaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		return RestriccionBuilder.convertirADominio(
				repositorioRestriccionJPA
					.findByDiaAplicacionOrDiaAplicacionNullAndTipoVehiculoAndEsActiva(diaSemana, tipoVehiculo, true));
	}

}
