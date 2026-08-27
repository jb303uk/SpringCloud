package uk.co.jb303.controller;

import uk.co.jb303.domain.Employee;
import uk.co.jb303.repository.EmployeeRepository;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@Controller("/employees")
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Get("/")
    @Operation(summary = "List all employees")
    public List<Employee> list() {
        return repository.findAll();
    }

    @Post("/")
    public Employee save(@Body Employee employee) {
        return repository.save(employee);
    }
}
