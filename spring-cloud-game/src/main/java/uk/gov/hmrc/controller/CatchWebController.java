package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Catches;
import uk.gov.hmrc.repository.CatchRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CatchWebController {

    private final CatchRepository catchRepository;

    public CatchWebController(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    @GetMapping("/")
    public String showInsertForm(Model model) {
        model.addAttribute("catches", catchRepository.findAll(Sort.by(Sort.Direction.DESC, "catchId")));
        return "catches";
    }

    @PostMapping("/")
    public String insertCatches(@ModelAttribute Catches catches) {
        catchRepository.save(catches);
        return "redirect:/?success=true";
    }
}
