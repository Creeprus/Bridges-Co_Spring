package com.example.BridgeAndCoCursach;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BridgeAndCoCursachApplication {

	public static void main(String[] args) {
		SpringApplication.run(BridgeAndCoCursachApplication.class, args);
	}


}
