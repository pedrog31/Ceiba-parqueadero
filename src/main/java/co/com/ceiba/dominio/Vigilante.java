package co.com.ceiba.dominio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.dominio.repositorio.RepositorioServicioParqueo;
import co.com.ceiba.dominio.repositorio.RepositorioTarifas;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.ceiba.dominio.repositorio.RepositorioVehiculo;

public class Vigilante {
	
	private List<RestriccionIngreso> restriccionesIngreso;
	private RepositorioServicioParqueo respositorioServicioParqueo;
	private RepositorioTarifas repositorioTarifas;
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado;

	public Vigilante(
			List<RestriccionIngreso> restriccionesIngreso,
			RepositorioServicioParqueo respositorioServicioParqueo, 
			RepositorioTarifas repositorioTarifas,
			RepositorioVehiculo repositorioVehiculo,
			RepositorioTasaRepresentativaMercado repositorioTasaRepresentativaMercado) {
		this.restriccionesIngreso = restriccionesIngreso;
		this.respositorioServicioParqueo = respositorioServicioParqueo;
		this.repositorioTarifas = repositorioTarifas;
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioTasaRepresentativaMercado = repositorioTasaRepresentativaMercado;
	}
	
	public ServicioParqueo consultarServicio (String placa) {
		ServicioParqueo servicioParqueo = respositorioServicioParqueo.buscarServicioVehiculo(placa);
		if (servicioParqueo.esServicioNuevo()) {
			Vehiculo vehiculo = repositorioVehiculo.obtenerVehiculoPorPlaca(placa);
			return new ServicioParqueo(null, vehiculo); 
		}
		return this.registrarSalidaVehiculo(servicioParqueo);
	}

	public ServicioParqueo registrarIngresoVehiculo(Vehiculo vehiculo) {
		this.validarIngresoPorRestricciones (vehiculo);
		ServicioParqueo servicio = new ServicioParqueo(new Date(), vehiculo);
		respositorioServicioParqueo.registrarIngresoVehiculo(servicio);
		return servicio;
	}

	private void validarIngresoPorRestricciones(Vehiculo vehiculo) {
		for (RestriccionIngreso restriccion: restriccionesIngreso)
			restriccion.esValido(vehiculo);
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