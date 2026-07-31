package uk.gov.hmrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.gov.hmrc.entity.Catches;
import java.util.List;

public interface PokemonRepository extends JpaRepository<Catches, Integer> {
	List<Catches> findAllByOrderByCatchIdDesc();
}