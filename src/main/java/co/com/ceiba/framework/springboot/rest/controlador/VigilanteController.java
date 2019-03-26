package co.com.ceiba.framework.springboot.rest.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;

@RestController
@RequestMapping("/vigilante")
public class VigilanteController {

	@Autowired
	Vigilante vigilante;
	
	@PostMapping(value = "/registrar-ingreso")
	public ResponseEntity<Object> registrarIngresoVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/registrar-salida")
	public ResponseEntity<Object> registrarSalidaVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/registrar-pago")
	public ResponseEntity<Object> registrarPagoVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
}
