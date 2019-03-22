package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.persistencia.entidad.RestriccionEntity;

public interface RepositorioRestriccionJPA  extends JpaRepository<RestriccionEntity, Long> {
	
	List<RestriccionEntity> findByDiaAplicacionAndTipoVehiculoAndEsActiva (Integer diaAplicacion, String tipoVehiculo, boolean esActiva);
}
