package uk.co.jb303.repository;
import uk.co.jb303.domain.Employee;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
