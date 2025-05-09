package edu.mirea.flower_shop_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FlowerShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerShopServiceApplication.class, args);
	}

}
