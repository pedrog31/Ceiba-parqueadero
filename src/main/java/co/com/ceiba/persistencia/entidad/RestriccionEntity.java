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
	
	@Column(nullable = false)
	private Integer capacidad;

	@Column(nullable = false)
	private Integer diaAplicacion;

	@Column(nullable = false)
	private String expresionRegular;
	
	@Column(nullable = false)
	private boolean esActiva;

	@Column(nullable = false)
	private String tipoVehiculo;
	
	public RestriccionEntity() {
		super();
	}

	public RestriccionEntity(Integer capacidad, Integer diaAplicacion, String expresionRegular,
			boolean esActiva, String tipoVehiculo) {
		super();
		this.identificador = null;
		this.capacidad = capacidad;
		this.diaAplicacion = diaAplicacion;
		this.expresionRegular = expresionRegular;
		this.tipoVehiculo = tipoVehiculo;
		this.esActiva = esActiva;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getDiaAplicacion() {
		return diaAplicacion;
	}

	public void setDiaAplicacion(Integer diaAplicacion) {
		this.diaAplicacion = diaAplicacion;
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	public void setExpresionRegular(String expresionRegular) {
		this.expresionRegular = expresionRegular;
	}

	public boolean isEsActiva() {
		return esActiva;
	}

	public void setEsActiva(boolean esActiva) {
		this.esActiva = esActiva;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
