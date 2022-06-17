package api.motortracker.motortracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "MotorTracker API", version = "1.0", description = "API to handle requests from the mobile and webapp motortracker applications"))
public class MotortrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotortrackerApplication.class, args);
	}

}
