package com.crud.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.crud.products.controllers", "com.crud.products.services"})
public class MsComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsComprasApplication.class, args);
	}

}
