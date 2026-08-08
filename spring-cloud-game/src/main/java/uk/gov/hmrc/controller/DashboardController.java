package uk.gov.hmrc.controller;

import uk.gov.hmrc.repository.CatchView;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/new")
public class DashboardController {

    private final CatchView catchView;

    public DashboardController(CatchView catchView) {
        this.catchView = catchView;
    }

    @GetMapping("/")
    public String getDashboard(
            @RequestParam(defaultValue = "0") int pageAll,
            @RequestParam(defaultValue = "5") int sizeAll,
            @RequestParam(defaultValue = "catchId") String sortAll,
            @RequestParam(defaultValue = "desc") String dirAll,            
            Model model) {
        Sort sortOrderAll = dirAll.equalsIgnoreCase("desc") ? Sort.by(sortAll).descending() : Sort.by(sortAll).ascending();
        Pageable pageableAll = PageRequest.of(pageAll, sizeAll, sortOrderAll);
        model.addAttribute("allCatches", catchView.findAll(pageableAll));
        model.addAttribute("sortAll", sortAll);
        model.addAttribute("dirAll", dirAll);
        return "dashboard";
    }
    
}
