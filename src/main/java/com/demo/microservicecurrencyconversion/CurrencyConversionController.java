package com.demo.microservicecurrencyconversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.java.Log;

@RestController
@Log
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@Parameter(description="Desde que se va a convertir", example = "EUR") @PathVariable String from,
										@Parameter(description="A que se va a convertir", example = "INR") @PathVariable String to,
										@Parameter(description="Cantidad a convertir", example = "200") @PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://127.0.0.1:8000/api/exchange/currency/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.id(), from, to, response.conversionMultiple(), quantity,
				quantity.multiply(response.conversionMultiple()), response.port());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@Parameter(description="Desde que se va a convertir", example = "EUR") @PathVariable String from,
														@Parameter(description="A que se va a convertir", example = "INR") @PathVariable String to,
														@Parameter(description="Cantidad a convertir", example = "200") @PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		log.info("Response: " + response);

		return new CurrencyConversionBean(response.id(), from, to, response.conversionMultiple(), quantity,
				quantity.multiply(response.conversionMultiple()), response.port());
	}
}
