package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages= {"dao", "service","controlador"})
@EntityScan(basePackages = {"model"})
@EnableJpaRepositories(basePackages= {"dao"})
@SpringBootApplication
@EnableEurekaClient
public class AnotacionesApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AnotacionesApplication.class, args);
	}
	
}
