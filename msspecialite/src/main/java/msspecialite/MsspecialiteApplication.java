package msspecialite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients("msspecialite")

public class MsspecialiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsspecialiteApplication.class, args);
	}

}
