package uk.co.jb303;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.util.function.Supplier;
import module java.base; 

@SpringBootApplication
public class StaticSite {

	public static void main(String[] args) {
		SpringApplication.run(StaticSite.class, args);
	}

    @Bean
    public Supplier<String> fn() {
        return () -> {
            try {
                ClassPathResource resource = new ClassPathResource("static/index-fn.html");
                return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            } catch (Exception e) {
                return "<h1>Error loading page</h1>";
            }
        };
    }
}
