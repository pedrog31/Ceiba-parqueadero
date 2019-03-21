package co.com.ceiba.dominio;

public class TipoVehiculo {
	
	private String nombre;
	private String descripcion;
	
	public TipoVehiculo(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
