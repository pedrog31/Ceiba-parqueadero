package co.com.ceiba.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EST_VEHICULO")
public class VehiculoEntity {
	
	@Id
	@SequenceGenerator(name="EST_SEQ_VEHICULOS", initialValue=1, allocationSize=100)
	private Long identificador;
	
	@Column(nullable = false)
	private String placa;
	
	@ManyToOne
	@JoinColumn(name="IDENTIFICADOR_TIPO_VEHICULO")
	private TipoVehiculoEntity tipoVehiculo;
	
	public Long getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
}
