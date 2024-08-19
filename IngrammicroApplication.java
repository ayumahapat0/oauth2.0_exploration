package com.example.ingrammicro;

import com.example.ingrammicro.HP.Request.CreateOrderRequest;
import com.example.ingrammicro.HP.Response.CancelOrderResponses;
import com.example.ingrammicro.HP.Token.TokenRequester;
import com.example.ingrammicro.HP.Token.TokenResponse;
import com.example.ingrammicro.HP.WarrantyHPIngram;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableFeignClients
public class IngrammicroApplication implements CommandLineRunner {

	private final static Logger log = LogManager.getLogger(com.example.ingrammicro.IngrammicroApplication.class);

	@Autowired
	private WarrantyHPIngram ingramTestClient;

	@Autowired
	private TokenRequester requester;

	public static void main(String[] args) {
		SpringApplication.run(IngrammicroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Getting Access Token");
		TokenResponse response = requester.getAccessToken();
		String token = response.getAccessToken();
		log.info("Response after getting token: {}", token);
		log.info("Creating an order");
		// TODO: create order object
		CreateOrderRequest req = new CreateOrderRequest();
		ObjectMapper map = new ObjectMapper();

		req = map.readValue(new File("C:/Users/MahAy001/Desktop/ingrammicro/ingrammicro/src/main/java/com/example/ingrammicro/WarrantyOrderTest.json"), CreateOrderRequest.class);
		System.out.println(map.writeValueAsString(req));
		log.info("Posting the order");
		try {
			ingramTestClient.create_order(req);
			// TODO: call create_order() method
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
//		log.info("results: {}", StreamUtils.copyToString(order.body().asInputStream(), StandardCharsets.UTF_8));
		log.info("testing cancel order");
		CancelOrderResponses json = ingramTestClient.cancel_order("30-VTRER", "US");
//		log.info("results: {}", StreamUtils.copyToString(json.body().asInputStream(), StandardCharsets.UTF_8));
		log.info("results: {}", json);
	}
}
