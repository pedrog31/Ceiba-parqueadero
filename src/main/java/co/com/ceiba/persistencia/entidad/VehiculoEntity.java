package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EST_VEHICULO")
public class VehiculoEntity {
	@Id
	@Column(nullable = false)
	private String placa;
	
	@OneToOne
	@JoinColumn(name="IDENTIFICADOR_TIPO_VEHICULO")
	private TipoVehiculoEntity tipoVehiculo;
	
	public VehiculoEntity() {
		super();
	}

	public VehiculoEntity(String placa, TipoVehiculoEntity tipoVehiculo) {
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

	public TipoVehiculoEntity getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
