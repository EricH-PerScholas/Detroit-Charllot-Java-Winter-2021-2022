package perscholas.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import perscholas.webservices.consumer.GetCountryRequest;
import perscholas.webservices.consumer.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport{

	private static final Logger log = LoggerFactory.getLogger(CountryClient.class);
	
	 public GetCountryResponse getCountry(String country) {
	        GetCountryRequest request = new GetCountryRequest();
	        request.setName(country);
	        
	        log.info("Requesting information for " + request.getName());
	 
	        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
	          .marshalSendAndReceive(request);
	        return response;
	    }
}
