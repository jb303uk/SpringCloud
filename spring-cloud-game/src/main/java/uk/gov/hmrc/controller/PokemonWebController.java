package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Catches;
import uk.gov.hmrc.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class PokemonWebController {

    private final PokemonRepository pokemonRepository;

    public PokemonWebController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

//    // Displays the JTE employee input screen
//    @GetMapping("/")
//    public String showInsertForm(Model model) {
//        Catch catch = new Catches();
//        //employee.setHireDate(LocalDate.now()); // Set default hire date
//        //model.addAttribute("catch", catch);
//        return "catch"; // Maps to src/main/jte/catch.jte
//    }

//    // Handles form processing
//    @PostMapping("/")
//    public String insertCatch(@ModelAttribute Catch catch) {
//    	pokemonRepository.save(catch);
//        return "redirect:/?success=true";
//    }
//    @GetMapping("/")
//    public String listEmployees(Model model) {
//        model.addAttribute("employees", pokemonRepository.findAll());
//        return "catch"; // Maps to src/main/jte/employees.jte
//    }
}
