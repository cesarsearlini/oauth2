package br.com.authenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AuthenticatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatorApplication.class, args);
    }

}