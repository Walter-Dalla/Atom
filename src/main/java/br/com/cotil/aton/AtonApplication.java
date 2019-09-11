package br.com.cotil.aton;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class AtonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtonApplication.class, args);
	}
	
	@PostConstruct
	  void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	  }

}