package com.jrl.employeetracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.jrl.employeetracker.rest"})
public class SpringBootDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);
	
	public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
		logger.info("SpringDemoApplication has started");
	}

}
