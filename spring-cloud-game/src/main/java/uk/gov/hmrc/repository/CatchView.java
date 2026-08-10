package uk.gov.hmrc.repository;

import uk.gov.hmrc.entity.CatchSummary;
import uk.gov.hmrc.repository.CatchView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CatchView extends PagingAndSortingRepository<CatchSummary, Integer> {
    //Page<CatchSummary> findAll(Pageable pageable);

	Page<CatchSummary> findByUserUUID(String uuid, Pageable pageable);
}
