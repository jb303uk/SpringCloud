package uk.gov.hmrc.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.gov.hmrc.demoapp.model.Tenant;
import uk.gov.hmrc.demoapp.service.MySqlRdsService;

import java.sql.SQLException;

@RestController
@RequestMapping(path = "/rds/mysql")
public class MySqlRdsDemoController {

    private final MySqlRdsService rdsService;

    @Autowired
    MySqlRdsDemoController(MySqlRdsService rdsService) {
        this.rdsService = rdsService;
    }

    @GetMapping("/{id}")
    @LoadBalanced
    public String apiGetTenant(@PathVariable long id) {
        try {
            return "Tenant Found: " + rdsService.getTenant(id).getName();
        } catch (SQLException e) {
            return "Tenant Not Found";
        }
    }

    @GetMapping("add/{name}")
    @LoadBalanced
    public String apiAddTenant(@PathVariable String name) {
        Tenant tenant = rdsService.saveTenant(name);
        return String.format("Created Tenent ID:%s Name:%s", tenant.getId(), tenant.getName());
    }

}
