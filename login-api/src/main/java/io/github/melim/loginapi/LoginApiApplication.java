package io.github.melim.loginapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LoginApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LoginApiApplication.class, args);
	}

}
