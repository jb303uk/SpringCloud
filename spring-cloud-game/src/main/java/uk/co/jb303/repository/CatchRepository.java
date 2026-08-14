package uk.co.jb303.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uk.co.jb303.entity.Catches;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "catches", path = "catches")
public interface CatchRepository extends JpaRepository<Catches, Integer> {
	//List<Catches> findAllByOrderByEmployeeIdDesc();
}


