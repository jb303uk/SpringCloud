package uk.gov.hmrc.demoapp;

import uk.gov.hmrc.demoapp.service.MySqlRdsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EntityScan("uk.gov.hmrc.hawk.demo.demoapp.model")
//@EnableJpaRepositories("uk.gov.hmrc.hawk.demo.demoapp.repository")
public class DemoappApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoappApplication.class, args);
        MySqlRdsService rdsService = context.getBean(MySqlRdsService.class);
        rdsService.saveTenant("HAWK");
    }
}
