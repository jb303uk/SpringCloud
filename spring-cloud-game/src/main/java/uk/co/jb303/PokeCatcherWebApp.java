package uk.co.jb303;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import uk.co.jb303.config.LinksConfig;


@Configuration
@EnableConfigurationProperties(LinksConfig.class)

@SpringBootApplication
public class PokeCatcherWebApp {

    public static void main(String[] args) {
        SpringApplication.run(PokeCatcherWebApp.class, args);
    }
}
