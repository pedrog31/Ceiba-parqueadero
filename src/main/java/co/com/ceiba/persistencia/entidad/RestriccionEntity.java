package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EST_RESTRICCIONES")
public class RestriccionEntity {

	@Id
	@Column(nullable = false)
	private Long identificador;
	
	@Column(nullable = true)
	private Integer capacidad;

	@Column(nullable = true)
	private Integer diaAplicacion;

	@Column(nullable = true)
	private String expresionRegular;
	
	@Column(nullable = false)
	private boolean esActiva;

	@Column(nullable = true)
	private String tipoVehiculo;
	
	public RestriccionEntity() {
		super();
	}

	public Long getIdentificador() {
		return identificador;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getDiaAplicacion() {
		return diaAplicacion;
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	public boolean isEsActiva() {
		return esActiva;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
}
