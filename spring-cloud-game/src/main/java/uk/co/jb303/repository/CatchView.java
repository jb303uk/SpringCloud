package uk.co.jb303.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.jb303.entity.CatchSummary;
import uk.co.jb303.repository.CatchView;

public interface CatchView extends PagingAndSortingRepository<CatchSummary, Integer> {

	Window<CatchSummary> findByUserUUID(String uuid, Pageable pageable);
}
