package com.solid12.solid12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Solid12Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Solid12Application.class);
		ConfigurableEnvironment environment = app.run(args).getEnvironment();

		printEnvironmentVariables(environment);
	}

	private static void printEnvironmentVariables(Environment environment) {
		String springAdapter = environment.getProperty("spring.adapter.service");
		String springProfiles = environment.getProperty("spring.profiles.active");

		System.out.println("====================ENVIRONMENT VARIABLES=====================");
		System.out.println("Spring Adapter Active: " + springAdapter);
		System.out.println("Spring Profiles Active: " + springProfiles);
	}

}
