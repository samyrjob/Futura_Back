package com.samyprojects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import com.samyprojects.rps.futura_back.model.Message;
import com.samyprojects.rps.futura_back.model.Utilisator;
import com.samyprojects.rps.futura_back.repository.MessageRepository;
import com.samyprojects.rps.futura_back.repository.UtilisatorRepository;
import com.samyprojects.rps.futura_back.service.MessageService;
import com.samyprojects.rps.futura_back.service.UtilisatorService;

// @SpringBootApplication(scanBasePackages={"com.samyprojects.rps.futura_back.repository.UserRepository"})
@SpringBootApplication(scanBasePackages={"com.samyprojects.rps.futura_back"})
public class FuturaBackApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FuturaBackApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UtilisatorService utilisatorService, MessageService messageService){
		return args -> {

			Utilisator utilisator = new Utilisator("samy", "blabla@hotmail.fr", "Cameleon");
			utilisatorService.saveUser(utilisator);
			messageService.saveMessage(new Message(10, "yeah this is a message baby !", utilisator));

		};
	}

	 @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FuturaBackApplication.class);
    }

}
