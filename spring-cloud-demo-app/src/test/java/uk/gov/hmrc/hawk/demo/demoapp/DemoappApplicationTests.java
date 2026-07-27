package uk.gov.hmrc.hawk.demo.demoapp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoappApplicationTests {

	@Disabled("Testing Build before Unit Tests in Kubernetes Runner")
    void contextLoads() {
    }

}
