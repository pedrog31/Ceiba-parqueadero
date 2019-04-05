package co.com.ceiba.framework.springboot.rest.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import co.com.ceiba.dominio.ServicioParqueo;
import co.com.ceiba.dominio.TasaRepresentativaMercado;
import co.com.ceiba.dominio.Vehiculo;
import co.com.ceiba.dominio.Vigilante;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;

@RestController
@RequestMapping("vigilante")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
public class VigilanteController {

	@Autowired
	Vigilante vigilante;
	
	@PutMapping(value = "/ingreso")
	public ResponseEntity<Object> registrarIngresoVehiculo (@Valid @RequestBody Vehiculo vehiculo) {
		vigilante.registrarIngresoVehiculo(vehiculo);
		return new ResponseEntity<> (HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/consulta-servicio")
	public ResponseEntity<ServicioParqueo> consultarServicio(@RequestParam String placa) {
		ServicioParqueo servicioParqueo = vigilante.consultarServicio(placa);
		return new ResponseEntity<> (servicioParqueo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/pago")
	public ResponseEntity<Object> registrarPagoVehiculo (@RequestParam Boolean pagado, @RequestParam String placa) {
		vigilante.registrarPagoServicio(pagado, placa);
		return new ResponseEntity<> (HttpStatus.OK);
	}
	
	@GetMapping(value = "/trm")
	public ResponseEntity<TasaRepresentativaMercado> obtenerTasaRepresentativaMercadoActual () {
		TasaRepresentativaMercado tasaRepresentativaMercado = vigilante.obtenerTasaRepresentativaMercadoActual();
		return new ResponseEntity<> (tasaRepresentativaMercado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/estado")
	public ResponseEntity<List<ServicioParqueo>> consultarEstado () {
		List<ServicioParqueo> serviciosParqueo = vigilante.obtenerVehiculosParqueados();
		return new ResponseEntity<> (serviciosParqueo, HttpStatus.OK);
	}
	
	@ExceptionHandler(VigilanteExcepcion.class)
	public ResponseEntity<String> manejarError(VigilanteExcepcion ve) {
		return new ResponseEntity<> (ve.getMessage(), HttpStatus.FORBIDDEN);
	} 
}
