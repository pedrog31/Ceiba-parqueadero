package co.com.ceiba.rest.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

@RestController
@RequestMapping("/vigilante")
public class VigilanteController {

	@Autowired
	private RepositorioServicioParqueo respositorioServicioParqueo;
	
	@Autowired
	private RepositorioRestriccion repositorioRestricciones;
	
	@Autowired
	private RepositorioTarifas repositorioTarifas;
	
	@PostMapping(value = "/registrar-ingreso")
	public ResponseEntity<String> registrarIngresoVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		Vigilante vigilante = new Vigilante (respositorioServicioParqueo, repositorioRestricciones, repositorioTarifas);
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> ("Vehiculo ingresado con exito", HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/registrar-ingreso")
	public ResponseEntity<String> prueba () {
		return new ResponseEntity<> ("Vehiculo ingresado con exito", HttpStatus.CREATED);
	}
}
