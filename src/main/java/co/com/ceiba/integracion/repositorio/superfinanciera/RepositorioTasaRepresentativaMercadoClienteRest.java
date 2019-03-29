package co.com.ceiba.integracion.repositorio.superfinanciera;

import java.rmi.RemoteException;

import org.springframework.stereotype.Component;

import co.com.ceiba.dominio.TasaRepresentativaMercado;
import co.com.ceiba.dominio.excepcion.VigilanteExcepcion;
import co.com.ceiba.dominio.repositorio.RepositorioTasaRepresentativaMercado;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.sc.nexura.superfinanciera.action.generic.services.trm.action.TcrmResponse;

@Component()
public class RepositorioTasaRepresentativaMercadoClienteRest implements RepositorioTasaRepresentativaMercado {

	private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	
	@Override
	public TasaRepresentativaMercado obtenerTasaRepresentativaMercadoActual() {
		TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
			TcrmResponse tcrmResponse;
			try {
				tcrmResponse = proxy.queryTCRM(null);
				return new TasaRepresentativaMercado(
						tcrmResponse.getUnit(),
						tcrmResponse.getValidityFrom().getTime(),
						tcrmResponse.getValidityTo().getTime(),
						tcrmResponse.getValue());
			} catch (RemoteException e) {
				throw new VigilanteExcepcion("Error en servicio Superfinanciera" + e.getMessage());
			}
	}

}
