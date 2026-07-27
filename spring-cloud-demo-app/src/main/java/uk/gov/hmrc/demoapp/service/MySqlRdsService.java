package uk.gov.hmrc.demoapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.hmrc.demoapp.model.Tenant;
import uk.gov.hmrc.demoapp.repository.TenantRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MySqlRdsService {

    @Autowired
    private TenantRepository tenantRepository;

    public Tenant saveTenant(String name) {
        Tenant tenant = Tenant.builder().id(getNextId()).name(name).dateAdded(LocalDateTime.now()).build();
        log.info("Saving new Tenant - " + tenant.toString());
        return tenantRepository.save(tenant);
    }

    private long getNextId() {
        List<Tenant> tenants = tenantRepository.findAll();
        long currentId = tenants.stream()
                    .mapToLong(Tenant::getId)
                    .max().orElse(0);

        return currentId+1;
    }

    public Tenant getTenant(Long id) throws SQLException {
        Optional<Tenant> tenant = tenantRepository.findById(id);
        if (tenant.isPresent()) {
            return tenant.get();
        } else {
            throw new SQLException("Tenant not found with ID:" + id);
        }
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAllByOrderByDateAddedDesc();
    }
}
