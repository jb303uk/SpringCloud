package uk.gov.hmrc.demoapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import uk.gov.hmrc.demoapp.model.Tenant;

import java.util.List;

@Repository
public interface TenantRepository extends CrudRepository<Tenant, Long> {
    Tenant findByName(String name);

    List<Tenant> findAll();

    List<Tenant> findAllByOrderByDateAddedDesc();

}
