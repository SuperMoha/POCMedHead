package mspatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients("mspatient")

public class MspatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MspatientApplication.class, args);
	}

}
