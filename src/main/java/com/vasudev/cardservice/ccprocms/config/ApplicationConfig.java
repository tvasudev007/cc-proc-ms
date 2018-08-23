package com.vasudev.cardservice.ccprocms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Model mapper bean for mapping entity to DTO and vice versa
 * 
 * @author vasudev007
 *
 */
@Configuration
public class ApplicationConfig {

	/**
	 * Modelmapper bean
	 * 
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
