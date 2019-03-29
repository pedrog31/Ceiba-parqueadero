package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.RestriccionEntity;

@Repository
public interface RepositorioRestriccionJPA  extends JpaRepository<RestriccionEntity, Long> {
	
	List<RestriccionEntity> findByDiaAplicacionOrDiaAplicacionNullAndTipoVehiculoAndEsActiva (Integer diaAplicacion, String tipoVehiculo, boolean esActiva);
}
