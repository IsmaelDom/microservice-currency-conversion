package com.demo.microservicecurrencyconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
//@EnableFeignClients("com.demo.currencyconversion")
@EnableFeignClients
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Microservicios API", version = "1.0", description = "Practica de Microservicios"))
public class MicroserviceCurrencyConversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCurrencyConversionApplication.class, args);
	}

}
