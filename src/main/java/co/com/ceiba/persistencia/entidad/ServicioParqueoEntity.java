package co.com.ceiba.persistencia.entidad;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "EST_SERVICIO_PARQUEO")
public class ServicioParqueoEntity {
	@Id
	@SequenceGenerator(name = "EST_SEQ_SERVICIOS_PARQUEADERO", sequenceName = "EST_SEQ_SERVICIOS_PARQUEADERO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "IDENTIFICADOR")
	private Long identificador;
	
	@Column(nullable = false)
	private Date fechaIngreso;
	
	@Column(nullable = true)
	private Date fechaSalida;
	
	@Column(nullable = true)
	private Boolean pagado;
	
	@Column(nullable = true)
	private long valor;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.REFRESH})
	private VehiculoEntity vehiculo;

	public ServicioParqueoEntity() {
		super();
		this.vehiculo = new VehiculoEntity();
	}

	public ServicioParqueoEntity(Date fechaIngreso, Date fechaSalida, Boolean pagado, long valor,
			VehiculoEntity vehiculo) {
		super();
		this.identificador = null;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.pagado = pagado;
		this.valor = valor;
		this.vehiculo = vehiculo;
	}

	public Long getIdentificador() {
		return identificador;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Boolean getPagado() {
		return pagado;
	}

	public long getValor() {
		return valor;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}
}
