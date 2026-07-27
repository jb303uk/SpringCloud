package uk.gov.hmrc.service;

import org.springframework.stereotype.Service;
import uk.gov.hmrc.entity.Employee;
import uk.gov.hmrc.exception.ResourceNotFoundException;
import uk.gov.hmrc.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found: " + id));
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(Integer id, Employee employee) {

        Employee existing = findById(id);

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setPhoneNumber(employee.getPhoneNumber());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        Employee employee = findById(id);
        repository.delete(employee);
    }
}