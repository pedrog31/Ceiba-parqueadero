package co.com.ceiba.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.dominio.Tarifa;
import co.com.ceiba.persistencia.entidad.TarifaEntity;

public final class TarifaBuilder {
	
	private TarifaBuilder() {}
	
	public static Tarifa convertirADominio(TarifaEntity tarifaEntity) {
		return new Tarifa (
						tarifaEntity.getCilindraje(),
						tarifaEntity.getHoraCaduca(),
						tarifaEntity.getHoraInicio(),
						tarifaEntity.getTipoVehiculo(),
						tarifaEntity.getValor());
	}
	
	public static List<Tarifa> convertirADominio(List<TarifaEntity> tarifasEntity) {
		List<Tarifa> tarifas = new ArrayList<>();
		for (TarifaEntity tarifaEntity: tarifasEntity) {
			tarifas.add(convertirADominio (tarifaEntity));
		}
		return tarifas;
	}
}
