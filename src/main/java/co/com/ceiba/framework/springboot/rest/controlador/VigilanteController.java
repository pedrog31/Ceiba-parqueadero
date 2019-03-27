package co.com.ceiba.framework.springboot.rest.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;

@RestController
@RequestMapping("/vigilante")
public class VigilanteController {

	@Autowired
	Vigilante vigilante;
	
	@PutMapping(value = "/ingreso")
	public ResponseEntity<Object> registrarIngresoVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/salida")
	public ResponseEntity<ServicioParqueo> registrarSalidaVehiculo (@RequestParam String placa) {
		ServicioParqueo servicioParqueo = vigilante.registrarSalidaVehiculo(placa);
		return new ResponseEntity<> (servicioParqueo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/pago")
	public ResponseEntity<Object> registrarPagoVehiculo (@RequestParam Boolean pagado, @RequestParam String placa) {
		vigilante.registrarPagoServicio(pagado, placa);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@GetMapping(value = "/estado")
	public ResponseEntity<List<ServicioParqueo>> registrarPagoVehiculo () {
		List<ServicioParqueo> serviciosParqueo = vigilante.obtenerVehiculosParqueados();
		return new ResponseEntity<> (serviciosParqueo, HttpStatus.OK);
	}
}
