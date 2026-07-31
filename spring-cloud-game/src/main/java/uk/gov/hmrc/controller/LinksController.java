package uk.gov.hmrc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uk.gov.hmrc.config.LinksConfig;

@Controller
public class LinksController {

    private final LinksConfig linksConfig;

    public LinksController(LinksConfig linksConfig) {
        this.linksConfig = linksConfig;
    }

    @GetMapping("/links")
    public String showLinks(Model model) {
        model.addAttribute("pageTitle", linksConfig.pageTitle());
        model.addAttribute("links", linksConfig.links());
        return "links"; // Resolves to links.jte
    }
}

