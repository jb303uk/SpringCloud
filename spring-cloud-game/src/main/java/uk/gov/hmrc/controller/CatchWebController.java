package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Catches;
import uk.gov.hmrc.repository.CatchView;
import uk.gov.hmrc.repository.CatchRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String getDashboard(
            @RequestParam(defaultValue = "0") int pageAll,
            @RequestParam(defaultValue = "10") int sizeAll,
            @RequestParam(defaultValue = "catchId") String sortAll,
            @RequestParam(defaultValue = "desc") String dirAll,            
            Model model) {
        Sort sortOrderAll = dirAll.equalsIgnoreCase("desc") ? Sort.by(sortAll).descending() : Sort.by(sortAll).ascending();
        Pageable pageableAll = PageRequest.of(pageAll, sizeAll, sortOrderAll);
        model.addAttribute("allCatches", catchView.findAll(pageableAll));
        model.addAttribute("sortAll", sortAll);
        model.addAttribute("dirAll", dirAll);
        return "catches";
    }

    @PostMapping("/")
    public String insertCatches(@ModelAttribute Catches catches) {
        catchRepository.save(catches);
        return "redirect:/?success=true";
    }
}
