package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Catches;
import uk.gov.hmrc.repository.CatchView;
import uk.gov.hmrc.repository.CatchRepository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final CatchView catchView;
    
    public CatchWebController(CatchRepository catchRepository, CatchView catchView) {
        this.catchRepository = catchRepository;
		this.catchView = catchView;
    }

    @GetMapping("/")
    public String showInsertForm(Model model,
    		@PageableDefault(size = 5) Pageable pageable)
    		{
        model.addAttribute("catches", catchRepository.findAll(Sort.by(Sort.Direction.DESC, "catchId")));
        return "catches";
    }

    @PostMapping("/")
    public String insertCatches(@ModelAttribute Catches catches) {
        catchRepository.save(catches);
        return "redirect:/?success=true";
    }
}
