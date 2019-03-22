package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EST_TARIFAS")
public class TarifaEntity {

	@Id
	@Column(nullable = false)
	private Long identificador;
	
	@Column(nullable = false)
	private byte horaCaduca;
	
	@Column(nullable = false)
	private byte horaInicio;

	@Column(nullable = false)
	private String tipoVehiculo;
	
	@Column(nullable = false)
	private int valor;
	
	public TarifaEntity() {
		
	}

	public TarifaEntity(byte horaCaduca, byte horaInicio, String tipoVehiculo,
			int valor) {
		super();
		this.horaCaduca = horaCaduca;
		this.horaInicio = horaInicio;
		this.tipoVehiculo = tipoVehiculo;
		this.valor = valor;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public byte getHoraCaduca() {
		return horaCaduca;
	}

	public void setHoraCaduca(byte horaCaduca) {
		this.horaCaduca = horaCaduca;
	}

	public byte getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(byte horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
