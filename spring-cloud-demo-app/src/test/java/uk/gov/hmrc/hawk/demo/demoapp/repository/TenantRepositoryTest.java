package uk.gov.hmrc.hawk.demo.demoapp.repository;

import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uk.gov.hmrc.demoapp.DemoappApplication;
import uk.gov.hmrc.demoapp.model.Tenant;
import uk.gov.hmrc.demoapp.repository.TenantRepository;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = DemoappApplication.class)
@Ignore
class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;

    @Disabled("Testing Build before Unit Tests in Kubernetes Runner")
    void testFindByName() {
        Tenant tenant = getTenant();
        tenantRepository.save(tenant);
        Tenant result = tenantRepository.findByName("HAWK Test");
        assertEquals(tenant.getId(), result.getId());
    }

    @Disabled("Testing Build before Unit Tests in Kubernetes Runner")
    void testFindAll() {
        Tenant tenant = Tenant.builder().id(1L).name("First Tenant").build();
        tenantRepository.save(tenant);
        tenant = Tenant.builder().id(2L).name("Second Tenant").build();
        tenantRepository.save(tenant);
        Iterable<Tenant> result = tenantRepository.findAll();
        List<Tenant> tenants = new ArrayList<>();
        result.forEach(tenants::add);
        result.forEach(x -> System.out.println(x.getName()));
        assertEquals(2, tenants.size());
    }

    private Tenant getTenant() {
        return new Tenant(1L, "HAWK Test", LocalDateTime.now());
    }
}
