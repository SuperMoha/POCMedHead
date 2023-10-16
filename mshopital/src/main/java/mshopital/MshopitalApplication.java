package mshopital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableFeignClients("mshopital")

public class MshopitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MshopitalApplication.class, args);
	}

}
