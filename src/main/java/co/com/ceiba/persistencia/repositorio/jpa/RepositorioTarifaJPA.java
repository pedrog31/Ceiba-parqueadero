package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.TarifaEntity;

@Repository
public interface RepositorioTarifaJPA extends JpaRepository<TarifaEntity, Long> {
	
	public List<TarifaEntity> findAllByTipoVehiculo (String tipoVehiculo);
}
