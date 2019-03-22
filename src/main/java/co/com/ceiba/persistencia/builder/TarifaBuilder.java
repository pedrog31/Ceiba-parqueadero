package co.com.ceiba.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.dominio.Tarifa;
import co.com.ceiba.persistencia.entidad.TarifaEntity;

public class TarifaBuilder {
	
	private TarifaBuilder() {}
	
	public static Tarifa convertirADominio(TarifaEntity tarifaEntity) {
		return tarifaEntity == null  ? 
				null : new Tarifa (
						tarifaEntity.getHoraCaduca(),
						tarifaEntity.getHoraInicio(),
						tarifaEntity.getTipoVehiculo(),
						tarifaEntity.getValor());
	}
	
	public static List<Tarifa> convertirADominio(List<TarifaEntity> tarifasEntity) {
		if (tarifasEntity == null) return new ArrayList<>();
		List<Tarifa> tarifas = new ArrayList<>();
		for (TarifaEntity tarifaEntity: tarifasEntity) {
			if (tarifaEntity != null) {
				tarifas.add(new Tarifa (
						tarifaEntity.getHoraCaduca(),
						tarifaEntity.getHoraInicio(),
						tarifaEntity.getTipoVehiculo(),
						tarifaEntity.getValor()));
			}
		}
		return tarifas;
	}
	
	public static TarifaEntity convertirAEntity(Tarifa tarifa) {
		return tarifa == null  ? 
				null : new TarifaEntity (
						tarifa.getHoraCaduca(),
						tarifa.getHoraInicio(),
						tarifa.getTipoVehiculo(),
						tarifa.getValor());
	}
}
