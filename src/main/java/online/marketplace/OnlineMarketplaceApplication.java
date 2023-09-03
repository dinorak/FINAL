package online.marketplace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "online.marketplace.repository")
public class OnlineMarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineMarketplaceApplication.class, args);

		
	}

}
