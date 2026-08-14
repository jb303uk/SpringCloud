package uk.co.jb303.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.jb303.entity.CatchSummary;
import uk.co.jb303.repository.CatchView;

public interface CatchView extends PagingAndSortingRepository<CatchSummary, Integer> {
    //Page<CatchSummary> findAll(Pageable pageable);

	Page<CatchSummary> findByUserUUID(String uuid, Pageable pageable);
}
