package msreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
@EnableFeignClients(basePackages = "msreservation.client")
public class MsreservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsreservationApplication.class, args);
	}

}
