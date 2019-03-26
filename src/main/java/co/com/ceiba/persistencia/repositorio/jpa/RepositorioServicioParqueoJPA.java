package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

@Repository
public interface RepositorioServicioParqueoJPA extends JpaRepository<ServicioParqueoEntity, Long> {

	ServicioParqueoEntity findByVehiculoPlacaAndFechaSalida(String placa, Date fechaSalida);

	@Modifying
	@Query("update ServicioParqueoEntity serv set serv.pagado = true where serv.vehiculo.placa = :placa and serv.fechaSalida = :fechaSalida")
	int updateValorByPlacaAndFechaSalida(@Param(value = "placa") String placa, @Param(value = "fechaSalida") Date fechaSalida);

	@Modifying
	@Query("update ServicioParqueoEntity serv set serv.fechaSalida = :fechaSalida, serv.valor = :valor where serv.vehiculo.placa = :placa and serv.fechaSalida = null")
	int updateFechaSalidaByPlaca(@Param(value = "fechaSalida") Date fechaSalida, @Param(value = "valor") long valor, @Param(value = "placa") String placa);
	
	Integer countByVehiculoTipoVehiculoAndFechaSalidaNull(String tipoVehiculo);
	
	List<ServicioParqueoEntity> findByFechaSalidaNull();
}
