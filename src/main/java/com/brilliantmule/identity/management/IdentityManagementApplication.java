package com.brilliantmule.identity.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Identity Management",
				description = "Microservice that emulates a customer's homegrown identity management system (IDMS).",
				contact = @Contact(
						name = "Alan Belisle",
						email = "abelisle@salesforce.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0.html"
				),
				version = "1.0.0"
		), tags = {
				@Tag(
						name = "Health",
						description = "Operations about this microservice's health"
				),
				@Tag(
						name = "Identities",
						description = "Operations about identities"
				)
		}, servers = {
				@Server(
						url = "https://dev.brilliantmule.com/v1",
						description = "Development endpoint"
				),
				@Server(
						url = "https://sit.brilliantmule.com/v1",
						description = "System integration testing endpoint"
				),
				@Server(
						url =  "https://staging.brilliantmule.com/v1",
						description =  "Staging endpoint"
				),
				@Server(
						url =  "https://api.brilliantmule.com/v1",
						description =  "Production endpoint"
				)
		}
)
public class IdentityManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityManagementApplication.class, args);
	}

}
