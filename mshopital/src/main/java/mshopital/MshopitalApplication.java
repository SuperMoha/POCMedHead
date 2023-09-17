package mshopital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients("mshopital")

public class MshopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MshopitalApplication.class, args);
	}

}
