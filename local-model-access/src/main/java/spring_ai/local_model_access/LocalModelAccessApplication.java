package spring_ai.local_model_access;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocalModelAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalModelAccessApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ChatClient.Builder builder){
		return (args)->{
			var client=builder.build();
			String res=client.prompt("When the computers were created?")
					.call()
					.content();
			System.out.println(res);
		};
    }
}
