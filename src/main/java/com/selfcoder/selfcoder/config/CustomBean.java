package com.selfcoder.selfcoder.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBean {

	@Bean
	 ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	
}
