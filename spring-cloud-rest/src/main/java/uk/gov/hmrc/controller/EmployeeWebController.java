package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Employee;
import uk.gov.hmrc.repository.EmployeeRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class EmployeeWebController {

    private final EmployeeRepository employeeRepository;

    public EmployeeWebController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public String showInsertForm(Model model) {
        Employee employee = new Employee();
        employee.setHireDate(LocalDate.now());
        model.addAttribute("employee", employee);
        model.addAttribute("employees", employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "employeeId")));
        return "employees";
    }

    @PostMapping("/")
    public String insertEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/?success=true";
    }
}
