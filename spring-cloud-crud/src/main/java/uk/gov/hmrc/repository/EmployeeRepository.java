package uk.gov.hmrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmrc.entity.Employee;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findAllByOrderByEmployeeIdDesc();
}