package com.vagas_professor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.vagas_professor.repository")
@SpringBootApplication
public class VagasProfessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VagasProfessorApplication.class, args);
	}

}
