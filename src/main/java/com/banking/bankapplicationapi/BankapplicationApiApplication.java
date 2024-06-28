package com.banking.bankapplicationapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication

public class BankapplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankapplicationApiApplication.class, args);
		log.info("The project is started Successfully");

	}
}
