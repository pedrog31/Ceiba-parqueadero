package co.com.ceiba.persistencia.repositorio.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.VehiculoEntity;

@Repository
public interface RepositorioVehiculoJPA extends JpaRepository<VehiculoEntity, String> {

	Optional<VehiculoEntity> findByPlaca(String placa);

}
