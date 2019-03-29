package co.com.ceiba.dominio;

import java.util.Date;

public class TasaRepresentativaMercado {

	String moneda;
	Date validoDesde;
	Date validoHasta;
	Float valor;
	
	public TasaRepresentativaMercado(String moneda, Date validoDesde, Date validoHasta, Float valor) {
		super();
		this.moneda = moneda;
		this.validoDesde = validoDesde;
		this.validoHasta = validoHasta;
		this.valor = valor;
	}

	public String getMoneda() {
		return moneda;
	}

	public Date getValidoDesde() {
		return validoDesde;
	}

	public Date getValidoHasta() {
		return validoHasta;
	}

	public Float getValor() {
		return valor;
	}
}
