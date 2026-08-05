package uk.gov.hmrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.gov.hmrc.entity.Catches;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "catches", path = "catches")
public interface CatchRepository extends JpaRepository<Catches, Integer> {
	//List<Catches> findAllByOrderByEmployeeIdDesc();

}

