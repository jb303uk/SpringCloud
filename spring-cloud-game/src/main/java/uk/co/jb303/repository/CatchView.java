package uk.co.jb303.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Window;
import org.springframework.data.repository.PagingAndSortingRepository;

import uk.co.jb303.entity.CatchSummary;
import uk.co.jb303.repository.CatchView;

public interface CatchView extends PagingAndSortingRepository<CatchSummary, Integer> {

	Window<CatchSummary> findByUserUUID(        
			String userUUID, 
	        ScrollPosition position, 
	        Limit limit, 
	        Sort sort
	        );
}
