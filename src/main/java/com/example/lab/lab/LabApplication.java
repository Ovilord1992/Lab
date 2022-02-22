package com.example.lab.lab;
import com.example.lab.lab.services.CryptoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.io.IOException;


@SpringBootApplication
@EnableScheduling
public class LabApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(LabApplication.class, args);
	}


}
