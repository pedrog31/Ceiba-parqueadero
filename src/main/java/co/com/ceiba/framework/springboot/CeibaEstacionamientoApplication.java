package co.com.ceiba.framework.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("co.com.ceiba.persistencia.entidad")
@ComponentScan("co.com.ceiba.persistencia")
@EnableJpaRepositories("co.com.ceiba.persistencia.repositorio.jpa") 
public class CeibaEstacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaEstacionamientoApplication.class, args);
	}

}
