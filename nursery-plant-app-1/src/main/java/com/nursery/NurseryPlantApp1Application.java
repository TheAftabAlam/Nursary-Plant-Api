package com.nursery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NurseryPlantApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(NurseryPlantApp1Application.class, args);
	}

}
