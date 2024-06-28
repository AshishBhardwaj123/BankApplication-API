package com.banking.bankapplicationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestBankapplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(BankapplicationApiApplication::main).with(TestBankapplicationApiApplication.class).run(args);
	}

}
