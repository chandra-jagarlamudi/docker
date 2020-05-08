package com.ragas.microservices.currencyconversionservice.resource;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Using Kubernetes environment variable CURRENCY_EXCHANGE_SERVICE_HOST set during POD creation, the draaw 
 * back here is if the POD is not created and available in time the currency service would fail. So it is
 * better to use the Kubernetes DNS service to look up for Currency Exchange service. 
 * 
 * Another option is set Spring profile for Kubernetes
 * 
 * In either case you have to specify docker ENV property in the deployment yaml file 
 * 
 * This works for both local and Kubernetes
 * @FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
 * 
 * This works when deployed to Kubernetes
 * @FeignClient(name = "currency-exchange-service")
 * 
 */

@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")//
public interface CurrencyExchangeServiceProxy {
	//http://localhost:8000/currency-exchange/from/USD/to/INR
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);
}