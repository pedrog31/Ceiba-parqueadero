package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.persistencia.entidad.RestriccionEntity;

public interface RepositorioRestriccionJPA  extends JpaRepository<RestriccionEntity, Long> {
	
	List<RestriccionEntity> findByDiaAplicacionAndTipoVehiculoStartingWithAndEsActiva (Integer diaAplicacion, String tipoVehiculo, boolean esActiva);
	
	/*
	 * 
	@Modifying
	@Query("select * from RestriccionEntity re where re.diaAplicacion = ?1 and ?2 like re.tipoVehiculo and re.esActiva = ?3")
	List<RestriccionEntity> findByDiaAplicacionAndTipoVehiculoAndEsActiva (Integer diaAplicacion, String tipoVehiculo, boolean esActiva);
	 */
}
