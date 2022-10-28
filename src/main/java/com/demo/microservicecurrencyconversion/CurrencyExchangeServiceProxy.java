package com.demo.microservicecurrencyconversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "demo-service", url="localhost:8000")
@Component
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/api/exchange/currency/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
	
}
