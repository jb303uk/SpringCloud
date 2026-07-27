package uk.gov.hmrc.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.gov.hmrc.demoapp.service.MySqlRdsService;

@Controller
@RequestMapping(path = "/web/rds/mysql")
public class MySqlRdsDemoUiController {

    private final MySqlRdsService rdsService;

    @Autowired
    MySqlRdsDemoUiController(MySqlRdsService rdsService) {
        this.rdsService = rdsService;
    }

    @GetMapping("/")
    @LoadBalanced
    public String apiGetTenants(Model model) {
        model.addAttribute("tenents", rdsService.getAllTenants());
        return "mysql/mysql";
    }

    @PostMapping("/add")
    @LoadBalanced
    public String apiAddTenant(@RequestParam("name") String name, Model model) {
        rdsService.saveTenant(name);
        return apiGetTenants(model);
    }
}
