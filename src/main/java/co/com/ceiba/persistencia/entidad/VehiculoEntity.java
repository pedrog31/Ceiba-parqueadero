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
	
	@Column(nullable = false)
	private String tipoVehiculo;
	
	public VehiculoEntity() {
		super();
	}

	public VehiculoEntity(String placa, String tipoVehiculo) {
		super();
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
