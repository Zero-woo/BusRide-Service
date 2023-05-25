package sprout.BusRide.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {

    @Bean
    public OpenAPI BusRideApi() {
        return new OpenAPI()
                .info(new Info().title("BusRide API")
                        .description("팀 새싹이들 버스타미 API입니다.")
                );
    }
}