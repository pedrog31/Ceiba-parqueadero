package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EST_VEHICULO")
public class VehiculoEntity {
	@Id
	@Column(nullable = false)
	private String placa;
	
	@Column(nullable = true)
	private Integer cilindraje;
	
	@Column(nullable = false)
	private String tipoVehiculo;
	
	public VehiculoEntity() {
		super();
	}
	
	public VehiculoEntity(String placa) {
		super();
		this.placa = placa;
	}

	public VehiculoEntity(String placa, Integer cilindraje, String tipoVehiculo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
}
