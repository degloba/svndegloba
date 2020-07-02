package SpringApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 
 * @author degloba
 * 
 * http://www.robertocrespo.net/kaizen/como-construir-microservicios-con-spring-boot/
 *
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@SpringBootApplication
public class EcommerceWebapp_SpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebapp_SpringApplication.class, args);
	}
}
