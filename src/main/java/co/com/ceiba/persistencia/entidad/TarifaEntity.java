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
	
	@Column (nullable = true)
	private String cilindraje;
	
	@Column(nullable = false)
	private byte horaCaduca;
	
	@Column(nullable = false)
	private byte horaInicio;

	@Column(nullable = false)
	private String tipoVehiculo;
	
	@Column(nullable = false)
	private int valor;

	public TarifaEntity() {
		super();
	}

	public Long getIdentificador() {
		return identificador;
	}

	public String getCilindraje() {
		return cilindraje;
	}

	public byte getHoraCaduca() {
		return horaCaduca;
	}

	public byte getHoraInicio() {
		return horaInicio;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public int getValor() {
		return valor;
	}
}
