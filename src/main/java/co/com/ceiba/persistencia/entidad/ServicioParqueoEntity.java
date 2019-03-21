package co.com.ceiba.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EST_SERVICIO_PARQUEO")
public class ServicioParqueoEntity {
	
	@Id
	@SequenceGenerator(name="EST_SEQ_SERVICIOS_PARQUEADERO", initialValue=1, allocationSize=100)
	private Long identificador;
	
	@Column(nullable = false)
	private Date fechaIngreso;
	
	@Column(nullable = true)
	private Date fechaSalida;
	
	@Column(nullable = true)
	private int valor;
	
	@ManyToOne
	@JoinColumn(name="IDENTIFICADOR_VEHICULO")
	private VehiculoEntity vehiculo;
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public Date getFechaSalida() {
		return fechaSalida;
	}
	
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}
}
