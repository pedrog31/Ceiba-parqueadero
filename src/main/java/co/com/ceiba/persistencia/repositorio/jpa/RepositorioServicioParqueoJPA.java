package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

@Repository
public interface RepositorioServicioParqueoJPA extends JpaRepository<ServicioParqueoEntity, Long> {

	ServicioParqueoEntity findByVehiculoPlacaAndFechaSalida(String placa, Date fechaSalida);

	Integer countByVehiculoTipoVehiculoAndPagadoFalse(String tipoVehiculo);
	
	List<ServicioParqueoEntity> findByPagadoFalse();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update ServicioParqueoEntity serv set serv.fechaSalida = :fechaSalida, serv.valor = :valor where serv.vehiculo.placa = :placa  and serv.fechaSalida = null")
	int registrarSalidaVehiculo(@Param("placa") String placa, @Param("fechaSalida") Date fechaSalida, @Param("valor") long valor);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update ServicioParqueoEntity serv set serv.pagado = :pagado where serv.vehiculo.placa = :placa and serv.fechaSalida != null")
	int registrarPagoServicio(@Param("pagado") Boolean pagado, @Param("placa") String placa);

}
