package com.example.runnerz;

//import com.example.runnerz.run.Location;
//import com.example.runnerz.run.Run;
//import com.example.runnerz.run.RunRepository;
import com.example.runnerz.user.User;
import com.example.runnerz.user.UserHttpClient;
import com.example.runnerz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.LocalDateTime;

@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(Appendable.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
	}

//	@Bean
//	//command line runner is run after application starts
//	CommandLineRunner runner(RunRepository runRepository){
//		return args -> {
//			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 3, Location.OUTDOOR );
//			log.info("Run " + run.toString());
//			runRepository.create(run);
//		};
//	}

	@Bean
	UserHttpClient userHttpClient() {
		//Create rest client and give the base url
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		//Creating proxy factory with the rest client
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		//Returning the client
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHttpClient client){
		return args -> {
			User user = client.findById(1);
			log.info("User " + user);
		};
	}

}
