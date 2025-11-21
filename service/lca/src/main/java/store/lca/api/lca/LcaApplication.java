package store.lca.api.lca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LcaApplication.class, args);
	}

}
