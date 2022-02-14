package perscholas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import perscholas.webservices.CountryClient;
import perscholas.webservices.consumer.GetCountryRequest;
import perscholas.webservices.consumer.GetCountryResponse;

@RestController
public class CountryClientController {
	
	@Autowired
	private CountryClient client;
	
	@GetMapping("/getCountry")
	public GetCountryResponse invokeCountryClientToGetCountry () {
		String name = "Spain";
		return client.getCountry(name);
	}

}
