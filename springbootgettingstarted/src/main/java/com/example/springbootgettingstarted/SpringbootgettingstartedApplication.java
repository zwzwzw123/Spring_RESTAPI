package com.example.springbootgettingstarted;

import org.apache.catalina.LifecycleException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringbootgettingstartedApplication {

	public static void main(String[] args) throws LifecycleException {
		SpringApplication.run(SpringbootgettingstartedApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
