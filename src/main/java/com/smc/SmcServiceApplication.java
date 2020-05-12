package com.smc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmcServiceApplication.class, args);
	}

}
