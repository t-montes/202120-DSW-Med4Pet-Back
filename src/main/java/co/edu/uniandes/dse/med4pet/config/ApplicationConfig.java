package co.edu.uniandes.dse.med4pet.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	 @Bean
	   public ModelMapper modelMapper() {
	      return new ModelMapper();
	   }
}
