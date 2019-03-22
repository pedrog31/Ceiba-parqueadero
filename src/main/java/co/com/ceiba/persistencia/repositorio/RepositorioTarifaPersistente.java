package co.com.ceiba.persistencia.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.Tarifa;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.persistencia.builder.TarifaBuilder;
import co.com.ceiba.persistencia.entidad.TarifaEntity;
import co.com.ceiba.persistencia.repositorio.jpa.RepositorioTarifaJPA;

@Component
public class RepositorioTarifaPersistente  implements RepositorioTarifas {
	
	@Autowired
	RepositorioTarifaJPA repositorioTarifaJPA;

	@Override
	public List<Tarifa> obtenerTarifasPorTipoVehiculo(String tipoVehiculo) {
		List<TarifaEntity> tarifasEntity = this.repositorioTarifaJPA.findAllByTipoVehiculo(tipoVehiculo);
		return TarifaBuilder.convertirADominio(tarifasEntity);
	}

}