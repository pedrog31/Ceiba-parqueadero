package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioRestriccion;
import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.ceiba.dominio.repositorio.RepositorioVehiculo;

public class Vigilante {

	public static final String VEHICULO_NO_AUTORIZADO = "Vehiculo no habilitado para el ingreso.";
	public static final String PARQUEADERO_LLENO = "No se encuentran espacios disponibles para parquear.";
	
	private RepositorioServicioParqueo respositorioServicioParqueo;
	private RepositorioRestriccion repositorioRestricciones;
	private RepositorioTarifas repositorioTarifas;
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado;
	
	//private List<ValidacionesIngreso> validates:

	public Vigilante(
			RepositorioServicioParqueo respositorioServicioParqueo,
			RepositorioRestriccion repositorioRestricciones, 
			RepositorioTarifas repositorioTarifas,
			RepositorioVehiculo repositorioVehiculo,
			RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado) {
		this.respositorioServicioParqueo = respositorioServicioParqueo;
		this.repositorioRestricciones = repositorioRestricciones;
		this.repositorioTarifas = repositorioTarifas;
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioTasaRepresentativaMercado = repositorioTasaRepresentativaMercado;
	}
	
	public ServicioParqueo consultarServicio (String placa) {
		ServicioParqueo servicioParqueo = respositorioServicioParqueo.buscarServicioVehiculo(placa);
		if (servicioParqueo.esVehiculoNuevo()) {
			Vehiculo vehiculo = repositorioVehiculo.obtenerVehiculoPorPlaca(placa);
			if (vehiculo == null)
				vehiculo = new Vehiculo(placa, null, null);
			return new ServicioParqueo(null, vehiculo); 
		} else if (servicioParqueo.esVehiculoIngresado()) {
			return this.registrarSalidaVehiculo(servicioParqueo);
		} else {
			return servicioParqueo;
		}
	}

	public ServicioParqueo registrarIngresoVehiculo(Vehiculo vehiculo) {
		this.validarIngresoPorRestricciones (vehiculo);
		ServicioParqueo servicio = new ServicioParqueo(new Date(), vehiculo);
		respositorioServicioParqueo.registrarIngresoVehiculo(servicio);
		return servicio;
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
 
	private ServicioParqueo registrarSalidaVehiculo(ServicioParqueo servicioParqueo) {
		List<Tarifa> tarifas = repositorioTarifas.obtenerTarifasPorTipoVehiculo(servicioParqueo.getVehiculo().getTipo());
		servicioParqueo.setTarifas(tarifas);
		servicioParqueo.setFechaSalida(new Date()); 
		servicioParqueo.calcularValorServicio();
		respositorioServicioParqueo.registrarSalidaVehiculo(servicioParqueo);
		return servicioParqueo;
	}

	public void registrarPagoServicio(Boolean pagado, String placa) {
		respositorioServicioParqueo.registrarPagoServicio(pagado, placa);
	}
	
	public List<ServicioParqueo> obtenerVehiculosParqueados () {
		return respositorioServicioParqueo.obtenerVehiculosParqueadero();
	}
	
	public TasaRepresentativaMercado obtenerTasaRepresentativaMercadoActual () {
		return repositorioTasaRepresentativaMercado.obtenerTasaRepresentativaMercadoActual();
	}
 }