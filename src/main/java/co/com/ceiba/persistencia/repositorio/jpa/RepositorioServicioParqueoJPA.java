package co.com.ceiba.persistencia.repositorio.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.persistencia.entidad.ServicioParqueoEntity;

@Repository
public interface RepositorioServicioParqueoJPA extends JpaRepository<ServicioParqueoEntity, Long> {

}
