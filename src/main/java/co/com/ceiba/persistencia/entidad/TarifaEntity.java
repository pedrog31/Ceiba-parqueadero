package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EST_TARIFAS")
public class TarifaEntity {

	@Id
	@Column(nullable = true)
	private Long identificador;
	
	@Column(nullable = false)
	private byte horaCaduca;
	
	@Column(nullable = false)
	private byte horaInicio;
	
	@ManyToOne
	@JoinColumn(name="IDENTIFICADOR_TIPO_VEHICULO")
	private TipoVehiculoEntity tipoVehiculo;
	
	@Column(nullable = false)
	private int valor;

	public TarifaEntity(byte horaCaduca, byte horaInicio, TipoVehiculoEntity tipoVehiculo,
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

	public TipoVehiculoEntity getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEntity tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
}
