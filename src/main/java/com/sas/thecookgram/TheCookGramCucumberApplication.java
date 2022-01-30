package com.sas.thecookgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sas.thecookgram")
public class TheCookGramCucumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheCookGramCucumberApplication.class, args);
	}

}

