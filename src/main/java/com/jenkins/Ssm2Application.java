package com.jenkins;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ssm2Application {

	public static void main(String[] args) {
//		SpringApplication.run(Ssm2Application.class, args);
		SpringApplication application = new SpringApplication(Ssm2Application.class);
		Map<String, Object> map = new HashMap<>();
		map.put("server.servlet.context-path", "/mydemo");
		map.put("server.port", "9528");
		application.setDefaultProperties(map);
		application.run(args);
	}

}
