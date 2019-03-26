package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;

public class Vigilante {

	public static final String VEHICULO_NO_AUTORIZADO = "Vehiculo no habilitado para el ingreso.";
	public static final String PARQUEADERO_LLENO = "No se encuentran espacios disponibles para parquear.";
	
	private RepositorioServicioParqueo respositorioServicioParqueo;
	private RepositorioRestriccion repositorioRestricciones;
	private RepositorioTarifas repositorioTarifas;
	
	//private List<ValidacionesIngreso> validates:

	public Vigilante(
			RepositorioServicioParqueo respositorioServicioParqueo,
			RepositorioRestriccion repositorioRestricciones, 
			RepositorioTarifas repositorioTarifas) {
		this.respositorioServicioParqueo = respositorioServicioParqueo;
		this.repositorioRestricciones = repositorioRestricciones;
		this.repositorioTarifas = repositorioTarifas;
	}

	public void registrarIngresoVehiculo(Vehiculo vehiculo) {
		this.validarIngresoPorRestricciones (vehiculo);
		ServicioParqueo servicio = new ServicioParqueo(new Date(), this.repositorioTarifas, vehiculo);
		respositorioServicioParqueo.registrarIngresoVehiculo(servicio);
	}

	private void validarIngresoPorRestricciones(Vehiculo vehiculo) {
		List<Restriccion> restricciones = this.repositorioRestricciones
				.obtenerRestriccionesActivas(vehiculo.getTipo());
		for (Restriccion restriccion: restricciones) {
			if (restriccion.getExpresionRegular() != null) {
				Pattern pat = Pattern.compile(restriccion.getExpresionRegular(), Pattern.CASE_INSENSITIVE);
				Matcher match = pat.matcher(vehiculo.getPlaca());
				if (match.find()) {
					throw new VigilanteExcepcion(VEHICULO_NO_AUTORIZADO);
				}
			} else if (restriccion.getCapacidad() != null) {
				Integer numeroVehiculos = respositorioServicioParqueo.obtenerNumeroVehiculosParqueados(vehiculo.getTipo());
				if (restriccion.getCapacidad() <= numeroVehiculos) {
					throw new VigilanteExcepcion(PARQUEADERO_LLENO);
				}
			}
		}
	}

	public ServicioParqueo registrarSalidaVehiculo(String placa) {
		ServicioParqueo servicioParqueo = respositorioServicioParqueo.buscarServicioVehiculo(placa, null);
		servicioParqueo.setRepositorioTarifas(this.repositorioTarifas);
		servicioParqueo.setFechaSalida(new Date());
		servicioParqueo.calcularValorServicio();
		respositorioServicioParqueo.registrarSalidaVehiculo(servicioParqueo);
		return servicioParqueo;
	}

	public void registrarPagoServicio(String placa, Date fechaFinalizacion) {
		ServicioParqueo servicioParqueo = respositorioServicioParqueo.buscarServicioVehiculo(placa, fechaFinalizacion);
		servicioParqueo.setRepositorioTarifas(this.repositorioTarifas);
		servicioParqueo.setFechaSalida(fechaFinalizacion);
		servicioParqueo.calcularValorServicio();
		respositorioServicioParqueo.registrarPagoServicio(servicioParqueo);
	}
 }