package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

@Repository
public interface RepositorioServicioParqueoJPA extends JpaRepository<ServicioParqueoEntity, Long> {

	ServicioParqueoEntity findByVehiculoPlacaAndFechaSalida(String placa, Date fechaSalida);

	@Modifying
	@Query("update ServicioParqueoEntity serv set serv.valor = ?1 where serv.vehiculo.placa = ?2 and serv.fechaSalida = ?3")
	int updateValorByPlacaAndFechaSalida(long valor, String placa, Date fechaSalida);

	@Modifying
	@Query("update ServicioParqueoEntity serv set serv.fechaSalida = ?1 where serv.vehiculo.placa = ?2 and serv.fechaSalida = null")
	int updateFechaSalidaByPlaca(Date fechaSalida, String placa);
	
	Integer countByVehiculoTipoVehiculoAndFechaSalidaNull(String tipoVehiculo);
	
	List<ServicioParqueoEntity> findByFechaSalidaNull();
}
