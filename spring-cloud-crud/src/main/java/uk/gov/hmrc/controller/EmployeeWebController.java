package uk.gov.hmrc.controller;

import uk.gov.hmrc.entity.Employee;
import uk.gov.hmrc.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;

@Controller
@RequestMapping("/web/employees")
public class EmployeeWebController {

    private final EmployeeRepository employeeRepository;

    public EmployeeWebController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Displays the JTE employee input screen
    @GetMapping("/new")
    public String showInsertForm(Model model) {
        Employee employee = new Employee();
        employee.setHireDate(LocalDate.now()); // Set default hire date
        model.addAttribute("employee", employee);
        return "insert-employee"; // Maps to src/main/jte/insert-employee.jte
    }

    // Handles form processing
    @PostMapping("/new")
    public String insertEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/web/employees/new?success=true";
    }
    @GetMapping("/show")
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees"; // Maps to src/main/jte/employees.jte
    }
}
