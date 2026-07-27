package uk.gov.hmrc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("${app.feign.config.name}")
public interface GreetingClient {
    @RequestMapping("/greeting")
    String greeting();
}
