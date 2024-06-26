package com.microservices.accounts;

import com.microservices.accounts.dto.AccountsConfigDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsConfigDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts/Customer Microservice REST API documentation",
                version = "v1.0",
                contact = @Contact(

                        name = "Akram Iguer",
                        email = "akram.iguernait@gmail.com"
                )

        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
