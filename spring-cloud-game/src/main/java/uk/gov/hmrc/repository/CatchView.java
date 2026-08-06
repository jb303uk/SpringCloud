package uk.gov.hmrc.repository;

import uk.gov.hmrc.entity.CatchSummary;
import uk.gov.hmrc.repository.CatchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CatchView extends PagingAndSortingRepository<CatchSummary, Integer> {
    // Standard paginated fetch
    Page<CatchSummary> findAll(Pageable pageable);

    // Custom query 1: High earners
    // Page<Catches> findBySalaryGreaterThan(java.math.BigDecimal salary, Pageable pageable);
}
